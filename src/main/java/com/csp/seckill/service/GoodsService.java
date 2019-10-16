package com.csp.seckill.service;

import com.csp.seckill.dao.GoodsDao;
import com.csp.seckill.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author csp
 * @description: TODO
 * @date 2019/10/16 16:56
 */

@Service
public class GoodsService {

    @Autowired
    private GoodsDao goodsDao;

    public List<GoodsVo> getGoodsVoList() {
        return goodsDao.getGoodsVoList();
    }

}
