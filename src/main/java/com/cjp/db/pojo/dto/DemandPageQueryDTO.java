package com.cjp.db.pojo.dto;

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
public class DemandPageQueryDTO implements Serializable {

    @NotNull
    private Integer pageNum;

    @NotNull
    private Integer pageSize;

    /**
     * 预算
     */
    private BigDecimal budget;

    /**
     * 期限
     */
    private Integer deadline;

}
