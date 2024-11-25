package com.cjp.db.pojo.vo;

import lombok.*;

import java.io.Serializable;

/**
 * @author chenjianpeng
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminVO extends UserVO implements Serializable {

    /**
     * 管理员级别
     */
    private Integer level;

    /**
     * 管理员备注信息
     */
    private String remark;
}
