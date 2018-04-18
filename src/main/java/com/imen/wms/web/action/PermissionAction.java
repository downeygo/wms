package com.imen.wms.web.action;


import com.imen.wms.domain.Permission;
import com.imen.wms.query.QueryObject;
import com.imen.wms.service.IPermissionService;
import com.imen.wms.util.RequiredPermission;
import com.opensymphony.xwork2.ActionContext;
import lombok.Getter;
import lombok.Setter;
import org.apache.struts2.ServletActionContext;

public class PermissionAction extends BaseAction{
    private static final long serialVersionUID = -99030989046336288L;
    @Setter
    private IPermissionService permissionService;
    @Getter
    private QueryObject qo=new QueryObject();
    @Getter
    private Permission permission=new Permission();
    @RequiredPermission("权限列表")
    public String execute() throws Exception {
        ActionContext.getContext().put("pageResult",permissionService.query(qo));
        return LIST;
    }

    @RequiredPermission("权限加载")
    public String reload() throws Exception {
        permissionService.reload();
        return NONE;
    }

    @RequiredPermission("权限删除")
    public String delete() throws Exception{
        permissionService.delete(permission.getId());
        ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
        ServletActionContext.getResponse().getWriter().print("删除成功");
        return NONE;
    }
}
