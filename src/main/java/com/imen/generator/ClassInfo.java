package com.imen.generator;

import com.imen.wms.domain.BaseDomain;
import lombok.Getter;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.List;

/**
 * 表示数据模型对象，封装了模板中占位符的数据
 */
@Getter
public class ClassInfo {
    private String basePkg;//基础包名
    private String className;//domain简单类名
    private String objectName;//对象名
    private List<String> props=new ArrayList<>();//对象的属性

    public ClassInfo(Class<?> clz) throws Exception {
        int lastIndex=clz.getPackage().getName().lastIndexOf(".");
        this.basePkg = clz.getPackage().getName().substring(0,lastIndex);
        this.className = clz.getSimpleName();
        this.objectName=clz.getSimpleName().substring(0,1).toLowerCase()+clz.getSimpleName().substring(1);
        BeanInfo beanInfo = Introspector.getBeanInfo(clz, BaseDomain.class);
        PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor pd : pds) {
            props.add(pd.getName());
        }
    }

    public static void main(String[] args) throws Exception{
    }
}
