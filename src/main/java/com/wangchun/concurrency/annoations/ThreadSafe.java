package com.wangchun.concurrency.annoations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <pre>
 *    @author  : wangchun
 *    @time    : 2019/2/14 20:12
 *    desc    : 课程里用来标记【线程安全】的类或写法
 *    version : v1.0
 * </pre>
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface ThreadSafe {

    String value() default "";
}
