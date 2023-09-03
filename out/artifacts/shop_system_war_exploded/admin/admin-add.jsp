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
    <ul class="tab_memu box">
        <li class="boxbox-flex2">
            <a href="javascript:void(0)" class="memu_title btn btn-border clickBombbox selected" data-id="0">基本设置</a>
        </li>
        <li class="boxbox-flex2">
            <a href="javascript:void(0)" class="memu_title btn btn-border clickBombbox" data-id="1">邮件设置</a>
        </li>
        <li class="boxbox-flex2">
            <a href="javascript:void(0)" class="memu_title btn btn-border clickBombbox" data-id="2">联系方式设置</a>
        </li>
    </ul>
    <div class="tab-box tabcontent">
        <form action="AdminServlet" id="myForm" method="post" name="myform">
        <div class="tab-conent prompt_style active">
            <input type="hidden" value="adminadd" name="flag" >
            <ul class="clearfix add_style">
                <li class="cell-item">
                    <label class="cell-left label_name" style="font-size: 12px;color:#8FA0AC;" >用户账号:</label>
                    <div class="cell-right">
                        <input type="text" style="font-size:12px;color:#8FA0AC;" class="col-xs-6 col-lg-12" onblur="checkAdminName()"
                               id="adminName" name="adminName"  value="${param.adminName }">
                        <span id="isExit"></span>
                        <label class="validate_info" style="font-size:12px;color:#8FA0AC; margin-left: 70px" id="adminName_msg">4-15位；只限数字(0-9)和英文(a-z),不区分大小写</label>
                    </div>
                </li>
                <li class="cell-item">
                    <label class="cell-left label_name" style="font-size: 12px;color:#8FA0AC;">用户密码:</label>
                    <div class="cell-right">
                        <input name="password" id="password" onblur="checkPassword()"
                               value="${param.password }" type="text" class="col-xs-6 col-lg-12">
                        <span id="isPassword"></span>
                        <label class="validate_info" style="font-size:12px;color:#8FA0AC; margin-left: 70px"
                               id="password_msg">数字或英文,6-20位</label>
                    </div>
                </li>
                <li class="cell-item">
                    <label class="cell-left label_name" style="font-size: 12px;color:#8FA0AC;">重复密码:</label>
                    <div class="cell-right">
                        <input id="pwdconfirm" type="text" onblur="checkBpwd()"
                               value="${param.password}" class="col-xs-6 col-lg-12">
                        <span id="isPwdconfirm"></span>
                        <label class="validate_info" style="font-size:12px;color:#8FA0AC; margin-left: 70px"
                               id="pwdconfirm_msg">请重复输入密码</label>
                    </div>
                </li>
                <li class="cell-item">
                    <label class="cell-left label_name" style="font-size: 12px;color:#8FA0AC;">备注信息:</label>
                    <div class="cell-right">
                        <textarea  name="note" id="note" class="textarea col-xs-4 col-lg-12 height200">${param.note}</textarea>
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
    function checkAdminName() {
        var adminName_msg=document.getElementById("adminName_msg")
        // if ((adminName.value).trim() == "") {
        //     alert("请输入账号");
        //     return false;
        // }else{
            var pattern = /^[0-9A-Za-z]{4,15}$/i;
            if(pattern.test(adminName.value)){
                $.ajax({
                    url:"AdminServlet",
                    type:"post",
                    data:{flag:"isExit",adminName:adminName.value},
                    success:function (data) {
                        if (data=="ok"){
                            adminName_msg.style.color="#8FA0AC";
                            $("#isExit").html("<font color=green size=5>√</font>");
                        }else{
                            $("#isExit").html("<font color=red size=5>×</font>");
                            alert("用户已存在，请修改！");
                        }
                    }
                })
                return true;
            }else{
                $("#isPassword").html("<font color=red size=5>×</font>");
                adminName_msg.style.color="red";
                return false;
                // alert("账号只限4-15位数字(0-9)和英文(a-z),不区分大小写")
            }
        // }
    }
    function checkPassword(){
        var password_msg=document.getElementById("password_msg")
        // if((password.value).trim()==""){
        //     alert("请输入密码");
        //     return false;
        // }else{
            var pattern = /^[0-9A-Za-z]{6,20}$/i;
            if(pattern.test(password.value)){
                password_msg.style.color="#8FA0AC";
                $("#isPassword").html("<font color=green size=5>√</font>");
                return  true
            }else {
                $("#isPassword").html("<font color=red size=5>×</font>");
                // alert("密码只限数字或英文,6-20位")
                password_msg.style.color="red";
                return false;
            }
        // }
    }
    function checkBpwd() {
        var pwdconfirm_msg=document.getElementById("pwdconfirm_msg")
        if (password.value != pwdconfirm.value) {
            $("#isPwdconfirm").html("<font color=red size=5>×</font>");
            pwdconfirm_msg.style.color="red";
            // alert("两次密码不一致！");
            return false;
        }else {
            pwdconfirm_msg.style.color="#8FA0AC";
            $("#isPwdconfirm").html("<font color=green size=5>√</font>");
            return true;
        }
        // document.myform.submit();
    }
<%--</script>--%>

<%--<script>--%>
    document.getElementById("form_btn").addEventListener("click", function(event) {
        event.preventDefault(); // 阻止默认的表单提交行为
        var form = document.getElementById("myForm");
        var adminName = document.getElementById("adminName");
        var password = document.getElementById("password");
        var pwdconfirm = document.getElementById("pwdconfirm");
        var checkName=checkAdminName();
        var checkword = checkPassword();
        var checkpwd = checkBpwd();
        console.log(checkName);
        console.log(checkpwd);
        console.log(checkword);
        // 检查输入字段是否为空值
        if (adminName.value.trim() === "" || password.value.trim() === "" || pwdconfirm.value.trim() === ""  ) {
            alert("请填写所有必填字段！");
        }
        else if(checkAdminName!=true || checkPassword!=true || checkBpwd!=true){
            alert("添加失败，请按规定添加用户！");
        }
        else {

            // 提交表单
            form.submit();
        }
    });
</script>