package com.ilia.leek.util;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.ilia.leek.common.enums.ResultCode;
import com.ilia.leek.common.exception.BaseBusinessException;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Alice on 2021/5/11
 * @version 1.0
 * @since 1.0
 */
public class CustomBeanUtil {

    /**
     * 赋值类,跳过空属性
     * @param target
     * @param old
     */
    public static void pushParam(Object target, Object old) {
        if (ObjectUtil.hasEmpty(target, old)) {
            throw new BaseBusinessException(ResultCode.MAKE_FALSE);
        }
        BeanUtil.copyProperties(old, target, CustomBeanUtil.getNullPropertyNames(old));
    }


    /**
     * 获取bean中空的属性
     * @param source source
     * @return String[]
     */
    public static String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();
        for(java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) {
                emptyNames.add(pd.getName());
            }
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }
}
