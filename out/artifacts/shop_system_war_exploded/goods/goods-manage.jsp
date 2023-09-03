<%@ page import="com.dao.CateDao" %>
<%@ page import="com.beans.CateInfo" %>
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
    <title>商品维护</title>
    <script src="js/jquery-1.9.1.min.js" type="text/javascript"></script>
    <script src="js/jquery.nicescroll.js" type="text/javascript"></script>
    <script src="js/HUpages.js" type="text/javascript"></script>
    <script src="js/common.js" type="text/javascript"></script>
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
        .div_a:hover::after{
            opacity: 1;
        }
        a.link:hover{
            color: red;
        }
        #preview{
            width: 250px;
            height: 300px;
        }
    </style>
</head>

<body id="situation">
<div class="pages-style">
    <!--右侧内容-->
    <div class="bk-con-message message-section" id="iframe_box">
        <!--编辑内容-->
        <form id="myform" action="GoodsServlet" method="post">
            <input type="hidden" name="flag" value="selectGoods">
            <div class="operation  mb15">
<%--                <button class="btn button_btn btn-danger" type="button" onclick="deletes()"><i class="iconfont"></i>&nbsp;批量删除--%>
<%--                </button>--%>
    <%
        CateDao cateDao=new CateDao();
        List<CateInfo> cateList = cateDao.getCateList(0);
        request.setAttribute("cateList",cateList);
    %>
                    <select id="bigCateId" name="bigCateId">
                        <option value="0">大分类</option>
                        <c:forEach var="bigcate" items="${cateList}">
                            <option value="${bigcate.id}">${bigcate.cateName}</option>
                        </c:forEach>
                    </select>
                <select id="smallCateId" name="smallCateId">
                    <option selected="selected" value="0">小分类</option>
                </select>
                <input type="text" name="goodsName" placeholder="查询商品名称">
                <button name="select">查询</button>
            </div>
            <!--列表内容-->
            <div class="page_content clearfix mb15 table-module ">
                <table class="gallery table table_list table_striped table-bordered " id="">
                    <thead>
                    <tr>
                        <th><label><input type="checkbox" name="ids" onclick='selectcheckbox(this.form)'
                                          class="ace"><span class="lbl"></span></label></th>
                        <th>名称</th>
                        <th>单位</th>
                        <th>价格</th>
                        <th>大分类</th>
                        <th>小分类</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="goods" items="${goodsList}">
                        <tr>
                            <td><label><input type="checkbox" id="id" name="id" value="${goods.id}" class="ace">
                                <span class="lbl"></span></label></td>
                            <td>${goods.goodsName}</td>
                            <td>${goods.unit}</td>
                            <td>${goods.price}</td>
                            <td>${goods.bigCateName}</td>
                            <td>${goods.smallCateName}</td>
                            <td>
                                <a href="javascript:void (0)" id="image" onmouseover="showDetails(${goods.id})" onmouseout="hideDetail()" >查看</a>
                                |<a href="GoodsServlet?flag=modifyGoods&goodsId=${goods.id}">修改</a>
                                |<a href="GoodsServlet?flag=deleteGoods&id=${goods.id}&pageIndex=${page.pageIndex }" onclick="return confirm('确定删除麼?')">删除</a>
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
                <div id="image-container" style="width: 50px;height: 100px;margin-left: 500px;"></div>
                    </div>
                <div style="height: 1000px"></div>
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
</script>

<script>
    function subForm(pageIndex) {
        window.location.href = "GoodsServlet?flag=manage&pageIndex=" + pageIndex;
    }
    function selectcheckbox(form) {
        for (var i = 0; i < form.elements.length; i++) {
            var e = form.elements[i];
            if (e.name != 'ids' && e.disabled != true) e.checked = form.ids.checked;
        }
    }
</script>
<%--下拉框--%>
<script>
    $(function () {
        $("#bigCateId").on("change",function () {
            var bigCate=$(this).val();
            if(bigCate==0){
                $("#smallCateId>option").remove();
                $("#smallCateId").append(
                    "<option selected value=0" + "> 小分类"  + "</option>")
                console.log(bigCate);
            }
            else {
                $.ajax({
                    url: "CateServlet",
                    data: {flag: "smallCate", bigCateId: bigCate},
                    type: "post",
                    dataType: "json",
                    success: function (data) {
                        console.log(data)
                        $("#smallCateId>option").remove();
                        for (var i = 0; i < data.length; i++) {
                            var d = data[i];
                            console.log(d.id)
                            console.log(d.cateName)
                            $("#smallCateId").append("<option value=" + d.id + ">" + d.cateName + "</option>")
                        }
                    }
                })
            }
            })

    })
</script>
<script>
    function showDetails(id) {
                // $("#image").append("<div id='image-container'>"+"<img id='preview' src="+'GoodsServlet?flag=showPicture&goodsId=' + id + ">"+"</div>")
                $("#image-container").append("<img id='preview' src="+'GoodsServlet?flag=showPicture&goodsId=' + id + ">")
    }
    function hideDetail() {
        $("#preview").remove()
    }
</script>