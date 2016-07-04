package com.haiyangroup.scampus.common;



import com.haiyangroup.scampus.base.BaseActivity;

import javax.inject.Singleton;

import dagger.Component;
/**
 * dagger2注解配置
 * @author 何伟杰
 * @version 1.0, 2016/5/11
 */
@Component(
        modules = AppModule.class
)//@Component: Components从根本上来说就是一个注入器，
// 也可以说是@Inject和@Module的桥梁，它的主要作用就是连接这两个部分。
// Components可以提供所有定义了的类型的实例，
// 比如：我们必须用@Component注解一个接口然后列出所有的@Modules组成该组件，
// 如 果缺失了任何一块都会在编译的时候报错。所有的组件都可以通过它的modules知道依赖的范围。
@Singleton
public interface AppComponent {
    BaseActivity inject(BaseActivity activity);
}
