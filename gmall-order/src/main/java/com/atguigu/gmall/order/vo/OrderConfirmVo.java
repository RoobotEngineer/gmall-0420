package com.atguigu.gmall.order.vo;

import com.atguigu.gmall.oms.vo.OrderItemVo;
import com.atguigu.gmall.ums.entity.UserAddressEntity;
import lombok.Data;

import java.util.List;

/**
 * @author wh
 * @user wh
 * @create 2020-10-19
 */
@Data
public class OrderConfirmVo {
    // 收货地址列表
    private List<UserAddressEntity> addresses;

    // 送货清单，根据购物车页面传递过来的skuIds查询
    private List<OrderItemVo> items;

    // 用户的购物积分信息，ums_member表中的integration字段
    private Integer bounds;

    // 防重的唯一标识保证提交订单接口的幂等性
    private String orderToken;
}
