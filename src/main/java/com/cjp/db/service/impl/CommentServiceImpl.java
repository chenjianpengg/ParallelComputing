package com.cjp.db.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.cjp.db.enums.Role;
import com.cjp.db.exceptions.CommentNotExistException;
import com.cjp.db.exceptions.NotAuthorizationException;
import com.cjp.db.exceptions.UserNotExistException;
import com.cjp.db.mapper.CommentMapper;
import com.cjp.db.pojo.dto.CommentDTO;
import com.cjp.db.pojo.entity.Comment;
import com.cjp.db.pojo.entity.User;
import com.cjp.db.pojo.vo.CommentVO;
import com.cjp.db.service.ICommentService;
import com.cjp.db.service.IUserService;
import com.cjp.db.utils.ThreadLocalUtil;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author chenjianpeng
 */
@Service
public class CommentServiceImpl implements ICommentService {

    @Resource
    private CommentMapper commentMapper;

    @Resource(name = "companyUserServiceImpl")
    private IUserService companyService;

    @Resource(name = "developerUserServiceImpl")
    private IUserService developerService;


    @Override
    public void addComment(CommentDTO commentDTO) {
        Comment comment = BeanUtil.toBean(commentDTO, Comment.class);
        comment.setCreatedTime(LocalDateTime.now());
        comment.setUpdatedTime(LocalDateTime.now());
        commentMapper.insert(comment);
    }

    @Override
    public List<CommentVO> listRootComment(Long demandId) {
        List<Comment> commentList = commentMapper.selectRootComment(demandId);
        List<CommentVO> commentVOList = BeanUtil.copyToList(commentList, CommentVO.class);
        for(CommentVO commentVO : commentVOList){
            addUserInfoToComment(commentVO);
        }
        return commentVOList;
    }

    protected void addUserInfoToComment(CommentVO commentVO) {
        if(commentVO.getUserId() == null || commentVO.getUserRole() == null){
            throw new UserNotExistException("用户不存在");
        }

        // 设置评论用户昵称和头像
        User user = getUser(commentVO.getUserId(), commentVO);
        assert user != null;
        commentVO.setUserNickname(user.getNickname());
        commentVO.setUserAvatar(user.getAvatar());

        // 设置被评论用户用户名昵称
        User targetUser = getUser(commentVO.getTargetId(), commentVO);
        assert targetUser != null;
        commentVO.setTargetNickname(targetUser.getNickname());
    }

    protected User getUser(Long id, CommentVO commentVO){
        if(Role.COMPANY.equals(commentVO.getUserRole())){
            return companyService.getUserById(id);
        }
        else if(Role.DEVELOPER.equals(commentVO.getUserRole())){
            return developerService.getUserById(id);
        }
        return null;
    }

    @Override
    public List<CommentVO> getChildrenComment(Long rootId) {
        Comment rootComment = commentMapper.getCommentById(rootId)
                .orElseThrow(() -> new CommentNotExistException("评论不存在"));

        List<Comment> childComments = commentMapper.getChildrenCommentByRootId(rootId);

        Map<Long, List<Comment>> childMap = childComments.stream()
                .filter(comment -> comment.getParentId() != null)
                .collect(Collectors.groupingBy(Comment::getParentId));

        return buildChildCommentVOList(rootComment, childMap);
    }

    @Override
    public void deleteComment(Long id) {
        Comment comment = commentMapper.getCommentById(id)
                .orElseThrow(() -> new CommentNotExistException("评论不存在"));

        // 判断是否有权限删除评论
        Map<String, Object> map = ThreadLocalUtil.get();
        Long userId = Long.parseLong(map.get("id").toString());
        Role role = Role.valueOf(map.get("role").toString());

        if(!userId.equals(comment.getUserId()) && !userId.equals(comment.getTargetId()) && Role.ADMIN.equals(role)){
            throw new NotAuthorizationException("你没有权限删除这条评论");
        }

        commentMapper.delete(id);
    }

    protected List<CommentVO> buildChildCommentVOList(Comment parentComment, Map<Long, List<Comment>> childMap) {
        List<CommentVO> commentVOList = new ArrayList<>();
        List<Comment> childComments = childMap.get(parentComment.getId());
        if(childComments != null){
            for(Comment childComment : childComments){
                CommentVO commentVO = BeanUtil.copyProperties(childComment, CommentVO.class);
                addUserInfoToComment(commentVO);
                commentVO.setChildren(buildChildCommentVOList(childComment, childMap));
                commentVOList.add(commentVO);
            }
        }
        return commentVOList;
    }
}
