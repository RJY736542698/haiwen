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
        <form action="GoodsServlet" id="myForm" method="post" name="myform" enctype="multipart/form-data">
            <div class="tab-conent prompt_style active">
                <input type="hidden" value="updateGoods" name="flag">
                <input type="hidden" name="goodsId" value="${goods.id}">
                <ul class="clearfix add_style">
                    <li class="cell-item">
                        <label class="cell-left label_name" style="font-size: 12px;color:#8FA0AC;">商品名称:</label>
                        <div class="cell-right">
                            <input type="text" style="font-size:12px;color:#8FA0AC;" class="col-xs-6 col-lg-12"
                                   onblur="checkGoodsName()"
                                   id="goodsName" name="goodsName" value="${goods.goodsName}">
                            <span id="isgoodsName"></span>
                            <label class="validate_info" style="font-size:12px;color:#8FA0AC; margin-left: 70px"
                                   id="goodsName_msg">2-20位非空白字符</label>
                        </div>
                    </li>
                    <%
                        CateDao cateDao = new CateDao();
                        List<CateInfo> cateList = cateDao.getCateList(0);
                        request.setAttribute("cateList", cateList);
                    %>
                    <li class="cell-item">
                        <label class="cell-left label_name" style="font-size: 12px;color:#8FA0AC;">所属分类:</label>

                        <select id="bigCate" name="bigCateId">
                            <option value="0">大分类</option>
                            <c:forEach var="bigcate" items="${cateList}">
                                <c:if test="${goods.bigCateId==bigcate.id}">
                                    <option selected="selected" value="${bigcate.id}">${bigcate.cateName}</option>
                                </c:if>
                                <c:if test="${goods.bigCateId!=bigcate.id}">
                                <option  value="${bigcate.id}">${bigcate.cateName}</option></c:if>
                            </c:forEach>
                        </select>
                        <select id="smallCate" name="smallCateId" onclick="checkCate()">
                            <option>小分类</option>
                        </select>
                        <span id="isCate"></span>
                        <label class="validate_info" style="font-size:12px;color:#8FA0AC; margin-left: 70px" id="cate_msg"
                        >请指定商品所属的分类</label>
                    </li>
                    <li class="cell-item">
                        <label class="cell-left label_name" style="font-size: 12px;color:#8FA0AC;">计量单位:</label>
                        <div class="cell-right">
                            <input name="unit" id="unit" onblur="checkunit()" value="${goods.unit}" type="text"
                                   class="col-xs-6 col-lg-12">
                            <span id="isUnit"></span>
                            <label class="validate_info" style="font-size:12px;color:#8FA0AC; margin-left: 70px" id="unit_msg"
                            >1-10个非空字符</label>
                        </div>
                    </li>
                    <li class="cell-item">
                        <label class="cell-left label_name" style="font-size: 12px;color:#8FA0AC;">商品价格:</label>
                        <div class="cell-right">
                            <input name="price" id="price" onblur="checkPrice()" value="${goods.price}"
                                   type="text" class="col-xs-6 col-lg-12">
                            <span id="isPrice"></span>
                            <label class="validate_info" style="font-size:12px;color:#8FA0AC; margin-left: 70px"
                                   id="price_msg">不能为空,以元为单位,可以是小数</label>
                        </div>
                    </li>
                    <li class="cell-item">
                        <label class="cell-left label_name" style="font-size: 12px;color:#8FA0AC;">生产厂商:</label>
                        <div class="cell-right">
                            <input id="producter" name="producter" value="${goods.producter }" type="text" class="col-xs-6 col-lg-12">
                        </div>
                    </li>
                    <li class="cell-item">
                        <label class="cell-left label_name" style="font-size: 12px;color:#8FA0AC;">商品图片:</label>
                        <div class="cell-right">
                            <div id="divpicture" onclick="$('#picture').click()">点此图片上传
                            </div>
                            <br/>
