package com.cjp.db.mapper;

import com.cjp.db.pojo.entity.Comment;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;

/**
 * @author chenjianpeng
 */
@Mapper
public interface CommentMapper {
    @Select("SELECT * FROM comment where demand_id = #{demandId} and deleted = 0 and root_id is NULL")
    List<Comment> selectRootComment(Long demandId);

    @Select("SELECT * FROM comment where root_id = #{rootId} and deleted = 0 ORDER BY created_time")
    List<Comment> getChildrenCommentByRootId(Long rootId);

    @Select("SELECT * FROM comment where id = #{rootId}")
    Optional<Comment> getCommentById(Long rootId);

    @Insert("INSERT INTO comment(demand_id, user_id, content, target_id, parent_id, root_id, created_time, updated_time, user_role) VALUES " +
            "(#{demandId}, #{userId}, #{content}, #{targetId}, #{parentId}, #{rootId}, #{createdTime}, #{updatedTime}, #{userRole})")
    void insert(Comment comment);

    @Update("UPDATE comment SET deleted = TRUE WHERE id = #{id}")
    void delete(Long id);
}
