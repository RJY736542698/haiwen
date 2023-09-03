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
		<title>系统框架首页</title>
		<script src="js/jquery-1.9.1.min.js" type="text/javascript"></script>
		<script src="js/jquery.cookie.js" type="text/javascript"></script>
		<script src="js/jquery.nicescroll.js" type="text/javascript"></script>
		<script src="js/HUpages.js" type="text/javascript"></script>
			<!--[if lt IE 9]>
          <script src="js/html5shiv.js" type="text/javascript"></script>
          <script src="js/css3-mediaqueries.js"  type="text/javascript"></script>
        <![endif]-->
	</head>
		<body id="situation" class="backgrounddd">
			<div class="spacing_style"></div>
			<div class="pages-style">
				<div class="clearfix">
					<div class="col-md-6">
						<div class="box-module">
							<div class="box-title">状态栏</div>
							<div class="box-content">
								<div class="Shops_info clearfix">
									<div class="left_shop">
										<div class="left_shop_logo">
											<div class="shop_logo"><img src="" onerror="this.src='images/noimage.gif',this.onerror=null"></div>
											<a href="#" class="btn bg-deep-blue padding5">进入首页</a>
										</div>
										<div class="Shops_content">
											<p><label class="name">商城名称：</label>三台山购物网</p>
											<ul class="clearfix">
												<li><label class="name">店铺等级：</label>三级</li>
												<li><label class="name">商城类型：</label>电子商务</li>
												<li><label class="name">销售类型：</label>综合</li>
												<li><label class="name">店铺ID：</label>234565</li>
												<li><label class="name">商家ID：</label>34656565</li>
												<li><label class="name">开通时间：</label>2016-08-21</li>
											</ul>
										</div>
										<div class="right_shop">
											<p> 店铺动态评分：</p>
											<ul>
												<li><label class="name">相符描述</label><span class="score">4.5分</span></li>
												<li><label class="name">服务态度</label><span class="score">4.0分</span></li>
												<li><label class="name">发货速度</label><span class="score">4.6分</span></li>
											</ul>
										</div>
									</div>
									<div class="operating_style Quick_operation menuUl">
										<ul class="menulist">
											<li class="column-name">
												<a href="javascript:void(0)" data-id="" name="add_product.html" class="btn menuUl_title" title="添加产品">添加产品</a>
											</li>
											<li class="column-name">
												<a href="javascript:void(0)" data-id=""  name="add_Advertising.html" class="btn menuUl_title" title="添加广告">添加广告</a>
											</li>
											<li class="column-name">
												<a href="javascript:void(0)" data-id="" name="add_Article.html" class="btn menuUl_title" title="添加文章">添加文章</a>
											</li>
											<li class="column-name">
												<a href="javascript:void(0)" data-id="" name="add_Singlepag.html" class="btn menuUl_title" title="新增单页面">新增单页面</a>
											</li>
											<li class="column-name">
										       <a href="javascript:void(0)" title="个人信息" class="btn menuUl_title" name="administrators.html" data-id="111"><i class="iconfont icon-guanliyuan"></i>个人信息</a>
									       </li>
									        <li class="column-name">
										     <a href="javascript:void(0)" title="系统设置" class="btn menuUl_title" name="system_info.html" data-id="46"><i class="iconfont icon-system-copy"></i>系统设置</a>
									       </li>
										</ul>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-3">
						<div class="box-module">
					    </div>
					</div>
					<div class="col-md-3">
						<div class="box-module">
							<div class="box-title clickBombbox selected " data-type="arrow">登录状态<i class="iconfont icon-35_xiangxiajiantou arrow"></i></div>
							<div class="box-content Bombbox">
								<table class="table table_striped  table-hover">
									<thead>
										<tr>
											<th>登录时间</th>
											<th>IP</th>
											<th>用户名</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td>2018-09-12 12:34</td>
											<td>192.168.0.156</td>
											<td>admin <span class="tab_prompt bg-orange">在线</span></td>
										</tr>
										<tr>
											<td>2018-09-12 12:21</td>
											<td>192.168.0.156</td>
											<td>admin <span class="tab_prompt bg-gray">离线</span></td>
										</tr>
										<tr>
											<td>2018-09-12 12:21</td>
											<td>192.168.0.156</td>
											<td>admin <span class="tab_prompt bg-gray">离线</span></td>
										</tr>
										<tr>
											<td>2018-09-12 12:21</td>
											<td>192.168.0.156</td>
											<td>admin <span class="tab_prompt bg-gray">离线</span></td>
										</tr>
										<tr>
											<td>2018-09-12 12:21</td>
											<td>192.168.0.156</td>
											<td>admin <span class="tab_prompt bg-gray">离线</span></td>
										</tr>
										<tr>
											<td>2018-09-12 12:21</td>
											<td>192.168.0.156</td>
											<td>admin <span class="tab_prompt bg-gray">离线</span></td>
										</tr>
										<tr>
											<td>2018-09-12 12:21</td>
											<td>192.168.0.156</td>
											<td>admin <span class="tab_prompt bg-gray">离线</span></td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
					</div>
					
				</div>
				<!---->
				<div class="clearfix">
					<div class="col-md-8">
						<div class="box-module">
							<div class="box-title">销售统计</div>
							<div class="box-content">
						        <div class="dd_echarts">
                                <div id="main" class="mainwidth" style=" height:305px"></div>
                                </div>
                            </div>
                        </div>
					</div>
					<div class="col-md-4">
						<div class="box-module">
							<div class="box-title">系统信息</div>
							<div class="box-content">
								<table class="table table-striped table-hover">
									<tbody>
										<tr>
											<td class="width30b">操作系统:</td>
											<td>PC</td>
										</tr>
										<tr>
											<td>服务器:</td>
											<td>Apache/2.4.10 (Win32) OpenSSL/1.0.1i mod_fcgid/2.3.9</td>
										</tr>
										<tr>
											<td>浏览器:</td>
											<td>Chorme</td>
										</tr>
										<tr>
											<td>PHP版本:</td>
											<td>5.6.1</td>
										</tr>
										<tr>
											<td>程序编码:</td>
											<td>UTF-8</td>
										</tr>
										<tr>
											<td>MySQL版本</td>
											<td>5.5.40</td>
										</tr>
										<tr>
											<td>当前操作者:</td>
											<td>Admin</td>
										</tr>
										<tr>
											<td>当前IP:</td>
											<td>112.25.25.255 (广州市)</td>
										</tr>
										<tr>
											<td>当前版本:</td>
											<td>AlphaAdmin 1.0(20170803)</td>
										</tr>
										<tr>
											<td>当前时间:</td>
											<td>2017年9月26日 11:22:00</td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
