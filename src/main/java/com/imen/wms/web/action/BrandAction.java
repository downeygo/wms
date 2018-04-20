package com.imen.wms.web.action;

import com.imen.wms.domain.Brand;
import com.imen.wms.query.BrandQueryObject;
import com.imen.wms.service.IBrandService;
import com.imen.wms.util.RequiredPermission;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import lombok.Getter;
import lombok.Setter;
import org.apache.struts2.ServletActionContext;

public class BrandAction extends BaseAction{
    private static final long serialVersionUID = 6887934604174049132L;
    @Setter
    private IBrandService brandService;
    @Getter
    private Brand brand=new Brand();
    @Getter
    private BrandQueryObject qo=new BrandQueryObject();

    @RequiredPermission("品牌列表")
    @InputConfig(methodName = "input")
    public String execute() throws Exception {
        ActionContext.getContext().put("pageResult",brandService.query(qo));
        return LIST;
    }

    @Override
    @RequiredPermission("品牌编辑")
    public String input() throws Exception {
        if(brand.getId()!=null){
            brand=brandService.get(brand.getId());
        }
        return INPUT;
    }

    @RequiredPermission("品牌保存或更新")
    public String saveOrUpdate() throws Exception{
        try {
            if(brand.getId()==null){
                brandService.save(brand);
                super.addActionMessage("保存成功");
            }else{
                brandService.update(brand);
                super.addActionMessage("修改成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            super.addActionError("操作失败:"+e.getMessage());
        }
        return SUCCESS;
    }

    @RequiredPermission("品牌删除")
    public String delete() throws Exception{
        brandService.delete(brand.getId());
        ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
        ServletActionContext.getResponse().getWriter().print("删除成功");
        return NONE;
    }

    public void prepareSaveOrUpdate() throws Exception{
        if (brand.getId() != null) {
            brand = brandService.get(brand.getId());
        }
    }

}
