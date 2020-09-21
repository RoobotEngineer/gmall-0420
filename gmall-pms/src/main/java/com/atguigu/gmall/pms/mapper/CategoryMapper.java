package com.atguigu.gmall.pms.mapper;

import com.atguigu.gmall.pms.entity.CategoryEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品三级分类
 * 
 * @author wh
 * @email wh@atguigu.com
 * @date 2020-09-21 18:53:57
 */
@Mapper
public interface CategoryMapper extends BaseMapper<CategoryEntity> {
	
}
