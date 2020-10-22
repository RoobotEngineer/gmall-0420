package com.atguigu.gmall.pms.mapper;
import com.atguigu.gmall.pms.entity.SkuAttrValueEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * sku销售属性&值
 * 
 * @author wh
 * @email wh@atguigu.com
 * @date 2020-09-21 18:53:57
 */
@Mapper
public interface SkuAttrValueMapper extends BaseMapper<SkuAttrValueEntity> {

    public List<SkuAttrValueEntity> querySaleAttrValuesBySpuId(Long spuId);

    public List<Map<String,Object>> querySkuIdMappingSaleAttrValueBySpuId(Long spuId);

}
