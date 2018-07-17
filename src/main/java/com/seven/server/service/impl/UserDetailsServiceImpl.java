/**
 * <p>
 * 项目名：	ssx-server
 * 文件名：	UserDetailsServiceImpl.java
 * 模块说明：
 * 修改历史：
 * 2018/7/17 - seven - 创建。
 */
package com.seven.server.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author seven
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {


  @Override
  public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
    return null;
  }
}
