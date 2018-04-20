package com.imen.wms.service;

import com.imen.wms.domain.Supplier;
import com.imen.wms.query.PageResult;
import com.imen.wms.query.SupplierQueryObject;

import java.util.List;

public interface ISupplierService {
    /**
     * 保存
     *
     * @param supplier 对象
     */
    void save(Supplier supplier);

    /**
     * 保存
     *
     * @param supplier 对象
     */
    void update(Supplier supplier);

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
    Supplier get(Long id);

    /**
     * 查询所有
     *
     * @return 对象集合
     */
    List<Supplier> listAll();

    /**
     * 高级查询+分页查询
     * @param qo
     * @return
     */
    PageResult query(SupplierQueryObject qo);
}
