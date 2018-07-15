package com.seven.server.mapper;

import com.alibaba.fastjson.JSONObject;
import com.seven.server.core.mapper.MyMapper;
import com.seven.server.model.Permission;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PermissionMapper extends MyMapper<Permission> {
  /**
   * 找到所有权限可控资源
   *
   * @return Json对象列表
   */
  List<JSONObject> findAllResourcePermission();

  /**
   * 获取所有权限代码
   *
   * @return 代码列表
   */
  @Select("SELECT p.code FROM `permission` p")
  List<String> findAllCode();
}