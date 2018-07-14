package com.seven.server.service.impl;

import com.seven.server.mapper.UserRoleMapper;
import com.seven.server.model.UserRole;
import com.seven.server.service.UserRoleService;
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
public class UserRoleServiceImpl extends AbstractService<UserRole> implements UserRoleService {
    @Resource
    private UserRoleMapper userRoleMapper;

}
