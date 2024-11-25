package com.cjp.db.enums;

import com.cjp.db.pojo.entity.Comment;
import lombok.Getter;


/**
 * @author chenjianpeng
 */
@Getter
public enum StatusCode {
    //用户类错误码
    NotAuthorizationError(50000, "权限错误"),
    UsernameExistError(50001, "用户名已存在"),
    PasswordNotMatchError(50002, "两次密码不匹配"),
    UsernameNotExistError(50003, "用户名不存在"),
    PasswordErrorError(50004, "密码错误"),
    UserNotExistError(50005, "用户不存在"),

    //公告类错误码
    AnnouncementNotExistError(50101, "公告不存在"),

    // 需求类错误码
    DemandNotExistError(50201, "需求不存在"),
    DemandStatusError(50202, "需求状态异常"),

    // 订单类错误码
    OrderNotExistError(50301, "订单不存在"),

    // 评论类错误
    CommentNotExistError(50401, "评论不存在");

    private final int code;

    private final String msg;

    StatusCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
