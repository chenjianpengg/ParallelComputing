package com.cjp.db.mapper;

import com.cjp.db.pojo.entity.Admin;
import com.cjp.db.pojo.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author chenjianpeng
 */
@Mapper
public interface AdminUserMapper {

    @Select("SELECT * FROM admin WHERE username = #{username}")
    Admin selectByUsename(String username);

    @Insert("INSERT INTO admin(username, password, nickname, sex, avatar, email, phone, " +
            "role, active, create_time, update_time, level, remark) VALUES " +
            "(#{username}, #{password}, #{nickname}, #{sex}, #{avatar}, #{email}, #{phone}, " +
            "#{role}, #{active}, #{createTime}, #{updateTime}, #{level}, #{remark})")
    void insert(User admin);

    void update(User admin);

    @Select("SELECT * FROM admin WHERE id = #{id}")
    User selectById(Long id);
}
