package com.atguigu.gmall.order.feign;

import com.atguigu.gmall.cart.api.GmallCartApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author wh
 * @user wh
 * @create 2020-10-19
 */
@FeignClient("cart-service")
public interface GmallCartClient extends GmallCartApi {
}
