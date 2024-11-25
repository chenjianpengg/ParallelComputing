package com.cjp.db.pojo.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author chenjianpeng
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyRegisterDTO extends UserRegisterDTO implements Serializable {
    /**
     * 公司名称
     */
    @NotBlank
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
