package com.cjp.db.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.cjp.db.controller.OrderController;
import com.cjp.db.enums.DemandStatus;
import com.cjp.db.enums.OrderStatus;
import com.cjp.db.exceptions.DemandStatusException;
import com.cjp.db.exceptions.OrderNotExistException;
import com.cjp.db.mapper.DemandMapper;
import com.cjp.db.mapper.OrderMapper;
import com.cjp.db.pojo.dto.OrderDTO;
import com.cjp.db.pojo.entity.Demand;
import com.cjp.db.pojo.entity.Order;
import com.cjp.db.pojo.vo.OrderVO;
import com.cjp.db.service.IOrderService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author chenjianpeng
 */
@Service
public class OrderServiceImpl implements IOrderService {

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private DemandMapper demandMapper;

    @Override
    public void insertOrder(OrderDTO orderDTO) {
        Demand demand = demandMapper.selectById(orderDTO.getDemandId());
        if(demand == null || demand.getStatus() != DemandStatus.PENDING){
            throw new DemandStatusException("需求状态异常");
        }
        Order order = BeanUtil.copyProperties(orderDTO, Order.class);
        order.setApplyTime(LocalDateTime.now());
        order.setStatus(OrderStatus.PENDING);
        // 向order表中写入数据
        orderMapper.insert(order);

        // TODO 提醒公司处理订单
    }

    @Override
    public void acceptOrder(Long id) {
        // 修改order表的id
        Long demandId = updateOrderStatus(id, OrderStatus.ACCEPTED);

        // 修改demand表的id
        updateDemandStatus(demandId, DemandStatus.IN_PROGRESS);
    }

    @Override
    public void refuseOrder(Long id) {
        updateOrderStatus(id, OrderStatus.REJECTED);
    }

    @Override
    public void completeOrder(Long id) {
        // 修改order表的id
        Long demandId = updateOrderStatus(id, OrderStatus.COMPLETED);

        // 修改demand表的id
        updateDemandStatus(demandId, DemandStatus.COMPLETED);
    }

    @Override
    public List<OrderVO> listByDemandId(Long demandId) {
        List<Order> orderList = orderMapper.listByDemandId(demandId);
        return BeanUtil.copyToList(orderList, OrderVO.class);
    }

    private Long updateOrderStatus(Long id, OrderStatus status){
        Order order = orderMapper.selectById(id);
        if(order == null){
            throw new OrderNotExistException("订单不存在");
        }

        Order orderBuild = Order.builder()
                .id(id)
                .status(status)
                .acceptTime(LocalDateTime.now())
                .build();
        orderMapper.updateStatus(orderBuild);

        return order.getDemandId();
    }

    private void updateDemandStatus(Long demandId, DemandStatus status){
        Demand demand = Demand.builder()
                .id(demandId)
                .status(status)
                .updatedTime(LocalDateTime.now())
                .build();
        demandMapper.update(demand);
    }
}
