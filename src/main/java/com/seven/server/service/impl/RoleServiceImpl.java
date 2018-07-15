package com.seven.server.service.impl;

import com.seven.server.mapper.RoleMapper;
import com.seven.server.mapper.RolePermissionMapper;
import com.seven.server.model.Role;
import com.seven.server.model.RolePermission;
import com.seven.server.service.RoleService;
import com.seven.server.core.service.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author seven
 * @date 2018/07/14
 */
@Service
@Transactional
@SuppressWarnings("SpringJavaAutowiringInspection")
public class RoleServiceImpl extends AbstractService<Role> implements RoleService {
  @Resource
  private RoleMapper roleMapper;

  @Resource
  private RolePermissionMapper rolePermissionMapper;

  @Override
  public List<com.seven.server.model.Resource> findAllRoleWithPermission() {
    return roleMapper.findAllRoleWithPermission();
  }

  @Override
  public void save(Role role) {
    roleMapper.insert(role);
    this.rolePermissionMapper.saveRolePermission(role.getId(), role.getPermissionIdList());
  }

  @Override
  public void update(Role role) {
    // 删掉所有权限，再添加回去
    final Condition condition = new Condition(RolePermission.class);
    condition.createCriteria().andCondition("role_id = ", role.getId());
    rolePermissionMapper.deleteByCondition(condition);
    rolePermissionMapper.saveRolePermission(role.getId(), role.getPermissionIdList());
  }
}
