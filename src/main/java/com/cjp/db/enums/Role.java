package com.cjp.db.enums;

/**
 * @author chenjianpeng
 */
public enum Role {
    // 管理员
    ADMIN,
    // 开发人员
    DEVELOPER,
    // 企业
    COMPANY;

    public static Role fromString(String roleString) {
        for (Role role : Role.values()) {
            if (role.name().equals(roleString)) {
                return role;
            }
        }
        throw new IllegalArgumentException("No enum constant for " + roleString);
    }
}
