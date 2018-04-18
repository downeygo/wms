package com.imen.wms.web.action;

import com.imen.wms.domain.Role;
import com.imen.wms.query.QueryObject;
import com.imen.wms.service.IPermissionService;
import com.imen.wms.service.IRoleService;
import com.imen.wms.service.ISystemMenuService;
import com.imen.wms.util.RequiredPermission;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import lombok.Getter;
import lombok.Setter;
import org.apache.struts2.ServletActionContext;

public class RoleAction extends BaseAction{
    private static final long serialVersionUID = 6887934604174049132L;
    @Setter
    private IRoleService roleService;
    @Setter
    private IPermissionService permissionService;
    @Setter
    private ISystemMenuService systemMenuService;
    @Getter
    private Role role=new Role();
    @Getter
    private QueryObject qo=new QueryObject();
    @RequiredPermission("角色列表")
    @InputConfig(methodName = "input")
    public String execute() throws Exception {
        ActionContext.getContext().put("pageResult",roleService.query(qo));
        return LIST;
    }

    @Override
    @RequiredPermission("角色新增")
    public String input() throws Exception {
        ActionContext.getContext().put("permissions",permissionService.listAll());
        ActionContext.getContext().put("role.permissions",roleService.listAll());
        ActionContext.getContext().put("menus",systemMenuService.listAll());
        ActionContext.getContext().put("role.menus",roleService.listAll());
        if(role.getId()!=null){
            role=roleService.get(role.getId());
        }
        return INPUT;
    }

    @RequiredPermission("角色保存或更新")
    public String saveOrUpdate() throws Exception{
        try {
            if(role.getId()==null){
                roleService.save(role);
                super.addActionMessage("保存成功");
            }else{
                roleService.update(role);
                super.addActionMessage("修改成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            super.addActionError("操作失败:"+e.getMessage());
        }
        return SUCCESS;
    }

    @RequiredPermission("角色删除")
    public String delete() throws Exception{
        roleService.delete(role.getId());
        ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
        ServletActionContext.getResponse().getWriter().print("删除成功");
        return NONE;
    }

    public void prepareSaveOrUpdate() throws Exception{
        role.getPermissions().clear();//清除第一次传来的权限
        role.getMenus().clear();//清除第一次传来的菜单，以免数据库重复
    }

}
