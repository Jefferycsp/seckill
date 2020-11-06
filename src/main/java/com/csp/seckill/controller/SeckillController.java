package com.csp.seckill.controller;

import com.csp.seckill.consts.Constants;
import com.csp.seckill.entity.OrderInfo;
import com.csp.seckill.entity.SeckillMessage;
import com.csp.seckill.entity.SeckillOrder;
import com.csp.seckill.entity.SeckillUser;
import com.csp.seckill.result.CodeMsg;
import com.csp.seckill.service.GoodsService;
import com.csp.seckill.service.OrderService;
import com.csp.seckill.service.SeckillService;
import com.csp.seckill.vo.GoodsVo;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @description: 秒杀
 * @author: csp52872
 * @date: 2020/10/23
 */
@Controller
@RequestMapping("/seckill")
public class SeckillController {

    @Autowired
    private SeckillService seckillService;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @RequestMapping("/do_seckill")
    public String list(Model model, SeckillUser user, @RequestParam("goodsId") long goodsId) {
        model.addAttribute("user", user);
        if (user == null) {
            return "login";
        }
        GoodsVo goods = goodsService.getGoodsVoByGoodsId(goodsId);
        // 判断该商品是否还有库存
        if (goods == null || goods.getStockCount() <= 0) {
            model.addAttribute("errMsg", CodeMsg.SEC_KILL_OVER.getMsg());
            return "seckill_fail";
        }
        Gson gson = new Gson();
        SeckillMessage seckillMessage = new SeckillMessage();
        seckillMessage.setUserId(user.getId());
        seckillMessage.setGoodsId(goodsId);
        kafkaTemplate.send(Constants.TOPIC, gson.toJson(seckillMessage));

        // 判断是否已经秒杀到该商品
        SeckillOrder seckillOrder = orderService.getSeckillOrderByUserIdGoodsId(user.getId(), goodsId);
        if (seckillOrder != null) {
            model.addAttribute("errMsg", CodeMsg.REPEATE_SEC_KILL.getMsg());
            return "seckill_fail";
        }
        // 进入秒杀
        OrderInfo orderInfo = seckillService.secKill(user, goods);
        model.addAttribute("orderInfo", orderInfo);
        model.addAttribute("goods", goods);
        return "order_detail";
    }

}
