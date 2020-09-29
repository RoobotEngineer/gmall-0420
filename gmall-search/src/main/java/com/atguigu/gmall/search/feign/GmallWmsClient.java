package com.atguigu.gmall.search.feign;

import com.atguigu.gmall.wms.api.GmallWmsApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author wh
 * @user wh
 * @create 2020-09-27
 */
@FeignClient("wms-service")
public interface GmallWmsClient extends GmallWmsApi {

}
