package com.seven.server.controller;

import com.seven.server.core.response.Result;
import com.seven.server.core.response.ResultGenerator;
import com.seven.server.model.Permission;
import com.seven.server.service.PermissionService;
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
@RequestMapping("/permission")
public class PermissionController {
  @Resource
  private PermissionService permissionService;

  @PostMapping
  public Result add(@RequestBody Permission permission) {
    permissionService.save(permission);
    return ResultGenerator.genOkResult();
  }

  @DeleteMapping("/{id}")
  public Result delete(@PathVariable Long id) {
    permissionService.deleteById(id);
    return ResultGenerator.genOkResult();
  }

  @PutMapping
  public Result update(@RequestBody Permission permission) {
    permissionService.update(permission);
    return ResultGenerator.genOkResult();
  }

  @GetMapping("/{id}")
  public Result detail(@PathVariable Long id) {
    Permission permission = permissionService.findById(id);
    return ResultGenerator.genOkResult(permission);
  }

  @GetMapping
  public Result list(@RequestParam(defaultValue = "0") Integer page,
                     @RequestParam(defaultValue = "0") Integer size) {
    PageHelper.startPage(page, size);
    List<Permission> list = permissionService.findAll();
    PageInfo pageInfo = new PageInfo(list);
    return ResultGenerator.genOkResult(pageInfo);
  }
}
