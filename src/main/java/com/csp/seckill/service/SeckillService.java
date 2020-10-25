package com.csp.seckill.service;

import com.csp.seckill.dao.GoodsDao;
import com.csp.seckill.entity.OrderInfo;
import com.csp.seckill.entity.SeckillUser;
import com.csp.seckill.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @description:
 * @author: csp52872
 * @date: 2020/10/25
 */
@Service
public class SeckillService {

    @Autowired
    private OrderService orderService;

    @Autowired
    private GoodsService goodsService;

    @Transactional
    public OrderInfo secKill(SeckillUser user, GoodsVo goods) {
        goodsService.reduceStock(goods);
        return orderService.createOrder(user, goods);
    }
}
