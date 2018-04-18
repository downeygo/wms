package com.imen.wms.web.action;

import com.imen.wms.domain.Department;
import com.imen.wms.domain.Employee;
import com.imen.wms.query.DepartmentQueryObject;
import com.imen.wms.query.QueryObject;
import com.imen.wms.service.IDepartmentService;
import com.imen.wms.util.RequiredPermission;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import lombok.Getter;
import lombok.Setter;
import org.apache.struts2.ServletActionContext;

public class DepartmentAction extends BaseAction {
    private static final long serialVersionUID = 2551022383289146466L;
    @Getter
    private Department department = new Department();
    @Setter
    private IDepartmentService departmentService;
    @Getter
    private Employee employee = new Employee();
    @Getter
    private QueryObject qo = new DepartmentQueryObject();

    @RequiredPermission("部门列表")
    @InputConfig(methodName = "input")
    public String execute() throws Exception {
        ActionContext.getContext().put("pageResult", departmentService.query(qo));
        ActionContext.getContext().put("department", departmentService.listAll());
        return LIST;
    }

    @Override
    @RequiredPermission("部门新增")
    public String input() throws Exception {
        if (department.getId() != null) {
            department = departmentService.get(department.getId());
        }
        return INPUT;
    }

    @RequiredPermission("部门保存或更新")
    public String saveOrUpdate() throws Exception {
        try {
            if (department.getId() == null) {
                departmentService.save(department);
                super.addActionMessage("保存成功");
            } else {
                departmentService.update(department);
                super.addActionMessage("修改成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            super.addActionError("操作失败:" + e.getMessage());
        }
        return SUCCESS;
    }

    @RequiredPermission("部门删除")
    public String delete() throws Exception {
        departmentService.delete(department.getId());
        ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
        ServletActionContext.getResponse().getWriter().print("删除成功");
        return NONE;
    }
}
