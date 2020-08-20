package com.example.asm;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

/**
 * Create by yiyonghao on 2020-08-20
 * Email: yiyonghao@bytedance.com
 */
@Target(METHOD)
@Retention(RetentionPolicy.CLASS)
public @interface Cat {
}
