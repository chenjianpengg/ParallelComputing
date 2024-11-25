package com.cjp.db.pojo.entity;

import com.cjp.db.enums.DemandStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author chenjianpeng
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Demand implements Serializable {

    /**
     * 需求ID
     */
    private Long id;

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

    /**
     * 创建时间
     */
    private LocalDateTime createdTime;

    /**
     * 更新时间
     */
    private LocalDateTime updatedTime;

    /**
     * 删除标志
     */
    private Long deleted;
}