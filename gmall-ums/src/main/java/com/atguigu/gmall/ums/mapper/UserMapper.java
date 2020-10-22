package com.atguigu.gmall.ums.mapper;

import com.atguigu.gmall.ums.entity.UserEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户表
 * 
 * @author wh
 * @email wh@atguigu.com
 * @date 2020-10-13 17:17:52
 */
@Mapper
public interface UserMapper extends BaseMapper<UserEntity> {
	
}
