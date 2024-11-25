package com.cjp.db.pojo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author chenjianpeng
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnnouncementDTO implements Serializable {

    private Long id;

    /**
     * 公告标题
     */
    @NotBlank
    private String title;

    /**
     * 公告内容
     */
    @NotBlank
    private String content;

    /**
     * 发布者管理员ID
     */
    @NotNull
    private Long adminId;
}
