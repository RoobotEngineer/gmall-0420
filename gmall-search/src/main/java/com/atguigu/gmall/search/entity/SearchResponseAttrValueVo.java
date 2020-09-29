package com.atguigu.gmall.search.entity;

import lombok.Data;

import java.util.List;

/**
 * @author wh
 * @user wh
 * @create 2020-09-28
 */
@Data
public class SearchResponseAttrValueVo {

    private Long attrId;
    private String attrName;
    private List<String> attrValues;
}
