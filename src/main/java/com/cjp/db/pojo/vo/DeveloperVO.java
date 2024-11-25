package com.cjp.db.pojo.vo;

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
public class DeveloperVO extends UserVO implements Serializable {
    /**
     * 技能（如Java, Python等，逗号分隔）
     */
    private String skills;

    /**
     * 工作年限
     */
    private Integer experienceYears;
}
