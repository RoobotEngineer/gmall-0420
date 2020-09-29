package com.atguigu.gmall.search.entity;

import com.atguigu.gmall.pms.entity.BrandEntity;
import com.atguigu.gmall.pms.entity.CategoryEntity;
import io.swagger.models.auth.In;
import lombok.Data;

import java.util.List;

/**
 * @author wh
 * @user wh
 * @create 2020-09-28
 */
@Data
public class SearchResponseVo {

//封装品牌过滤条件
    private List<BrandEntity> brands;
    //封装分类过滤条件
    private List<CategoryEntity> categories;
    //封装规格参数过滤条件
    private List<SearchResponseAttrValueVo> filters;

    private Integer pageNum;
    private Integer pageSize;
    private Long total;
    private List<Goods> goodsList;
}
