package com.imen.wms.service;

import com.imen.wms.domain.OrderBill;
import com.imen.wms.query.PageResult;
import com.imen.wms.query.OrderBillQueryObject;

import java.util.List;

public interface IOrderBillService {
    /**
     * 保存
     *
     * @param orderBill 对象
     */
    void save(OrderBill orderBill);

    /**
     * 保存
     *
     * @param orderBill 对象
     */
    void update(OrderBill orderBill);

    /**
     * 删除
     *
     * @param id ID
     */
    void delete(Long id);

    /**
     * 查询
     *
     * @param id 对象ID
     * @return 对象
     */
    OrderBill get(Long id);

    /**
     * 查询所有
     *
     * @return 对象集合
     */
    List<OrderBill> listAll();

    /**
     * 高级查询+分页查询
     * @param qo
     * @return
     */
    PageResult query(OrderBillQueryObject qo);

    /**
     * 订单审核
     * @param orderBill
     */
    void audit(OrderBill orderBill);
}
