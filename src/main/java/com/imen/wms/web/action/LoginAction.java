package com.imen.wms.web.action;

import com.imen.wms.domain.Employee;
import com.imen.wms.service.IEmployeeService;
import lombok.Getter;
import lombok.Setter;

public class LoginAction extends BaseAction{
    private static final long serialVersionUID = -2196978369167152586L;
    @Setter
    private String username;
    @Setter
    private String password;

    @Setter
    private IEmployeeService employeeService;
    @Getter
    private Employee employee=new Employee();
    @Override
    public String execute() throws Exception {
        employee=employeeService.checkLogin(username,password);
        if(employee==null){
            System.out.println(username+password);
            super.addActionMessage("用户名或密码错误！");
            return LOGIN;
        }
        return SUCCESS;
    }
}
