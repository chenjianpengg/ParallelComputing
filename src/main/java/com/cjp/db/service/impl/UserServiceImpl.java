package com.cjp.db.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.cjp.db.enums.Role;
import com.cjp.db.exceptions.PasswordErrorException;
import com.cjp.db.exceptions.PasswordNotMatchException;
import com.cjp.db.exceptions.UsernameExistException;
import com.cjp.db.exceptions.UsernameNotExistException;
import com.cjp.db.pojo.dto.UserDTO;
import com.cjp.db.pojo.dto.UserLoginDTO;
import com.cjp.db.pojo.dto.UserRegisterDTO;
import com.cjp.db.pojo.dto.UserUpdatePasswordDTO;
import com.cjp.db.pojo.entity.User;
import com.cjp.db.utils.JwtUtil;
import com.cjp.db.utils.Md5Util;
import com.cjp.db.utils.ThreadLocalUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author chenjianpeng
 */
@Service
@Slf4j
public class UserServiceImpl {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    private static final int EXPIRE_TIME = 1000*60*60*100;

    protected void register(
            UserRegisterDTO registerDTO,
            Function<String, User> findUserByUsername, // 根据username查询user
            Consumer<User> saveUser, // 将username保存到数据库
            Supplier<User> createUserInstance,
            Role role
    ) {
        String username = registerDTO.getUsername();
        String password = registerDTO.getPassword();
        String rePassword = registerDTO.getRePassword();

        User user = findUserByUsername.apply(username);
        if (user != null) {
            throw new UsernameExistException("用户名已存在");
        }

        if (!password.equals(rePassword)) {
            throw new PasswordNotMatchException("两次密码不匹配");
        }

        user = createUserInstance.get();
        BeanUtil.copyProperties(registerDTO, user);
        user.setPassword(Md5Util.generateMd5(password));
        user.setRole(role);
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());

        saveUser.accept(user);
    }

    public String login(
            UserLoginDTO userLoginDTO,
            Function<String, User> selectByUsername,
            String redisKey) {
        String username = userLoginDTO.getUsername();
        String password = userLoginDTO.getPassword();
        User user = selectByUsername.apply(username);

        if (user == null){
            throw new UsernameNotExistException("用户名不存在");
        }

        if (!Md5Util.verify(password, user.getPassword())){
            throw new PasswordErrorException("密码错误");
        }

        String key = redisKey + user.getUsername() + "_" + user.getId();
        Map<String, Object> map = new HashMap<>();
        map.put("id", user.getId());
        map.put("username", user.getUsername());
        map.put("role", user.getRole().name());
        String token = JwtUtil.genToken(map, EXPIRE_TIME);
        stringRedisTemplate.opsForValue().set(key , token, EXPIRE_TIME);

        return token;
    }

    public void updatePassword(
            UserUpdatePasswordDTO userUpdatePasswordDTO,
            Function<String, User> selectByUsername,
            Supplier<User> createUser,
            Consumer<User> updateUser,
            String redisKey) {

        String oldPassword = userUpdatePasswordDTO.getOldPassword();
        String newPassword = userUpdatePasswordDTO.getNewPassword();
        String rePassword = userUpdatePasswordDTO.getRePassword();

        Map<String, Object> map = ThreadLocalUtil.get();
        long id = Long.parseLong(map.get("id").toString());
        String username = (String) map.get("username");
        User user = selectByUsername.apply(username);

        if(user == null){
            throw new UsernameNotExistException("用户名不存在");
        }

        if (!Md5Util.verify(oldPassword, user.getPassword())){
            throw new PasswordErrorException("密码错误");
        }

        if(!newPassword.equals(rePassword)){
            throw new PasswordNotMatchException("两次密码不匹配");
        }

        User userEntity = createUser.get();

        userEntity.setId(id);
        userEntity.setPassword(Md5Util.generateMd5(newPassword));
        userEntity.setUpdateTime(LocalDateTime.now());

        log.info("user :{}, {}", userEntity.getId(), userEntity.getPassword());

        updateUser.accept(userEntity);
        String key = redisKey + username + "_" + id;
        stringRedisTemplate.delete(key);
    }

    public void logout(String redisKey) {
        Map<String, Object> map = ThreadLocalUtil.get();
        String id = map.get("id").toString();
        String username = map.get("username").toString();
        String key = redisKey + username + "_" + id;
        stringRedisTemplate.delete(key);
    }

    public void update(UserDTO userDTO,
                       Supplier<User> createUser,
                       Consumer<User> updateUser) {
        Map<String, Object> map = ThreadLocalUtil.get();
        long id = Long.parseLong(map.get("id").toString());
        User user = createUser.get();
        BeanUtil.copyProperties(userDTO, user);
        user.setId(id);
        user.setUpdateTime(LocalDateTime.now());
        log.info("user: {}", user);
        updateUser.accept(user);
    }
}
