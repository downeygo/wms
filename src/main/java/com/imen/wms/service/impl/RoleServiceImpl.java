package com.imen.wms.service.impl;

import com.imen.wms.dao.impl.RoleDAOImpl;
import com.imen.wms.domain.Role;
import com.imen.wms.query.PageResult;
import com.imen.wms.query.QueryObject;
import com.imen.wms.service.IRoleService;
import lombok.Setter;

import java.util.List;

public class RoleServiceImpl implements IRoleService {
    @Setter
    private RoleDAOImpl roleDao;
    @Override
    public void save(Role r) {
        roleDao.save(r);
    }

    @Override
    public void update(Role r) {
        roleDao.update(r);
    }

    @Override
    public void delete(Long id) {
        roleDao.delete(id);
    }

    @Override
    public Role get(Long id) {
        return roleDao.get(id);
    }

    @Override
    public List<Role> listAll() {
        return roleDao.listAll();
    }

    @Override
    public PageResult query(QueryObject qo) {
        return roleDao.query(qo);
    }
}
