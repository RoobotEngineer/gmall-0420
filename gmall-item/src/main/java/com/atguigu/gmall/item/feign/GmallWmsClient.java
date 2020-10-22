package com.atguigu.gmall.item.feign;

import com.atguigu.gmall.wms.api.GmallWmsApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author wh
 * @user wh
 * @create 2020-10-12
 */
@FeignClient("wms-service")
public interface GmallWmsClient extends GmallWmsApi {
}
