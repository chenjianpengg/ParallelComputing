package com.cjp.db.controller;

import com.cjp.db.annotation.Authority;
import com.cjp.db.enums.Role;
import com.cjp.db.pojo.dto.DemandDTO;
import com.cjp.db.pojo.dto.DemandPageQueryDTO;
import com.cjp.db.pojo.result.PageResult;
import com.cjp.db.pojo.vo.DemandVO;
import com.cjp.db.service.IDemandService;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author chenjianpeng
 */
@RestController
@RequestMapping("/demand")
public class DemandController {

    @Resource
    private IDemandService demandService;

    /**
     * 根据id展示demand
     */
    @GetMapping("/details/{id}")
    public DemandVO selectById(@PathVariable Long id) {
        return demandService.selectById(id);
    }

    /**
     * 分页查询所有需求
     */
    @GetMapping("/list")
    public PageResult<DemandVO> list(@RequestBody DemandPageQueryDTO demandPageQueryDTO) {
        return demandService.list(demandPageQueryDTO);
    }

    /**
     * 发布需求
     */
    @Authority(Role.COMPANY)
    @PostMapping("/publish")
    public void publishDemand(@RequestBody @Validated DemandDTO demandDTO) {
        demandService.publish(demandDTO);
    }

    /**
     * 修改需求
     */
    @Authority(Role.COMPANY)
    @PutMapping("/update")
    public void update(@RequestBody @Validated DemandDTO demandDTO){
        demandService.update(demandDTO);
    }

    /**
     * 删除需求
     */
    @Authority(Role.COMPANY)
    @PutMapping("/delete/{id}")
    public void delete(@PathVariable Long id){
        demandService.delete(id);
    }

    /**
     * 查询自己已发布的需求
     */
    @GetMapping("/list/{id}")
    public List<DemandVO> listByCompanyId(@PathVariable Long id){
        return demandService.listByCompanyId(id);
    }
}
