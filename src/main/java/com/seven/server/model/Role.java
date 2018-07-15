package com.seven.server.model;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

public class Role {
  /**
   * 角色Id
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /**
   * 角色名称
   */
  @NotEmpty(message = "角色名不能为空")
  private String name;

      /* ---------- 以下字段来自联表查询 ------------*/

  /**
   * 角色对应的权限
   */
  private List<Resource> resourceList;

     /* ---------- 以下字段来自请求的Json ------------*/

  /**
   * 角色对应的权限Id
   */
  @JSONField(serialize = false)
  @Transient
  private List<Integer> permissionIdList;

  /**
   * 获取角色Id
   *
   * @return id - 角色Id
   */
  public Long getId() {
    return id;
  }

  /**
   * 设置角色Id
   *
   * @param id
   *         角色Id
   */
  public void setId(Long id) {
    this.id = id;
  }

  /**
   * 获取角色名称
   *
   * @return name - 角色名称
   */
  public String getName() {
    return name;
  }

  /**
   * 设置角色名称
   *
   * @param name
   *         角色名称
   */
  public void setName(String name) {
    this.name = name;
  }

  public List<Resource> getResourceList() {
    return resourceList;
  }

  public void setResourceList(List<Resource> resourceList) {
    this.resourceList = resourceList;
  }

  public List<Integer> getPermissionIdList() {
    return permissionIdList;
  }

  public void setPermissionIdList(List<Integer> permissionIdList) {
    this.permissionIdList = permissionIdList;
  }
}