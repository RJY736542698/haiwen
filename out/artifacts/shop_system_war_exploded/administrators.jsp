<%@ page import="com.dao.MenuDao" %>
<%@ page import="com.beans.MenuInfo" %>
<%@ page import="java.util.List" %>
<%@ page import="com.beans.AdminInfo" %>
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
		<meta name="viewport" content="maximum-scale=1.0,minimum-scale=1.0,user-scalable=0,width=device-width,initial-scale=1.0" />
		<meta name="format-detection" content="telephone=no, email=no, date=no, address=no">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="format-detection" content="telephone=no" />
		<meta name="apple-mobile-web-app-capable" content="yes" />
		<meta content="black" name="apple-mobile-web-app-status-bar-style">
		<link href="css/bksystem.css" rel="stylesheet" type="text/css" /> 
		<link href="font/iconfont.css" rel="stylesheet" type="text/css" />
		<link href="css/module.css" rel="stylesheet" type="text/css" />
		<link href="css/pages.css" rel="stylesheet" type="text/css" />
		<title>个人信息</title>
		<script src="js/jquery-1.9.1.min.js" type="text/javascript"></script>
		<script src="js/jquery.cookie.js" type="text/javascript"></script>
		<script src="js/jquery.nicescroll.js" type="text/javascript"></script>
		<script src="js/HUpages.js" type="text/javascript"></script>
	</head>
	<body id="pagestyle" class="backgrounddd">
		 <div class="bk-con-message message-section" id="iframe_box">
			<div class="box-module height100b margin0">
				<div class="box-title">个人信息</div>
				<div class="box-content padding15">
					<div class="clearfix ptb20">
						<div class="col-lg-6 padding15">
						   <div class="box-module boxcolor">
						   <div class="box-content padding15 text-center Bombbox">
							   <div class="box-title btn-green clickBombbox selected" data-type="arrow">当前用户信息 <i class="iconfont icon-35_xiangxiajiantou arrow"></i></div>
							   <div class="box-content padding15 text-center Bombbox">
								   <table  style="align-items: center; width:30%; text-align:center; font-size:12px; border-collapse: collapse; border: none;">
									   <tr>
										   <td>用户账户:</td>
										   <td><input type="text" value="${session_admin.adminName}" readonly></td>
									   </tr>
									   <tr>
										   <td>用户密码:</td>
										   <td><input type="text" value="******* 不该显示用户密码 " readonly></td>
									   </tr>
									   <tr>
										   <td>备注信息:</td>
										   <td><input type="text" value="${session_admin.note}" readonly></td>
									   </tr>
									   <tr>
										   <td>最后更新日期:</td>
										   <td><input type="text" value="${session_admin.editDate}" readonly></td>
									   </tr>
								   </table>
							   </div>

                          </div>
						   </div>
						</div>
					</div>
				</div>
			</div>
	    </div>
	</body>
</html>
<script>
	$(function() {
		$("#pagestyle").Hupage({
			slide: '#breadcrumb',
			scrollbar:function(e){
				e.niceScroll({
					      cursorcolor: "#dddddd",
					      cursoropacitymax: 1,
					      touchbehavior: false,
					      cursorwidth: "3px",
					      cursorborder: "0",
					      cursorborderradius: "3px",
				 });						
			},
			expand: function(thisBox, settings) {
				settings.scrollbar(thisBox);//设置当前页滚动样式
			}
		})
	})
</script>