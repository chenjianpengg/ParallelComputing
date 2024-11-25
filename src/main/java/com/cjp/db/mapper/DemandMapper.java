package com.cjp.db.mapper;

import com.cjp.db.pojo.entity.Demand;
import com.cjp.db.pojo.vo.DemandVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author chenjianpeng
 */
@Mapper
public interface DemandMapper {

    @Insert("INSERT INTO demand(title, description, budget, deadline, status, company_id, created_time, updated_time) VALUES " +
            "(#{title}, #{description}, #{budget}, #{deadline}, #{status}, #{companyId}, #{createdTime}, #{updatedTime})")
    void insert(Demand demand);

    void update(Demand demand);

    @Select("SELECT * FROM demand where id = #{id}")
    Demand selectById(Long id);

    @Select("SELECT * FROM demand where company_id = #{companyId}")
    List<Demand> listByCompanyId(Long companyId);

    List<Demand> list(Demand demand);
}
