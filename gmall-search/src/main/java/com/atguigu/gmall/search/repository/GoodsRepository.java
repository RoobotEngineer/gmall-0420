package com.atguigu.gmall.search.repository;

import com.atguigu.gmall.search.entity.Goods;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author wh
 * @user wh
 * @create 2020-09-27
 */
public interface GoodsRepository extends ElasticsearchRepository<Goods,Long> {

}
