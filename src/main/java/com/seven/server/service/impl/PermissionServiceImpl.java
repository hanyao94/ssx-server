package com.seven.server.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.seven.server.mapper.PermissionMapper;
import com.seven.server.model.Permission;
import com.seven.server.service.PermissionService;
import com.seven.server.core.service.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author seven
 * @date 2018/07/14
 */
@Service
@Transactional
@SuppressWarnings("SpringJavaAutowiringInspection")
public class PermissionServiceImpl extends AbstractService<Permission> implements PermissionService {
    @Resource
    private PermissionMapper permissionMapper;

    @Override
    public List<JSONObject> findAllResourcePermission() {
        return permissionMapper.findAllResourcePermission();
    }
}
