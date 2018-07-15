package com.seven.server.service;

import com.seven.server.model.Resource;
import com.seven.server.model.Role;
import com.seven.server.core.service.Service;

import java.util.List;

/**
 * @author seven
 * @date 2018/07/14
 */
public interface RoleService extends Service<Role> {
  /**
   * 获取所有角色以及对应的权限
   *
   * @return 角色可控资源列表
   */
  List<Resource> findAllRoleWithPermission();
}
