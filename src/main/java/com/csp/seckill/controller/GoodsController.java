package com.csp.seckill.controller;

import com.csp.seckill.entity.SeckillUser;
import com.csp.seckill.service.SeckillUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author csp
 * @description: TODO
 * @date 2019/10/14 10:09
 */

@Controller
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private SeckillUserService userService;

    @RequestMapping("/to_list")
    public String login(Model model,
//                        @CookieValue(value = SeckillUserService.COOKIE_NAME, required = false) String cookieToken,
//                        @RequestParam(value = SeckillUserService.COOKIE_NAME, required = false) String paramToken,
                        SeckillUser user) {
//        if (StringUtils.isBlank(cookieToken)&& StringUtils.isBlank(paramToken)) {
//            return "login";
//        }
//        String token = StringUtils.isBlank(paramToken) ? cookieToken : paramToken;
//        SeckillUser user = userService.getByToken(token);
        model.addAttribute("user", user);
        return "goods_list";
    }
}
