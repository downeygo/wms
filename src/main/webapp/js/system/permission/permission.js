$(function () {
    $(".btn_reload").click(function(){
        var url=$(this).data("url");
        $.dialog({
            title:"操作提示",
            icon:"question",
            content:"加载权限可能会耗费大量时间，确定要加载吗?",
            cancel:true,
            ok:function(){
                var dialog=$.dialog({
                    title:"操作提示",
                    icon:"succeed",
                });
                $.get(url,function () {
                    dialog.content("加载成功").button({
                        name:"确定",
                        callback:function () {
                            window.location.reload();
                        }
                    });
                })
            }
        });
    });
});