package com.csp.seckill.vo;

import com.csp.seckill.entity.Goods;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author csp
 * @description: TODO
 * @date 2019/10/16 16:50
 */

@Getter
@Setter
public class GoodsVo extends Goods {
    private Double seckillPrice;
    private Integer stockCount;
    private Date startDate;
    private Date endDate;
}
