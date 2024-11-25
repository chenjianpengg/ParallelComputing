package com.cjp.db.service.impl;

import com.cjp.db.constant.KeyConstant;
import com.cjp.db.enums.Role;
import com.cjp.db.mapper.CompanyUserMapper;
import com.cjp.db.pojo.dto.*;
import com.cjp.db.pojo.entity.Company;
import com.cjp.db.pojo.entity.User;
import com.cjp.db.pojo.result.PageResult;
import com.cjp.db.pojo.vo.CompanyVO;
import com.cjp.db.pojo.vo.DeveloperVO;
import com.cjp.db.service.IUserService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


/**
 * @author chenjianpeng
 */
@Service
@Slf4j
public class CompanyUserServiceImpl implements IUserService {

    @Resource
    private CompanyUserMapper companyUserMapper;

    @Resource
    private UserServiceImpl userService;

    @Override
    public void register(UserRegisterDTO companyRegisterDTO) {
        userService.register(companyRegisterDTO,
                companyUserMapper::selectByUsename,
                companyUserMapper::insert,
                Company::new,
                Role.COMPANY);
    }

    @Override
    public String login(UserLoginDTO userLoginDTO) {
        return userService.login(userLoginDTO, companyUserMapper::selectByUsename, KeyConstant.COMPANY_LOGIN);
    }

    @Override
    public User getUserById(Long id) {
        return companyUserMapper.selectById(id);
    }

    @Override
    public void update(UserDTO companyDTO) {
        log.info("starting update :{}", companyDTO);
        userService.update(companyDTO, Company::new, companyUserMapper::update);
    }

    @Override
    public void updatePassword(UserUpdatePasswordDTO userUpdatePasswordDTO) {
        userService.updatePassword(userUpdatePasswordDTO,
                companyUserMapper::selectByUsename,
                Company::new,
                companyUserMapper::update,
                KeyConstant.COMPANY_LOGIN);
    }

    @Override
    public void logout() {
        userService.logout(KeyConstant.COMPANY_LOGIN);
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
