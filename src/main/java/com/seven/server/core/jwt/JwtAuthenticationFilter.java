/**
 * <p>
 * 项目名：	ssx-server
 * 文件名：	JwtAuthenticationFilter.java
 * 模块说明：
 * 修改历史：
 * 2018/7/21 - seven - 创建。
 */
package com.seven.server.core.jwt;

import com.seven.server.util.IpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Nonnull;
import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 身份认证过滤器
 *
 * @author seven
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
  private final static Logger log = LoggerFactory.getLogger(JwtAuthenticationFilter.class);
  @Resource
  private JwtUtil jwtUtil;

  @Override
  protected void doFilterInternal(@Nonnull final HttpServletRequest request, @Nonnull final HttpServletResponse response, @Nonnull final FilterChain filterChain)
          throws ServletException, IOException {

    // 解决跨域问题
    response.setHeader("Access-Control-Allow-Origin", "*");
    response.setHeader("Access-Control-Allow-Credentials", "true");
    response.setHeader("Access-Control-Allow-Headers", "Content-Type, Content-Length, Authorization, Accept, X-Requested-With");
    // 明确允许通过的方法，不建议使用*
    response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
    response.setHeader("Access-Control-Max-Age", "3600");
    response.setHeader("Access-Control-Expose-Headers", "*");
    // 预请求后，直接返回
    // 返回码必须为 200 否则视为请求失败

    if ("OPTIONS".equals(request.getMethod())) {
      return;
    }

    final String token = this.jwtUtil.getTokenFromRequest(request);
    if (token == null) {
      log.info("JwtFilter => Anonymous<> request URL<{}> Method<{}>", IpUtil.getIpAddress(request), request.getRequestURL(), request.getMethod());
    } else {
      final String username = this.jwtUtil.getUsername(token);
      log.info("JwtFilter => user<{}> token : {}", username, token);
      log.info("JwtFilter => request URL<{}> Method<{}>", request.getRequestURL(), request.getMethod());

      if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
        final UsernamePasswordAuthenticationToken authentication = this.jwtUtil.getAuthentication(username, token);

        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        log.info("JwtFilter => user<{}> is authorized, set security context", username);
      }
    }
    filterChain.doFilter(request, response);
  }
}