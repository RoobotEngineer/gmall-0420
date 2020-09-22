package com.atguigu.gmall.pms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.atguigu.gmall.common.bean.PageResultVo;
import com.atguigu.gmall.common.bean.PageParamVo;
import com.atguigu.gmall.pms.entity.SkuEntity;

import java.util.List;
import java.util.Map;

/**
 * sku信息
 *
 * @author wh
 * @email wh@atguigu.com
 * @date 2020-09-21 18:53:57
 */
public interface SkuService extends IService<SkuEntity> {

    PageResultVo queryPage(PageParamVo paramVo);

    List<SkuEntity> querySkuBySpuId(Long spuId);
}

