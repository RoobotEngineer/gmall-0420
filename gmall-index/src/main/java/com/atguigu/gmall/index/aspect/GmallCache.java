package com.atguigu.gmall.index.aspect;

import java.lang.annotation.*;

/**
 * @author wh
 * @user wh
 * @create 2020-10-11
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface GmallCache {
    //缓存key的前缀
    String prefix() default "";
    //分布式锁的前缀
    String lock() default "lock:";
    //缓存的超时时间，默认分钟
    int timeout() default 5;
    //避免缓存雪崩，给缓存添加随机值
    int random() default 5;

}
