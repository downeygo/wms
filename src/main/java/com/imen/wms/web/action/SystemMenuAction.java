package com.imen.wms.web.action;

import com.alibaba.fastjson.JSON;
import com.imen.wms.domain.SystemMenu;
import com.imen.wms.query.SystemMenuQueryObject;
import com.imen.wms.service.ISystemMenuService;
import com.imen.wms.util.RequiredPermission;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import lombok.Getter;
import lombok.Setter;
import org.apache.struts2.ServletActionContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SystemMenuAction extends BaseAction {
    private static final long serialVersionUID = 6887934604174049132L;
    @Setter
    private ISystemMenuService systemMenuService;
    @Getter
    private SystemMenu systemMenu = new SystemMenu();
    @Getter
    private SystemMenuQueryObject qo = new SystemMenuQueryObject();

    @RequiredPermission("系统菜单列表")
    @InputConfig(methodName = "input")
    public String execute() throws Exception {
        if (systemMenu.getId() != null) {
            ActionContext.getContext().put("menus", systemMenuService.listMenu(systemMenu.getId()));
        }
        ActionContext.getContext().put("pageResult", systemMenuService.query(qo));
        return LIST;
    }

    @Override
    @RequiredPermission("系统菜单编辑")
    public String input() throws Exception {
        if (qo.getParentId() > 0) {
            ActionContext.getContext().put("parentName", systemMenuService.get(qo.getParentId()).getName());
        } else {
            ActionContext.getContext().put("parentName", "根目录");
        }
        if (systemMenu.getId() != null) {
            systemMenu = systemMenuService.get(systemMenu.getId());
        }
        return INPUT;
    }

    @RequiredPermission("系统菜单保存或更新")
    public String saveOrUpdate() throws Exception {
        try {
            if (qo.getParentId() > 0) {
                SystemMenu parent = systemMenuService.get(qo.getParentId());
                systemMenu.setParent(parent);
            }
            if (systemMenu.getId() == null) {
                systemMenuService.save(systemMenu);
                super.addActionMessage("保存成功");
            } else {
                systemMenuService.update(systemMenu);
                super.addActionMessage("修改成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            super.addActionError("操作失败:" + e.getMessage());
        }
        return SUCCESS;
    }

    @RequiredPermission("系统菜单删除")
    public String delete() throws Exception {
        ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
        if (!systemMenuService.hasChidMenu(systemMenu.getId())) {
            systemMenuService.delete(systemMenu.getId());
            ServletActionContext.getResponse().getWriter().print("删除成功");
        } else {
            ServletActionContext.getResponse().getWriter().print("该菜单菜单还有子菜单不能删除");
        }
        return NONE;
    }

    public String loadMenusByParentSn()throws Exception{
        List<SystemMenu> systemMenus = systemMenuService.loadMenusByParentSn(qo.getParentSn());
        List<Map<String, Object>> jsonList=new ArrayList<>();
        for (SystemMenu menu : systemMenus) {
            jsonList.add(menu.json(menu));
        }
        ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
        ServletActionContext.getResponse().getWriter().print(JSON.toJSONString(jsonList));
        return NONE;
    }

    public void prepareSaveOrUpdate() throws Exception {
        if (systemMenu.getId() != null) {
            systemMenu = systemMenuService.get(systemMenu.getId());
        }
    }

}
