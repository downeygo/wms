<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link href="style/basic_layout.css" rel="stylesheet" type="text/css">
    <link href="style/common_style.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="js/jquery/jquery.js"></script>
    <script type="text/javascript" src="js/commonAll.js"></script>
    <script type="text/javascript" src="js/plugin/My97DatePicker/My97DatePicker/WdatePicker.js"></script>
    <title>WMS-订单管理</title>
    <style>
        .alt td {
            background: black !important;
        }
    </style>
    <script type="text/javascript">
        $(function () {
            var a=$("input[name='qo.beginTime']").addClass("Wdate").click(function () {
                WdatePicker({
                    maxDate:$("input[name='qo.endTime']").val() || new Date(),
                });
            });

            var a=$("input[name='qo.endTime']").addClass("Wdate").click(function () {
                WdatePicker({
                    minDate:$("input[name='qo.beginTime']").val(),
                    maxDate:new Date(),
                });
            });
        });
    </script>
</head>
<body>
<%@ include file="/WEB-INF/view/common/common_msg.jsp" %>
<form id="searchForm" action="#" method="post">
    <div id="container">
        <div class="ui_content">
            <div class="ui_text_indent">
                <div id="box_border">
                    <div id="box_top">订单信息搜索</div>
                    <div id="box_center">
                        业务时间
                        <s:date name="qo.beginTime" format="yyyy-MM-dd" var="bt"/>
                        <s:date name="qo.endTime" format="yyyy-MM-dd" var="et"/>
                        <s:textfield name="qo.beginTime" cssClass="ui_input_txt02" value="%{bt}"/>~<s:textfield name="qo.endTime" cssClass="ui_input_txt02" value="%{et}"/>
                        供应商
                        <s:select name="qo.supplierId" list="#supplier" listKey="id" listValue="name" headerKey="-1" headerValue="全部" cssClass="ui_select03"/>
                        订单状态
                        <s:select name="qo.status" list="#{'-1':'全部','0':'未审核','1':'已审核'}" cssClass="ui_select03"/>
                    </div>
                    <div id="box_bottom">
                        <input type="button" value="查询" class="ui_input_btn01"/>
                        <input type="button" value="新增" class="ui_input_btn01 btn_input"
                               data-url="<s:url namespace="/" action="orderBill_input"/>"/>
                    </div>
                </div>
            </div>
        </div>
        <div class="ui_content">
            <div class="ui_tb">
                <table class="table" cellspacing="0" cellpadding="0" width="100%" align="center" border="0">
                    <tr>
                        <th width="30"><input type="checkbox" id="all"/></th>
                        <th>订单编号</th>
                        <th>业务时间</th>
                        <th>供应商</th>
                        <th>采购总数量</th>
                        <th>采购总金额</th>
                        <th>制单人</th>
                        <th>审核人</th>
                        <th>审核状态</th>
                        <th></th>
                    </tr>
                    <tbody>
                    <s:iterator value="#pageResult.result">
                        <tr>
                            <td><input type="checkbox" name="IDCheck" autocomplete="off" class="acb"
                                       data-eid="<s:property value="id"/>"/></td>
                            <td><s:property value="sn"/></td>
                            <td><s:date name="vdate" format="yyyy-MM-dd"/></td>
                            <td><s:property value="supplier.name"/></td>
                            <td><s:property value="totalNumber"/></td>
                            <td><s:property value="totalAmount"/></td>
                            <td><s:property value="inputUser.name"/></td>
                            <td><s:property value="auditor.name"/></td>
                            <td>
                                <s:if test="status==0"><span style="color: green">未审核</span></s:if>
                                <s:elseif test="status==1"><span style="color: red">已审核</span></s:elseif>
                            </td>
                            <td>
                                <s:if test="status==0">
                                    <s:a namespace="/" action="orderBill_audit">
                                        <s:param name="orderBill.id" value="id"/>审核
                                    </s:a>

                                    <s:url var="editUrl" namespace="/" action="orderBill_input">
                                        <s:param name="orderBill.id" value="id"/>
                                    </s:url>
                                    <a href="<s:property value="editUrl"/>">编辑</a>

                                    <s:url var="deleteUrl" namespace="/" action="orderBill_delete">
                                        <s:param name="orderBill.id" value="id"/>
                                    </s:url>
                                    <a href="javascript:;" class="btn_delete"
                                       data-url="<s:property value="deleteUrl"/>">删除</a>
                                </s:if>
                                <s:if test="status==1">
                                    <s:a namespace="/" action="orderBill_open">
                                        <s:param name="orderBill.id" value="id"/>查看
                                    </s:a>
                                </s:if>
                            </td>
                        </tr>
                    </s:iterator>
                    </tbody>
                </table>
            </div>
            <%@include file="/WEB-INF/view/common/common_page.jsp" %>
        </div>
    </div>
    </div>
</form>
</body>
</html>

