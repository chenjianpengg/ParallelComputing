package com.cjp.db.pojo.dto;

import com.cjp.db.enums.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class UserPageQueryDTO implements Serializable {

    @NotNull
    private Integer pageNum;

    @NotNull
    private Integer pageSize;

    private Boolean active;

    private LocalDateTime createTime;
}
