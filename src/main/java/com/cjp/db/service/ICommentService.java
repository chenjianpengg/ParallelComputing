package com.cjp.db.service;

import com.cjp.db.pojo.dto.CommentDTO;
import com.cjp.db.pojo.vo.CommentVO;

import java.util.List;

/**
 * @author chenjianpeng
 */
public interface ICommentService {
    void addComment(CommentDTO commentDTO);

    List<CommentVO> listRootComment(Long demandId);

    List<CommentVO> getChildrenComment(Long rootId);

    void deleteComment(Long id);
}
