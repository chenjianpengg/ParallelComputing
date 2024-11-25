package com.cjp.db.pojo.dto;

import com.cjp.db.enums.Role;
import lombok.AllArgsConstructor;
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
public class UserDTO implements Serializable {
    private Long id;

    private String nickname;

    private String sex;

    private String avatar;

    private String email;

    private String phone;

    private Role role;

    private Boolean active;

    private LocalDateTime createTime;
}
