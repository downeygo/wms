package com.imen.wms.query;


import lombok.Getter;
import lombok.Setter;

public class SystemMenuQueryObject extends QueryObject{
    @Setter@Getter
    private Long parentId=-1L;
    @Setter@Getter
    private String parentSn;//父菜单的编码
    public void customizedQuery(){
        if(parentId>0){
            super.addQuery(" obj.parent.id=?",parentId);
        }else{
            super.addQuery(" obj.parent.id IS NULL ");
        }
    }

}
