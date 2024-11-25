package com.cjp.db.mapper;


import com.cjp.db.pojo.entity.Company;
import com.cjp.db.pojo.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author chenjianpeng
 */
@Mapper
public interface CompanyUserMapper {

    @Select("SELECT * FROM company WHERE username = #{username}")
    Company selectByUsename(String username);

    @Insert("INSERT INTO company(username, password, nickname, sex, avatar, email, phone, role, " +
            "active, create_time, update_time, company_name, description, website_url, address) VALUES " +
            "(#{username}, #{password}, #{nickname}, #{sex}, #{avatar}, #{email}, #{phone}, #{role}, " +
            "#{active}, #{createTime}, #{updateTime}, #{companyName}, #{description}, #{websiteUrl}, #{address})")
    void insert(User company);

    Long count(Company company);

    void update(User company);

    @Select("SELECT * FROM company where id = #{id}")
    User selectById(Long id);

    List<User> list(User user);
}
