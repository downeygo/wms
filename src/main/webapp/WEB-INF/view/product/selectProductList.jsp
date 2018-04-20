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
    <title>WMS-商品管理</title>
    <style>
        .alt td {
            background: black !important;
        }
    </style>
</head>
<body>
<%@ include file="/WEB-INF/view/common/common_msg.jsp"%>
<form id="searchForm" action="#" method="post">
    <div id="container">
        <div class="ui_content">
            <div class="ui_text_indent">
                <div id="box_border">
                    <div id="box_top">搜索</div>
                    <div id="box_center">
                        名称/编号
                        <s:textfield cssClass="ui_input_txt02" name="qo.keyword"/>
                        商品品牌
                        <s:select name="qo.brandId" list="#brands" listKey="id" listValue="name" headerKey="-1" headerValue="所有品牌"/>
                    </div>
                    <div id="box_bottom">
                        <input type="button" value="查询" class="ui_input_btn01"/>
                    </div>
                </div>
            </div>
        </div>
        <div class="ui_content">
            <div class="ui_tb">
                <table class="table" cellspacing="0" cellpadding="0" width="100%" align="center" border="0">
                    <tr>
                        <th>编码</th>
                        <th>名称</th>
                        <th>品牌</th>
                        <th>成本价格</th>
                        <th>销售价格</th>
                        <th></th>
                    </tr>
                    <tbody>
                    <s:iterator value="#pageResult.result">
                        <tr>
                            <td><s:property value="sn"/></td>
                            <td><s:property value="name"/></td>
                            <td><s:property value="brand.name"/></td>
                            <td><s:property value="costPrice"/></td>
                            <td><s:property value="salePrice"/></td>
                            <td><s:property value="productJson"/></td>
                            <td>
                                <input type="button" id="button" value="选择该商品" class="left2right" href="/orderBill_selectProductList.action"/>
                            </td>
                        </tr>
                    </s:iterator>
                    </tbody>
                </table>
            </div>
                <%@include file="/WEB-INF/view/common/common_page.jsp"%>
            </div>
        </div>
    </div>
</form>
</body>
</html>

