/**角色列表移动*/
$(function () {
    $("#selectAll").click(function () {
        $(".right_select").append($(".left_select option"));
    });

    $("#deselectAll").click(function () {
        $(".left_select").append($(".right_select option"));
    });

    $("#select").click(function () {
        $(".right_select").append($(".left_select option:selected"));
    });

    $("#deselect").click(function () {
        $(".left_select").append($(".right_select option:selected"));
    });
});

/**左右列表去重*/
$(function () {
    var leftOp = $(".left_select option");
    var rightOp = $(".right_select option");
    var ids = $.map(rightOp, function (item) {
        return item.value;
    });

    $.each(leftOp, function (index, item) {
        var id = item.value;
        if($.inArray(id,ids)>=0){
            $(item).remove();
        }
    });
});

/**右列表默认选中*/
$(function () {
    $("#editForm").submit(function(){
        $(".right_select option").prop("selected",true)
    });
});

/**员工表单校验*/
$(function () {
    if ($("#editForm").length > 0) {
        $("#editForm").validate({
            rules: {
                'employee.name': {
                    required: true,
                    rangelength: [2, 8]
                },
                'employee.password': {
                    required: true,
                    minlength: 6,
                    maxlength: 12
                },
                'repassword': {
                    equalTo: ":input[name='employee.password']"
                },
                'employee.email': {
                    email: true
                },
                'employee.age': {
                    range: [18, 60]
                }
            },
            messages: {
                'employee.name': {
                    required: "用户名不能为空",
                    rangelength: "用户名长度必须在{0}到{1}个字符之间"
                },
                'employee.password': {
                    required: "密码不能为空",
                    minlength: "密码长度必须大于{0}个字符",
                    maxlength: "密码长度必须小于{0}个字符",
                },
                'repassword': {
                    equalTo: "两次输入的密码不一致"
                },
                'employee.email': {
                    email: "请输入正确的邮箱"
                },
                'employee.age': {
                    range: "年龄必须在{0}到{1}之间"
                }
            }
        });
    }
});