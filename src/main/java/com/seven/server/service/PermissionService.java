package com.seven.server.service;

import com.alibaba.fastjson.JSONObject;
import com.seven.server.model.Permission;
import com.seven.server.core.service.Service;

import java.util.List;

/**
 * @author seven
 * @date 2018/07/14
 */
public interface PermissionService extends Service<Permission> {
  /**
   * 找到所有权限可控资源
   *
   * @return Json对象列表
   */
  List<JSONObject> findAllResourcePermission();
}
