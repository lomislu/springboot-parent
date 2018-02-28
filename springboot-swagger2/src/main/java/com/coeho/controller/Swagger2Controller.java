package com.coeho.controller;

import com.coeho.model.Result;
import com.coeho.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Swagger2 API测试类
 *
 * @author: Lomis Lu (http://blog.coeho.com)
 * @email: lomis.lu@gmail.com
 * @datetime: 2018-02-12 18:02
 */
@Api(value = "用户管理", description = "主要进行用户的CRUD操作")
@Controller
public class Swagger2Controller {

    private static List<User> users = new ArrayList<User>();

    static {
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setId(i);
            user.setNickname("nickname-" + i);
            user.setUsername("username-" + i);
            user.setPassword("password-" + i);
            user.setEmail("lomis" + i + "@gmail.com");
            user.setPhone("110");
            user.setAddress("Shanghai-" + i);
            user.setCreateDate(new Date());
            user.setModifyDate(new Date());
            users.add(user);
        }
    }

    @ApiOperation(value = "用户登录API", notes = "用户登录API", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", type = "String", required = true),
            @ApiImplicitParam(name = "password", value = "密码", type = "String", required = true)
    })
    @RequestMapping(value = "/login", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Result login(String username, String password) {
        return new Result("200", "login success!!!", null);
    }

    @ApiOperation(value = "用户列表API",notes = "用户列表API")
    @RequestMapping("/list")
    @ResponseBody
    public Result list() {
        return new Result("200", "success", users);
    }
}
