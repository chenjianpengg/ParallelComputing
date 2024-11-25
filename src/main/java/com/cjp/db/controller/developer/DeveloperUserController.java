package com.cjp.db.controller.developer;

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
@RequestMapping("/developer/user")
@Slf4j
public class DeveloperUserController {

    @Resource(name = "developerUserServiceImpl")
    private IUserService developerUserService;

    /**
     * 用户注册
     */
    @PostMapping("/register")
    public void register(@RequestBody @Validated DeveloperRegisterDTO developerRegisterDTO) {
        developerUserService.register(developerRegisterDTO);
    }

    /**
     * 用户登录
     */
    @PostMapping("/login")
    public String login(@RequestBody @Validated UserLoginDTO userLoginDTO) {
        return developerUserService.login(userLoginDTO);
    }

    /**
     * 用户登出
     */
    @GetMapping("/logout")
    public void logout(){
        developerUserService.logout();
    }

    @PutMapping("/update")
    public void update(DeveloperDTO developerDTO){
        developerUserService.update(developerDTO);
    }

    /**
     * 修改密码
     */
    @PutMapping("/update/password")
    public void updatePassword(@RequestBody @Validated UserUpdatePasswordDTO userUpdatePasswordDTO){
        developerUserService.updatePassword(userUpdatePasswordDTO);
    }
}
