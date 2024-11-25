package com.cjp.db.pojo.vo;

import com.cjp.db.enums.OrderStatus;
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
public class OrderVO implements Serializable {
    /**
     * 订单ID
     */
    private Long id;

    /**
     * 对应的需求ID
     */
    private Long demandId;

    /**
     * 接单的开发人员ID
     */
    private Long developerId;

    /**
     *  申请时间
     */
    private LocalDateTime applyTime;

    /**
     * 同意时间
     */
    private LocalDateTime acceptTime;

    /**
     * 订单状态（枚举：PENDING, COMPLETED, CANCELED）
     */
    private OrderStatus status;
}
