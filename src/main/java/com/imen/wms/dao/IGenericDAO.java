package com.imen.wms.dao;

import com.imen.wms.query.PageResult;
import com.imen.wms.query.QueryObject;

import java.util.List;

public interface IGenericDAO<T> {
    /**
     * 保存
     *
     * @param obj 对象
     */
    void save(T obj);

    /**
     * 保存
     *
     * @param obj 对象
     */
    void update(T obj);

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
     T get(Long id);

    /**
     * 查询所有
     *
     * @return 对象集合
     */
    List<T> listAll();

    /**
     * 高级查询+分页查询
     * @param qo
     * @return
     */
    PageResult query(QueryObject qo);

    /**
     * 分页查询
     * @param currentPage
     * @param pageSize
     * @return
     */
    PageResult query(int currentPage, int pageSize);

    /**
     * 高级查询+分页查询
     * @param condition
     * @param params
     * @param currentPage
     * @param pageSize
     * @return
     */
    List<T> queryForList(String condition, Object[] params, int currentPage, int pageSize);

    /**
     * 高级查询
     * @param condition
     * @param params
     * @return
     */
    List<T> queryForList(String condition, Object[] params);

    /**
     * 高级查询（单个对象）
     * @param condition
     * @param params
     * @return
     */
    T queryForObject(String condition, Object[] params);

    /**
     * 批量删除
     * @param ids
     */
    void batchDelete(List ids);
}
