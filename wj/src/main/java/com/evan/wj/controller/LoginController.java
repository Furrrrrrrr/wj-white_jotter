package com.evan.wj.controller;

import com.evan.wj.pojo.User;
import com.evan.wj.result.Result;
import com.evan.wj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.HtmlUtils;

import java.util.Objects;

@Controller
public class LoginController {

    @Autowired
    UserService userService;

    // TODO: 2019/12/27 什么意思？
    @CrossOrigin
    @RequestMapping(value = "api/login")
    @ResponseBody
    public Result login(@RequestBody User requestUser) {
        String username = requestUser.getUsername();
        // 对 html 标签进行转义，防止 XSS 攻击
        username = HtmlUtils.htmlEscape(username);

        /*if (!Objects.equals("admin", username) || !Objects.equals("123456", requestUser.getPassword())) {
            System.err.println("验证不成功，无法登录");
            return new Result(400);
        } else {
            System.err.println("验证成功，登录");
            return new Result(200);
        }*/

        User user = userService.get(username, requestUser.getPassword());
        if (Objects.isNull(user)) {
            return new Result(400);
        } else {
            return new Result(200);
        }

    }

}