package com.atguigu.gmall.pms.vo;

import com.atguigu.gmall.pms.entity.SkuAttrValueEntity;
import com.atguigu.gmall.pms.entity.SkuEntity;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author wh
 * @user wh
 * @create 2020-09-23
 */
@Data
public class SkuVo extends SkuEntity {
    private List<String> images;

    // 积分活动
    private BigDecimal growBounds;
    private BigDecimal buyBounds;
    private List<Integer> work;

    // 满减活动
    private BigDecimal fullPrice;
    private BigDecimal reducePrice;
    private Integer fullAddOther;

    //打折相关信息
    private Integer fullCount;
    private BigDecimal discount;
    private Integer ladderAddOther;

    private List<SkuAttrValueEntity> saleAttrs;
}
