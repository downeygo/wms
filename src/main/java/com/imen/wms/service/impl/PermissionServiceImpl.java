package com.imen.wms.service.impl;

import com.imen.wms.dao.IPermissionDAO;
import com.imen.wms.domain.Permission;
import com.imen.wms.query.PageResult;
import com.imen.wms.query.QueryObject;
import com.imen.wms.service.IPermissionService;
import com.imen.wms.util.PermissionUtil;
import com.imen.wms.util.RequiredPermission;
import com.imen.wms.web.action.BaseAction;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import lombok.Setter;

import java.lang.reflect.Method;
import java.util.*;

public class PermissionServiceImpl implements IPermissionService, ApplicationContextAware {
    @Setter
    private IPermissionDAO permissionDao;

    private ApplicationContext ctx;

    @Override//也可以用@Autowired标签
    public void setApplicationContext(ApplicationContext ctx) throws BeansException {
        this.ctx = ctx;
    }

    @Override
    public void delete(Long id) {
        permissionDao.delete(id);
    }

    @Override
    public List<Permission> listAll() {
        return permissionDao.listAll();
    }

    @Override
    public PageResult query(QueryObject qo) {
        return permissionDao.query(qo);
    }

    @Override
    public void reload() {
        List<Permission> permissions = permissionDao.listAll();
        Set<String> expressions = new HashSet<>();
        for (Permission permission : permissions) {
            expressions.add(permission.getExpression());
        }
        Map<String, BaseAction> beansMap = ctx.getBeansOfType(BaseAction.class);
        for (BaseAction action : beansMap.values()) {
            Method[] ms = action.getClass().getDeclaredMethods();
            for (Method m : ms) {
                RequiredPermission rp = m.getAnnotation(RequiredPermission.class);
                String expression = PermissionUtil.buildExpression(m);
                if (!expressions.contains(expression)) {
                    if (rp != null) {
                        String name = rp.value();
                        Permission p = new Permission(name, expression);
                        permissionDao.save(p);
                    }
                }
            }
        }
    }
}
