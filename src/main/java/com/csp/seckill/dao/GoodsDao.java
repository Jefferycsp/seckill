package com.csp.seckill.dao;

import com.csp.seckill.vo.GoodsVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author csp
 * @description: TODO
 * @date 2019/10/16 16:45
 */

@Mapper
public interface GoodsDao {

    @Select("select g.*,s.seckill_price,s.stock_count,s.start_time,s.end_time from goods g left join seckill_goods s on g.id = s.goods_id")
    List<GoodsVo> getGoodsVoList();
}
