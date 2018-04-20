package com.imen.wms.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * 品牌实体类
 */
@Getter@Setter
public class Brand extends BaseDomain{
    private String name;//品牌名称
    private String sn;//品牌编码
}
