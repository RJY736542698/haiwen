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
<%--    <ul class="tab_memu box">--%>
<%--        <li class="boxbox-flex2">--%>
<%--            <a href="javascript:void(0)" class="memu_title btn btn-border clickBombbox selected" data-id="0">基本设置</a>--%>
<%--        </li>--%>
<%--        <li class="boxbox-flex2">--%>
<%--            <a href="javascript:void(0)" class="memu_title btn btn-border clickBombbox" data-id="1">邮件设置</a>--%>
<%--        </li>--%>
<%--        <li class="boxbox-flex2">--%>
<%--            <a href="javascript:void(0)" class="memu_title btn btn-border clickBombbox" data-id="2">联系方式设置</a>--%>
<%--        </li>--%>
<%--    </ul>--%>
    <div style="width: 100%;height: 20px;font-size: 10px;background: #07B074; color:white">订单商品</div>
    <table class="gallery table table_list table_striped table-bordered ">
        <thead>
        <tr>
            <th>商品名称</th>
            <th>单位</th>
            <th>单价</th>
            <th>购买数量</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="orderGoods" items="${orderGoodsList}">
        <tr>
            <td>${orderGoods.goodsName}</td>
            <td>${orderGoods.unit}</td>
            <td>${orderGoods.price}</td>
            <td>${orderGoods.saleCount}</td>
        </tr>
        </c:forEach>
        </tbody>
    </table>
    <div style="width: 100%;height: 20px;font-size: 10px;background: #07B074; color:white">订单详情表</div>
    <div class="tab-box tabcontent">
        <form action="OrderServlet" method="post" name="myform" >
            <div class="tab-conent prompt_style active">
                <input type="hidden" value="manage" name="flag">
                <ul class="clearfix add_style">
                    <li class="cell-item">
                        <label class="cell-left label_name" style="font-size: 12px;color:#8FA0AC;">订单单号:</label>
                        <div class="cell-right">
                            <div style="width: 400px">
                                <input style="margin-right: 400px" type="text" value="${order.orderNo}" readonly>
                            </div>
                            <label class="cell-left label_name" style="font-size: 12px;color:#8FA0AC;">快递邮费:</label>
                            <input  value="${order.postage }" type="text" readonly>

                        </div>
                    </li><li class="cell-item">
                        <label class="cell-left label_name" style="font-size: 12px;color:#8FA0AC;">付款方式:</label>
                        <div class="cell-right">
                            <div style="width: 400px">
                                <input style="margin-right: 400px" type="text" value="${order.payMethod}" readonly>
                            </div>
                            <label class="cell-left label_name" style="font-size: 12px;color:#8FA0AC;">邮递方式:</label>
                            <input  value="${order.postMethod }" type="text" readonly>

                        </div>
                    </li><li class="cell-item">
                        <label class="cell-left label_name" style="font-size: 12px;color:#8FA0AC;">下单日期:</label>
                        <div class="cell-right">
                            <div style="width: 400px">
                                <input style="margin-right: 400px" type="text" value="${order.orderDate}" readonly>
                            </div>
                            <label class="cell-left label_name" style="font-size: 12px;color:#8FA0AC;">订单金额:</label>
                            <input  value="${total }" type="text" readonly>

                        </div>
                    </li><li class="cell-item">
                        <label class="cell-left label_name" style="font-size: 12px;color:#8FA0AC;">订单状态:</label>
                        <div class="cell-right">
                            <div style="width: 400px">
                                <input style="margin-right: 400px" type="text" value="${order.orderState}" readonly>
                            </div>
                            <label class="cell-left label_name" style="font-size: 12px;color:#8FA0AC;">发货日期:</label>
                            <input  value="${order.sendDate }" type="text" readonly>

                        </div>
                    </li><li class="cell-item">
                        <label class="cell-left label_name" style="font-size: 12px;color:#8FA0AC;">会员ID:&nbsp;&nbsp;</label>
                        <div class="cell-right">
                            <div style="width: 400px">
                                <input style="margin-right: 400px" type="text" value="${order.memberNo}" readonly>
                            </div>
                            <label class="cell-left label_name" style="font-size: 12px;color:#8FA0AC;">发货地址:</label>
                            <textarea id="myTextarea" rows="10" cols="40" style="width: 200px; "  readonly>${order.address}</textarea>

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
<style>
    textarea {
        resize: none; /* 禁止用户手动调整大小 */
        overflow: hidden; /* 初始时隐藏溢出的文本 */

        /* 设置文本域高度自动调整 */
        height: auto;
        min-height: 30px; /* 设置最小高度 */
        max-height: 70px; /* 设置最大高度 */
    }
</style>
