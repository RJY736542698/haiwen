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
	<body id="pagestyle" class="backgrounddd">
		 <div class="bk-con-message message-section" id="iframe_box">
			<div class="box-module height100b margin0">
				<div class="box-title">个人信息</div>
				<div class="box-content padding15">
					<div class="clearfix ptb20">
						<div class="col-lg-6 padding15">
						   <div class="box-module boxcolor">
						   <div class="box-content padding15 text-center Bombbox">
							   <form action="AdminServlet" method="post" id="myForm">
								   <input type="hidden" name="flag" value="passwordUpdate">
							   <div class="box-title btn-blue clickBombbox selected" data-type="arrow">用户密码修改 <i class="iconfont icon-35_xiangxiajiantou arrow"></i></div>
							   <div class="box-content padding15 text-center Bombbox">
								   <ul class="clearfix add_style">
									   <li class="cell-item">
										   <input type="hidden" id="id" name="id" value="${session_admin.id}">
										   <label class="cell-left label_name" style="font-size: 12px;color:#8FA0AC;" >用户账号:</label>
										   <div class="cell-right">
											   <input type="text" style="font-size:12px;color:#8FA0AC;" class="col-xs-6 col-lg-12" readonly
													  id="adminName" name="adminName"  value="${session_admin.adminName }">
										   </div>
									   </li>
									   <li class="cell-item">
										   <label class="cell-left label_name" style="font-size: 12px;color:#8FA0AC;">&nbsp;&nbsp;&nbsp;旧密码:</label>
										   <div class="cell-right">
											   <input name="oldPassword" id="oldPassword" onblur="checkOldPassword()"
													   type="password" class="col-xs-6 col-lg-12">
											   <label class="validate_info" style="font-size:12px;color:#8FA0AC; margin-left: 70px">数字或英文,6-20位</label>
										   </div>
									   </li>
									   <li class="cell-item">
										   <label class="cell-left label_name" style="font-size: 12px;color:#8FA0AC;">&nbsp;&nbsp;&nbsp;新密码:</label>
										   <div class="cell-right">
											   <input name="password" id="password" onblur="checkPassword()"
													   type="password" class="col-xs-6 col-lg-12">
											   <span id="isPassword"></span>
											   <label class="validate_info" style="font-size:12px;color:#8FA0AC; margin-left: 70px"
													  id="password_msg">数字或英文,6-20位</label>
										   </div>
									   </li>
									   <li class="cell-item">
										   <label class="cell-left label_name" style="font-size: 12px;color:#8FA0AC;">重复密码:</label>
										   <div class="cell-right">
											   <input id="pwdconfirm" type="password" onblur="checkBpwd()"
													  class="col-xs-6 col-lg-12">
											   <span id="isPwdconfirm"></span>
											   <label class="validate_info" style="font-size:12px;color:#8FA0AC; margin-left: 70px"
													  id="pwdconfirm_msg">请重复输入密码</label>
										   </div>
									   </li>
								   </ul>
							   </div>
								   <div class="buttonstyle">
									   <label id="result_msg" class="result_msg">${msg}</label>&nbsp;&nbsp;
									   <button id="form_btn" class="btn padding10 bg-deep-blue"   onclick="return confirm('确定提交吗?')">保存修改</button>
								   </div>
							   </form>
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
<script>
	function checkPassword(){
		if((password.value).trim()==""){
			alert("请输入密码");
			return false;
		}else{
			var pattern = /^[0-9A-Za-z]{6,20}$/i;
			if(pattern.test(password.value)){
				$("#isPassword").html("<font color=green size=5>√</font>");
			}else {
				$("#isPassword").html("<font color=red size=5>×</font>");
				alert("密码只限数字或英文,6-20位")
			}
		}
	}
	function checkBpwd() {
		if (password.value != pwdconfirm.value) {
			$("#isPwdconfirm").html("<font color=green size=5>×</font>");
			alert("两次密码不一致！");
			// return false;
		}else {
			$("#isPwdconfirm").html("<font color=green size=5>√</font>");
		}
	}
	function checkOldPassword() {
		if(oldPassword.value.trim()==""){
			alert("请输入旧密码");
			return false;
		}
	}
</script>


<script>
	$("#form_btn").submit(function (event) {
		event.preventDefault(); // 阻止默认的表单提交行为
		// var form = document.getElementById("myForm");
		var oldPassword = document.getElementById("oldPassword");
		var password = document.getElementById("password");
		var pwdconfirm = document.getElementById("pwdconfirm");
		var id=document.getElementById("id");

		// 检查输入字段是否为空值
		if (oldPassword.value.trim() === "" || password.value.trim()==="" || pwdconfirm.value.trim()==="" ) {
			alert("请填写所有必填字段！");
		} else {
			// 提交表单
			form.submit();

		}
	});
</script>