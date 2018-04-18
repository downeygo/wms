package com.imen.wms.web.action;

import com.imen.wms.domain.Employee;
import com.imen.wms.query.EmployeeQueryObject;
import com.imen.wms.query.QueryObject;
import com.imen.wms.service.IDepartmentService;
import com.imen.wms.service.IEmployeeService;
import com.imen.wms.service.IRoleService;
import com.imen.wms.service.ISystemMenuService;
import com.imen.wms.util.RequiredPermission;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import lombok.Getter;
import lombok.Setter;
import org.apache.struts2.ServletActionContext;

import java.util.ArrayList;
import java.util.List;

public class EmployeeAction extends BaseAction {
    private static final long serialVersionUID = -4235573664069119021L;
    @Getter
    private Employee employee = new Employee();
    @Setter
    private IEmployeeService employeeService;
    @Setter
    private IDepartmentService departmentService;
    @Setter
    private IRoleService roleService;
    @Getter
    private QueryObject qo = new EmployeeQueryObject();
    @Setter
    private List<Long> ids = new ArrayList<>();
    @RequiredPermission("员工列表")
    @InputConfig(methodName = "input")
    public String execute() throws Exception {
        try {
            ActionContext.getContext().put("pageResult", employeeService.query(qo));
            ActionContext.getContext().put("depts", departmentService.listAll());
        } catch (Exception e) {
            e.printStackTrace();
            super.addActionError(e.getMessage());
        }
        return LIST;
    }

    @Override
    @RequiredPermission("员工编辑")
    public String input() throws Exception {
        ActionContext.getContext().put("depts", departmentService.listAll());
        ActionContext.getContext().put("roles", roleService.listAll());
        if (employee.getId() != null) {
            employee = employeeService.get(employee.getId());
        }
        return INPUT;
    }

    @RequiredPermission("员工保存或更新")
    public String saveOrUpdate() throws Exception {
        try {
            if (employee.getId() == null) {
                employeeService.save(employee);
                super.addActionMessage("添加成功");
            } else {
                employeeService.update(employee);
                super.addActionMessage("修改成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            super.addActionError("操作失败:"+e.getMessage());
        }
        return SUCCESS;
    }

    @RequiredPermission("员工删除")
    public String delete() throws Exception {
        employeeService.delete(employee.getId());
        ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
        ServletActionContext.getResponse().getWriter().print("删除成功");
        return NONE;
    }

    @RequiredPermission("员工批量删除")
    public String batchDelete() throws Exception{
        if (ids.size() > 0) {
            employeeService.batchDelete(ids);
        }
        return NONE;
    }

    //防止更新信息时密码丢失
    public void prepareSaveOrUpdate() throws Exception {
        if (employee.getId() != null) {
            employee = employeeService.get(employee.getId());
            employee.setDept(null);//打破级联：解决更新时出错
            employee.getRoles().clear();
        }
    }
}
