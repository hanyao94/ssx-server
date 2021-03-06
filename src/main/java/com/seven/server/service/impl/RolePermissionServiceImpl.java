package com.seven.server.service.impl;

import com.seven.server.mapper.RolePermissionMapper;
import com.seven.server.model.RolePermission;
import com.seven.server.service.RolePermissionService;
import com.seven.server.core.service.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author seven
 * @date 2018/07/14
 */
@Service
@Transactional(rollbackFor = Exception.class)
@SuppressWarnings("SpringJavaAutowiringInspection")
public class RolePermissionServiceImpl extends AbstractService<RolePermission> implements RolePermissionService {
    @Resource
    private RolePermissionMapper rolePermissionMapper;

}
