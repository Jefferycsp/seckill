package com.csp.seckill.controller;

import com.csp.seckill.entity.SeckillUser;
import com.csp.seckill.service.GoodsService;
import com.csp.seckill.vo.GoodsVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author csp
 * @description: TODO
 * @date 2019/10/14 10:09
 */
@Api(tags = "商品管理")
@Controller
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @ApiOperation(value = "登录跳转商品接口")
    @RequestMapping("/to_list")
    public String login(Model model, SeckillUser user) {
        model.addAttribute("user", user);
        List<GoodsVo> goodsVoList = goodsService.getGoodsVoList();
        model.addAttribute("goodsList", goodsVoList);
        return "goods_list";
    }

    @ApiOperation(value = "商品详情接口")
    @RequestMapping("/to_detail/{goods_id}")
    public String detail(Model model, SeckillUser user, @PathVariable("goods_id") long goodsId) {
        model.addAttribute("user", user);
        GoodsVo goods = goodsService.getGoodsVoByGoodsId(goodsId);
        model.addAttribute("goods", goods);
        long startTime = goods.getStartTime().getTime();
        long endTime = goods.getEndTime().getTime();
        long now = System.currentTimeMillis();
        int seckillStatus = 0;
        int remainSeconds = 0;
        // 秒杀还未开始
        if (now < startTime) {
            seckillStatus = 0;
            remainSeconds = (int) (startTime - endTime) / 1000;
        } else if (now > endTime) {
            // 秒杀已经结束
            seckillStatus = 2;
            remainSeconds = -1;
        } else {
            seckillStatus = 1;
            remainSeconds = 0;
        }
        model.addAttribute("seckillStatus", seckillStatus);
        model.addAttribute("remainSeconds", remainSeconds);
        return "goods_detail";
    }

}
