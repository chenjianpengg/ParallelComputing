package com.cjp.db.pojo.vo;

import com.cjp.db.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author chenjianpeng
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVO implements Serializable {
    private Long id;

    private String username;

    private String nickname;

    private String sex;

    private String avatar;

    private String email;

    private String phone;

    private Role role;
}
