package com.cjp.db.pojo.vo;

import com.cjp.db.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author chenjianpeng
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentVO implements Serializable {

    /**
     * 评论ID
     */
    private Long id;

    /**
     * 所属需求ID
     */
    private Long demandId;

    /**
     * 评论者ID（开发人员或企业用户）
     */
    private Long userId;

    /**
     * 评论者的用户类型
     */
    private Role userRole;

    /**
     * 被回复的id
     */
    private Long targetId;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 直接父级评论
     */
    private Long parentId;

    /**
     * 顶级评论
     */
    private Long rootId;

    /**
     * 创建时间
     */
    private LocalDateTime createdTime;

    /**
     * 修改时间
     */
    private LocalDateTime updatedTime;

    /**
     * 是否被删除
     */
    private Boolean deleted;

    /**
     * 用户名称
     */
    private String userNickname;

    /**
     * 用户头像
     */
    private String userAvatar;

    /**
     * 被回复的用户名
     */
    private String targetNickname;

    /**
     * 子评论
     */
    private List<CommentVO> children;
}
