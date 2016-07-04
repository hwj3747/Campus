package com.haiyangroup.scampus.common;

import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;
/**
 * dagger2注解配置
 * @author 何伟杰
 * @version 1.0, 2016/5/11
 */
@Scope
@Retention(RUNTIME)//@Scope: Scopes可是非常的有用，Dagger2可以通过自定义注解限定注解作用域。
public @interface MyScope {}
