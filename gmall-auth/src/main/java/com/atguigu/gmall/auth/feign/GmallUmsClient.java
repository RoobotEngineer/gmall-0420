package com.atguigu.gmall.auth.feign;

import com.atguigu.gmall.ums.api.GmallUmsApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author wh
 * @user wh
 * @create 2020-10-14
 */
@FeignClient("ums-service")
public interface GmallUmsClient extends GmallUmsApi {
}
