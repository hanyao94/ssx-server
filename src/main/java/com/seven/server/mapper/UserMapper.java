package com.seven.server.mapper;

import com.seven.server.core.mapper.MyMapper;
import com.seven.server.model.User;

import java.util.List;
import java.util.Map;

public interface UserMapper extends MyMapper<User> {
  /**
   * 获取所有用户以及对应角色
   *
   * @return 用户列表
   */
  List<User> findAllUserWithRole();

  /**
   * 按条件查询用户信息
   *
   * @param param
   *         参数map
   * @return 用户
   */
  User findDetailBy(Map<String, Object> param);

  /**
   * 按用户名更新最后登陆时间
   *
   * @param username
   *         用户名
   */
  void updateLoginTimeByUsername(String username);
}