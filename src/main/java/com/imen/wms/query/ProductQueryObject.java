package com.imen.wms.query;

import lombok.Getter;
import lombok.Setter;

@Setter@Getter
public class ProductQueryObject extends QueryObject{
    private String keyword;//关键字
    private Long brandId=-1L;//品牌ID
    public void customizedQuery(){
        if(keyword!=null){
            String word="%"+keyword+"%";
            super.addQuery("(obj.name LIKE ? OR obj.sn LIKE ?)",word,word);
        }
        if(brandId>0){
            super.addQuery("obj.brand.id=?",brandId);
        }
    }

}
