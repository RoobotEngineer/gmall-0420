package com.atguigu.gmall.payment.controller;

import com.atguigu.gmall.common.bean.UserInfo;
import com.atguigu.gmall.common.exception.OrderException;
import com.atguigu.gmall.oms.entity.OrderEntity;
import com.atguigu.gmall.payment.interceptor.LoginInterceptor;
import com.atguigu.gmall.payment.service.PaymentService;
import com.atguigu.gmall.payment.vo.PayAsyncVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author wh
 * @user wh
 * @create 2020-10-20
 */
@Controller
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping("pay.html")
    public String toPay(@RequestParam("orderToken")String orderToken, Model model){
        OrderEntity orderEntity=this.paymentService.queryOrderByToken(orderToken);
//        UserInfo userInfo = LoginInterceptor.getUserInfo();
//        Long userId = userInfo.getUserId();
//        if (orderEntity==null||orderEntity.getUserId()!=userId||orderEntity.getStatus()!=0){
//            throw new OrderException("非法参数");
//        }
        model.addAttribute("orderEntity",orderEntity);
        return "pay";
    }

    /**
     * 支付宝支付同步回调接口
     * 仅仅跳转到支付成功页面即可
     * @return
     */
    @GetMapping("pay/ok")
    public String payOk(PayAsyncVo payAsyncVo){
        String out_trade_no = payAsyncVo.getOut_trade_no();
        //查询订单，回显订单信息
        return "paysuccess";
    }
    /*
    * 异步回调接口  ：修改订单状态并减库存*/
    @PostMapping("pay/success")
    @ResponseBody
    public String paySuccess(PayAsyncVo payAsyncVo){
        System.out.println("异步回调成功====================================" + payAsyncVo);


        //6.响应数据，告诉支付宝处理成功
        return "success";
    }


}
