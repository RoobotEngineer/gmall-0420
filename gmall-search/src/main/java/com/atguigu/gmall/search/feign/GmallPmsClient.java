package com.atguigu.gmall.search.feign;

import com.atguigu.gmall.pms.api.GmallPmsApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author wh
 * @user wh
 * @create 2020-09-27
 */
@FeignClient("pms-service")
public interface GmallPmsClient extends GmallPmsApi {

}
