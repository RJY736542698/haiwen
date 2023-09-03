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
    <meta charset="UTF-8">
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
    <title>用户维护</title>
    <script src="js/jquery-1.9.1.min.js" type="text/javascript"></script>
    <script src="js/jquery.nicescroll.js" type="text/javascript"></script>
    <script src="js/HUpages.js" type="text/javascript"></script>
    <script src="js/common.js" type="text/javascript"></script>
    <style>
        a.link:hover{
            color: red;
        }
    </style>
    <style>
        .div_page {
            width: 98%;
            margin: 0px auto;
            margin-top: 8px;
            font-size: 12px;
            height: 20px;
            color: #295568;
        }


        .div_page_left {
            float: left;
        }

        .div_page_right {
            float: right;
        }

        .div_page_right button {
            /*background:url(../images/btn_bg.jpg) repeat;*/
            border: 1px solid #8FB9D0;
            font-size: 12px;
            margin-left: 8px;
            line-height: 14px;
            color: #295568;
            cursor: pointer;
        }

        .div_page_right button:hover {
            color: red;
        }


        .div_page_left label {
            font-weight: bold; /*页码加粗显示*/
            color: red;
        }

        .div_page_right input {
            width: 20px;
            border: 1px solid #8FB9D0;

        }
    </style>
</head>

<body id="situation">
<div class="pages-style">
    <!--右侧内容-->
    <div class="bk-con-message message-section" id="iframe_box">
        <!--编辑内容-->
        <form id="myform" action="AdminServlet?flag=deletesAdmin" method="post">
            <div class="page_content clearfix mb15 table-module ">
                <table class="gallery table table_list table_striped table-bordered " id="">
                    <thead>
                    <tr>
                        <th>角色名称</th>
                        <th>角色描述</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="role" items="${roleList}">
                        <tr>
                            <td>${role.roleName}</td>
                            <td>${role.des}</td>
                            <td>
                                <a href="RoleServlet?flag=goUpdateRole&roleId=${role.id}">修改</a>|
                                <a href="RoleServlet?flag=deleteRole&roleId=${role.id}&pageIndex=${page.pageIndex}" onclick="godelete()">删除</a>|
                                <a href="RoleServlet?flag=roleMenu&id=${role.id}">角色权限分配</a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <div class="div_page">
                    <div class="div_page_left"> 共有 <label>${page.rowCount }</label> 条记录，当前第
                        <label>${page.pageIndex }</label> 页，共 <label>${page.pageCount }</label> 页
                    </div>
                    <div class="div_page_right">
                        <c:choose>
                            <c:when test="${page.hasPre }">
                                <button onclick="subForm(1); return false">首页</button>
                                <button onclick="subForm(${page.pageIndex-1}); return false">上一页</button>
                            </c:when>
                            <c:otherwise>
                                首页
                                上一页
                            </c:otherwise>
                        </c:choose>
                        <c:choose>
                            <c:when test="${page.hasNext }">
                                <button onclick="subForm(${page.pageIndex+1}); return false">下一页</button>
                                <button onclick="subForm(${page.pageCount});return false">尾页</button>
                            </c:when>
                            <c:otherwise>
                                下一页
                                尾页
                            </c:otherwise>
                        </c:choose>
                        <button onclick="subForm(pageIndex.value)">转到</button>
                        <input type="text" name="pageIndex" id="pageIndex" value="${page.pageIndex }"/> 页
                    </div>
                </div>
                <div style="height: 300px"></div>
            </div>
        </form>
    </div>

</div>
</body>
</html>
<script>
    $(function () {
        //内页框架结构编辑
        $("#situation").Hupage({
            slide: '#breadcrumb',
            padding: 15,
            menuModule: '#bk-con-menu', //菜单模块
            pagecontent: '.page_content',//自定义属性
            operation: '.operation',//自定义属性
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
                var pc = "";
                $(settings.pagecontent).css("margin-bottom") != null ? pc = parseInt($(settings.pagecontent).css("margin-bottom").replace("px", "")) : '';
                $(settings.pagecontent).css({height: $(window).height() - $(settings.operation).outerHeight() - pc - (settings.padding * 2)})
                settings.scrollbar($(settings.slide) && $(settings.pagecontent));
            }//自定义方法
        });
    });
</script>
<script type="text/javascript">
    $(function () {
        $("table tr").mouseover(function () {
            $(this).css("background", "#D3EAEF");
            $(this).siblings().css("background", "white");
        });
    });
    function subForm(pageIndex) {
        window.location.href = "RoleServlet?flag=manage&pageIndex=" + pageIndex;
    }
</script>
<script>
    function godelete(){
        var yn=confirm("确认删除麽？")
        var str=${msg}
        console.log(str);
        if(yn=true){
            if(str==1){
                // alert("删除成功！");
                alert("该用户正在使用，不允许被删除！！！");
            }else {
                alert("删除成功！");
                // alert("该用户正在使用，不允许被删除！！！");
            }
        }
    }
</script>


