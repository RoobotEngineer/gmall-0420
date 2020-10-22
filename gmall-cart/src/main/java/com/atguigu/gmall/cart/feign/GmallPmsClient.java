package com.atguigu.gmall.cart.feign;

import com.atguigu.gmall.pms.api.GmallPmsApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author wh
 * @user wh
 * @create 2020-10-15
 */
@FeignClient("pms-service")
public interface GmallPmsClient extends GmallPmsApi {
}
