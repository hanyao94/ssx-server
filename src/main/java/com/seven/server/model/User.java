package com.seven.server.model;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class User {
  /**
   * 用户Id
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /**
   * 邮箱
   */
  @NotEmpty(message = "邮箱不能为空")
  @Email
  private String email;

  /**
   * 用户名
   */
  @NotEmpty(message = "用户名不能为空")
  @Size(min = 3, message = "用户名长度不能小于3")
  private String username;

  /**
   * 密码
   */
//  @JSONField(serialize = false)
  @NotEmpty(message = "密码不能为空")
  @Size(min = 6, message = "密码长度不能小于6")
  private String password;

  /**
   * 头像
   */
  private String avatar;

  /**
   * 简介
   */
  private String resume;

  /**
   * 注册时间
   */
  @Column(name = "register_time")
  private Date registerTime;

  /**
   * 上一次登录时间
   */
  @Column(name = "login_time")
  private Date loginTime;

    /* ---------- 以下字段来自联表查询 ------------*/
  /**
   * 用户的角色Id
   */
  @Transient
  private Long roleId;

  /**
   * 用户的角色名
   */
  @Transient
  private String roleName;

  /**
   * 用户的角色对应的权限code
   */
  @Transient
  private List<String> permissionCodeList;

  /**
   * 获取用户Id
   *
   * @return id - 用户Id
   */
  public Long getId() {
    return id;
  }

  /**
   * 设置用户Id
   *
   * @param id
   *         用户Id
   */
  public void setId(Long id) {
    this.id = id;
  }

  /**
   * 获取邮箱
   *
   * @return email - 邮箱
   */
  public String getEmail() {
    return email;
  }

  /**
   * 设置邮箱
   *
   * @param email
   *         邮箱
   */
  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * 获取用户名
   *
   * @return username - 用户名
   */
  public String getUsername() {
    return username;
  }

  /**
   * 设置用户名
   *
   * @param username
   *         用户名
   */
  public void setUsername(String username) {
    this.username = username;
  }

  /**
   * 获取密码
   *
   * @return password - 密码
   */
  public String getPassword() {
    return password;
  }

  /**
   * 设置密码
   *
   * @param password
   *         密码
   */
  public void setPassword(String password) {
    this.password = password;
  }

  /**
   * 获取头像
   *
   * @return avatar - 头像
   */
  public String getAvatar() {
    return avatar;
  }

  /**
   * 设置头像
   *
   * @param avatar
   *         头像
   */
  public void setAvatar(String avatar) {
    this.avatar = avatar;
  }

  /**
   * 获取简介
   *
   * @return resume - 简介
   */
  public String getResume() {
    return resume;
  }

  /**
   * 设置简介
   *
   * @param resume
   *         简介
   */
  public void setResume(String resume) {
    this.resume = resume;
  }

  /**
   * 获取注册时间
   *
   * @return register_time - 注册时间
   */
  public Date getRegisterTime() {
    return registerTime;
  }

  /**
   * 设置注册时间
   *
   * @param registerTime
   *         注册时间
   */
  public void setRegisterTime(Date registerTime) {
    this.registerTime = registerTime;
  }

  /**
   * 获取上一次登录时间
   *
   * @return login_time - 上一次登录时间
   */
  public Date getLoginTime() {
    return loginTime;
  }

  /**
   * 设置上一次登录时间
   *
   * @param loginTime
   *         上一次登录时间
   */
  public void setLoginTime(Date loginTime) {
    this.loginTime = loginTime;
  }

  public Long getRoleId() {
    return roleId;
  }

  public void setRoleId(Long roleId) {
    this.roleId = roleId;
  }

  public String getRoleName() {
    return roleName;
  }

  public void setRoleName(String roleName) {
    this.roleName = roleName;
  }

  public List<String> getPermissionCodeList() {
    return permissionCodeList;
  }

  public void setPermissionCodeList(List<String> permissionCodeList) {
    this.permissionCodeList = permissionCodeList;
  }
}