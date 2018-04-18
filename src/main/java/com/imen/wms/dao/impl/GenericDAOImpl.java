package com.imen.wms.dao.impl;

import com.imen.wms.dao.IGenericDAO;
import com.imen.wms.query.PageResult;
import com.imen.wms.query.QueryObject;
import lombok.Setter;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.lang.reflect.ParameterizedType;
import java.util.Collections;
import java.util.List;

public class GenericDAOImpl<T> implements IGenericDAO<T> {
    @Setter
    protected SessionFactory sessionFactory;
    private Class<T> targetType;

    //得到T类型的字节码对象
    public GenericDAOImpl() {
        ParameterizedType pType = (ParameterizedType) this.getClass().getGenericSuperclass();
        targetType = (Class<T>) pType.getActualTypeArguments()[0];
    }

    @Override
    public void save(T obj) {
        Session session = sessionFactory.getCurrentSession();
        session.save(obj);
    }

    @Override
    public void update(T obj) {
        Session session = sessionFactory.getCurrentSession();
        session.update(obj);
    }

    @Override
    public void delete(Long id) {
        Session session = sessionFactory.getCurrentSession();
        T obj = (T) session.get(targetType, id);
        session.delete(obj);
    }

    @Override
    public T get(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return (T) session.get(targetType, id);
    }

    @Override
    public List<T> listAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createCriteria(targetType).list();
    }

    @Override
    public PageResult query(int currentPage, int pageSize) {
        String countHql = "SELECT COUNT(*) FROM " + targetType.getSimpleName() + " obj";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(countHql);
        int totalCount = ((Long) query.uniqueResult()).intValue();
        if (totalCount == 0) {
            return new PageResult(0, Collections.emptyList(), 1, 1);
        }
        String hql = "SELECT obj FROM " + targetType.getSimpleName() + " obj";
        query = session.createQuery(hql);
        List list = query.setFirstResult((currentPage - 1) * pageSize).setMaxResults(pageSize).list();
        return new PageResult(totalCount, list, currentPage, pageSize);
    }

    @Override
    public List<T> queryForList(String condition, Object[] params, int currentPage, int pageSize) {
        String hql = "SELECT obj FROM " + targetType.getSimpleName() + " obj";
        if (params != null && params.length > 0) {
            hql = hql + " WHERE " + condition;
        }
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(hql);
        if (params != null && params.length > 0) {
            for (int index = 0; index < params.length; index++) {
                query.setParameter(index, params[index]);
            }
        }
        if (currentPage > 0 && pageSize > 0) {
            query.setFirstResult((currentPage - 1) * pageSize).setMaxResults(pageSize);
        }
        return query.list();
    }

    @Override
    public List<T> queryForList(String condition, Object[] params) {
        return this.queryForList(condition, params, -1, -1);
    }

    @Override
    public T queryForObject(String condition, Object... params) {
        List<T> list = this.queryForList(condition, params);
        return list.size() == 0 ? null:list.get(0) ;
    }

    @Override
    public void batchDelete(List ids) {
        String hql="DELETE FROM "+targetType.getSimpleName()+" obj WHERE obj.id IN (:ids)";
        Session session=sessionFactory.getCurrentSession();
        Query query = session.createQuery(hql);
        query.setParameterList("ids",ids);
        query.executeUpdate();
    }

    @Override
    public PageResult query(QueryObject qo) {
        int currentPage = qo.getCurrentPage();
        int pageSize = qo.getPageSize();
        String countHql = "SELECT COUNT(*) FROM " + targetType.getSimpleName() + " obj" + qo.getQuery();
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(countHql);
        for (int index = 0; index < qo.getParameters().size(); index++) {
            query.setParameter(index, qo.getParameters().get(index));
        }
        int totalCount = ((Long) query.uniqueResult()).intValue();
        if (totalCount == 0) {
            return new PageResult(0, Collections.emptyList(), 1, 1);
        }
        String hql = "SELECT obj FROM " + targetType.getSimpleName() + " obj" + qo.getQuery();
        query = session.createQuery(hql);
        for (int index = 0; index < qo.getParameters().size(); index++) {
            query.setParameter(index, qo.getParameters().get(index));
        }
        List list = query.setFirstResult((currentPage - 1) * pageSize).setMaxResults(pageSize).list();
        return new PageResult(totalCount, list, currentPage, pageSize);
    }
}
