package com.cjp.db.controller.admin;

import com.cjp.db.annotation.Authority;
import com.cjp.db.enums.Role;
import com.cjp.db.pojo.dto.*;
import com.cjp.db.pojo.result.PageResult;
import com.cjp.db.pojo.vo.CommentVO;
import com.cjp.db.pojo.vo.CompanyVO;
import com.cjp.db.pojo.vo.DeveloperVO;
import com.cjp.db.pojo.vo.UserVO;
import com.cjp.db.service.IUserService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author chenjianpeng
 */
@RestController
@RequestMapping("/admin/user")
@Slf4j
public class AdminUserController {

    @Resource(name = "adminUserServiceImpl")
    private IUserService adminUserService;

    /**
     * 用户注册
     */
    @PostMapping("/register")
    public void register(@RequestBody @Validated AdminRegisterDTO adminRegisterDTO) {
        adminUserService.register(adminRegisterDTO);
    }

    /**
     * 用户登录
     */
    @PostMapping("/login")
    public String login(@RequestBody @Validated UserLoginDTO userLoginDTO) {
        return adminUserService.login(userLoginDTO);
    }

    /**
     * 修改用户信息
     */
    @Authority(Role.ADMIN)
    @PutMapping("/update")
    public void update(@RequestBody @Validated AdminDTO adminDTO){
        adminUserService.update(adminDTO);
    }

    /**
     * 用户登出
     */
    @GetMapping("/logout")
    public void logout(){
        adminUserService.logout();
    }

    /**
     * 分页查看企业用户信息
     */
    @Authority(Role.ADMIN)
    @PostMapping("/list/company")
    public PageResult<CompanyVO> listCompany(@RequestBody UserPageQueryDTO userPageQueryDTO){
        return adminUserService.listCompany(userPageQueryDTO);
    }

    /**
     * 分页查看开发人员用户信息
     */
    @Authority(Role.ADMIN)
    @PostMapping("/list/developer")
    public PageResult<DeveloperVO> listDeveloper(@RequestBody UserPageQueryDTO userPageQueryDTO){
        return adminUserService.listDeveloper(userPageQueryDTO);
    }

    /**
     * 启用禁用用户
     */
    @Authority(Role.ADMIN)
    @PostMapping("/status")
    public void startOrStop(@RequestBody UserDTO userDTO){ //传id, role, status
        adminUserService.startOrStop(userDTO);
    }

    /**
     * 统计用户数量
     */
    @Authority(Role.ADMIN)
    @PostMapping("/count")
    public Long count(@RequestBody UserQueryDTO userqueryDTO){
        return adminUserService.count(userqueryDTO);
    }

    /**
     * 修改密码
     */
    @PutMapping("/update/password")
    public void updatePassword(@RequestBody UserUpdatePasswordDTO userUpdatePasswordDTO){
        adminUserService.updatePassword(userUpdatePasswordDTO);
    }
}
