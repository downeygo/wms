package com.imen.wms.dao.impl;

import com.imen.wms.dao.ISystemMenuDAO;
import com.imen.wms.domain.Role;
import com.imen.wms.domain.SystemMenu;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

public class SystemMenuDAOImpl extends GenericDAOImpl<SystemMenu> implements ISystemMenuDAO {
    @Override
    public List<SystemMenu> loadMenusByParentSn(String parentSn) {
        Session session=sessionFactory.getCurrentSession();
        String hql="SELECT obj FROM SystemMenu obj WHERE obj.parent.sn=?";
        Query query = session.createQuery(hql);
        query.setParameter(0,parentSn);
        return query.list();
    }

    @Override
    public List<SystemMenu> loadMenusByParentSnAndRole(String parentSn, List<Role> roles) {
        Session session=sessionFactory.getCurrentSession();
        String hql="SELECT m FROM Role r JOIN r.menus m WHERE m.parent.sn=:parentSn AND r IN (:roles)";
        Query query = session.createQuery(hql);
        query.setParameter("parentSn",parentSn);
        query.setParameterList("roles",roles);
        return query.list();
    }
}
