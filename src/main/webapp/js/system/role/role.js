/**权限列表移动*/
$(function(){
    $("#selectAll").click(function(){
        $(".right_select").append($(".left_select option"));
    });

    $("#deselectAll").click(function(){
        $(".left_select").append($(".right_select option"));
    });

    $("#select").click(function(){
        $(".right_select").append($(".left_select option:selected"));
    });

    $("#deselect").click(function(){
        $(".left_select").append($(".right_select option:selected"));
    });
});

/**菜单列表移动*/
$(function(){
    $("#mselectAll").click(function(){
        $(".right_menu_select").append($(".left_menu_select option"));
    });

    $("#mdeselectAll").click(function(){
        $(".left_menu_select").append($(".right_menu_select option"));
    });

    $("#mselect").click(function(){
        $(".right_menu_select").append($(".left_menu_select option:selected"));
    });

    $("#mdeselect").click(function(){
        $(".left_menu_select").append($(".right_menu_select option:selected"));
    });
});


/**权限左右列表去除重复*/
$(function(){
    var leftOp=($(".left_menu_select option"));
    var rightOp=($(".right_menu_select option"));
    var ids=$.map(rightOp,function (item) {
        return item.value;
    });
    $.each(leftOp,function(index,item){
        var id=item.value;
        if($.inArray(id,ids)>=0){
            $(item).remove();
        }
    });
})

/**菜单左右列表去除重复*/
$(function(){
    var leftOp=($(".left_select option"));
    var rightOp=($(".right_select option"));
    var ids=$.map(rightOp,function (item) {
        return item.value;
    });
    $.each(leftOp,function(index,item){
        var id=item.value;
        if($.inArray(id,ids)>=0){
            $(item).remove();
        }
    });
})

/**权限、菜单右列表默认选中*/
$(function () {
    $("#editForm").submit(function(){
        $(".right_select option").prop("selected",true);
        $(".right_menu_select option").prop("selected",true);
    });
});