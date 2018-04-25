package com.imen.wms.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * 角色对象
 */
@Getter@Setter
public class Role extends BaseDomain{
    private String name;//角色名称
    private String sn;//角色编码
    private List<Permission> permissions=new ArrayList<>();//角色的所有权限
    private List<SystemMenu> menus=new ArrayList<>();//角色管理的菜单
}
