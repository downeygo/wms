package com.imen.wms.web.action;

import com.imen.wms.domain.OrderBill;
import com.imen.wms.domain.OrderBillItem;
import com.imen.wms.query.OrderBillQueryObject;
import com.imen.wms.service.IOrderBillService;
import com.imen.wms.service.IProductService;
import com.imen.wms.service.ISupplierService;
import com.imen.wms.util.RequiredPermission;
import com.imen.wms.util.UserContext;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import lombok.Getter;
import lombok.Setter;
import org.apache.struts2.ServletActionContext;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

public class OrderBillAction extends BaseAction {
    private static final long serialVersionUID = 6887934604174049132L;
    @Setter
    private IOrderBillService orderBillService;
    @Setter
    private ISupplierService supplierService;
    @Setter
    private IProductService productService;
    @Getter
    private OrderBill orderBill = new OrderBill();
    @Getter
    private OrderBillQueryObject qo = new OrderBillQueryObject();

    @RequiredPermission("订单列表")
    @InputConfig(methodName = "input")
    public String execute() throws Exception {
        ActionContext.getContext().put("pageResult", orderBillService.query(qo));
        return LIST;
    }

    @Override
    @RequiredPermission("订单编辑")
    public String input() throws Exception {
        ActionContext.getContext().put("supplier", supplierService.listAll());
        ActionContext.getContext().put("product", productService.listAll());
        if (orderBill.getId() != null) {
            orderBill = orderBillService.get(orderBill.getId());
        }
        return INPUT;
    }

    @RequiredPermission("订单保存或更新")
    public String saveOrUpdate() throws Exception {
        if (orderBill.getId() == null) {
            orderBillService.save(orderBill);
        }else if(orderBill.getId()!=null){
            orderBillService.update(orderBill);
        }
        return SUCCESS;
    }

    @RequiredPermission("订单删除")
    public String delete() throws Exception {
        orderBillService.delete(orderBill.getId());
        ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
        ServletActionContext.getResponse().getWriter().print("删除成功");
        return NONE;
    }

    public void prepareSaveOrUpdate() throws Exception {
        if (orderBill.getId() != null) {
            orderBill = orderBillService.get(orderBill.getId());
            orderBill.getItems().clear();
        }
    }

}