<%--                            <img id="preview" src='GoodsServlet?flag=modifyGoods'/>--%>
                            <img id="preview" src='<%="GoodsServlet?flag=showPicture&goodsId="+request.getAttribute("goodsId") %>'/>
                            <input name="picture" id="picture"
                                   onchange="setImagePreview(this,divpicture,preview,'100px','125px');"
                                   type="file" style="display:none" class="col-xs-6 col-lg-12">
                        </div>
                    </li>
                    <li class="cell-item">
                        <label class="cell-left label_name" style="font-size: 12px;color:#8FA0AC;">描述信息:</label>
                        <div class="cell-right">
                            <textarea name="des" id="des"
                                      class="textarea col-xs-4 col-lg-12 height100">${goods.des}</textarea>
                        </div>
                    </li>
                </ul>
                <div class="buttonstyle">
                    <label id="result_msg" class="result_msg"> ${msg}</label>&nbsp;&nbsp;
                    <button id="form_btn" class="btn padding10 bg-deep-blue" onclick="return confirm('确定提交吗?')">保存修改
                    </button>
                </div>
                <div style="height: 380px"></div>
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

    //校验商品名称
    function checkGoodsName() {
        var goodsName_msg=document.getElementById("goodsName_msg");
        var pattern = /^\S{2,20}$/i;
        if (pattern.test(goodsName.value)) {
            goodsName_msg.style.color="#8FA0AC";
            $("#isgoodsName").html("<font color=green size=5>√</font>");
            return true;
        } else {
            goodsName_msg.style.color="red";
            $("#isgoodsName").html("<font color=red size=5>×</font>");
            return false;
            // alert("商品名称应是2-20位非空白字符")
        }

    }

    function checkunit() {
        var unit_msg=document.getElementById("unit_msg");
        var pattern = /^\S{1,10}$/i;
        if (pattern.test(unit.value)) {
            unit_msg.style.color="#8FA0AC";
            $("#isUnit").html("<font color=green size=5>√</font>");
            return true;
        } else {
            unit_msg.style.color="red";
            $("#isUnit").html("<font color=red size=5>×</font>");
            return false;
            // alert("计量单位应是1-10位非空白字符")
        }
    }

    function checkPrice() {
        var price_msg=document.getElementById("price_msg");
        //可以是整数 也可以是小数
        var pattern = /^\d+(\.\d{1,2})?$/;
        if (pattern.test(price.value)) {
            price_msg.style.color="#8FA0AC";
            $("#isPrice").html("<font color=green size=5>√</font>");
            return true;
        } else {
            price_msg.style.color="red";
            $("#isPrice").html("<font color=red size=5>×</font>");
            // alert("不能为空,可以是整数,可以是小数")
            return false;
        }
    }

    function checkCate() {
        var cate_msg=document.getElementById("cateName_msg");
        if ((smallCate.value >= 1) || (bigCate.value == 0)) {
            cate_msg.style.color="#8FA0AC";
            $("#isCate").html("<font color=green size=5>√</font>");
            return true;
        } else {
            cate_msg.style.color="red";
            $("#isCate").html("<font color=red size=5>×</font>");
            return false;
        }

    }
</script>

