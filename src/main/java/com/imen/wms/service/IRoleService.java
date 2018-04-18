package com.imen.wms.service;

import com.imen.wms.domain.Role;
import com.imen.wms.query.PageResult;
import com.imen.wms.query.QueryObject;

import java.util.List;

public interface IRoleService {
    /**
     * 保存
     *
     * @param r 对象
     */
    void save(Role r);

    /**
     * 保存
     *
     * @param r 对象
     */
    void update(Role r);

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
    Role get(Long id);

    /**
     * 查询所有
     *
     * @return 对象集合
     */
    List<Role> listAll();

    /**
     * 高级查询+分页查询
     * @param qo
     * @return
     */
    PageResult query(QueryObject qo);
}
