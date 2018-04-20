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
    <script type="text/javascript" src="js/system/supplier/supplier.js"></script>

</head>
<body>
<%@ include file="/WEB-INF/view/common/common_msg.jsp" %>
<s:form name="editForm" namespace="/" action="supplier_saveOrUpdate" method="post" id="editForm">
    <s:hidden name="supplier.id"/>
    <div id="container">
        <div id="box_top">供应商信息</div>
        <div class="ui_content">
            <table cellspacing="0" cellpadding="0" width="100%" align="left" border="0">
                <tr>
                    <td class="ui_text_rt" width="140">供应商名称</td>
                    <td class="ui_text_lt">
                        <s:textfield name="supplier.name" cssClass="ui_input_txt02"/>
                    </td>
                </tr>
                <tr>
                    <td class="ui_text_rt" width="140">供应商电话</td>
                    <td class="ui_text_lt">
                        <s:textfield name="supplier.phone" cssClass="ui_input_txt02"/>
                    </td>
                </tr>
                <tr>
                    <td class="ui_text_rt" width="140">供应商地址</td>
                    <td class="ui_text_lt">
                        <s:textfield name="supplier.addres" cssClass="ui_input_txt02"/>
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