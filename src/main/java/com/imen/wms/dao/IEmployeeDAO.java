package com.imen.wms.dao;

import com.imen.wms.domain.Employee;


public interface IEmployeeDAO extends IGenericDAO<Employee>{
    Employee checkLogin(String username, String password);
}
