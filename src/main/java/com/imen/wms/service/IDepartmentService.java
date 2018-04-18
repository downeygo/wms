package com.imen.wms.service;

import com.imen.wms.domain.Department;
import com.imen.wms.query.PageResult;
import com.imen.wms.query.QueryObject;

import java.util.List;

public interface IDepartmentService {
    /**
     * 保存
     *
     * @param d
     */
    void save(Department d);

    /**
     * 更新
     *
     * @param d
     */
    void update(Department d);

    /**
     * 删除
     *
     * @param id
     */
    void delete(Long id);

    /**
     * 查询
     *
     * @param id
     *            员工ID
     * @return 员工对象
     */
    Department get(Long id);

    /**
     * 查询所有
     *
     * @return 员工对象集合
     */
    List<Department> listAll();

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
}
