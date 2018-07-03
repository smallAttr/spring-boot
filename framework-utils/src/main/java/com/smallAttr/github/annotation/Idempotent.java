package com.smallAttr.github.annotation;

import java.lang.annotation.*;

/**
 * @Author: chenGang
 * @Date: 2018/7/3 下午1:55
 * @Description:
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Idempotent {

    /**
     * declare custom annotation. you must guarantee the key unique..
     * and for example:
     * the key = "module-operationType-columnListStr-idempotent"
     * @return
     */

    // belong to which module
    String module() default "";

    // operation type
    String operationType() default "";

    // Multiple parameters combined into string. support expression language
    String columnListStr() default "";

    // expire time (ms)
    int expireMillis() default 60000;
}
