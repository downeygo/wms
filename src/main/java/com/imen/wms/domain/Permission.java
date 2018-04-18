package com.imen.wms.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.lang.annotation.Target;

/**
 * 权限对象
 */
@Getter@Setter@ToString
public class Permission extends BaseDomain{
    private String name;//权限名称
    private String expression;//权限表达式

    public Permission() {
    }

    public Permission(String name, String expression) {
        this.name = name;
        this.expression = expression;
    }
}
