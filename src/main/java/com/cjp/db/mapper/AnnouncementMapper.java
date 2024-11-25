package com.cjp.db.mapper;

import com.cjp.db.pojo.entity.Announcement;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author chenjianpeng
 */
@Mapper
public interface AnnouncementMapper {
    @Insert("INSERT INTO announcement(title, content, admin_id, create_time, update_time) VALUES " +
            "(#{title}, #{content}, #{adminId}, #{createTime}, #{updateTime})")
    void insert(Announcement announcement);

    void update(Announcement announcement);

    @Select("SELECT * FROM announcement where id = #{id} and deleted = 0")
    Announcement selectById(Long id);

    @Select("SELECT * FROM announcement where deleted = 0")
    List<Announcement> list();
}
