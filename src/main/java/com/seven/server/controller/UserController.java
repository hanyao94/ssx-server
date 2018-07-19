package com.seven.server.controller;

import com.seven.server.core.response.Result;
import com.seven.server.core.response.ResultGenerator;
import com.seven.server.model.User;
import com.seven.server.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.seven.server.service.impl.UserDetailsServiceImpl;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

/**
 * @author seven
 * @date 2018/07/14
 */
@RestController
@RequestMapping("/user")
public class UserController {
  @Resource
  private UserService userService;
  @Resource
  private UserDetailsServiceImpl userDetailsService;

  @PostMapping("/register")
  public Result register(@RequestBody @Valid User user, BindingResult bindingResult) {
    // {"username":"123456", "password":"123456", "email": "123456@qq.com"}
    if (bindingResult.hasErrors()) {
      //noinspection ConstantConditions
      final String msg = bindingResult.getFieldError().getDefaultMessage();
      return ResultGenerator.genFailedResult(msg);
    } else {
      this.userService.save(user);
      return this.getToken(user);
    }
  }

  @PostMapping("/password")
  public Result validatePassword(@RequestBody User user) {
    User oldUser = userService.findById(user.getId());
    boolean isValidate = userService.verifyPassword(user.getPassword(), oldUser.getPassword());
    return ResultGenerator.genOkResult(isValidate);
  }

  @PostMapping
  public Result add(@RequestBody User user) {
    userService.save(user);
    return ResultGenerator.genOkResult();
  }

  @PreAuthorize("hasAuthority('user:delete')")
  @DeleteMapping("/{id}")
  public Result delete(@PathVariable Long id) {
    userService.deleteById(id);
    return ResultGenerator.genOkResult();
  }

  @PutMapping
  public Result update(@RequestBody User user) {
    userService.update(user);
    return ResultGenerator.genOkResult();
  }

  @GetMapping("/{id}")
  public Result detail(@PathVariable Long id) {
    User user = userService.findById(id);
    return ResultGenerator.genOkResult(user);
  }

  @GetMapping("/info")
  public Result info(final Principal user) {
    final User userDB = userService.findDetailByUsername(user.getName());
    return ResultGenerator.genOkResult(userDB);
  }

  @PreAuthorize("hasAuthority('user:list')")
  @GetMapping
  public Result list(@RequestParam(defaultValue = "0") Integer page,
                     @RequestParam(defaultValue = "0") Integer size) {
    PageHelper.startPage(page, size);
    List<User> list = userService.findAllUserWithRole();
    PageInfo pageInfo = new PageInfo(list);
    return ResultGenerator.genOkResult(pageInfo);
  }

  @PostMapping("/login")
  public Result login(@RequestBody User user) {
    // {"username":"admin", "password":"admin123"}
    // {"email":"admin@qq.com", "password":"admin123"}
    if (user.getUsername() == null && user.getEmail() == null) {
      return ResultGenerator.genFailedResult("username or email empty");
    }
    if (user.getPassword() == null) {
      return ResultGenerator.genFailedResult("password empty");
    }
    // 用户名登录
    User dbUser = null;
    if (user.getUsername() != null) {
      dbUser = userService.findBy("username", user.getUsername());
      if (dbUser == null) {
        return ResultGenerator.genFailedResult("username error");
      }
    }
    // 邮箱登录
    if (user.getEmail() != null) {
      dbUser = userService.findBy("email", user.getEmail());
      if (dbUser == null) {
        return ResultGenerator.genFailedResult("email error");
      }
      user.setUsername(dbUser.getUsername());
    }
    // 验证密码
    //noinspection ConstantConditions
    if (!userService.verifyPassword(user.getPassword(), dbUser.getPassword())) {
      return ResultGenerator.genFailedResult("password error");
    }
    // 更新登录时间
    userService.updateLoginTimeByUsername(user.getUsername());
    return getToken(user);
  }

  @GetMapping("/logout")
  public Result logout(Principal user) {
    return ResultGenerator.genOkResult();
  }

  private Result getToken(@Valid User user) {
    String username = user.getUsername();
    UserDetails userDetails = userDetailsService.loadUserByUsername(username);
//    String token = jwtUtil.sign(username, userDetails.getAuthorities());
//    return ResultGenerator.genOkResult(token);
    return null;
  }

}
