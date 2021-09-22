package com.example.majiang.valid;

import java.lang.annotation.*;

/**
 * @Author kyle
 * @create 2021/9/22 15:05
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Hu {
    Class preClazz() default BasePreHuValid.class;
}
