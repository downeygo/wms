package com.imen.wms.web.interceptor;

import com.imen.wms.util.UserContext;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class CheckLoginInterceptor extends AbstractInterceptor{
    @Override
    public String intercept(ActionInvocation actionInvocation) throws Exception {
        Object user= UserContext.getCurrentUser();
        if(user==null){
            return Action.LOGIN;
        }
        //不为空就放行
        return actionInvocation.invoke();
    }
}
