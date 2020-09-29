package com.atguigu.gmall.search.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;
import java.util.List;

/**
 * @author wh
 * @user wh
 * @create 2020-09-27
 */
@Data
@Document(indexName = "goods",type = "info",shards = 3,replicas = 2)
public class Goods {

    //搜索列表字段
    @Id
    @Field(type = FieldType.Long)
    private Long skuId;
    @Field(type = FieldType.Text,analyzer = "ik_max_word")
    private String title;
    @Field(type =FieldType.Keyword,index = false)
    private String subTitle;
    @Field(type =FieldType.Keyword,index = false)
    private String defaultImage;
    @Field(type = FieldType.Double)
    private Double price;


    //排序和筛选字段
    @Field(type = FieldType.Long)
    private Long sales;
    @Field(type = FieldType.Date)
    private Date createTime;
    @Field(type = FieldType.Boolean)
    private Boolean store=false;

    //聚合字段
    @Field(type = FieldType.Long)
    private Long brandId;
    @Field(type = FieldType.Keyword)
    private String brandName;
    @Field(type = FieldType.Keyword)
    private String logo;

    @Field(type = FieldType.Long)
    private Long categoryId;
    @Field(type = FieldType.Keyword)
    private String categoryName;

    @Field(type = FieldType.Nested)
    private List<SearchAttrValueVo> searchAttrs;
}
