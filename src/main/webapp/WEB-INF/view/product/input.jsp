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
    <script type="text/javascript" src="js/system/product/product.js"></script>

</head>
<body>
<%@ include file="/WEB-INF/view/common/common_msg.jsp" %>
<s:form name="editForm" namespace="/" action="product_saveOrUpdate" method="post" id="editForm" enctype="multipart/form-data">
    <s:hidden name="product.id"/>
    <div id="container">
        <div id="box_top">商品信息</div>
        <div class="ui_content">
            <table cellspacing="0" cellpadding="0" width="100%" align="left" border="0">
                <tr>
                    <td class="ui_text_rt" width="140">商品编码</td>
                    <td class="ui_text_lt">
                        <s:textfield name="product.sn" cssClass="ui_input_txt02"/>
                    </td>
                </tr>
                <tr>
                    <td class="ui_text_rt" width="140">商品名称</td>
                    <td class="ui_text_lt">
                        <s:textfield name="product.name" cssClass="ui_input_txt02"/>
                    </td>
                </tr>
                <tr>
                    <td class="ui_text_rt" width="140">商品品牌</td>
                    <td class="ui_text_lt">
                        <s:select list="#brands" listValue="name" listKey="id" name="product.brand.id" cssClass="ui_select01"/>
                    </td>
                </tr>
                <tr>
                    <td class="ui_text_rt" width="140">成本价格</td>
                    <td class="ui_text_lt">
                        <s:textfield name="product.costPrice" cssClass="ui_input_txt02"/>
                    </td>
                </tr>
                <tr>
                    <td class="ui_text_rt" width="140">销售价格</td>
                    <td class="ui_text_lt">
                        <s:textfield name="product.salePrice" cssClass="ui_input_txt02"/>
                    </td>
                </tr>
                <%--<tr>
                    <td class="ui_text_rt" width="140">上传图片</td>
                    <td class="ui_text_lt">
                        <s:file name="picture" cssClass="ui_file"/>
                    </td>
                </tr>--%>
                <tr>
                    <td class="ui_text_rt" width="140">商品介绍</td>
                    <td class="ui_text_lt">
                        <s:textarea name="product.intro" cssClass="ui_input_txtarea"/>
                    </td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td class="ui_text_lt">
                        &nbsp;<input type="submit" value="确定保存" class="ui_input_btn01"/>
                        &nbsp;<input id="cancelbutton" type="button" value="重置" class="ui_input_btn01"/>
                    </td>
                </tr>
            </table>
        </div>
    </div>
</s:form>
</body>
</html>