package com.csp.seckill.controller;

import com.csp.seckill.entity.SeckillUser;
import com.csp.seckill.service.GoodsService;
import com.csp.seckill.service.SeckillUserService;
import com.csp.seckill.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author csp
 * @description: TODO
 * @date 2019/10/14 10:09
 */

@Controller
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @RequestMapping("/to_list")
    public String login(Model model, SeckillUser user) {
        model.addAttribute("user", user);
        List<GoodsVo> goodsVoList = goodsService.getGoodsVoList();
        model.addAttribute("goodsList", goodsVoList);
        return "goods_list";
    }

}
