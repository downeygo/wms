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
    <title>WMS-系统菜单管理</title>
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
                    <div id="box_top">系统菜单信息</div>
                    <div id="box_bottom">
                        <s:url var="inputUrl" namespace="/" action="systemMenu_input">
                            <s:param name="qo.parentId" value="qo.parentId"/>
                        </s:url>
                        <input type="button" value="新增" class="ui_input_btn01 btn_input" data-url="<s:property value="inputUrl"/>"/>
                    </div>
                </div>
            </div>
        </div>
        <div class="ui_content">
            <div class="ui_tb">
                当前位置：<s:a namespace="/" action="systemMenu">根目录</s:a>
                <s:iterator value="#menus">
                    >><s:a namespace="/" action="systemMenu">
                        <s:property value="name"/>
                        <s:param name="qo.parentId" value="id"/>
                        <s:param name="systemMenu.id" value="id"/>
                </s:a>
                </s:iterator>
                <table class="table" cellspacing="0" cellpadding="0" width="100%" align="center" border="0">
                    <tr>
                        <th width="30"><input type="checkbox" id="all"/></th>
                        <th>编号</th>
                        <th>菜单名称</th>
                        <th>菜单编号</th>
                        <th>URL</th>
                        <th></th>
                    </tr>
                    <tbody>
                    <s:iterator value="#pageResult.result">
                        <tr>
                            <td><input type="checkbox" name="IDCheck" autocomplete="off" class="acb" data-eid="<s:property value="id"/>"/></td>
                            <td><s:property value="id"/></td>
                            <td><s:property value="name"/></td>
                            <td><s:property value="sn"/></td>
                            <td><s:property value="url"/></td>
                            <td>
                                <%--<s:url var="selectUrl" namespace="/" action="systemMenu">
                                    <s:param name="qo.parentId" value="id"/>
                                </s:url>
                                <a href="<s:property value="selectUrl"/>">查看子菜单</a>--%>

                                <s:a namespace="/" action="systemMenu">
                                    <s:param name="qo.parentId" value="id"/>
                                    <s:param name="systemMenu.id" value="id"></s:param>
                                    查看子菜单
                                </s:a>

                                <s:a namespace="/" action="systemMenu_input">
                                    <s:param name="systemMenu.id" value="id"/>
                                    <s:param name="qo.parentId" value="qo.parentId"/>
                                    编辑
                                </s:a>

                                <s:url var="deleteUrl" namespace="/" action="systemMenu_delete">
                                    <s:param name="systemMenu.id" value="id"/>
                                </s:url>
                                <a href="javascript:;" class="btn_delete" data-url="<s:property value="deleteUrl"/>">删除</a>
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

