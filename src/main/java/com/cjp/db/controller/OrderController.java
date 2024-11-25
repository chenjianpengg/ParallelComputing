package com.cjp.db.controller;

import com.cjp.db.annotation.Authority;
import com.cjp.db.enums.Role;
import com.cjp.db.pojo.dto.OrderDTO;
import com.cjp.db.pojo.vo.OrderVO;
import com.cjp.db.service.IOrderService;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author chenjianpeng
 */
@RestController
@RequestMapping
public class OrderController {

    @Resource
    private IOrderService orderService;

    /**
     * 申请接单
     */
    @PostMapping("/request")
    public void requestOrder(@RequestBody @Validated OrderDTO orderDTO) {
        orderService.insertOrder(orderDTO);
    }

    /**
     * 展示该需求的所有等待中和已接受的订单
     */
    @Authority(Role.COMPANY)
    @GetMapping("list/{demandId}")
    public List<OrderVO> listOrderByDemandId(@PathVariable Long demandId) {
        return orderService.listByDemandId(demandId);
    }

    /**
     * 同意接单
     */
    @Authority(Role.COMPANY)
    @GetMapping("/accept/{id}")
    public void acceptOrder(@PathVariable Long id) {
        orderService.acceptOrder(id);
    }

    /**
     * 拒绝接单
     */
    @Authority(Role.COMPANY)
    @GetMapping("/refuse/{id}")
    public void refuseOrder(@PathVariable Long id) {
        orderService.refuseOrder(id);
    }

    /**
     * 完成订单
     */
    @Authority(Role.COMPANY)
    @GetMapping("/complete/{id}")
    public void completeOrder(@PathVariable Long id) {
        orderService.completeOrder(id);
    }
}
