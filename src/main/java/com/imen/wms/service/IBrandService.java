package com.imen.wms.service;

import com.imen.wms.domain.Brand;
import com.imen.wms.query.PageResult;
import com.imen.wms.query.BrandQueryObject;

import java.util.List;

public interface IBrandService {
    /**
     * 保存
     *
     * @param brand 对象
     */
    void save(Brand brand);

    /**
     * 保存
     *
     * @param brand 对象
     */
    void update(Brand brand);

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
    Brand get(Long id);

    /**
     * 查询所有
     *
     * @return 对象集合
     */
    List<Brand> listAll();

    /**
     * 高级查询+分页查询
     * @param qo
     * @return
     */
    PageResult query(BrandQueryObject qo);
}
