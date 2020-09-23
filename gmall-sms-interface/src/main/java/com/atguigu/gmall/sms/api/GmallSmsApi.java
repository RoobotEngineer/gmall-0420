package com.atguigu.gmall.sms.api;

import com.atguigu.gmall.common.bean.ResponseVo;
import com.atguigu.gmall.sms.vo.SkuSaleVo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author wh
 * @user wh
 * @create 2020-09-23
 */
public interface GmallSmsApi {
    @PostMapping("sms/skubounds/sales")
    public ResponseVo saveSales(@RequestBody SkuSaleVo skuSaleVo);


}
