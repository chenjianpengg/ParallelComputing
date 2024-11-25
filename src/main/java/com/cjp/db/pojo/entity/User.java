package com.cjp.db.pojo.entity;

import com.cjp.db.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author chenjianpeng
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    private Long id;

    private String username;

    private String password;

    private String nickname;

    private String sex;

    private String avatar;

    private String email;

    private String phone;

    private Role role;

    private Boolean active;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