</html>
<script src="js/dist/echarts.js" type="text/javascript"></script>
<script>
	//内页框架
	$(function() {
		$("#situation").Hupage({
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
	/*********************/
   require.config({
            paths: {
                echarts: './js/dist'
            }
        });
        require(
            [
                'echarts',
				'echarts/theme/macarons',
                'echarts/chart/line',   // 按需加载所需图表，如需动态类型切换功能，别忘了同时加载相应图表
                'echarts/chart/bar'
            ],
            function (ec,theme) {
                var myChart = ec.init(document.getElementById('main'),theme);
               option = {
    title : {
        text: '当周交易记录',
        subtext: '每周7天的交易记录'
    },
    tooltip : {
        trigger: 'axis'
    },
    legend: {
        data:['所有订单','已完成','未完成']
    },
    toolbox: {
        show : true,
        feature : {
            mark : {show: true},
            dataView : {show: true, readOnly: false},
            magicType : {show: true, type: ['line', 'bar']},
            restore : {show: true},
            saveAsImage : {show: true}
        }
    },
    calculable : true,
    xAxis : [
        {
            type : 'category',
            boundaryGap : false,
            data : ['周一','周二','周三','周四','周五','周六','周日']
        }
    ],
    yAxis : [
        {
            type : 'value',
            axisLabel : {
                formatter: '{value}单'
            }
        }
    ],
    series : [
        {
            name:'所有订单',
            type:'line',
            data:[110, 210, 150, 130, 125, 133, 106],
            markPoint : {
                data : [
                    {type : 'max', name: '最大值'},
                    {type : 'min', name: '最小值'}
                ]
            },
            markLine : {
                data : [
                    {type : 'average', name: '平均值'}
                ]
            }
        },
        {
            name:'已完成',
            type:'line',
            data:[110, 105, 140, 130, 110, 121, 100],
            markPoint : {
                data : [
                    {name : '周最低', value : -2, xAxis: 1, yAxis: -1.5}
                ]
            },
            markLine : {
                data : [
                    {type : 'average', name : '平均值'}
                ]
            }
        },
		   {
            name:'未完成',
            type:'line',
            data:[0, 25, 10, 10, 15, 12, 6],
            markPoint : {
                data : [
                    {name : '周最低', value : -2, xAxis: 1, yAxis: -1.5}
                ]
            },
            markLine : {
                data : [
                    {type : 'average', name : '平均值'}
                ]
            }
        }
    ]
};
                  
			myChart.setOption(option);
			}
			);
</script>