package com.atguigu.gmall.item.vo;

import com.atguigu.gmall.pms.entity.CategoryEntity;
import com.atguigu.gmall.pms.entity.SkuImagesEntity;
import com.atguigu.gmall.pms.vo.ItemGroupVo;
import com.atguigu.gmall.pms.vo.SaleAttrValueVo;
import com.atguigu.gmall.sms.vo.ItemSaleVo;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @author wh
 * @user wh
 * @create 2020-10-12
 */
@Data
public class ItemVo {
    //三级分类
    private List<CategoryEntity> categories;;
    //品牌
    private Long brandId;
    private String brandName;

    //spu
    private Long spuId;
    private String spuName;

    //sku
    private Long skuId;
    private String title;
    private String subTitle;
    private BigDecimal price;
    private Integer weight;
    private String defaultImage;

    //sku图片
    private List<SkuImagesEntity> images;

    //营销信息
    private List<ItemSaleVo> sales;
    //库存信息
    private Boolean store = false;
    //sku所属spu下的所有sku的销售属性
    private List<SaleAttrValueVo> saleAttrs;

    // 当前sku的销售属性：{3:'白色',8:'8G',9:'128G'}
    private Map<Long, String> saleAttr;
    // sku列表：{'白色,8G,128G': 4, '白色,8G,256G': 5, '白色,8G,512G': 6, '白色,12G,128G': 7}
    private String skuJsons;

    // spu的海报信息
    private List<String> spuImages;

    // 规格参数组及组下的规格参数(带值)
    private List<ItemGroupVo> groups;



}
