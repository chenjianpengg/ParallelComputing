package com.cjp.db.pojo.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author chenjianpeng
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO implements Serializable {

    /**
     * 对应的需求ID
     */
    @NotNull
    private Long demandId;

    /**
     * 接单的开发人员ID
     */
    @NotNull
    private Long developerId;
}
