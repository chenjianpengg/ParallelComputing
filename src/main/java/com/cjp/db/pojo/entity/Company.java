package com.cjp.db.pojo.entity;

import lombok.*;

import java.io.Serializable;

/**
 * @author chenjianpeng
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Company extends User implements Serializable {
    /**
     * 公司名称
     */
    private String companyName;

    /**
     * 公司简介
     */
    private String description;

    /**
     * 公司官网链接
     */
    private String websiteUrl;

    /**
     * 地址
     */
    private String address;
}
