package com.csp.seckill.entity;

import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;

@Getter
@Setter
public class SeckillOrder implements Serializable {
    private Long id;
    private Long userId;
    private Long orderId;
    private Long goodsId;
}
