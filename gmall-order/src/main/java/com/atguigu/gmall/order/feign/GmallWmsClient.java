package com.atguigu.gmall.order.feign;

import com.atguigu.gmall.wms.api.GmallWmsApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author wh
 * @user wh
 * @create 2020-10-15
 */
@FeignClient("wms-service")
public interface GmallWmsClient extends GmallWmsApi {
}
