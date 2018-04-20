package com.imen.wms.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Getter@Setter
public class Product extends BaseDomain{
    private String sn;//编码
    private String name;//名称
    private BigDecimal costPrice;//成本价格
    private BigDecimal salePrice;//销售价格
    private String imagePath;//图片路径
    private String intro;//货品介绍
    private Brand brand;//货品品牌

    public Map<String,Object> getProductJson(){
        Map<String,Object> jsonMap=new HashMap<>();
        jsonMap.put("name",name);
        jsonMap.put("brand",brand.getName());
        jsonMap.put("costPrice",costPrice);
        jsonMap.put("salePriec",salePrice);
        return jsonMap;
    }
}
