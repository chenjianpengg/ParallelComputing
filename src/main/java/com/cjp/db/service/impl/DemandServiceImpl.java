package com.cjp.db.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.cjp.db.enums.DemandStatus;
import com.cjp.db.exceptions.DemandNotExistException;
import com.cjp.db.mapper.DemandMapper;
import com.cjp.db.pojo.dto.DemandDTO;
import com.cjp.db.pojo.dto.DemandPageQueryDTO;
import com.cjp.db.pojo.entity.Demand;
import com.cjp.db.pojo.result.PageResult;
import com.cjp.db.pojo.vo.DemandVO;
import com.cjp.db.service.IDemandService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author chenjianpeng
 */
@Service
public class DemandServiceImpl implements IDemandService {

    @Resource
    private DemandMapper demandMapper;

    @Override
    public void publish(DemandDTO demandDTO) {
        Demand demand = BeanUtil.copyProperties(demandDTO, Demand.class);
        demand.setStatus(DemandStatus.PENDING);
        demand.setCreatedTime(LocalDateTime.now());
        demand.setUpdatedTime(LocalDateTime.now());
        demandMapper.insert(demand);
    }

    @Override
    public void update(DemandDTO demandDTO) {
        Long id = demandDTO.getId();
        if(id == null){
            throw new DemandNotExistException("需求不存在");
        }
        Demand demand = BeanUtil.copyProperties(demandDTO, Demand.class);
        demand.setUpdatedTime(LocalDateTime.now());
        demandMapper.update(demand);
    }

    @Override
    public void delete(Long id) {
        Demand demand = demandMapper.selectById(id);
        if(demand == null){
            throw new DemandNotExistException("需求不存在");
        }
        Demand demandBuild = Demand.builder().id(id).deleted(id).build();
        demandMapper.update(demandBuild);
    }

    @Override
    public List<DemandVO> listByCompanyId(Long id) {
        List<Demand> demandList = demandMapper.listByCompanyId(id);
        return BeanUtil.copyToList(demandList, DemandVO.class);
    }

    @Override
    public DemandVO selectById(Long id) {
        Demand demand = demandMapper.selectById(id);
        return BeanUtil.copyProperties(demand, DemandVO.class);
    }

    @Override
    public PageResult<DemandVO> list(DemandPageQueryDTO demandPageQueryDTO) {
        Demand demand = BeanUtil.copyProperties(demandPageQueryDTO, Demand.class);
        PageHelper.startPage(demandPageQueryDTO.getPageNum(), demandPageQueryDTO.getPageSize());
        List<Demand> demandList = demandMapper.list(demand);
        PageInfo<Demand> pageInfo = new PageInfo<>(demandList);
        List<DemandVO> demandVOList = BeanUtil.copyToList(pageInfo.getList(), DemandVO.class);
        return new PageResult<>(pageInfo.getTotal(), demandVOList);
    }
}
