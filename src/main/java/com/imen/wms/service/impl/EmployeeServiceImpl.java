package com.imen.wms.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.imen.wms.dao.IEmployeeDAO;
import com.imen.wms.domain.Employee;
import com.imen.wms.domain.Permission;
import com.imen.wms.domain.Role;
import com.imen.wms.query.PageResult;
import com.imen.wms.query.QueryObject;
import com.imen.wms.service.IEmployeeService;
import com.imen.wms.util.UserContext;
import lombok.Setter;

public class EmployeeServiceImpl implements IEmployeeService {
    @Setter
    private IEmployeeDAO employeeDao;
    /*@Setter
    private IRoleDAO roleDao;*/

    public void save(Employee e) {
        employeeDao.save(e);
    }

    public void update(Employee e) {
        employeeDao.update(e);
    }

    public void delete(Long id) {
        employeeDao.delete(id);
    }

    public Employee get(Long id) {
        return employeeDao.get(id);
    }

    public List<Employee> listAll() {
        return employeeDao.listAll();
    }

    @Override
    public PageResult query(QueryObject qo) {
        return employeeDao.query(qo);
    }

    @Override
    public PageResult query(int currentPage, int pageSize) {
        return employeeDao.query(currentPage, pageSize);
    }

    @Override
    public Employee checkLogin(String username, String password) {
        Employee current = employeeDao.checkLogin(username, password);
        if (current == null) {
            return null;
        }
        UserContext.setEmployee(current);
        //不是超级管理员才查询权限并放入session
        if (!current.isAdmin()) {
            List<Role> roles = current.getRoles();//查询出当前用户的角色；
            Set<String> permissionSet = new HashSet<>();//存放角色中的权限表达式
            for (Role role : roles) {
                List<Permission> ps = role.getPermissions();
                for (Permission p : ps) {
                    permissionSet.add(p.getExpression());
                }
            }
            UserContext.setPermissions(permissionSet);
        }
        return current;
    }

    @Override
    public void batchDelete(List ids) {
        employeeDao.batchDelete(ids);
    }
}
