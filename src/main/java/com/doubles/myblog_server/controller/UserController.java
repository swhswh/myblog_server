package com.doubles.myblog_server.controller;

import com.doubles.myblog_server.common.dto.LoginForm;
import com.doubles.myblog_server.common.Result;
import com.doubles.myblog_server.common.enums.TokenEnum;
import com.doubles.myblog_server.entity.User;
import com.doubles.myblog_server.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * UserController
 *
 * @author swh
 * @date 2019-10-24
 */
@RestController
@RequestMapping("/admin")
public class UserController {

    @Resource
    UserService userService;

    @PostMapping("/login")
    public Result login( @RequestBody LoginForm form) {
        User user = userService.getUserByAccount(form.getAccount());
        List userToken = new ArrayList<>();
        if (user != null) {
            if (user.getPassword().equals(form.getPassword())) {
                // 密码认证成功，生成token
                userToken.add(userService.generateToken(user.getId(), user.getAuth(), TokenEnum.ACCESS.toString(), "60*60"));
                userToken.add(userService.generateToken(user.getId(), user.getAuth(), TokenEnum.REFRESH.toString(), "60*60*24*30"));
            } else {
                return Result.error("用户密码错误");
            }
        } else {
            return Result.error("用户不存在");
        }
        return Result.ok().put("accessToken", userToken.get(0)).put("refreshToken",userToken.get(1));
    }

    @GetMapping("/user/info/{id}")
    public Result getUserInfo(@PathVariable Integer id){
        User user = userService.getUserById(id);
        return Result.ok().put("user",user);
    }

    @GetMapping("/user/list")
    public Result getUserList(){
        List<User> userList = userService.listUser();
        return Result.ok().put("userList",userList);
    }

    @GetMapping("/user/listnoadmin")
    public Result getUserListNoAdmin(){
        List<User> userList = userService.listUserNoAdmin();
        return Result.ok().put("userList",userList);
    }
}
