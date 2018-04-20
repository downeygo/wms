package com.imen.wms.web.action;

import com.imen.wms.domain.Product;
import com.imen.wms.query.ProductQueryObject;
import com.imen.wms.service.IBrandService;
import com.imen.wms.service.IProductService;
import com.imen.wms.util.FileUploadUtil;
import com.imen.wms.util.RequiredPermission;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import lombok.Getter;
import lombok.Setter;
import org.apache.struts2.ServletActionContext;

import java.io.File;

public class ProductAction extends BaseAction {
    private static final long serialVersionUID = 6887934604174049132L;
    @Setter
    private IProductService productService;
    @Setter
    private IBrandService brandService;
    @Getter
    private Product product = new Product();
    @Getter
    private ProductQueryObject qo = new ProductQueryObject();

    @RequiredPermission("商品列表")
    @InputConfig(methodName = "input")
    public String execute() throws Exception {
        ActionContext.getContext().put("pageResult", productService.query(qo));
        ActionContext.getContext().put("brands",brandService.listAll());
        return LIST;
    }

    @Override
    @RequiredPermission("商品编辑")
    public String input() throws Exception {
        ActionContext.getContext().put("brands", brandService.listAll());
        if (product.getId() != null) {
            product = productService.get(product.getId());
        }
        return INPUT;
    }

    @RequiredPermission("商品保存或更新")
    public String saveOrUpdate() throws Exception {
        try {
            if (product.getId() == null) {
                productService.save(product);
                super.addActionMessage("保存成功");
            } else {
                productService.update(product);
                super.addActionMessage("修改成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            super.addActionError("操作失败:" + e.getMessage());
        }
        return SUCCESS;
    }

    @RequiredPermission("商品删除")
    public String delete() throws Exception {
        productService.delete(product.getId());
        ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
        ServletActionContext.getResponse().getWriter().print("删除成功");
        return NONE;
    }

    @RequiredPermission("商品列表")
    @InputConfig(methodName = "input")
    public String selectProductList() throws Exception {
        ActionContext.getContext().put("pageResult", productService.query(qo));
        ActionContext.getContext().put("brands",brandService.listAll());
        return "selectProductList";
    }


    public void prepareSaveOrUpdate() throws Exception {
        if (product.getId() != null) {
            product = productService.get(product.getId());
            product.setBrand(null);
        }
    }

}
