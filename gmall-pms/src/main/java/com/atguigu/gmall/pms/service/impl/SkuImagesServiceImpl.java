package com.atguigu.gmall.pms.service.impl;

import com.atguigu.gmall.pms.entity.SkuEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.gmall.common.bean.PageResultVo;
import com.atguigu.gmall.common.bean.PageParamVo;

import com.atguigu.gmall.pms.mapper.SkuImagesMapper;
import com.atguigu.gmall.pms.entity.SkuImagesEntity;
import com.atguigu.gmall.pms.service.SkuImagesService;


@Service("skuImagesService")
public class SkuImagesServiceImpl extends ServiceImpl<SkuImagesMapper, SkuImagesEntity> implements SkuImagesService {

    @Override
    public PageResultVo queryPage(PageParamVo paramVo) {
        IPage<SkuImagesEntity> page = this.page(
                paramVo.getPage(),
                new QueryWrapper<SkuImagesEntity>()
        );

        return new PageResultVo(page);
    }

    @Override
    public List<SkuImagesEntity> queryImagesBySkuId(Long skuId) {
        QueryWrapper<SkuImagesEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("sku_id",skuId);
        return this.list(wrapper);
    }

}