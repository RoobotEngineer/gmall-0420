package com.atguigu.gmall.pms.service.impl;

import com.atguigu.gmall.pms.entity.*;
import com.atguigu.gmall.pms.feign.GmallSmsClient;
import com.atguigu.gmall.pms.mapper.SkuMapper;
import com.atguigu.gmall.pms.mapper.SpuDescMapper;
import com.atguigu.gmall.pms.service.*;
import com.atguigu.gmall.pms.vo.SkuVo;
import com.atguigu.gmall.pms.vo.SpuAttrValueVo;
import com.atguigu.gmall.pms.vo.SpuVo;
import com.atguigu.gmall.sms.vo.SkuSaleVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.gmall.common.bean.PageResultVo;
import com.atguigu.gmall.common.bean.PageParamVo;

import com.atguigu.gmall.pms.mapper.SpuMapper;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Service("spuService")
public class SpuServiceImpl extends ServiceImpl<SpuMapper, SpuEntity> implements SpuService {
    @Autowired
    private SpuDescMapper descMapper;
    @Autowired
    private SpuAttrValueService baseAttrService;
    @Autowired
    private SkuMapper skuMapper;
    @Autowired
    private SkuImagesService imagesService;
    @Autowired
    private SkuAttrValueService attrValueService;
    @Autowired
    private GmallSmsClient smsClient;
    @Autowired
    private SpuDescService descService;

    @Override
    public PageResultVo queryPage(PageParamVo paramVo) {
        IPage<SpuEntity> page = this.page(
                paramVo.getPage(),
                new QueryWrapper<SpuEntity>()
        );

        return new PageResultVo(page);
    }

    @Override
    public PageResultVo queryCategoryByCid(Long cid, PageParamVo pageParamVo) {
        QueryWrapper<SpuEntity> wrapper = new QueryWrapper<>();
        if (cid!=0){
            wrapper.eq("category_id",cid);
        }

        String key = pageParamVo.getKey();
        if (StringUtils.isNotBlank(key)) {
//            wrapper.eq("id",key).or().like("name",key);
            wrapper.and(t->t.eq("id",key).or().like("name",key));
        }
        IPage<SpuEntity> page = this.page(
                pageParamVo.getPage(),
                wrapper
        );
        return new PageResultVo(page);
    }

    @Override
    public void bigSave(SpuVo spu) {

        //保存spu
        //1  pms_spu
        Long spuId = saveSpu(spu);
        //2  pms_spu_desc
        this.descService.saveSpuDesc(spu, spuId);

        //3  pms_spu_attr_value
        saveBaseAttrs(spu, spuId);

        //保存sku
        saveSkus(spu, spuId);

    }

    private void saveSkus(SpuVo spu, Long spuId) {
        List<SkuVo> skus = spu.getSkus();
        if (CollectionUtils.isEmpty(skus)) {
            return;
        }
        skus.forEach(skuVo -> {
            //1  pms_sku
            skuVo.setSpuId(spuId);
            skuVo.setCatagoryId(spu.getCategoryId());
            skuVo.setBrandId(spu.getBrandId());
            List<String> images = skuVo.getImages();
            if (!CollectionUtils.isEmpty(images)){
                skuVo.setDefaultImage(StringUtils.isNotBlank(skuVo.getDefaultImage())?skuVo.getDefaultImage():images.get(0));
            }
            this.skuMapper.insert(skuVo);
            Long skuId =skuVo.getId();
            //2  pms_sku_images
            if (!CollectionUtils.isEmpty(images)) {
                imagesService.saveBatch( images.stream().map(image->{
                    SkuImagesEntity skuImagesEntity = new SkuImagesEntity();
                    skuImagesEntity.setSkuId(skuId);
                    skuImagesEntity.setUrl(image);
                    skuImagesEntity.setSort(0);
                    if (StringUtils.equals(image,skuVo.getDefaultImage())){
                        skuImagesEntity.setDefaultStatus(1);
                    }
                    return skuImagesEntity;
                }).collect(Collectors.toList()));
            }
            //3  pms_sku_attr_value
            List<SkuAttrValueEntity> saleAttrs = skuVo.getSaleAttrs();
            if (!CollectionUtils.isEmpty(saleAttrs)){
                saleAttrs.forEach(attr->{
                    attr.setSkuId(skuId);
                    attr.setSort(0);
                });
                this.attrValueService.saveBatch(saleAttrs);
            }
            //保存spu sms /  feign远程调用
            SkuSaleVo skuSaleVo = new SkuSaleVo();
            BeanUtils.copyProperties(skuVo,skuSaleVo);
            skuSaleVo.setSkuId(skuId);
            this.smsClient.saveSales(skuSaleVo);
            //1  sms_sku_bounds
            //2  sms_sku_full_reduction
            //3  sms_sku_ladder
        });
    }

    private void saveBaseAttrs(SpuVo spu, Long spuId) {
        List<SpuAttrValueVo> baseAttrs = spu.getBaseAttrs();
//        if (!CollectionUtils.isEmpty(baseAttrs)){
//            List<SpuAttrValueEntity> spuAttrValueEntities=new ArrayList<>();
//            baseAttrs.forEach(spuAttrValueVo -> {
//                SpuAttrValueEntity spuAttrValueEntity =
//                        new SpuAttrValueEntity();
//                BeanUtils.copyProperties(spuAttrValueVo,spuAttrValueEntity);
//                spuAttrValueEntity.setSpuId(spuId);
//                spuAttrValueEntity.setSort(0);
//                spuAttrValueEntities.add(spuAttrValueEntity);
//            });
//            this.baseAttrService.saveBatch(spuAttrValueEntities);
//        }

//        if (!CollectionUtils.isEmpty(baseAttrs)) {
//            List<SpuAttrValueEntity> spuAttrValueEntities =
//                    baseAttrs.stream().map(spuAttrValueVO -> {
//                spuAttrValueVO.setSpuId(spuId);
//                spuAttrValueVO.setSort(0);
//                return spuAttrValueVO;
//            }).collect(Collectors.toList());
//            this.baseAttrService.saveBatch(spuAttrValueEntities);
//        }
        if (!CollectionUtils.isEmpty(baseAttrs)) {
            this.baseAttrService.saveBatch( baseAttrs.stream().map(spuAttrValueVo -> {
                SpuAttrValueEntity spuAttrValueEntity = new SpuAttrValueEntity();
                BeanUtils.copyProperties(spuAttrValueVo,spuAttrValueEntity);
                spuAttrValueEntity.setSpuId(spuId);
                spuAttrValueEntity.setSort(0);
                return spuAttrValueEntity;
            }).collect(Collectors.toList()));
        }
    }



    private Long saveSpu(SpuVo spu) {
        spu.setCreateTime(new Date());
        spu.setUpdateTime(spu.getCreateTime());
        this.save(spu);
        return spu.getId();
    }

}