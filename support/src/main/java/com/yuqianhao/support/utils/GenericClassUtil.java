package com.yuqianhao.support.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class GenericClassUtil {
    private GenericClassUtil(){}

    /**
     * 获取一个类的泛型参数数组
     * @param clazz 要获取的类的Class引用
     * @return 如果这个类拥有泛型参数,则返回这个类的泛型参数列表,否则返回null
     * */
    public static final Type[] getClassGenericTypeArray(Class clazz){
        return getParameterizedTypeArray(clazz.getGenericSuperclass());
    }


    /**
     * 获取一个接口类的泛型参数数组
     * @param clazz 要获取的接口的Class的引用
     * @param index 要获取的接口的索引,一个类可以实现多个接口,所以这里需要传入一个接口的实现索引
     * @return 如果这个类拥有泛型参数,则返回这个类的泛型参数列表,否则返回null
     * */
    public static final Type[] getInterfaceGenericTypeArray(Class clazz,int index){
        return getParameterizedTypeArray(clazz.getGenericInterfaces()[index]);
    }


    private static final Type[] getParameterizedTypeArray(Type type){
        if(type instanceof ParameterizedType){
            return ((ParameterizedType)type).getActualTypeArguments();
        }else{
            return null;
        }
    }


    public static Map<String, Object> object2Map(Object obj) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (obj == null) {
            return null;
        }
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            String fieldName = field.getName();
            if (getValueByFieldName(fieldName, obj) != null)
                map.put(fieldName, getValueByFieldName(fieldName, obj));
        }
        return map;

    }

    private static Object getValueByFieldName(String fieldName, Object object) {
        String firstLetter = fieldName.substring(0, 1).toUpperCase();
        String getter = "get" + firstLetter + fieldName.substring(1);
        try {
            Method method = object.getClass().getMethod(getter, new Class[]{});
            Object value = method.invoke(object, new Object[]{});
            return value;
        } catch (Exception e) {
            return null;
        }

    }

}
