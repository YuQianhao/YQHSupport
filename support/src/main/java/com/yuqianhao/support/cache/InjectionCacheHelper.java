package com.yuqianhao.support.cache;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class InjectionCacheHelper {
    private InjectionCacheHelper() {}
    /**
     * 将对象按照序列化成String，如果不想被序列化进去的成员变量需要时用 {@link CacheSkip}标注
     * @param object 要序列化的对象
     * @return 序列化完成的字符串
     * */
    public static String serializationObject(Object object) throws IllegalAccessException, JSONException {
        JSONObject jsonObject=new JSONObject();
        Field[] fields=screeningField(object);
        for(Field tmpField:fields){
            tmpField.setAccessible(true);
            jsonObject.put(tmpField.getName(),tmpField.get(object));
        }
        return jsonObject.toString();
    }

    public static JSONObject serializationObjectToJSONObject(Object object) throws JSONException, IllegalAccessException {
        return new JSONObject(serializationObject(object));
    }

    public static Map<String,String> serializationObjectTpMap(Object object) throws IllegalAccessException {
        Map<String,String> resultMap=new HashMap<>();
        Field[] fields=screeningField(object);
        for(Field tmpField:fields){
            tmpField.setAccessible(true);
            resultMap.put(tmpField.getName(),tmpField.get(object).toString());
        }
        return resultMap;
    }

    /**
     * 反序列化对象，如果不想被反序列化进行初始化的成员变量需要时用 {@link CacheSkip}标注
     * @param object 要初始化的对象
     * @param jsonObject 要反序列化的字符串的封装
     * */
    public static void deserializationObject(Object object,JSONObject jsonObject) throws IllegalAccessException, JSONException {
        Field[] fields=screeningField(object);
        for(Field tmpField:fields){
            tmpField.setAccessible(true);
            if(!jsonObject.has(tmpField.getName())){
                continue;
            }
            if(jsonObject.get(tmpField.getName()).toString().equals("null")){
                continue;
            }
            tmpField.set(object,jsonObject.get(tmpField.getName()));
        }
    }


    private static final Field[] screeningField(Object object){
        ArrayList<Field> fieldArrayList=new ArrayList<>();
        Class objectClass=object.getClass();
        Field[] fields=objectClass.getDeclaredFields();
        for(Field tmpField:fields){
            CacheSkip cacheSkip=tmpField.getAnnotation(CacheSkip.class);
            if(cacheSkip==null){
                fieldArrayList.add(tmpField);
            }
        }
        Field[] resultArray=new Field[fieldArrayList.size()];
        fieldArrayList.toArray(resultArray);
        return resultArray;
    }

}
