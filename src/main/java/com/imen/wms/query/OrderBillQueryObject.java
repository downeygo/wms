package com.imen.wms.query;


import com.imen.wms.util.DateUtil;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter@Setter
public class OrderBillQueryObject extends QueryObject{
    private Date beginTime;//业务开始时间
    private Date endTime;//业务结束时间
    private Long supplierId=-1L;//供应商ID
    private int status=-1;//订单状态
    public void customizedQuery(){
        if(status>=0){
            addQuery("obj.status=?",status);
        }
        if(supplierId>0){
            addQuery("obj.supplier.id=?",supplierId);
        }
        if(beginTime!=null){
            addQuery("obj.vdate >=?", DateUtil.getBeginTime(beginTime));
        }
        if(endTime!=null){
            addQuery("obj.vdate <=?", DateUtil.getEndTime(endTime));
        }
    }

}
