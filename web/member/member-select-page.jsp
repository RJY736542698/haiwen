<%@ page import="com.beans.CateInfo" %>
<%@ page import="com.dao.CateDao" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>

<head>
    <base href="<%=basePath%>"/>
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

        .result_msg {
            font-size: 12px;
            font-weight: bold;
            color: blue
        }

        #divpicture {
            width: 110px;
            height: 25px;
            font-size: 15px;
            border: 1px solid #8b9aa3;
            cursor: pointer;
        }
    </style>
</head>

<body id="pagestyle" class="padding15">
<div class="Bombbox-info Tab-Module">
    <div class="tab-box tabcontent">
        <form action="MemberServlet" method="post" name="myform" >
            <div class="tab-conent prompt_style active">
                <input type="hidden" value="manage" name="flag">
                <ul class="clearfix add_style">
                    <li class="cell-item">
                        <label class="cell-left label_name" style="font-size: 12px;color:#8FA0AC;">会员账号:</label>
                        <div class="cell-right">
                            <input type="text" style="font-size:12px;color:#8FA0AC;" class="col-xs-6 col-lg-12"
                                     value="${member.memberNo}" readonly>
                        </div>
                    </li>
                    <li class="cell-item">
                        <label class="cell-left label_name" style="font-size: 12px;color:#8FA0AC;">会员姓名:</label>
                        <div class="cell-right">
                            <input  value="${member.memberName }" type="text" class="col-xs-6 col-lg-12" readonly>
                        </div>
                    </li><li class="cell-item">
                        <label class="cell-left label_name" style="font-size: 12px;color:#8FA0AC;">联系电话:</label>
                        <div class="cell-right">
                            <input value="${member.phone }" type="text" class="col-xs-6 col-lg-12" readonly>
                        </div>
                    </li><li class="cell-item">
                        <label class="cell-left label_name" style="font-size: 12px;color:#8FA0AC;">电子邮箱:</label>
                        <div class="cell-right">
                            <input  value="${member.email }" type="text" class="col-xs-6 col-lg-12" readonly>
                        </div>
                    </li><li class="cell-item">
                        <label class="cell-left label_name" style="font-size: 12px;color:#8FA0AC;">注册日期:</label>
                        <div class="cell-right">
                            <input  value="${member.registerDate }" type="text" class="col-xs-6 col-lg-12" readonly>
                        </div>
                    </li><li class="cell-item">
                        <label class="cell-left label_name" style="font-size: 12px;color:#8FA0AC;">身份证号:</label>
                        <div class="cell-right">
                            <input value="${member.idCard }" type="text" class="col-xs-6 col-lg-12" readonly>
                        </div>
                    </li><li class="cell-item">
                        <label class="cell-left label_name" style="font-size: 12px;color:#8FA0AC;">登陆次数:</label>
                        <div class="cell-right">
                            <input  value="${member.loginCounts }" type="text" class="col-xs-6 col-lg-12" readonly>
                        </div>
                    </li><li class="cell-item">
                        <label class="cell-left label_name" style="font-size: 12px;color:#8FA0AC;">最后登陆时间:</label>
                        <div class="cell-right">
                            <input value="${member.lastLoginDate }" type="text" class="col-xs-6 col-lg-12" readonly>
                        </div>
                    </li><li class="cell-item">
                        <label class="cell-left label_name" style="font-size: 12px;color:#8FA0AC;">会员常用ip:</label>
                        <div class="cell-right">
                            <input  value="${member.ip }" type="text" class="col-xs-6 col-lg-12" readonly>
                        </div>
                    </li>
                    <li class="cell-item">
                        <label class="cell-left label_name" style="font-size: 12px;color:#8FA0AC;">会员等级:</label>
                        <div class="cell-right">
                            <input  value="${member.memberLevel }" type="text" class="col-xs-6 col-lg-12" readonly>
                        </div>
                    </li>
                </ul>
                <div class="buttonstyle">
                    <button id="form_btn" class="btn padding10 bg-deep-blue">返回</button>
                </div>
                <div style="height: 500px"></div>
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

            function () {
                $(this).css("color", "#295568").css("background", "#BAD9E3");
            });

    })

</script>

