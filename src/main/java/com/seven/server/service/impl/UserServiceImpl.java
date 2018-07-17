package com.seven.server.service.impl;

import com.seven.server.core.exception.ServiceException;
import com.seven.server.mapper.PermissionMapper;
import com.seven.server.mapper.UserMapper;
import com.seven.server.mapper.UserRoleMapper;
import com.seven.server.model.User;
import com.seven.server.model.UserRole;
import com.seven.server.service.UserService;
import com.seven.server.core.service.AbstractService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author seven
 * @date 2018/07/14
 */
@Service
@Transactional(rollbackFor = Exception.class)
@SuppressWarnings("SpringJavaAutowiringInspection")
public class UserServiceImpl extends AbstractService<User> implements UserService {
  @Resource
  private UserMapper userMapper;
  @Resource
  private UserRoleMapper userRoleMapper;
  @Resource
  private PermissionMapper permissionMapper;
  @Resource
  private PasswordEncoder passwordEncoder;

  /**
   * 重写save方法，密码加密后再存
   */
  @Override
  public void save(final User user) {
    User u = findBy("username", user.getUsername());
    if (u != null) {
      throw new ServiceException("用户名已存在");
    }
    u = findBy("email", user.getEmail());
    if (u != null) {
      throw new ServiceException("邮箱已存在");
    }

    user.setPassword(passwordEncoder.encode(user.getPassword().trim()));
    userMapper.insertSelective(user);
    Long roleId = user.getRoleId();
    if (roleId == null) {
      roleId = 2L;
    }
    userRoleMapper.insert(new UserRole().setUserId(user.getId()).setRoleId(roleId));
  }

  @Override
  public void update(final User user) {
    //如果修改了密码
    if (user.getPassword() != null && user.getPassword().length() >= 6) {
      //密码修改后需要加密
      user.setPassword(passwordEncoder.encode(user.getPassword().trim()));
    }
    this.userMapper.updateByPrimaryKeySelective(user);
  }

  @Override
  public List<User> findAllUserWithRole() {
    return userMapper.findAllUserWithRole();
  }

  @Override
  public User findDetailBy(final String column, final Object param) {
    final Map<String, Object> map = new HashMap<>(1);
    map.put(column, param);
    return userMapper.findDetailBy(map);
  }

  @Override
  public User findDetailByUsername(final String username) throws UsernameNotFoundException {
    final User user = findDetailBy("username", username);
    if (user == null) {
      throw new UsernameNotFoundException("not found this username");
    }
    if ("ROLE_ADMIN".equals(user.getRoleName())) {
      //超级管理员所有权限都有
      user.setPermissionCodeList(permissionMapper.findAllCode());
    }
    return user;
  }

  @Override
  public void updateLoginTimeByUsername(final String username) {
    this.userMapper.updateLoginTimeByUsername(username);
  }

  @Override
  public boolean verifyPassword(final String rawPassword, final String encodedPassword) {
    return passwordEncoder.matches(rawPassword, encodedPassword);
  }
}
