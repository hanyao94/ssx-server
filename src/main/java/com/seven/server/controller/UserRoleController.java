package com.seven.server.controller;

import com.seven.server.core.response.Result;
import com.seven.server.core.response.ResultGenerator;
import com.seven.server.model.User;
import com.seven.server.model.UserRole;
import com.seven.server.service.UserRoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author seven
 * @date 2018/07/14
 */
@RestController
@RequestMapping("/user/role")
public class UserRoleController {
  @Resource
  private UserRoleService userRoleService;

  @PreAuthorize("hasAuthority('role:update')")
  @PutMapping
  public Result updateUserRole(@RequestBody User user) {
    userRoleService.updateUserRole(user);
    return ResultGenerator.genOkResult();
  }

}
