package com.csp.seckill.entity;

import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class SeckillGoods implements Serializable {
    private Long id;
    private Long goodsId;
    private Integer stockCount;
    private Date startDate;
    private Date endDate;
}
