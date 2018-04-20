package com.imen.wms.service.impl;

import com.imen.wms.dao.impl.OrderBillDAOImpl;
import com.imen.wms.domain.OrderBill;
import com.imen.wms.domain.OrderBillItem;
import com.imen.wms.query.PageResult;
import com.imen.wms.query.OrderBillQueryObject;
import com.imen.wms.service.IOrderBillService;
import com.imen.wms.util.UserContext;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

public class OrderBillServiceImpl implements IOrderBillService {
    @Setter
    private OrderBillDAOImpl orderBillDao;

    @Override
    public void save(OrderBill orderBill) {
        //1设置制单人和制单时间
        orderBill.setInputUser(UserContext.getCurrentUser());
        orderBill.setInputTime(new Date());
        //2.手动设置单据的审核状态为未审核状态
        orderBill.setStatus(OrderBill.NORMAL);
        parseItems(orderBill);
        orderBillDao.save(orderBill);
    }

    @Override
    public void update(OrderBill orderBill) {
        //判断当前的审核状态，已经审核的单据不能更新
        if (orderBill.getStatus() != OrderBill.AUDIT) {
            parseItems(orderBill);
            orderBillDao.update(orderBill);
        }
    }

    @Override
    public void delete(Long id) {
        orderBillDao.delete(id);
    }

    @Override
    public OrderBill get(Long id) {
        return orderBillDao.get(id);
    }

    @Override
    public List<OrderBill> listAll() {
        return orderBillDao.listAll();
    }

    @Override
    public PageResult query(OrderBillQueryObject qo) {
        return orderBillDao.query(qo);
    }

    private void parseItems(OrderBill orderBill) {
        orderBill.setTotalNumebr(BigDecimal.ZERO);
        orderBill.setTotalAmount(BigDecimal.ZERO);
        //3.设置单据和明细之间的关系
        for (OrderBillItem item : orderBill.getItems()) {
            item.setBill(orderBill);
            //4.计算明细之间的小计
            item.setAmount((item.getNumber().multiply(item.getCostPrice())).setScale(2, RoundingMode.HALF_UP));
            //5.计算单据的总金额和总数量
            orderBill.setTotalNumebr(orderBill.getTotalNumebr().add(item.getNumber()));
            orderBill.setTotalAmount(orderBill.getTotalAmount().add(item.getAmount()));
        }
    }
}
