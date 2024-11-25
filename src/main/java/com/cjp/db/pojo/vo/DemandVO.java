package com.cjp.db.pojo.vo;

import com.cjp.db.enums.DemandStatus;
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
public class DemandVO implements Serializable {

    /**
     * 需求标题
     */
    private String title;

    /**
     * 需求描述
     */
    private String description;

    /**
     * 所属企业ID
     */
    private Long companyId;

    /**
     * 预算
     */
    private BigDecimal budget;

    /**
     * 期限
     */
    private Integer deadline;

    /**
     * 需求状态（枚举：PENDING, IN_PROGRESS, COMPLETED, CANCELED）
     */
    private DemandStatus status;
}
