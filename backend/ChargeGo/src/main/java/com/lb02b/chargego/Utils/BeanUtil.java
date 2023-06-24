package com.lb02b.chargego.Utils;

import cn.hutool.core.util.ReflectUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

public class BeanUtil {

    public static String[] getNullPropertyNames (Object source){
        var src = new BeanWrapperImpl(source);
        var pds = src.getPropertyDescriptors();

        var emptyNames = new HashSet<String>();
        for(var pd : pds) {
            var srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null || srcValue == ""){
                emptyNames.add(pd.getName());
            }
        }
        var result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

    public static <T, M> List<T> copyListProperties(List<M> sources, Class<T> beanClass) {
        if (Objects.isNull(sources) || Objects.isNull(beanClass) || sources.isEmpty()) {
            throw new IllegalArgumentException();
        }
        List<T> targets = new ArrayList<>(sources.size());
        for (M source : sources) {
            T t = ReflectUtil.newInstance(beanClass);;
            BeanUtils.copyProperties(source,t);
            targets.add(t);
        }
        return targets;
    }


}
