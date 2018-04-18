<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<script type="text/javascript" src="js/plugin/artDialog/jquery.artDialog.js?skin=black"></script>
<script type="text/javascript">

    <s:if test="hasActionErrors()">
        var msg = '<s:property value="errorMessages[0]"/>';
        $.dialog({
            title:"操作提示",
            icon:'face-sad',
            content:msg,
            ok:true,
            drag:false,//不可拖动
            resize:false,//大小不可变
        });
    </s:if>
    <s:if test="hasActionMessages()">
        var msg = '<s:property value="actionMessages[0]"/>';
        $.dialog({
            title:"操作提示",
            icon:'face-smile',
            content:msg,
            ok:true,
            drag:false,//不可拖动
            resize:false,//大小不可变
        });
    </s:if>
</script>