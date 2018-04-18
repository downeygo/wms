package com.imen.wms.dao;

import com.imen.wms.domain.Role;
import com.imen.wms.domain.SystemMenu;

import java.util.List;

public interface ISystemMenuDAO extends IGenericDAO<SystemMenu>{
    List<SystemMenu> loadMenusByParentSn(String parentSn);

    List<SystemMenu> loadMenusByParentSnAndRole(String parentSn,List<Role> roles);
}
