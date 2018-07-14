package com.seven.server.controller;

import com.seven.server.core.response.Result;
import com.seven.server.core.response.ResultGenerator;
import com.seven.server.model.UserRole;
import com.seven.server.service.UserRoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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

  @PostMapping
  public Result add(@RequestBody UserRole userRole) {
    userRoleService.save(userRole);
    return ResultGenerator.genOkResult();
  }

  @DeleteMapping("/{id}")
  public Result delete(@PathVariable Long id) {
    userRoleService.deleteById(id);
    return ResultGenerator.genOkResult();
  }

  @PutMapping
  public Result update(@RequestBody UserRole userRole) {
    userRoleService.update(userRole);
    return ResultGenerator.genOkResult();
  }

  @GetMapping("/{id}")
  public Result detail(@PathVariable Long id) {
    UserRole userRole = userRoleService.findById(id);
    return ResultGenerator.genOkResult(userRole);
  }

  @GetMapping
  public Result list(@RequestParam(defaultValue = "0") Integer page,
                     @RequestParam(defaultValue = "0") Integer size) {
    PageHelper.startPage(page, size);
    List<UserRole> list = userRoleService.findAll();
    PageInfo pageInfo = new PageInfo(list);
    return ResultGenerator.genOkResult(pageInfo);
  }
}
