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
    <script type="text/javascript" src="js/system/role/role.js"></script>

</head>
<body>
<s:debug/>
<%@ include file="/WEB-INF/view/common/common_msg.jsp"%>
<s:form name="editForm" namespace="/" action="role_saveOrUpdate" method="post" id="editForm">
    <s:hidden name="role.id"/>
    <div id="container">
        <div id="box_top">角色信息</div>
        <div class="ui_content">
            <table cellspacing="0" cellpadding="0" width="100%" align="left" border="0">
                <tr>
                    <td class="ui_text_rt" width="140">角色名称</td>
                    <td class="ui_text_lt">
                        <s:textfield name="role.name" cssClass="ui_input_txt02"/>
                    </td>
                </tr>
                    <tr>
                        <td class="ui_text_rt" width="140">角色编码</td>
                        <td class="ui_text_lt">
                            <s:textfield name="role.sn" cssClass="ui_input_txt02"/>
                        </td>
                    </tr>
                    <tr>
                        <td class="ui_text_rt" width="140">角色</td>
                        <td class="ui_text_lt">
                            <table>
                                <tr>
                                    <td>
                                        <s:select list="#permissions" multiple="true" listValue="name" listKey="id" cssClass="ui_multiselect01 left_select"/>
                                    </td>
                                    <td align="center">
                                        <input type="button" id="select" value="-->" class="left2right"/><br/>
                                        <input type="button" id="selectAll" value="==>" class="left2right"/><br/>
                                        <input type="button" id="deselect" value="<--" class="left2right"/><br/>
                                        <input type="button" id="deselectAll" value="<==" class="left2right"/>
                                    </td>
                                    <td>
                                        <s:select list="role.permissions" name="role.permissions.id" multiple="true" listValue="name" listKey="id" cssClass="ui_multiselect01 right_select"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <s:select list="#menus" multiple="true" listValue="name" listKey="id" cssClass="ui_multiselect01 left_menu_select"/>
                                    </td>
                                    <td align="center">
                                        <input type="button" id="mselect" value="-->" class="left2right"/><br/>
                                        <input type="button" id="mselectAll" value="==>" class="left2right"/><br/>
                                        <input type="button" id="mdeselect" value="<--" class="left2right"/><br/>
                                        <input type="button" id="mdeselectAll" value="<==" class="left2right"/>
                                    </td>
                                    <td>
                                        <s:select list="role.menus" name="role.menus.id" multiple="true" listValue="name" listKey="id" cssClass="ui_multiselect01 right_menu_select"/>
                                    </td>
                                </tr>
                            </table>
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