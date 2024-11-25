package com.cjp.db.pojo.dto;

import com.cjp.db.enums.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author chenjianpeng
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO implements Serializable {

    /**
     * 所属需求ID
     */
    @NotNull
    private Long demandId;

    /**
     * 评论作者ID（开发人员或企业用户）
     */
    @NotNull
    private Long userId;

    /**
     * 评论者的用户类型
     */
    @NotNull
    private Role userRole;

    /**
     * 被回复评论的用户ID
     */
    private Long targetId;

    /**
     * 评论内容
     */
    @NotBlank
    private String content;

    /**
     * 直接父级评论id
     */
    private Long parentId;

    /**
     * 顶级评论id
     */
    private Long rootId;
}
