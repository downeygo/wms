package com.imen.wms.service;

import com.imen.wms.domain.Product;
import com.imen.wms.query.PageResult;
import com.imen.wms.query.ProductQueryObject;

import java.util.List;

public interface IProductService {
    /**
     * 保存
     *
     * @param product 对象
     */
    void save(Product product);

    /**
     * 保存
     *
     * @param product 对象
     */
    void update(Product product);

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
    Product get(Long id);

    /**
     * 查询所有
     *
     * @return 对象集合
     */
    List<Product> listAll();

    /**
     * 高级查询+分页查询
     * @param qo
     * @return
     */
    PageResult query(ProductQueryObject qo);
}
