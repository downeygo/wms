package com.imen.wms.util;

import java.lang.reflect.Method;

public class PermissionUtil {
    public static String buildExpression(Method m ){
        String className=m.getDeclaringClass().getName();
        String methodName=m.getName();
        return className+":"+methodName;
    }
}
