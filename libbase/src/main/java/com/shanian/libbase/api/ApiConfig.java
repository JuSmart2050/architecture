package com.shanian.libbase.api;

import com.shanian.libbase.api.impl.DefaultConfig;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ApiConfig {

    Class<? extends Api.Config> value() default DefaultConfig.class;
}
