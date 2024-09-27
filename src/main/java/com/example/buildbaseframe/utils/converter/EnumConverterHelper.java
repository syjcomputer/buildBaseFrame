package com.example.buildbaseframe.utils.converter;

import lombok.SneakyThrows;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.function.Function;

/**
 * <b>枚举转换帮助类</b>
 *
 * @author lq
 * @version 1.0
 */
public class EnumConverterHelper {

    public static <T extends Enum<?>> T integerToEnum(Integer value, Class<T> enumClass) {
        return objToEnum(value, enumClass, "ordinal");  // ordinal是枚举类型的序数
    }

    public static <T extends Enum<?>> T stringToEnum(String value, Class<T> enumClass) {
        return objToEnum(value, enumClass, "name"); // name是枚举类型的名字
    }

    @SneakyThrows({NoSuchMethodException.class, InvocationTargetException.class, IllegalAccessException.class})
    public static <T extends Enum<?>> T objToEnum(Object value, Class<T> enumClass, String methodName) {
        if (value == null) {
            return null;
        }
        Method method = enumClass.getMethod(methodName);
        for (var e : enumClass.getEnumConstants()) {
            if (method.invoke(e).equals(value)) {
                return e;
            }
        }
        return null;
    }


    // Function<T, Object> toObjFunc是一个函数接口，它接受一个泛型类型T的参数，并返回一个Object类型的结果
    // <T extends Enum<?>>表示T是一个泛型类型，它必须是枚举类型的子类型；T表示方法的返回类型；
    public static <T extends Enum<?>> T objToEnum(Object value, Class<T> enumClass, Function<T, Object> toObjFunc) {
        if (value == null) {
            return null;
        }
        for (var e : enumClass.getEnumConstants()) {
            if (toObjFunc.apply(e).equals(value)) {
                return e;
            }
        }
        return null;
    }

}
