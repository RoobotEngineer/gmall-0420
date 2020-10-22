package com.atguigu.gmall.item.feign;

import com.atguigu.gmall.sms.api.GmallSmsApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author wh
 * @user wh
 * @create 2020-10-12
 */
@FeignClient("sms-service")
public interface GmallSmsClient extends GmallSmsApi {
}
