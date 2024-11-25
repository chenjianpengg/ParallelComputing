package com.cjp.db.mapper;

import com.cjp.db.pojo.entity.Order;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author chenjianpeng
 */
@Mapper
public interface OrderMapper {

    @Insert("INSERT INTO `order`(demand_id, developer_id, status, apply_time, accept_time) VALUES " +
            "(#{deamndId}, #{developerId}, #{status}, #{createTime}, #{acceptTime})")
    void insert(Order order);

    @Select("SELECT * FROM `order` where id = #{id}")
    Order selectById(Long id);

    @Update("UPDATE `order` SET status = #{status}, accept_time = #{acceptTime} where id = #{id}")
    void updateStatus(Order orderBuild);

    @Select("SELECT * FROM `order` WHERE demand_id = #{demand_id} and status in ('PENDING', 'IN_PROGRESS')")
    List<Order> listByDemandId(Long demandId);
}
