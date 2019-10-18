package com.csp.seckill.entity;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;

@Getter
@Setter
@ApiModel("商品实体描述")
public class Goods implements Serializable {
	private Long id;
	private String goodsName;
	private String goodsTitle;
	private String goodsImage;
	private String goodsDetail;
	private Double goodsPrice;
	private Integer goodsStock;
}
