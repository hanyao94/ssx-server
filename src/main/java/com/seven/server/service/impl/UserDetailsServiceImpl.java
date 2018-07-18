/**
 * <p>
 * 项目名：	ssx-server
 * 文件名：	UserDetailsServiceImpl.java
 * 模块说明：
 * 修改历史：
 * 2018/7/17 - seven - 创建。
 */
package com.seven.server.service.impl;

import com.seven.server.model.User;
import com.seven.server.service.UserService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author seven
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  @Resource
  private UserService userService;

  @Override
  public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
    final User user = userService.findDetailByUsername(username);
    //权限
    final List<SimpleGrantedAuthority> authorities =
            user.getPermissionCodeList()
                    .stream()
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());
    //角色
    authorities.add(new SimpleGrantedAuthority(user.getRoleName())); //将权限和用户放在一起比较方便
    return new org.springframework.security.core.userdetails.User(
            user.getUsername(),
            user.getPassword(),
            authorities);
  }
}
