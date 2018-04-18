package com.imen.wms.query;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.ArrayList;
import java.util.List;

@ToString
public class EmployeeQueryObject extends QueryObject{
    @Getter@Setter
    private String keyword;
    @Getter@Setter
    private Long deptId=-1L;

    List<String> conditions=new ArrayList<>();//HQL
    List<Object> params=new ArrayList<>();//查询条件
    public void customizedQuery(){
        StringBuilder sb=new StringBuilder(80);
        if(this.hasLength(keyword)){
            this.addQuery("(obj.name LIKE ? OR obj.email LIKE ?)","%"+keyword+"%","%"+keyword+"%");
        }
        if(this.deptId>0){
            conditions.add("obj.dept.id=?");
            params.add(deptId);
            this.addQuery("obj.dept.id=?",deptId);
        }
    }

}
