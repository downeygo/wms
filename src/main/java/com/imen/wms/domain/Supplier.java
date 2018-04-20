package com.imen.wms.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * 供应商实体类
 */
@Getter@Setter
public class Supplier extends BaseDomain{
    private String name;//供应商名称
    private int phone;//供应商电话
    private String addres;//供应商地址
}
