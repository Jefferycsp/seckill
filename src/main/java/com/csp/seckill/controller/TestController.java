package com.csp.seckill.controller;

import com.csp.seckill.entity.User;
import com.csp.seckill.redis.RedisService;
import com.csp.seckill.redis.UserKey;
import com.csp.seckill.result.CodeMsg;
import com.csp.seckill.result.Result;
import com.csp.seckill.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

/**
 * @author csp
 * @description: TODO
 * @date 2019/10/12 21:16
 */
@Slf4j
@Controller
@RequestMapping("/user")
public class TestController {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisService redisService;

    @GetMapping("/redis/{id}")
    @ResponseBody
    public Result<User> redisTest(@PathVariable("id") @NotNull Integer id) {
        User user = (User) redisService.get(UserKey.getById, id.toString());
        if (user == null) {
            user = userService.findById(id);
            redisService.set(UserKey.getById, id.toString(), user);
        }
        return Result.success(user);
    }

    @RequestMapping("/db/{id}")
    @ResponseBody
    public Result<User> get(@PathVariable("id") Integer id) {
        User user = userService.findById(id);
        return Result.success(user);
    }

    @PostMapping("/db/insert")
    @Transactional
    @ResponseBody
    public Result<User> insert(@RequestBody User user) {
        userService.insert(user);
        return Result.success(user);
    }

    @RequestMapping("/success")
    @ResponseBody
    public Result<String> success() {
        return Result.success("成功");
    }

    @RequestMapping("/error")
    @ResponseBody
    public Result<String> error() {
        return Result.error(CodeMsg.SUCCESS);
    }

    @RequestMapping("/thymeleaf")
    public String getPage(Model model) {
        model.addAttribute("name", "csp");
        return "hello";
    }
}
