package com.atguigu.gmall.pms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.atguigu.gmall.common.bean.PageResultVo;
import com.atguigu.gmall.common.bean.PageParamVo;
import com.atguigu.gmall.pms.entity.CategoryEntity;

import java.util.List;
import java.util.Map;

/**
 * 商品三级分类
 *
 * @author wh
 * @email wh@atguigu.com
 * @date 2020-09-21 18:53:57
 */
public interface CategoryService extends IService<CategoryEntity> {

    PageResultVo queryPage(PageParamVo paramVo);


    List<CategoryEntity> queryCategoriesByPid(Long pid);

    List<CategoryEntity> queryCategoryLvl2WithSubsByPid(Long pid);

    List<CategoryEntity> queryCategoriesByCid3(Long cid3);
}

