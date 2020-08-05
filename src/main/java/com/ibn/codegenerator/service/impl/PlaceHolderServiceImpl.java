package com.ibn.codegenerator.service.impl;

import com.google.common.collect.Maps;
import com.ibn.codegenerator.service.PlaceholderService;
import com.ibn.codegenerator.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @version 1.0
 * @description:
 * @projectName：code-generator
 * @see: com.ibn.codegenerator.service.impl
 * @author： RenBin
 * @createTime：2020/8/4 11:01
 */
@Service("placeHolderService")
public class PlaceHolderServiceImpl implements PlaceholderService {
    @Autowired
    private Environment environment;

    private static final Logger logger = LoggerFactory.getLogger(PlaceHolderServiceImpl.class);

    // 占位符匹配规则
    private static final Pattern placeholderPatter = Pattern.compile("\\#\\{(.{1,})\\}");

    @Override
    public void fillPlaceholderFromEnvironment(Object object) {
        if (null == object) {
            return;
        }
        Class objectClass = object.getClass();
        // 获取对象中的所有属性
        Field[] declaredFields = objectClass.getDeclaredFields();
        Method[] declaredMethods = objectClass.getDeclaredMethods();
        for (Field declaredField : declaredFields) {
            dealField(object, declaredMethods, declaredField);
        }
    }
    /**
     * @description: 处理属性
     * @author：RenBin
     * @createTime：2020/8/5 10:41
     */
    private void dealField(Object object, Method[] declaredMethods, Field declaredField) {
        String fieldName;
        String methodName;
        Method getter = null;
        fieldName = StringUtil.upperCaseFirstLatter(declaredField.getName());
        methodName = String.format("get%s", fieldName);
        for (Method declaredMethod : declaredMethods) {
            if (methodName.equals(declaredMethod.getName())) {
                getter=declaredMethod;
            }
        }
        if (null == getter) {
            return;
        }
        try {
            String valueStr;
            Object value = getter.invoke(object, null);
            if (value instanceof String) {
                replaceValue(object, declaredField, (String) value);
            } else if (value instanceof Map){
                Map<String,Object> valueMap= (Map<String, Object>) value;
                Set<Map.Entry<String, Object>> entries = valueMap.entrySet();
                for (Map.Entry<String, Object> entry : entries) {
                    Object entryValue = entry.getValue();
                    if (entryValue instanceof String) {
                        valueStr = this.replacePlaceholder((String) entryValue);
                        if (null != valueStr) {
                            entry.setValue(valueStr);
                        }
                    } else {
                        this.fillPlaceholderFromEnvironment(entryValue);
                    }
                }
            } else {
                this.fillPlaceholderFromEnvironment(value);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
    /**
     * @description: 设置值
     * @author：RenBin
     * @createTime：2020/8/5 11:33
     */
    private void replaceValue(Object object, Field declaredField, String value) throws IllegalAccessException {
        String valueStr= this.replacePlaceholder(value);
        if (null ==valueStr) {
            return;
        }
        declaredField.setAccessible(true);
        declaredField.set(object, valueStr);
    }

    /**
     * @description: 替换所有的占位符
     * @author：RenBin
     * @createTime：2020/7/22 10:28
     */
    private String replacePlaceholder(String value) {
        Matcher matcher = placeholderPatter.matcher(value);
        Map<String, String> placeholderMap = Maps.newHashMap();
        while (matcher.find()) {
            String group = matcher.group(1);
            if (null != placeholderMap.get(group)) {
               continue;
            }
            String property = environment.getProperty(group);
            placeholderMap.put(group, property);
        }
        if (placeholderMap.size() < 1) {
            return null;
        }
        Set<Map.Entry<String, String>> entries = placeholderMap.entrySet();
        for (Map.Entry<String, String> entry : entries) {
            value = value.replace(String.format("#{%s}", entry.getKey()), entry.getValue());
        }
        return value;
    }
}
