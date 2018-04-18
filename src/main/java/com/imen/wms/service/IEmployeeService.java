package com.imen.wms.service;

import java.util.List;

import com.imen.wms.domain.Employee;
import com.imen.wms.query.PageResult;
import com.imen.wms.query.QueryObject;

public interface IEmployeeService {
    /**
     * 保存
     *
     * @param e
     */
    void save(Employee e);

    /**
     * 更新
     *
     * @param e
     */
    void update(Employee e);

    /**
     * 删除
     *
     * @param id
     */
    void delete(Long id);

    /**
     * 查询
     *
     * @param id 员工ID
     * @return 员工对象
     */
    Employee get(Long id);

    /**
     * 查询所有
     *
     * @return 员工对象集合
     */
    List<Employee> listAll();

    /**
     *高级查询+分页查询
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
     * 登录检查
     * @param username
     * @param password
     * @return
     */
    Employee checkLogin(String username, String password);

    /**
     * 批量删除
     * @param ids
     */
    void batchDelete(List ids);
}