<script>
    var bigCate = $("#bigCate").val();
    console.log(bigCate);
        $.ajax({
            url: "CateServlet",
            data: {flag: "smallCate", bigCateId: bigCate},
            type: "post",
            dataType: "json",
            success: function (data) {
                console.log(data)
                $("#smallCate>option").remove();
                for (var i = 0; i < data.length; i++) {
                    var d = data[i];
                    var id=d.id
                    console.log(d.cateName)
                    var sid=${goods.smallCateId}
                        console.log(sid)
                    if(sid==id){
                        $("#smallCate").append(
                            "<option selected value=" + d.id + ">" + d.cateName + "</option>")

                    }else{
                        $("#smallCate").append(
                            "<option value=" + d.id + ">" + d.cateName + "</option>")}
                }
            }
        })
    $(function () {
        $("#bigCate").on("change", function () {
            var bigCate = $(this).val();
            console.log(bigCate);
            $.ajax({
                url: "CateServlet",
                data: {flag: "smallCate", bigCateId: bigCate},
                type: "post",
                dataType: "json",
                success: function (data) {
                    console.log(data)
                    $("#smallCate>option").remove();
                    for (var i = 0; i < data.length; i++) {
                        var d = data[i];
                        var id=d.id
                        console.log(d.cateName)
                        var sid=${goods.smallCateId}
                        console.log(sid)
                        if(sid==id){
                            $("#smallCate").append(
                                "<option selected value=" + d.id + ">" + d.cateName + "</option>")
                        }else{
                        $("#smallCate").append(
                            "<option value=" + d.id + ">" + d.cateName + "</option>")}
                    }
                }
            })
        })

    })
</script>
<script>
    var imageInput=document.getElementById("picture");
    var imageFile = imageInput.files[0];
    var formData = new FormData();
    formData.append("image", imageFile);
    $("#form_btn").onclick(function () {
        $.ajax({
            url:"GoodsServlet",
            data:{flag:"updateGoods",goodsName:goodsName.value,bigCateId:bigCateId.value,smallCateId:smallCateId.value,unit:unit.value,
                price:price.value,producter:producter.value,des:des.value,picture:formData},
            type: "post",
            processData: false,
            contentType: false,
            success:function (data) {
                if(data==1){
                    alert("修改成功!")
                }else{
                    alert("修改失败!")
                }
            }
        })
    })
</script>
<script>
    document.getElementById("form_btn").addEventListener("click", function(event) {
        event.preventDefault(); // 阻止默认的表单提交行为
        var form = document.getElementById("myForm");
        var goodsName = document.getElementById("goodsName");
        var unit = document.getElementById("unit");
        var price = document.getElementById("price");
        var bigCate = document.getElementById("bigCate");
        var smallCate = document.getElementById("smallCate");
        var big = bigCate.value;
        console.log(big)
        var small = smallCate.value;
        console.log(small);
        var checkName = checkGoodsName();
        var checkunit1 = checkunit();
        var checkprice_msg = checkPrice();
        var checkcate_msg = checkCate();
        // 检查输入字段是否为空值
        if (goodsName.value.trim() === "" || unit.value.trim() === "" || bigCate.value.trim() === "" || price.value.trim() === "" ) {
            alert("请填写所有必填字段！");
        }
        else if(checkName!=true || checkunit1!=true || checkprice_msg!=true || checkcate_msg!=true){
            alert("添加失败，请按规定添加商品信息！");
        }

        else {
            // 提交表单
            form.submit();
        }
    });
</script>
<%--显示图片--%>
<script>
    function setImagePreview(docObj, localImagId, imgObjPreview, width, height) {
        if (docObj.files && docObj.files[0]) { //火狐下，直接设img属性
            imgObjPreview.style.display = 'block';
            imgObjPreview.style.width = width;
            imgObjPreview.style.height = height;
            //火狐7以上版本不能用上面的getAsDataURL()方式获取，需要一下方式
            imgObjPreview.src = window.URL.createObjectURL(docObj.files[0]);
        } else { //IE下，使用滤镜 (估计下面的代码以后是用不上了,可以注掉)
            docObj.select();
            var imgSrc = document.selection.createRange().text;
            //必须设置初始大小
            localImagId.style.width = width;
            localImagId.style.height = height;
            //图片异常的捕捉，防止用户修改后缀来伪造图片
            try {
                localImagId.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";
                localImagId.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = imgSrc;
            } catch (e) {
                alert("您上传的图片格式不正确，请重新选择!");
                return false;
            }
            imgObjPreview.style.display = 'none';
            document.selection.empty();
        }
    }
</script>

