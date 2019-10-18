package com.csp.seckill.controller;

import com.csp.seckill.result.Result;
import com.csp.seckill.service.SeckillUserService;
import com.csp.seckill.vo.LoginVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * @author csp
 * @description: TODO
 * @date 2019/10/13 18:59
 */

@Api(tags = "登录管理")
@Slf4j
@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private SeckillUserService userService;

    @RequestMapping("/page")
    public String getLoginPage() {
        return "login";
    }

    @PostMapping("/do_login")
    @ResponseBody
    @ApiOperation(value = "登录入口")
    public Result<Boolean> login(HttpServletResponse response, @Valid LoginVo loginVo) {
        userService.login(response, loginVo);
        return Result.success(true);
    }

}
