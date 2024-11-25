package com.cjp.db.service;

import com.cjp.db.pojo.dto.*;
import com.cjp.db.pojo.entity.User;
import com.cjp.db.pojo.result.PageResult;
import com.cjp.db.pojo.vo.CompanyVO;
import com.cjp.db.pojo.vo.DeveloperVO;

/**
 * @author chenjianpeng
 */
public interface IUserService {

    void register(UserRegisterDTO userRegisterDTO);

    String login(UserLoginDTO userLoginDTO);

    User getUserById(Long id);

    void update(UserDTO userDTO);

    void updatePassword(UserUpdatePasswordDTO userUpdatePasswordDTO);

    Long count(UserQueryDTO userQueryDTO);

    void startOrStop(UserDTO userDTO);

    void logout();

    PageResult<CompanyVO> listCompany(UserPageQueryDTO userPageQueryDTO);

    PageResult<DeveloperVO> listDeveloper(UserPageQueryDTO userPageQueryDTO);
}
