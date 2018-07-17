package com.seven.server.service.impl;

import com.seven.server.mapper.UserRoleMapper;
import com.seven.server.model.User;
import com.seven.server.model.UserRole;
import com.seven.server.service.UserRoleService;
import com.seven.server.core.service.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;

/**
 * @author seven
 * @date 2018/07/14
 */
@Service
@Transactional(rollbackFor = Exception.class)
@SuppressWarnings("SpringJavaAutowiringInspection")
public class UserRoleServiceImpl extends AbstractService<UserRole> implements UserRoleService {
    @Resource
    private UserRoleMapper userRoleMapper;

    @Override
    public void updateUserRole(final User user) {
        final Condition condition = new Condition(UserRole.class);
        condition.createCriteria().andCondition("user_id = ",user.getId());
        final UserRole userRole = new UserRole().setUserId(user.getId()).setRoleId(user.getRoleId());
        userRoleMapper.updateByConditionSelective(userRole,condition);
    }
}
