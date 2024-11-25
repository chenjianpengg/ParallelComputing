package com.cjp.db.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author chenjianpeng
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminRegisterDTO extends UserRegisterDTO implements Serializable {
    /**
     * 管理员备注信息
     */
    private String remark;
}