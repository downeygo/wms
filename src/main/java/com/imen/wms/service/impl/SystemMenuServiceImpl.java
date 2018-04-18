package com.imen.wms.service.impl;

import com.imen.wms.dao.impl.SystemMenuDAOImpl;
import com.imen.wms.domain.Role;
import com.imen.wms.domain.SystemMenu;
import com.imen.wms.query.PageResult;
import com.imen.wms.query.SystemMenuQueryObject;
import com.imen.wms.service.ISystemMenuService;
import com.imen.wms.util.UserContext;
import com.imen.wms.vo.SystemMenuVO;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SystemMenuServiceImpl implements ISystemMenuService {
    @Setter
    private SystemMenuDAOImpl systemMenuDao;

    @Override
    public void save(SystemMenu systemMenu) {
        systemMenuDao.save(systemMenu);
    }

    @Override
    public void update(SystemMenu systemMenu) {
        systemMenuDao.update(systemMenu);
    }

    @Override
    public void delete(Long id) {
        systemMenuDao.delete(id);
    }

    @Override
    public SystemMenu get(Long id) {
        return systemMenuDao.get(id);
    }

    @Override
    public List<SystemMenu> listAll() {
        return systemMenuDao.listAll();
    }

    @Override
    public PageResult query(SystemMenuQueryObject qo) {
        return systemMenuDao.query(qo);
    }

    public boolean hasChidMenu(Long id) {
        SystemMenuQueryObject qo = new SystemMenuQueryObject();
        qo.setParentId(id);
        PageResult pageResult = this.query(qo);
        if (pageResult.getTotalCount() > 0) {
            return true;
        }
        return false;
    }

    @Override
    public List<SystemMenuVO> listMenu(Long id) {
        List<SystemMenuVO> menus = new ArrayList<>();
        SystemMenu parent = this.get(id);
        this.listParentName(menus, parent);
        Collections.reverse(menus);
        return menus;
    }


    public List<SystemMenuVO> listParentName(List<SystemMenuVO> menus, SystemMenu currentParent) {
        if (currentParent != null) {
            SystemMenuVO systemMenuVO = new SystemMenuVO();
            systemMenuVO.setId(currentParent.getId());
            systemMenuVO.setName(currentParent.getName());
            menus.add(systemMenuVO);
            listParentName(menus, currentParent.getParent());
        }
        return menus;
    }

    @Override
    public List<SystemMenu> loadMenusByParentSn(String parentSn) {
        if (UserContext.getCurrentUser().isAdmin()) {
            return systemMenuDao.loadMenusByParentSn(parentSn);
        }
        List<Role> roles = UserContext.getCurrentUser().getRoles();
        return systemMenuDao.loadMenusByParentSnAndRole(parentSn, roles);
    }
}
