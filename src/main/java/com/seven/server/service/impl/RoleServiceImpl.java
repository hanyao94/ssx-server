package com.seven.server.service.impl;

import com.seven.server.mapper.RoleMapper;
import com.seven.server.model.Role;
import com.seven.server.service.RoleService;
import com.seven.server.core.service.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author seven
 * @date 2018/07/14
 */
@Service
@Transactional
@SuppressWarnings("SpringJavaAutowiringInspection")
public class RoleServiceImpl extends AbstractService<Role> implements RoleService {
    @Resource
    private RoleMapper roleMapper;

}
