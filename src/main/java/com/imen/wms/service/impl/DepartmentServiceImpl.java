package com.imen.wms.service.impl;

import com.imen.wms.dao.impl.DepartmentDAOImpl;
import com.imen.wms.domain.Department;
import com.imen.wms.query.PageResult;
import com.imen.wms.query.QueryObject;
import com.imen.wms.service.IDepartmentService;
import lombok.Setter;

import java.util.List;

public class DepartmentServiceImpl implements IDepartmentService {
    @Setter
    private DepartmentDAOImpl departmentDao;
    public void save(Department d) {
        departmentDao.save(d);
    }

    public void update(Department d) {
        departmentDao.update(d);
    }

    public void delete(Long id) {
        departmentDao.delete(id);
    }

    public Department get(Long id) {
        return departmentDao.get(id);
    }

    public List<Department> listAll() {
        return departmentDao.listAll();
    }

    public PageResult query(int currentPage, int pageSize) {
        return departmentDao.query(currentPage,pageSize);
    }

    public PageResult query(QueryObject qo) {
        return departmentDao.query(qo);
    }
}
