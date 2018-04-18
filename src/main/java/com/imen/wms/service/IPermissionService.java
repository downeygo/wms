package com.imen.wms.service;

import com.imen.wms.domain.Permission;
import com.imen.wms.query.PageResult;
import com.imen.wms.query.QueryObject;

import java.util.List;

public interface IPermissionService {

    /**
     * 删除
     *
     * @param id
     */
    void delete(Long id);


    /**
     * 查询所有
     *
     * @return 员工对象集合
     */
    List<Permission> listAll();

    /**
     *高级查询+分页查询
     * @param qo
     * @return
     */

    PageResult query(QueryObject qo);

    /**
     * 加载权限
     */
    void reload();

}
