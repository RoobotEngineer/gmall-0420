package com.atguigu.gmall.pms.service.impl;

import com.atguigu.gmall.pms.entity.SpuAttrValueEntity;
import com.atguigu.gmall.pms.entity.SpuDescEntity;
import com.atguigu.gmall.pms.mapper.SpuDescMapper;
import com.atguigu.gmall.pms.service.SpuAttrValueService;
import com.atguigu.gmall.pms.vo.SpuAttrValueVo;
import com.atguigu.gmall.pms.vo.SpuVo;
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
import com.atguigu.gmall.pms.entity.SpuEntity;
import com.atguigu.gmall.pms.service.SpuService;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Service("spuService")
public class SpuServiceImpl extends ServiceImpl<SpuMapper, SpuEntity> implements SpuService {
    @Autowired
    private SpuDescMapper descMapper;
    @Autowired
    private SpuAttrValueService baseAttrService;


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
        spu.setCreateTime(new Date());
        spu.setUpdateTime(spu.getCreateTime());
        this.save(spu);
        Long spuId=spu.getId();
        //2  pms_spu_desc
        if (!CollectionUtils.isEmpty(spu.getSpuImages())) {
            SpuDescEntity spuDescEntity = new SpuDescEntity();
            spuDescEntity.setSpuId(spuId);
            spuDescEntity.setDecript(StringUtils.join(spu.getSpuImages(),","));
            this.descMapper.insert(spuDescEntity);
        }

        //3  pms_spu_attr_value
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

        if (!CollectionUtils.isEmpty(baseAttrs)) {
            List<SpuAttrValueEntity> spuAttrValueEntities = baseAttrs.stream().map(spuAttrValueVO -> {
                spuAttrValueVO.setSpuId(spuId);
                spuAttrValueVO.setSort(0);
                return spuAttrValueVO;
            }).collect(Collectors.toList());
            this.baseAttrService.saveBatch(spuAttrValueEntities);
        }

        //保存sku
        //1  pms_sku
        //2  pms_sku_images
        //3  pms_sku_attr_value
        //保存spu   sms
        //1  sms_sku_bounds
        //2  sms_sku_full_reduction
        //3  sms_sku_ladder
    }

}