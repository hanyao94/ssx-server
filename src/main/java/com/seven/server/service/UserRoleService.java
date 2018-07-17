package com.seven.server.service;

import com.seven.server.model.User;
import com.seven.server.model.UserRole;
import com.seven.server.core.service.Service;

/**
 * @author seven
 * @date 2018/07/14
 */
public interface UserRoleService extends Service<UserRole> {
  /**
   * 更新用户角色
   *
   * @param user 用户
   */
  void updateUserRole(User user);
}
