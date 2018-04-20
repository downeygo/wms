package com.imen.wms.web.action;

import com.imen.wms.domain.Supplier;
import com.imen.wms.query.SupplierQueryObject;
import com.imen.wms.service.ISupplierService;
import com.imen.wms.util.RequiredPermission;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import lombok.Getter;
import lombok.Setter;
import org.apache.struts2.ServletActionContext;

public class SupplierAction extends BaseAction{
    private static final long serialVersionUID = 6887934604174049132L;
    @Setter
    private ISupplierService supplierService;
    @Getter
    private Supplier supplier=new Supplier();
    @Getter
    private SupplierQueryObject qo=new SupplierQueryObject();

    @RequiredPermission("供应商列表")
    @InputConfig(methodName = "input")
    public String execute() throws Exception {
        ActionContext.getContext().put("pageResult",supplierService.query(qo));
        return LIST;
    }

    @Override
    @RequiredPermission("供应商编辑")
    public String input() throws Exception {
        if(supplier.getId()!=null){
            supplier=supplierService.get(supplier.getId());
        }
        return INPUT;
    }

    @RequiredPermission("供应商保存或更新")
    public String saveOrUpdate() throws Exception{
        try {
            if(supplier.getId()==null){
                supplierService.save(supplier);
                super.addActionMessage("保存成功");
            }else{
                supplierService.update(supplier);
                super.addActionMessage("修改成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            super.addActionError("操作失败:"+e.getMessage());
        }
        return SUCCESS;
    }

    @RequiredPermission("供应商删除")
    public String delete() throws Exception{
        supplierService.delete(supplier.getId());
        ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
        ServletActionContext.getResponse().getWriter().print("删除成功");
        return NONE;
    }

    public void prepareSaveOrUpdate() throws Exception{
        if (supplier.getId() != null) {
            supplier = supplierService.get(supplier.getId());
        }
    }

}
