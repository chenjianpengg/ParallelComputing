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
public class Developer extends User implements Serializable {
    /**
     * 技能（如Java, Python等，逗号分隔）
     */
    private String skills;

    /**
     * 工作年限
     */
    private Integer experienceYears;
}
