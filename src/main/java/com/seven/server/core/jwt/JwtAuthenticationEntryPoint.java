/**
 * <p>
 * 项目名：	ssx-server
 * 文件名：	JwtAuthenticationEntryPoint.java
 * 模块说明：
 * 修改历史：
 * 2018/7/21 - seven - 创建。
 */
package com.seven.server.core.jwt;

import com.seven.server.core.response.ResultGenerator;
import org.springframework.core.type.StandardAnnotationMetadata;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;

/**
 * Json web token 入口点
 *
 * @author seven
 */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {

  /**
   * 当访问的资源没有权限时被调用
   */
  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    response.setHeader("Content-type", MediaType.APPLICATION_JSON_UTF8_VALUE);
    response.setCharacterEncoding(StandardCharsets.UTF_8.displayName());
    response.getWriter().print(ResultGenerator.genUnauthorizedResult().toString());
    response.getWriter().close();
  }
}
