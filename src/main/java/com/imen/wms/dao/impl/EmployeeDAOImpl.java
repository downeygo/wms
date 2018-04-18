package com.imen.wms.dao.impl;


import com.imen.wms.dao.IEmployeeDAO;
import com.imen.wms.domain.Employee;
import com.imen.wms.util.MD5;

public class EmployeeDAOImpl extends GenericDAOImpl<Employee> implements IEmployeeDAO {

    @Override
    public void save(Employee obj) {
        obj.setPassword(MD5.encode(obj.getPassword()));
        super.save(obj);
    }

    @Override
    public Employee checkLogin(String username, String password) {
        return super.queryForObject("obj.name=? AND obj.password=?",username,MD5.encode(password));
    }
}
