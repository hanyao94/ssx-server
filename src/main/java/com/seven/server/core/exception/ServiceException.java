/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，2016，所有权利保留。
 * <p>
 * 项目名：	ssx-server
 * 文件名：	ServiceException.java
 * 模块说明：
 * 修改历史：
 * 2018/7/14 - seven - 创建。
 */
package com.seven.server.core.exception;

/**
 * @author seven
 */
public class ServiceException extends RuntimeException {
  public ServiceException(String message) {
    super(message);
  }

  public ServiceException(String message, Throwable cause) {
    super(message, cause);
  }
}
