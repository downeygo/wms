package com.imen.wms.util;

import com.imen.wms.domain.Employee;
import com.opensymphony.xwork2.ActionContext;

import java.util.Set;

public class UserContext {

    public static void setEmployee(Employee employee) {
        if (employee != null) {
            ActionContext.getContext().getSession().put("USER_IN_SESSION", employee);
        } /*else {
            ActionContext.getContext().getSession().clear();
        }*/
    }

    public static void setPermissions(Set<String> permissions) {
            ActionContext.getContext().getSession().put("PERMISSIONS_IN_SESSION", permissions);
    }

    public static Employee getCurrentUser(){
        return (Employee) ActionContext.getContext().getSession().get("USER_IN_SESSION");
    }

    public static Set<String> getPermisssions(){
        return (Set<String>)ActionContext.getContext().getSession().get("PERMISSIONS_IN_SESSION");
    }

}
