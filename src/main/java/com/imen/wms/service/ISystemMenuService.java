package com.imen.wms.service;

import com.imen.wms.domain.SystemMenu;
import com.imen.wms.query.PageResult;
import com.imen.wms.query.SystemMenuQueryObject;
import com.imen.wms.vo.SystemMenuVO;

import java.util.List;

public interface ISystemMenuService {
    /**
     * 保存
     *
     * @param systemMenu 对象
     */
    void save(SystemMenu systemMenu);

    /**
     * 保存
     *
     * @param systemMenu 对象
     */
    void update(SystemMenu systemMenu);

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
    SystemMenu get(Long id);

    /**
     * 查询所有
     *
     * @return 对象集合
     */
    List<SystemMenu> listAll();

    /**
     * 高级查询+分页查询
     * @param qo
     * @return
     */
    PageResult query(SystemMenuQueryObject qo);

    /**
     * 判断菜单是否有子菜单
     * @param id
     * @return 有则返回true,无则返回false
     */
    boolean hasChidMenu(Long id);

    /**
     *
     * @return
     */
    List<SystemMenuVO> listMenu(Long id);

    List<SystemMenuVO> listParentName(List<SystemMenuVO> menus,SystemMenu currentParent);

    /**
     * 根据当前登陆用户的角色和父菜单的sn查询出所有的子菜单
     * @return
     */
    List<SystemMenu> loadMenusByParentSn(String parentSn);
}
