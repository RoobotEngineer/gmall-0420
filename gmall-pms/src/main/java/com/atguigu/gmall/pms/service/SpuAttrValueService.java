package com.atguigu.gmall.pms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.atguigu.gmall.common.bean.PageResultVo;
import com.atguigu.gmall.common.bean.PageParamVo;
import com.atguigu.gmall.pms.entity.SpuAttrValueEntity;

import java.util.List;
import java.util.Map;

/**
 * spu属性值
 *
 * @author wh
 * @email wh@atguigu.com
 * @date 2020-09-21 18:53:57
 */
public interface SpuAttrValueService extends IService<SpuAttrValueEntity> {

    PageResultVo queryPage(PageParamVo paramVo);


    List<SpuAttrValueEntity> querySearchSpuAttrValuesByCidAndSpuId(Long cid, Long spuId);
}

