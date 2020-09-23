package com.atguigu.gmall.pms.feign;
import com.atguigu.gmall.sms.api.GmallSmsApi;
import org.springframework.cloud.openfeign.FeignClient;
/**
 * @author wh
 * @user wh
 * @create 2020-09-23
 */
@FeignClient("sms-service")
public interface GmallSmsClient extends GmallSmsApi {
}
