package com.cjp.db.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.cjp.db.constant.KeyConstant;
import com.cjp.db.enums.Role;
import com.cjp.db.mapper.AdminUserMapper;
import com.cjp.db.mapper.CompanyUserMapper;
import com.cjp.db.mapper.DeveloperUserMapper;
import com.cjp.db.pojo.dto.*;
import com.cjp.db.pojo.entity.Admin;
import com.cjp.db.pojo.entity.Company;
import com.cjp.db.pojo.entity.Developer;
import com.cjp.db.pojo.entity.User;
import com.cjp.db.pojo.result.PageResult;
import com.cjp.db.pojo.vo.CompanyVO;
import com.cjp.db.pojo.vo.DeveloperVO;
import com.cjp.db.service.IUserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Function;

/**
 * @author chenjianpeng
 */
@Service
public class AdminUserServiceImpl implements IUserService {

    @Resource
    private AdminUserMapper adminUserMapper;

    @Resource
    private DeveloperUserMapper developerUserMapper;

    @Resource
    private CompanyUserMapper companyUserMapper;

    @Resource
    private UserServiceImpl userService;

    @Override
    public void register(UserRegisterDTO adminRegisterDTO) {
        userService.register(adminRegisterDTO,
                adminUserMapper::selectByUsename,
                adminUserMapper::insert,
                Admin::new,
                Role.ADMIN);
    }

    @Override
    public String login(UserLoginDTO userLoginDTO) {
        return userService.login(userLoginDTO, adminUserMapper::selectByUsename, KeyConstant.ADMIN_LOGIN);
    }

    @Override
    public User getUserById(Long id) {
        return adminUserMapper.selectById(id);
    }

    @Override
    public void logout() {
        userService.logout(KeyConstant.ADMIN_LOGIN);
    }

    @Override
    public PageResult<CompanyVO> listCompany(UserPageQueryDTO userPageQueryDTO) {
        return listUserByRole(userPageQueryDTO, companyUserMapper::list, CompanyVO.class);
    }

    @Override
    public PageResult<DeveloperVO> listDeveloper(UserPageQueryDTO userPageQueryDTO) {
        return listUserByRole(userPageQueryDTO, developerUserMapper::list, DeveloperVO.class);
    }

    private <T>PageResult<T> listUserByRole(UserPageQueryDTO userPageQueryDTO,
                                        Function<User,List<User>> listUser,
                                              Class<T> voClass){
        PageHelper.startPage(userPageQueryDTO.getPageNum(), userPageQueryDTO.getPageSize());
        User user = new User();
        user.setActive(userPageQueryDTO.getActive());
        user.setCreateTime(userPageQueryDTO.getCreateTime());

        List<User> users = listUser.apply(user);
        // 封装分页结果
        PageInfo<User> pageInfo = new PageInfo<>(users);
        List<T> userVOList = BeanUtil.copyToList(pageInfo.getList(), voClass);
        return new PageResult<>(pageInfo.getTotal(), userVOList);
    }

    @Override
    public void update(UserDTO adminDTO) {
        userService.update(adminDTO, Admin::new, adminUserMapper::update);
    }

    @Override
    public void updatePassword(UserUpdatePasswordDTO userUpdatePasswordDTO) {
        userService.updatePassword(userUpdatePasswordDTO,
                adminUserMapper::selectByUsename,
                Admin::new,
                adminUserMapper::update,
                KeyConstant.ADMIN_LOGIN);
    }

    @Override
    public Long count(UserQueryDTO userqueryDTO) {
        Developer developer = BeanUtil.copyProperties(userqueryDTO, Developer.class);
        Company company = BeanUtil.copyProperties(userqueryDTO, Company.class);
        if(Role.COMPANY.equals(userqueryDTO.getRole())){
            return countCompanyNum(company);
        }
        else if(Role.DEVELOPER.equals(userqueryDTO.getRole())){
            return countDeveloperNum(developer);
        }
        return 0L;
    }

    private Long countDeveloperNum(Developer developer){
        return developerUserMapper.count(developer);
    }

    private Long countCompanyNum(Company company){
        return companyUserMapper.count(company);
    }

    @Override
    public void startOrStop(UserDTO userDTO) {
        if (userDTO.getRole() == Role.DEVELOPER){
            Developer developer = new Developer();
            developer.setId(userDTO.getId());
            developer.setActive(userDTO.getActive());
            developer.setUpdateTime(LocalDateTime.now());
            developerUserMapper.update(developer);
        }
        else if(userDTO.getRole() == Role.COMPANY){
            Company company = new Company();
            company.setId(userDTO.getId());
            company.setActive(userDTO.getActive());
            company.setUpdateTime(LocalDateTime.now());
            companyUserMapper.update(company);
        }
    }
}
