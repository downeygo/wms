package com.imen.wms.web.interceptor;

import com.imen.wms.domain.Employee;
import com.imen.wms.util.PermissionUtil;
import com.imen.wms.util.RequiredPermission;
import com.imen.wms.util.UserContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

import java.lang.reflect.Method;
import java.util.Set;

public class SecurityInterceptor extends AbstractInterceptor {
    private static final long serialVersionUID = 8119604895347677349L;

    @Override
    public String intercept(ActionInvocation actionInvocation) throws Exception {
        Employee current= UserContext.getCurrentUser();
        //有用户并且是超级管理员就放行
        if(current!=null && current.isAdmin()){
            actionInvocation.invoke();
        }
        //判断当前请求的Action方法上是否存在RequiredPermission标签，如果没有放行
        String methodName =actionInvocation.getProxy().getMethod();
        Method actionMethod=actionInvocation.getProxy().getAction().getClass().getDeclaredMethod(methodName);
        RequiredPermission rp=actionMethod.getAnnotation(RequiredPermission.class);
        if(rp==null){
            actionInvocation.invoke();
        }
        //获取当前请求Action方法对应的权限表达式
        String expression= PermissionUtil.buildExpression(actionMethod);
        //判断当前session中是否存在Action方法上的权限表达式
        Set<String> permissions= UserContext.getPermisssions();
        //如果有则放行
        if(permissions.contains(expression) && permissions!=null){
            actionInvocation.invoke();
        }
        return "nopermissions";
    }
}
