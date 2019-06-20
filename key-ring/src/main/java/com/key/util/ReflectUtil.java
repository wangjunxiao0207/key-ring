package com.key.util;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ReflectUtil {
    // 通过名字获取类中的方法，不考虑重载情况
    public static Method getMethodByName(Class clazz, String name) {
        if(clazz == null || StringUtils.isBlank(name)) {
            throw new RuntimeException("参数错误");
        }
        Method[] methods = clazz.getMethods();
        for(Method method : methods) {
            if(method.getName().equals(name)) {
               return method;
            }
        }
        return null;
    }

    // 获取对象中的所有的字段对象
    public static Object[] getFieldObjects(Object object) {
        Field[] fields = object.getClass().getDeclaredFields();
        List<Object> fieldObjects = new ArrayList();
        try {
            for(Field field : fields) {
                field.setAccessible(true);
                fieldObjects.add(field.get(object));
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return fieldObjects.toArray();
    }

    public static String[] getFieldNames(Object object) {
        Field[] declaredFields = object.getClass().getDeclaredFields();
        String[] fieldNames = new String[declaredFields.length];
        for(int i = 0; i < declaredFields.length; ++i) {
            fieldNames[i] = declaredFields[i].getName();
        }
        return fieldNames;
    }

    public static Object newInstanceWithParam(Class clazz, Object param) throws IllegalAccessException, InstantiationException {
//        Object instance = clazz.newInstance();
//        Field[] declaredFields = clazz.getDeclaredFields();
//        Object[] paramFieldObjects = getFieldObjects(param);
//        for(Field field : declaredFields) {
//            field.setAccessible(true);
//            field.set(instance,);
//        }
        return null;
    }

    // 执行对象中的方法
    public static Object invoke(Object object, String methodName, Object param) {
        Method method = ReflectUtil.getMethodByName(object.getClass(), methodName);
        Object[] params = getFieldObjects(param);
        return invoke(object, methodName, params);
    }

    /**
     *
     * @param object     目标对象
     * @param methodName 目标对象的目标方法
     * @param params     参数
     * @return 如果原方法返回void, 则返回null
     */
    public static Object invoke(Object object, String methodName, Object[] params) {
        Method method = ReflectUtil.getMethodByName(object.getClass(), methodName);
        Object result = null;
        try {
            result = method.invoke(object, params);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }



}
