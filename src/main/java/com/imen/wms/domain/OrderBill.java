package com.imen.wms.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 订单
 */
@Getter@Setter
public class OrderBill extends BaseDomain{
    public static  final int NORMAL=0;//未审核
    public static  final int AUDIT=1;//已审核

    private String sn;//单据编码，可以自动生成也可以手动录入
    private Date vdate;//业务时间
    private int status;//单据审核状态，缺省是为审核
    private BigDecimal totalAmount;//采购总金额
    private BigDecimal totalNumebr;//采购总数量
    private Date inputTime;//制单时间
    private Date auditTime;//审核时间
    private Employee inputUser;//制单人
    private Employee auditor;//审核人
    private Supplier supplier;//供应商
    private List<OrderBillItem> items=new ArrayList<>();//子订单

}
