package com.cjp.db.controller;

import com.cjp.db.annotation.Authority;
import com.cjp.db.enums.Role;
import com.cjp.db.pojo.dto.CommentDTO;
import com.cjp.db.pojo.vo.CommentVO;
import com.cjp.db.service.ICommentService;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author chenjianpeng
 */
@RestController
@RequestMapping("/comment")
public class CommentController {

    @Resource
    private ICommentService commentService;

    /**
     * 添加评论（包括评论和回复）
     */
    @PostMapping("/add")
    public void addComment(@RequestBody @Validated CommentDTO commentDTO){
        commentService.addComment(commentDTO);
    }

    /**
     * 展示所有根评论
     */
    @GetMapping("/list/{demandId}")
    private List<CommentVO> listRootComment(@PathVariable Long demandId){
        return commentService.listRootComment(demandId);
    }

    /**
     * 展示直接所有子评论
     */
    @GetMapping("/children/{rootId}")
    private List<CommentVO> getChildrenComment(@PathVariable Long rootId){
        return commentService.getChildrenComment(rootId);
    }

    /**
     * 删除评论
     */
    @DeleteMapping("/delete/{id}")
    public void deleteComment(@PathVariable Long id){
        commentService.deleteComment(id);
    }

}
