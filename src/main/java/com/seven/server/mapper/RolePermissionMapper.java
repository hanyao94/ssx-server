package com.seven.server.mapper;

import com.seven.server.core.mapper.MyMapper;
import com.seven.server.model.RolePermission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RolePermissionMapper extends MyMapper<RolePermission> {
  /**
   * 保存角色以及对应的权限ID
   *
   * @param roleId
   *         角色ID
   * @param permissionIdList
   *         权限ID列表
   */
  void saveRolePermission(@Param("roleId") Long roleId,
                          @Param("permissionIdList") List<Integer> permissionIdList);
}