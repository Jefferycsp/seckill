package com.csp.seckill.entity;

import java.io.Serializable;

/**
 * @description: 秒杀消息
 * @author: csp52872
 * @date: 2020/10/31
 */
public class SeckillMessage implements Serializable {

    private static final long serialVersionUID = -7269460704443423080L;

    private Long userId;

    private Long goodsId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }
}
