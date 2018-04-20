<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
    <title>信息管理系统</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link href="style/basic_layout.css" rel="stylesheet" type="text/css">
    <link href="style/common_style.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="js/jquery/jquery.js"></script>
    <script type="text/javascript" src="/js/plugin/jquery-validate/jquery.validate.min.js"></script>
    <script type="text/javascript" src="js/commonAll.js"></script>
    <script type="text/javascript">
        $(function () {
            //克隆
            $(".appendRow").on("click", function () {
                var tr = $("#edit_table_body tr:first").clone();
                tr.find("[tag=costPrice]").val("");
                tr.find("[tag=number]").val("");
                tr.find("[tag=amount]").text("");
                tr.find("[tag=remark]").val("");
                tr.appendTo("#edit_table_body");
            });

            //金额小计
            $("#edit_table_body").on("change", "[tag=costPrice],[tag=number]", function () {
                var tr = $(this).closest("tr")
                var cost = tr.find("[tag=costPrice]").val();
                var num = tr.find("[tag=number]").val();
                if (cost && num) {
                    var amount = (cost * num).toFixed(2);
                    tr.find("[tag=amount]").text(amount);
                }
            }).on("click",".removeItem",function () {//删除明细
                $(this).closest("tr").remove();
            });
            //提交表单
            $(".btn_submit").click(function () {
                var trs = $("#edit_table_body tr");
                $.each(trs, function (index, item) {
                    var tr = $(item);
                    tr.find("[tag=pid]").prop("name", "orderBill.items[" + index + "].product.id");
                    tr.find("[tag=costPrice]").prop("name", "orderBill.items[" + index + "].costPrice");
                    tr.find("[tag=number]").prop("name", "orderBill.items[" + index + "].number");
                    tr.find("[tag=remark]").prop("name", "orderBill.items[" + index + "].remark");
                });
                $("#editForm").submit();
            });
        })
    </script>
</head>
<body>
<s:debug/>
<%@ include file="/WEB-INF/view/common/common_msg.jsp" %>
<s:form name="editForm" namespace="/" action="orderBill_saveOrUpdate.action" method="post" id="editForm">
    <s:hidden name="orderBill.id"/>
    <div id="container">
        <div id="box_top">订单信息</div>
        <div class="ui_content">
            <table cellspacing="0" cellpadding="0" width="100%" align="left" border="0">
                <tr>
                    <td class="ui_text_rt" width="140">订单编号</td>
                    <td class="ui_text_lt">
                        <s:textfield name="orderBill.sn" cssClass="ui_input_txt02"/>
                    </td>
                </tr>
                <tr>
                    <td class="ui_text_rt" width="140">供应商</td>
                    <td class="ui_text_lt">
                        <s:select list="#supplier" listKey="id" listValue="name" name="orderBill.supplier.id"
                                  cssClass="ui_select01"/>
                    </td>
                </tr>
                <tr>
                    <td class="ui_text_rt" width="140">业务时间</td>
                    <td class="ui_text_lt">
                        <s:textfield name="orderBill.vdate" cssClass="ui_input_txt02"/>
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td>
                        <input type="button" value="添加明细" class="ui_input_btn01 appendRow"/>
                        <table class="edit_table" cellspacing="0" cellpadding="0" border="0" style="width: auto">
                            <thead>
                            <tr>
                                <th width="200">货品</th>
                                <th width="80">价格</th>
                                <th width="80">数量</th>
                                <th width="80">金额小计</th>
                                <th width="150">备注</th>
                                <th width="60"></th>
                            </tr>
                            </thead>
                            <tbody id="edit_table_body">
                            <s:if test="orderBill.id==null">
                                <tr>
                                    <td>
                                        <s:select list="#product" listValue="name" listKey="id"
                                                  name="orderBill.items.product.id" cssClass="ui_select01" tag="pid"/>
                                    </td>
                                    <td><s:textfield tag="costPrice" name="orderBill.items.costPrice"
                                                     cssClass="ui_input_txt04"/></td>
                                    <td><s:textfield tag="number" name="orderBill.items.number"
                                                     cssClass="ui_input_txt04"/></td>
                                    <td><span tag="amount"></span></td>
                                    <td><s:textfield tag="remark" name="orderBill.items.remark"
                                                     cssClass="ui_input_txt02"/></td>
                                    <td>
                                        <a href="javascript:;" class="removeItem">删除明细</a>
                                    </td>
                                </tr>
                            </s:if>
                            <s:else>
                                <s:iterator value="orderBill.items">
                                    <tr>
                                        <td>
                                            <s:select list="#product" listValue="name" listKey="id"
                                                      name="product.id" cssClass="ui_select01"
                                                      tag="pid"/>
                                        </td>
                                        <td><s:textfield tag="costPrice" name="costPrice"
                                                         cssClass="ui_input_txt04"/></td>
                                        <td><s:textfield tag="number" name="number"
                                                         cssClass="ui_input_txt04"/></td>
                                        <td><span tag="amount"><s:property value="amount"/> </span></td>
                                        <td><s:textfield tag="remark" name="remark"
                                                         cssClass="ui_input_txt02"/></td>
                                        <td>
                                            <a href="javascript:;" class="removeItem">删除明细</a>
                                        </td>
                                    </tr>
                                </s:iterator>
                            </s:else>
                            </tbody>
                        </table>
                    </td>
                </tr>

                <tr>
                    <td>&nbsp;</td>
                    <td class="ui_text_lt">
                        &nbsp;<input type="button" value="确定保存" class="ui_input_btn01 btn_submit"/>
                        &nbsp;<input id="cancelbutton" type="button" value="重置" class="ui_input_btn01"/>
                    </td>
                </tr>
            </table>
        </div>
    </div>
</s:form>
</body>
</html>