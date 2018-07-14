package com.seven.server.service.impl;

import com.seven.server.mapper.UserMapper;
import com.seven.server.model.User;
import com.seven.server.service.UserService;
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
public class UserServiceImpl extends AbstractService<User> implements UserService {
    @Resource
    private UserMapper userMapper;

}
