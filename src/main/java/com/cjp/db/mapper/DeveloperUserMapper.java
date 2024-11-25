package com.cjp.db.mapper;

import com.cjp.db.pojo.entity.Developer;
import com.cjp.db.pojo.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author chenjianpeng
 */
@Mapper
public interface DeveloperUserMapper {

    @Select("SELECT * FROM developer WHERE username = #{username}")
    Developer selectByUsename(String username);

    @Insert("INSERT INTO developer(username, password, nickname, sex, avatar, email, phone, " +
            "role, active, create_time, update_time, skills, experience_years) VALUES " +
            "(#{username}, #{password}, #{nickname}, #{sex}, #{avatar}, #{email}, #{phone}, " +
            "#{role}, #{active}, #{createTime}, #{updateTime}, #{skills}, #{experienceYears})")
    void insert(User developer);

    Long count(User developer);

    void update(User developer);

    @Select("SELECT * FROM developer where id = #{id}")
    User selectById(Long id);

    List<User> list(User developer);
}
