package com.cjp.db.pojo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author chenjianpeng
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DemandDTO implements Serializable {

    private Long id;
    /**
     * 需求标题
     */
    @NotBlank
    private String title;

    /**
     * 需求描述
     */
    @NotBlank
    private String description;

    /**
     * 所属企业ID
     */
    @NotNull
    private Long companyId;

    /**
     * 预算
     */
    @NotNull
    private BigDecimal budget;

    /**
     * 期限
     */
    private Integer deadline;
}
