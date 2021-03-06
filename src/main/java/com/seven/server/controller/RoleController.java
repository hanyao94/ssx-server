package com.seven.server.controller;

import com.seven.server.core.response.Result;
import com.seven.server.core.response.ResultGenerator;
import com.seven.server.model.Role;
import com.seven.server.service.RoleService;
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
@RequestMapping("/role")
public class RoleController {
  @Resource
  private RoleService roleService;

  @PreAuthorize("hasAuthority('role:add')")
  @PostMapping
  public Result add(@RequestBody Role role) {
    roleService.save(role);
    return ResultGenerator.genOkResult();
  }

  @PreAuthorize("hasAuthority('role:delete')")
  @DeleteMapping("/{id}")
  public Result delete(@PathVariable Long id) {
    roleService.deleteById(id);
    return ResultGenerator.genOkResult();
  }

  @PreAuthorize("hasAuthority('role:update')")
  @PutMapping
  public Result update(@RequestBody Role role) {
    roleService.update(role);
    return ResultGenerator.genOkResult();
  }

  @GetMapping("/{id}")
  public Result detail(@PathVariable Long id) {
    Role role = roleService.findById(id);
    return ResultGenerator.genOkResult(role);
  }


  @PreAuthorize("hasAuthority('role:list')")
  @GetMapping
  public Result list(@RequestParam(defaultValue = "0") Integer page,
                     @RequestParam(defaultValue = "0") Integer size) {
    PageHelper.startPage(page, size);
    List<com.seven.server.model.Resource> list = roleService.findAllRoleWithPermission();
    PageInfo pageInfo = new PageInfo(list);
    return ResultGenerator.genOkResult(pageInfo);
  }
}
