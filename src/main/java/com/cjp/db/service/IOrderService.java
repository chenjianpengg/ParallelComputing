package com.cjp.db.service;

import com.cjp.db.pojo.dto.OrderDTO;
import com.cjp.db.pojo.vo.OrderVO;

import java.util.List;

/**
 * @author chenjianpeng
 */
public interface IOrderService {
    void insertOrder(OrderDTO orderDTO);

    void acceptOrder(Long id);

    void refuseOrder(Long id);

    void completeOrder(Long id);

    List<OrderVO> listByDemandId(Long demandId);
}
