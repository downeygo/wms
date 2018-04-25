package com.imen.wms.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

/**
 * 系统菜单
 */

@Getter@Setter
public class SystemMenu extends BaseDomain implements SystemMenu2JSON {
    private String name;//菜单名字
    private String url;//根菜单的url为空
    private String sn;//只有根菜单的菜单编码（sn）不为空
    private SystemMenu parent;//父菜单对象

    //把菜单需要的数据转换成Map（过滤）
    @Override
    public Map<String,Object> json(SystemMenu menu) {
        Map<String,Object> jsonMap=new HashMap<>();
        jsonMap.put("id",menu.getId());
        jsonMap.put("pId",menu.getParent().getId());
        jsonMap.put("name",menu.getName());
        jsonMap.put("action",menu.getUrl()!=null?menu.getUrl():null);
        return jsonMap;
    }
    //private List<SystemMenu> children;//子菜单对象，暂时不用


}
