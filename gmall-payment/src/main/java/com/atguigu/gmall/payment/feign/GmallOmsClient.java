package com.atguigu.gmall.payment.feign;

import com.atguigu.gmall.oms.api.GmallOmsApi;
import org.springframework.cloud.openfeign.FeignClient;


/**
 * @author wh
 * @user wh
 * @create 2020-10-20
 */
@FeignClient("oms-service")
public interface GmallOmsClient extends GmallOmsApi {

}
