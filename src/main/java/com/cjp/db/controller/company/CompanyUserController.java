package com.cjp.db.controller.company;

import com.cjp.db.pojo.dto.*;
import com.cjp.db.service.IUserService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author chenjianpeng
 */
@RestController
@RequestMapping("/company/user")
@Slf4j
public class CompanyUserController {

    @Resource(name = "companyUserServiceImpl")
    private IUserService companyUserService;

    /**
     * 用户注册
     */
    @PostMapping("/register")
    public void register(@RequestBody @Validated CompanyRegisterDTO companyRegisterDTO) {
        companyUserService.register(companyRegisterDTO);
    }

    /**
     * 用户登录
     */
    @PostMapping("/login")
    public String login(@RequestBody @Validated UserLoginDTO userLoginDTO) {
        return companyUserService.login(userLoginDTO);
    }

    /**
     * 用户登出
     */
    @GetMapping("/logout")
    public void logout(){
        companyUserService.logout();
    }

    /**
     * 更新信息
     */
    @PutMapping("/update")
    public void update(@RequestBody @Validated CompanyDTO companyDTO){
        companyUserService.update(companyDTO);
    }

    /**
     * 修改密码
     */
    @PutMapping("/update/password")
    public void updatePassword(@RequestBody @Validated UserUpdatePasswordDTO userUpdatePasswordDTO){
        companyUserService.updatePassword(userUpdatePasswordDTO);
    }
}
