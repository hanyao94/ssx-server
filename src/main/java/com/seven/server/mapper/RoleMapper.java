package com.seven.server.mapper;

import com.seven.server.core.mapper.MyMapper;
import com.seven.server.model.Resource;
import com.seven.server.model.Role;

import java.util.List;

public interface RoleMapper extends MyMapper<Role> {
  /**
   * 获取所有角色以及对应的权限
   *
   * @return 角色可控资源列表
   */
  List<Resource> findAllRoleWithPermission();
}