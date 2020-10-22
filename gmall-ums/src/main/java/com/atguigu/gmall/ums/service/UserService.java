package com.atguigu.gmall.ums.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.atguigu.gmall.common.bean.PageResultVo;
import com.atguigu.gmall.common.bean.PageParamVo;
import com.atguigu.gmall.ums.entity.UserEntity;
import org.springframework.ui.Model;

import java.util.Map;

/**
 * 用户表
 *
 * @author wh
 * @email wh@atguigu.com
 * @date 2020-10-13 17:17:52
 */
public interface UserService extends IService<UserEntity> {

    PageResultVo queryPage(PageParamVo paramVo);

    Boolean checkData(String data, Integer type);


    UserEntity queryUser(String loginName, String password);

    void register(UserEntity userEntity, String code);
}

