package com.stefanini.taskmanager.utils;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class ReflectionUtil {

    public static Map getAllPrivateFieldsValues(Object object) throws IllegalAccessException, IllegalArgumentException {

        Map fieldsMap = new HashMap();
        Field[] objFields = object.getClass().getDeclaredFields();

        for (int i = 0; i < objFields.length; i++) {
            String fieldName = objFields[i].getName();
            objFields[i].setAccessible(true);

            Object fieldValue = objFields[i].get(object);
            fieldsMap.put(fieldName, fieldValue);
        }
        return fieldsMap;
    }

    public static <T extends Annotation> T getMethodAnnotation(JoinPoint joinPoint, Class<T> annotationClass) throws NoSuchMethodException {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        if(method.isAnnotationPresent(annotationClass)) {
            return method.getAnnotation(annotationClass);
        } else {
            return null;
        }
    }
}
