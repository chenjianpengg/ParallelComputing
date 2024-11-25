package com.cjp.db.handler;

import com.cjp.db.enums.StatusCode;
import com.cjp.db.exceptions.*;
import com.cjp.db.pojo.result.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author chenjianpeng
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(UsernameExistException.class)
    public ResponseResult<Void> handleUsernameExistException(final UsernameExistException e) {
        log.error("该用户名已存在：{}", e.getMessage());
        return ResponseResult.error(StatusCode.UsernameExistError.getCode(), StatusCode.UsernameExistError.getMsg());
    }

    @ExceptionHandler(PasswordNotMatchException.class)
    public ResponseResult<Void> handlePasswordNotMatchException(final PasswordNotMatchException e) {
        log.error("两次密码不匹配：{}", e.getMessage());
        return ResponseResult.error(StatusCode.PasswordNotMatchError.getCode(), StatusCode.PasswordNotMatchError.getMsg());
    }

    @ExceptionHandler(UsernameNotExistException.class)
    public ResponseResult<Void> handleUsernameNotExistException(final UsernameNotExistException e) {
        log.error("用户名不存在：{}", e.getMessage());
        return ResponseResult.error(StatusCode.UsernameNotExistError.getCode(), StatusCode.UsernameNotExistError.getMsg());
    }

    @ExceptionHandler(PasswordErrorException.class)
    public ResponseResult<Void> handlePasswordErrorException(final PasswordErrorException e) {
        log.error("密码错误：{}", e.getMessage());
        return ResponseResult.error(StatusCode.PasswordErrorError.getCode(), StatusCode.PasswordErrorError.getMsg());
    }

    @ExceptionHandler(NotAuthorizationException.class)
    public ResponseResult<Void> handleNotAuthorizationException(final NotAuthorizationException e) {
        log.error("没有权限：{}", e.getMessage());
        return ResponseResult.error(StatusCode.NotAuthorizationError.getCode(), StatusCode.NotAuthorizationError.getMsg());
    }

    @ExceptionHandler(AnnouncementNotExistException.class)
    public ResponseResult<Void> handleAnnouncementNotExistException(final AnnouncementNotExistException e) {
        log.error("公告不存在：{}", e.getMessage());
        return ResponseResult.error(StatusCode.AnnouncementNotExistError.getCode(), StatusCode.AnnouncementNotExistError.getMsg());
    }

    @ExceptionHandler(DemandNotExistException.class)
    public ResponseResult<Void> handleDemandNotExistException(final DemandNotExistException e) {
        log.error("改需求不存在： {}", e.getMessage());
        return ResponseResult.error(StatusCode.DemandNotExistError.getCode(), StatusCode.DemandNotExistError.getMsg());

    }

    @ExceptionHandler(DemandStatusException.class)
    public ResponseResult<Void> handleDemandStatusException(final DemandStatusException e) {
        log.error("需求状态异常： {}", e.getMessage());
        return ResponseResult.error(StatusCode.DemandStatusError.getCode(), StatusCode.DemandStatusError.getMsg());
    }

    @ExceptionHandler(OrderNotExistException.class)
    public ResponseResult<Void> handleOrderNotExistException(final OrderNotExistException e) {
        log.error("订单不存在： {}", e.getMessage());
        return ResponseResult.error(StatusCode.OrderNotExistError.getCode(), StatusCode.OrderNotExistError.getMsg());
    }

    @ExceptionHandler(UserNotExistException.class)
    public ResponseResult<Void> handleUserNotExistException(final UserNotExistException e) {
        log.error("用户不存在： {}", e.getMessage());
        return ResponseResult.error(StatusCode.UserNotExistError.getCode(), StatusCode.UserNotExistError.getMsg());
    }

    @ExceptionHandler(CommentNotExistException.class)
    public ResponseResult<Void> handleCommentNotExistException(final CommentNotExistException e) {
        log.error("评论不存在： {}", e.getMessage());
        return ResponseResult.error(StatusCode.CommentNotExistError.getCode(), StatusCode.CommentNotExistError.getMsg());
    }

//    @ExceptionHandler(Exception.class)
//    public ResponseResult<Void> handleException(Exception e) {
//        log.error("未知错误：{} \n {}", e.getMessage(), e.getClass().getSimpleName());
//        return ResponseResult.error(ERROR_CODE, e.getMessage());
//    }
}
