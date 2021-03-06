package com.atguigu.gmall.oms.listener;

import com.atguigu.gmall.oms.mapper.OrderMapper;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author wh
 * @user wh
 * @create 2020-10-20
 */
@Component
public class OrderListener {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private OrderMapper orderMapper;
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "ORDER_DISABLE_QUEUE", durable = "true"),
            exchange = @Exchange(value = "ORDER_EXCHANGE", ignoreDeclarationExceptions = "true", type = ExchangeTypes.TOPIC),
            key = {"order.disable"}
    ))
    public void disableOrder(String orderToken, Channel channel, Message message) throws IOException {

        // 如果订单状态更新为无效订单成功，发送消息给wms解锁库存
        this.orderMapper.updateStatus(orderToken,0,5);
        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
    }

    @RabbitListener(queues = "ORDER_DEAD_QUEUE")
    public void closeOrder(String orderToken, Channel channel, Message message) throws IOException {

        if (this.orderMapper.updateStatus(orderToken, 0, 4) == 1) {
            this.rabbitTemplate.convertAndSend("ORDER_EXCHANGE", "stock.unlock", orderToken);
        }
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }


}
