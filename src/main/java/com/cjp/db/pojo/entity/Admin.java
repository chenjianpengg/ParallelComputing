package com.cjp.db.pojo.entity;

import lombok.*;

import java.io.Serializable;

/**
 * @author chenjianpeng
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Admin extends User implements Serializable {

    /**
     * 管理员级别
     */
    private Integer level;

    /**
     * 管理员备注信息
     */
    private String remark;
}
