<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>

<head>
    <base href="<%=basePath%>" />
    <meta name="viewport"
          content="maximum-scale=1.0,minimum-scale=1.0,user-scalable=0,width=device-width,initial-scale=1.0"/>
    <meta name="format-detection" content="telephone=no, email=no, date=no, address=no">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="format-detection" content="telephone=no"/>
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta content="black" name="apple-mobile-web-app-status-bar-style">
    <link href="css/bksystem.css" rel="stylesheet" type="text/css"/>
    <link href="font/iconfont.css" rel="stylesheet" type="text/css"/>
    <link href="css/module.css" rel="stylesheet" type="text/css"/>
    <link href="css/pages.css" rel="stylesheet" type="text/css"/>
    <title>系统设置</title>
    <script src="js/jquery-1.9.1.min.js" type="text/javascript"></script>
    <script src="js/jquery.cookie.js" type="text/javascript"></script>
    <script src="js/jquery.nicescroll.js" type="text/javascript"></script>
    <script src="js/HUpages.js" type="text/javascript"></script>
    <script src="js/common.js" type="text/javascript"></script>
    <style>
        .input_focus {
            background: #D1EAF9;
        }
        .result_msg{
            font-size:12px;
            font-weight: bold;
            color:blue
        }
    </style>
</head>

<body id="pagestyle" class="padding15">
<div class="Bombbox-info Tab-Module">
    <div class="tab-box tabcontent">
        <form action="RoleServlet" method="post" name="myform" id="myForm">
        <div class="tab-conent prompt_style active">
            <input type="hidden" value="roleadd" name="flag" >
            <ul class="clearfix add_style">
                <li class="cell-item">
                    <label class="cell-left label_name" style="font-size: 12px;color:#8FA0AC;" >角色名称:</label>
                    <div class="cell-right">
                        <input type="text" style="font-size:12px;color:#8FA0AC;" class="col-xs-6 col-lg-12" onblur="checkAdminName()" id="roleName"
                                name="roleName"  value="${param.roleName}">
                        <span id="isExit"></span>
                    </div>
                </li>
                <li class="cell-item">
                    <label class="cell-left label_name" style="font-size: 12px;color:#8FA0AC;">角色描述:</label>
                    <div class="cell-right">
                        <textarea  name="des"  class="textarea col-xs-4 col-lg-12 height200">${param.des}</textarea>
                    </div>
                </li>
            </ul>
            <div class="buttonstyle">
                <label id="result_msg" class="result_msg">  ${msg}</label>&nbsp;&nbsp;
                <button id="form_btn" class="btn padding10 bg-deep-blue"   onclick="return confirm('确定提交吗?')">保存添加</button>
            </div>
        </div>
        </form>
    </div>

</div>
</body>

</html>
<script>
    //内页框架
    $(function () {
        $("#pagestyle").Hupage({
            pagecontent: '.Bombbox-info',//自定义属性
            tabcontent: '.tabcontent',
            tabmemu: '.tab_memu',
            scrollbar: function (e) {
                e.niceScroll({
                    cursorcolor: "#dddddd",
                    cursoropacitymax: 1,
                    touchbehavior: false,
                    cursorwidth: "3px",
                    cursorborder: "0",
                    cursorborderradius: "3px",
                });
            },
            expand: function (thisBox, settings) {
                $(settings.pagecontent).css({height: $(window).height() - 30});
                settings.scrollbar($(settings.tabcontent).css({height: $(window).height() - $(settings.tabmemu).outerHeight() - 30}));
            }
        })
    })
    $(function () {
        $("input,textarea").focus(function () {
            $(this).addClass("input_focus");
        }).blur(function () {
            $(this).removeClass("input_focus");
        });
        $("#form_btn").hover(function () {
                $(this).css("color", "red").css("background", "#6FB2DB");
            },

            function (){
                $(this).css("color", "#295568").css("background", "#BAD9E3");
            });

    })
</script>
<script>
    function checkAdminName() {
        var roleName=document.getElementById("roleName")
                $.ajax({
                    url:"RoleServlet",
                    type:"post",
                    data:{flag:"isExit",roleName:roleName.value},
                    success:function (data) {
                        if (data=="ok"){
                            $("#isExit").html("<font color=green size=5>√</font>");
                            return true;
                        }else{
                            $("#isExit").html("<font color=red size=5>×</font>");
                            alert("该角色已存在，请修改！");
                            return false;
                        }
                    }
                })
    }
</script>
<script>
    document.getElementById("form_btn").addEventListener("click", function (event) {
        event.preventDefault(); // 阻止默认的表单提交行为
        var form = document.getElementById("myForm");
        var roleName = document.getElementById("roleName")
        if (roleName.value.trim() === "") {
            alert("请填写所添加的用户角色！");
        } else {
            // 提交表单
            form.submit();
        }
    });
</script>