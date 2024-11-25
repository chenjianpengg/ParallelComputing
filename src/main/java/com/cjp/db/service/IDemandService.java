package com.cjp.db.service;

import com.cjp.db.pojo.dto.DemandDTO;
import com.cjp.db.pojo.dto.DemandPageQueryDTO;
import com.cjp.db.pojo.result.PageResult;
import com.cjp.db.pojo.vo.DemandVO;

import java.util.List;

/**
 * @author chenjianpeng
 */
public interface IDemandService {
    void publish(DemandDTO demandDTO);

    void update(DemandDTO demandDTO);

    void delete(Long id);

    List<DemandVO> listByCompanyId(Long id);

    DemandVO selectById(Long id);

    PageResult<DemandVO> list(DemandPageQueryDTO demandPageQueryDTO);
}
