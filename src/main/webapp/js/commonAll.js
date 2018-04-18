/**禁用将表单元素数组或者对象序列化*/
jQuery.ajaxSettings.traditional = true
/**删除*/
$(function () {
    $(".btn_delete").click(function () {
        var url = $(this).data("url");
        $.dialog({
            title: "操作提示",
            icon: "question",
            content: '确定要删除吗?',
            cancel: true,
            ok: function () {
                var dialog = $.dialog({
                    title: "操作提示",
                    icon: "succeed",
                });
                $.get(url, function (data) {
                    dialog.content(data).button({
                        name: "确定",
                        callback: function () {
                            window.location.reload();
                        }
                    });
                });
            }
        });
    });
});

/**批量删除*/
$(function () {
    //获取复选框
    $("#all").click(function () {
        ($(":input[name='IDCheck']")).prop("checked", $(this).prop("checked"));
    });
    //点击批量删除按钮
    $(".btn_batch_delete").click(function () {

        /*  var ids=[];
          $.each($(":input[name='IDCheck']:checked"),function(index,item){
              ids[index]=item.id;
          });*/
        var url=$(this).data("url");
        var ids = $.map($(":input[name='IDCheck']:checked"), function (item) {
            return $(item).data("eid");
        });
        if (ids.length == 0) {
            $.dialog({
                title: "操作提示",
                icon: "warning",
                content: "请选择要删除的数据",
                ok: true
            });
            return;
        }
        $.dialog({
            title: "操作提示",
            icon: "question",
            content: "确定要删除吗?",
            cancel: true,
            ok: function () {
                var dialog = $.dialog({
                    title: "操作提示",
                    icon: "succeed",
                });
                $.get(url, {ids: ids}, function () {
                    dialog.content("删除成功").button({
                        name: "确定",
                        callback: function () {
                            window.location.reload();
                        }
                    });
                });
            }
        });
    });
});

/**高级查询和分页*/
$(function () {
    //页面大小
    $(":input[name='qo.pageSize']").change(function () {
        $(":input[name='qo.currentPage']").val(1);
        $("#searchForm").submit();
    });
    //分页
    $(".btn_page").click(function () {
        var pageNo = $(this).data("page") || $(":input[name='qo.currentPage']").val();
        $(":input[name='qo.currentPage']").val(pageNo);
        $("#searchForm").submit();
    });
    //高级查询
    $(":input[value='查询']").click(function () {
        $("#searchForm").submit();
    });
})

/**点击按钮打开另一个页
 });面*/
$(function () {
    $(".btn_input").click(function () {
        window.location.href = $(this).data("url");
    });
});

/** table鼠标悬停换色* */
$(function () {
    // 如果鼠标移到行上时，执行函数
    $(".table tr").mouseover(function () {
        $(this).css({background: "#CDDAEB"});
        $(this).children('td').each(function (index, ele) {
            $(ele).css({color: "#1D1E21"});
        });
    }).mouseout(function () {
        $(this).css({background: "#FFF"});
        $(this).children('td').each(function (index, ele) {
            $(ele).css({color: "#909090"});
        });
    });
});
