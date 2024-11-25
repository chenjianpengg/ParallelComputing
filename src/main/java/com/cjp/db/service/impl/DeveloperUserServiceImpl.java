package com.cjp.db.service.impl;

import com.cjp.db.constant.KeyConstant;
import com.cjp.db.enums.Role;
import com.cjp.db.mapper.DeveloperUserMapper;
import com.cjp.db.pojo.dto.*;
import com.cjp.db.pojo.entity.Developer;
import com.cjp.db.pojo.entity.User;
import com.cjp.db.pojo.result.PageResult;
import com.cjp.db.pojo.vo.CompanyVO;
import com.cjp.db.pojo.vo.DeveloperVO;
import com.cjp.db.service.IUserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * @author chenjianpeng
 */
@Service
public class DeveloperUserServiceImpl implements IUserService {

    @Resource
    private DeveloperUserMapper developerUserMapper;

    @Resource
    private UserServiceImpl userService;

    @Override
    public void register(UserRegisterDTO developerRegisterDTO) {
        userService.register(developerRegisterDTO,
                developerUserMapper::selectByUsename,
                developerUserMapper::insert,
                Developer::new,
                Role.DEVELOPER);
    }

    @Override
    public String login(UserLoginDTO userLoginDTO) {
        return userService.login(userLoginDTO, developerUserMapper::selectByUsename, KeyConstant.DEVELOPER_LOGIN);
    }

    @Override
    public User getUserById(Long id) {
        return developerUserMapper.selectById(id);
    }

    @Override
    public void update(UserDTO developerDTO) {
        userService.update(developerDTO, Developer::new, developerUserMapper::update);
    }

    @Override
    public void updatePassword(UserUpdatePasswordDTO userUpdatePasswordDTO) {
        userService.updatePassword(userUpdatePasswordDTO,
                developerUserMapper::selectByUsename,
                Developer::new,
                developerUserMapper::update,
                KeyConstant.DEVELOPER_LOGIN);
    }

    @Override
    public void logout() {
        userService.logout(KeyConstant.DEVELOPER_LOGIN);
    }

    @Override
    public PageResult<CompanyVO> listCompany(UserPageQueryDTO userPageQueryDTO) {
        return null;
    }

    @Override
    public PageResult<DeveloperVO> listDeveloper(UserPageQueryDTO userPageQueryDTO) {
        return null;
    }

    @Override
    public Long count(UserQueryDTO userqueryDTO) {
        return null;
    }

    @Override
    public void startOrStop(UserDTO userDTO) {

    }
}
