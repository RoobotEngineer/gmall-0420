package com.atguigu.gmall.pms.service;

import com.atguigu.gmall.pms.vo.SaleAttrValueVo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.atguigu.gmall.common.bean.PageResultVo;
import com.atguigu.gmall.common.bean.PageParamVo;
import com.atguigu.gmall.pms.entity.SkuAttrValueEntity;

import java.util.List;
import java.util.Map;

/**
 * sku销售属性&值
 *
 * @author wh
 * @email wh@atguigu.com
 * @date 2020-09-21 18:53:57
 */
public interface SkuAttrValueService extends IService<SkuAttrValueEntity> {

    PageResultVo queryPage(PageParamVo paramVo);

    List<SkuAttrValueEntity> querySearchSkuAttrValuesByCidAndSkuId(Long cid, Long skuId);

    List<SaleAttrValueVo> querySaleAttrValuesBySpuId(Long spuId);

    List<SkuAttrValueEntity> querySkuAttrValueBySkuId(Long skuId);

    String querySkuIdMappingSaleAttrValueBySpuId(Long spuId);
}

