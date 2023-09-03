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

<body id="situation">
<div class="pages-style">
    <!--右侧内容-->
    <div class="bk-con-message message-section" id="iframe_box">
        <div class="operation  mb15">
            <span class="btn button_btn bg-deep-blue" href="admin/admin-add.jsp">
                <i class="iconfont"></i>
                &nbsp;${roleInfo.roleName}
            </span>
        </div>
        <!--编辑内容-->
        <form id="myform" action="RoleServlet" method="post">
            <!--列表内容-->
            <div class="page_content clearfix mb15 table-module ">
                <table class="gallery table table_list table_striped table-bordered " id="">
                    <input type="hidden" name="id" value="${roleInfo.id}">
                    <input type="hidden" name="flag" value="updateRoleMenu">
                    <thead >
                    <tr>
                        <th style="width: 30%">一级菜单</th>
                        <th style="width: 70%">二级菜单</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="menu" items="${menuList}">
                        <tr>
                            <td><input type="checkbox" class="parent-checkbox" name="ids"  value="${menu.id }">${menu.menuName}</td>
                            <td>
                            <c:forEach var="m_sub" items="${menu.subMenuList}">
                                <input type="checkbox"  class="child-checkbox"  name="ids"  value="${m_sub.id }">${m_sub.menuName}<br>
                            </c:forEach>
                        </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <div class="buttonstyle">
                    <label id="result_msg" class="result_msg">  ${msg}</label>&nbsp;&nbsp;
                    <button id="form_btn" class="btn padding10 bg-deep-blue"   onclick="return confirm('确定修改吗?')">保存修改</button>
                    <div style="height: 300px"></div>
                </div>
            </div>
        </form>
    </div>

</div>
</body>
</html>
<%--勾选好原有的权限--%>
<script>
    //获取服务端传过来的菜单ID 字符串类型
    var menuIdStr="${menuIdStr}";
    //将字符串转成数组 中间有 ，按照 ， 拆分 使用split()
    var array=menuIdStr.split(",");
    //数组中没有 contains()方法 需要自己定义
    Array.prototype.contains=function contains(e){
        for(var i=0;i<this.length;i++){
            if(this[i]==e)
                return true;
        }
        return false;
    }
    //判断 checkbox 中的 type="checkbox" 是否在数组里 如果在数组里则默认勾选
    $("[type=checkbox]").each(function(){
        console.log(this.value);
        if(array.contains(this.value)){
            this.checked=true
        }
    });
</script>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
    $(document).ready(function() {
        // 事件委托，处理复选框点击事件
        $(document).on('click', '.parent-checkbox', function() {
            var isChecked = $(this).prop('checked'); // 获取一级复选框的选中状态
            $(this).closest('td').nextUntil('tr:has(.parent-checkbox)').find('.child-checkbox').prop('checked', isChecked); // 控制二级复选框的选中状态
        });

        $(document).on('click', '.child-checkbox', function() {
            var isChecked = $(this).prop('checked'); // 获取二级复选框的选中状态
            $(this).closest('td').prevAll('td:has(.parent-checkbox)').first().find('.parent-checkbox').prop('checked', isChecked); // 更新上级一级复选框的选中状态
        });
    });
</script>

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
<script>
</script>

<script type="text/javascript">
    $(function () {
        $("table tr").mouseover(function () {
            $(this).css("background", "#D3EAEF");
            $(this).siblings().css("background", "white");
        });
    });
</script>


