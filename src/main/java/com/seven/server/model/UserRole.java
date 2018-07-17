package com.seven.server.model;

import javax.persistence.*;

@Table(name = "user_role")
public class UserRole {
    /**
     * 用户Id
     */
    @Id
    @Column(name = "user_id")
    private Long userId;

    /**
     * 角色Id
     */
    @Column(name = "role_id")
    private Long roleId;

    /**
     * 获取用户Id
     *
     * @return user_id - 用户Id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 设置用户Id
     *
     * @param userId 用户Id
     */
    public UserRole setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    /**
     * 获取角色Id
     *
     * @return role_id - 角色Id
     */
    public Long getRoleId() {
        return roleId;
    }

    /**
     * 设置角色Id
     *
     * @param roleId 角色Id
     */
    public UserRole setRoleId(Long roleId) {
        this.roleId = roleId;
        return this;
    }
}