/**
 * zTree树菜单
 */
var setting = {
    data: {
        simpleData: {
            enable: true,//使用简单JSON数据
        }
    },
    callback: {
        onClick: function (event, treeId, treeNode) {
            if (treeNode.action) {
                $("#rightMain").prop("src", treeNode.action + ".action");
                $("#here_area").text("当前位置：系统>>" + treeNode.name);
            }
        }
    },
    async: {
        enable: true,
        url: "/systemMenu_loadMenusByParentSn.action",
        autoParam: ["sn=qo.parentSn"],
    }
};


var zNodes = {
    'business': [
        {id: 1, name: "业务管理", isParent: true, sn: 'business'},
    ],
    'system': [
        {id: 2, name: "系统管理", isParent: true, sn: 'system'},
    ],
    'chart': [
        {id: 3, name: "报表管理", isParent: true, sn: 'chart'},
    ],
};

function loadMenu(sn) {
    $.fn.zTree.init($("#dleft_tab1"), setting, zNodes[sn]);
}


/**
 *菜单列表和标题切换
 */
$(function () {
    loadMenu('business');//默认加载业务管理
    $("#TabPage2 li").click(function () {
        $.each($("#TabPage2 li"), function (index, item) {
            $(item).removeClass("selected");
            $(item).children("img").prop("src", "images/common/" + (index + 1) + ".jpg");
        });
        $(this).addClass("selected");
        $(this).children("img").prop("src", "images/common/" + ($(this).index() + 1) + "_hover.jpg");
        $("#nav_module").children("img").prop("src", "images/common/module_" + ($(this).index() + 1) + ".png");
        loadMenu($(this).data("rootmenu"));
    });
})


//加载当前日期
function loadDate() {
    var time = new Date();
    var myYear = time.getFullYear();
    var myMonth = time.getMonth() + 1;
    var myDay = time.getDate();
    if (myMonth < 10) {
        myMonth = "0" + myMonth;
    }
    document.getElementById("day_day").innerHTML = myYear + "." + myMonth + "." + myDay;
}

/**
 * 隐藏或者显示侧边栏
 *
 **/
function switchSysBar(flag) {
    var side = $('#side');
    var left_menu_cnt = $('#left_menu_cnt');
    if (flag == true) {	// flag==true
        left_menu_cnt.show(500, 'linear');
        side.css({width: '280px'});
        $('#top_nav').css({width: '77%', left: '304px'});
        $('#main').css({left: '280px'});
    } else {
        if (left_menu_cnt.is(":visible")) {
            left_menu_cnt.hide(10, 'linear');
            side.css({width: '60px'});
            $('#top_nav').css({width: '100%', left: '60px', 'padding-left': '28px'});
            $('#main').css({left: '60px'});
            $("#show_hide_btn").find('img').attr('src', '/images/common/nav_show.png');
        } else {
            left_menu_cnt.show(500, 'linear');
            side.css({width: '280px'});
            $('#top_nav').css({width: '77%', left: '304px', 'padding-left': '0px'});
            $('#main').css({left: '280px'});
            $("#show_hide_btn").find('img').attr('src', '/images/common/nav_hide.png');
        }
    }
}

$(function () {
    loadDate();

    // 显示侧边栏
    switchSysBar(true);
    // 显示隐藏侧边栏
    $("#show_hide_btn").click(function () {
        switchSysBar();
    });
});