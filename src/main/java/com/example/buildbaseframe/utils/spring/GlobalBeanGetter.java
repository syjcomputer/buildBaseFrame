package com.example.buildbaseframe.utils.spring;

import com.example.buildbaseframe.BuildBaseFrameApplication;

/**
 * <b>全局 spring bean 获取器</b>
 * <p>
 *     为了解决 data bean 通过 new 实例化，无法实现全局 bean 依赖注入的问题
 * </p>
 *
 * @author syj
 * @version 1.0
 */
public interface GlobalBeanGetter {

    /**
     * 获取 spring 全局单例 bean
     * 在不能使用new的地方使用
     * 例如：SomeBean someBean = globalBeanGetter.getInstance(SomeBean.class);
     */
    default <T> T getInstance(Class<T> tClass) {
        return BuildBaseFrameApplication.getContext().getBean(tClass);
    }

}
