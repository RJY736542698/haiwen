(function($) {
	"use strict";
	$.fn.Hupage = function(options) {
		var page = this;
		var defaults = {
			boxname:'',//盒子模块
			footerheight: 40, //底部高度
			menuWidth: 200, //当差菜单栏显示在左侧或右侧竖排显示时该方法有效
			tabs: '#tab-navigation',
			niconfont:'iconfont',//字体库显示图标名称(自定义字体库名称)
			skinicon:'icon-duigou',//皮肤选中状态图标
			prompt_btn: '.dropdown',
			iframe: '.show_iframe',//显示窗口位置属性
			content: '.content-section',//内容区
			message: '#iframe_box',//设置窗体显示位置
			menuModule:'#bk-con-menu', //菜单模块
			menu:'.menu-section',
			padding:15,//设置内间距距离
			closebtn: '.close_btn', //点击隐藏
			showbtn: '.show_btn', //点击显示	
			table:'.table_list',//指定table显示宽度
			mouIconOpen:"icon-35_xiangxiajiantou", // 箭头
			clickname:'.menuUl_title',
			mouIconClose:"icon-iconfonticontrianglecopy", //箭头
			Promp: '.PrompClose',//提示盒子
			scrollbar:function(e){},//滚动
			expand:function(){}  //扩展			
		}
		var settings = $.extend({}, defaults, options);//将一个空对象做为第一个参数
		return this.each(function() {
			var thisBox = $(this),//当前对象
			contentform = thisBox.find(settings.content),
			closemenu = thisBox.find(settings.closebtn),
			clickpage = thisBox.find(settings.clickname),
			prompname = thisBox.find(settings.Promp),
			messageform = thisBox.find(settings.message),
			leftmenu = thisBox.find(settings.menuModule),
			tabsform = thisBox.find(settings.tabs),	
			showmenu = thisBox.find(settings.showbtn),
			menuform = thisBox.find(settings.menu),
			Bombbox = thisBox.find(settings.boxname);
			var mobile_flag = isMobile(); // true为PC端，false为手机端
			//移动端判断
			function isMobile() {
				var userAgentInfo = navigator.userAgent;
				var mobileAgents = ["Android", "iPhone", "SymbianOS", "Windows Phone", "iPad", "iPod"];
				var mobile_flag = false;
				//根据userAgent判断是否是手机
				for(var v = 0; v < mobileAgents.length; v++) {
					if(userAgentInfo.indexOf(mobileAgents[v]) > 0) {
						mobile_flag = true;
						break;
					}
				}
				var screen_width = window.screen.width;
				var screen_height = window.screen.height;
				//根据屏幕分辨率判断是否是手机
				if(screen_width < 500 && screen_height < 800) {
					mobile_flag = true;
				}
				return mobile_flag;
			}
			mobile_flag ? thisBox.addClass("mobileStyle"):'';
			//编辑事件
			$.learuntab = {
			    pattern:function(){
			    	 var mw='';
			    	 var pw='';
			    	 leftmenu.css("width")!=null ? mw=parseInt(leftmenu.css("width").replace("px","")):'';
			    	 if(mw==0){
			    	 	 messageform.addClass("leftMessage").css({
						left:0,
						width: $(window).width(),
					    });
			    	 }else{
			    	 	leftmenu.addClass("leftModule").css({
						width: settings.menuWidth,
						height: $(window).height(),
						float: "left"
					    });
					    messageform.addClass("leftMessage").css({
						left: settings.menuWidth + "px",
						width: $(window).width() - settings.menuWidth,
					    });		
			    	}
			    	messageform.addClass("leftMessage").css({
						float: "left",
						position: "absolute",
						height: $(window).height(),
						padding:settings.padding+'px'
					});	 
			    	messageform.css("width")!=null ? pw=parseInt(messageform.css("width").replace("px","")):'';
					    if(pw <= 650){
					    	$(settings.table).css({width:1100+'px'})
					    }else{
					    	$(settings.table).css({width:100+'%'})
					    }
			    },
				clickBombbox:function(e){
				var target = $(e.target);
				var p = target.attr('data-position'); //方向
				var t = target.attr('data-type'); //箭头
				var name = $('.clickBombbox');
				var x = target.attr('data-id'); //
				var $btn = $(".memu_title");
				if(target.is('.clickBombbox') || target.parent().is('.clickBombbox')) {
					if(target.hasClass("selected")) {
						target.next('.Bombbox').css({"right":0,"display": "none"}).animate({"top": 0}, 500);
						target.removeClass("selected");
						target.parent().removeClass("selected");
						(t === 'arrow') ? target.find('i').attr('class', settings.niconfont+' '+ settings.mouIconClose ): target.find('i');
					} else {
						target.next('.Bombbox').css({display: "block"}).animate({"top": 0}, 500).parent().siblings().children('.Bombbox').hide();
						target.addClass("selected").parent().siblings().children('a').removeClass('selected').find('i').attr('class', settings.iconfont+' '+ settings.mouIconClose);
						(t === 'arrow') ? target.find('i').attr('class', settings.niconfont+' '+settings.mouIconOpen+' arrow'): target.find('i');
						$btn.eq(x).addClass('selected').siblings().removeClass('selected');
						$('.mode').attr('data-id', x);
						$('.tab-box > div ').eq(x).show().siblings().hide();
						var index = $btn.index(target);
						if(index >= 0) {
							$btn.eq(index).addClass("selected");
							$('.tab-box > div ').eq(index).show().siblings().hide();
						}
					}
				} else if(!target.is('.clickBombbox') || !target.parent().is('.clickBombbox')) {
					//name.find('i.arrow').attr('class', settings.niconfont+ ' '  + settings.mouIconClose);
					//name.removeClass("selected");
					//name.find('a').removeClass("selected");
				}
				if(target.is(settings.prompt_btn)) {
					if($(settings.prompt_btn).hasClass("promptclose")) {
						$('.dropdown-menu').css({
							"right":0,
							"display": "none",
							top: $('.dropdown-menu').outerHeight(),
						}).animate({"right": -($('.dropdown-menu').outerWidth()),}, 500);
						$(settings.prompt_btn).attr('class', 'dropdown '+settings.niconfont+' '+settings.mouIconClose).removeClass("promptclose");
					} else {
						if(footerform.hasClass('footer-top')){
							$('.dropdown-menu').css({
							display: "block",
							top:(settings.footerheight),
							margin:0,
							borderBottom:"1px solid #cccccc"
						}).animate({"right": 0}, 500);	
						}else{	
							$('.dropdown-menu').css({
							display: "block",
							top: -($('.dropdown-menu').outerHeight() + 2),
						}).animate({"right": 0}, 500);
						}
						$(settings.prompt_btn).attr('class', 'dropdown '+settings.niconfont + ' ' + settings.mouIconOpen).addClass("promptclose");
					}
				}
				if(!target.is(settings.prompt_btn)) {
					$('.dropdown-menu').animate({
						"right": -($('.dropdown-menu').outerWidth()),
					}, 500).css({
						"display": "none"
					});
					$(settings.prompt_btn).attr('class', 'dropdown  '+settings.niconfont + ' ' + settings.mouIconClose).removeClass("promptclose");
				}					
				},
				tabNavallwidth:function(){
					if(settings.bkposition == "top") {
					var topWindow = $(window.parent.document);
					var taballwidth = 0,
						$tabNav = topWindow.find(menuform),
						$tabNavWp = topWindow.find("#bk-con-menu"),
						$tabNavitem = topWindow.find("#menu-section > li.column-name "),
						$tabNavmore = $(".Hui-tabNav-more");
					if(!$tabNav[0]) {
						return
					}
					$tabNavitem.each(function(index, element) {
						taballwidth += Number(parseFloat($(this).width()));
					});
					$tabNav.width(taballwidth);
					var w = $tabNavWp.width();
					if(taballwidth > w) {
						$tabNavmore.show();
					} else {
						$tabNavmore.hide();
						$tabNav.css({
							left: 0
						});
					}
				}					
				},
				//点击选项卡
				clickframe:function(){
					var name=$(this);
					var uid=$(name).attr('data-id');
						if($(name).attr('name')) {
						var bStop = false;
						var bStopIndex = 0;
						var _href = $(name).attr('name');
						var _titleName = $(name).text();
						var _pageid= $(name).attr('data-id');
						var topWindow = $(window.parent.document);
						var show_navLi = topWindow.find("#tab-navigation li");	
						show_navLi.each(function() {
							if($(this).find('span').attr("data-id") === uid) {
								bStop = true;
								bStopIndex = show_navLi.index($(this));
								return false;
							}
						});
						if(!bStop) {
							show_navLi.removeClass("active");
							$.learuntab.creatIframe(_href, _titleName,bStopIndex,_pageid); 
							$.learuntab.min_titleList();
						} else {
							show_navLi.removeClass("active").eq(bStopIndex).addClass("active");
							var iframe_box = topWindow.find(messageform);
							iframe_box.find(settings.iframe).hide().eq(bStopIndex).show().find("iframe").attr({"src":_href, "data-href":_href });
						}
					}
					
				},
				min_titleList:function(){
					var topWindow = $(window.parent.document);
				    var show_nav = topWindow.find(tabsform ,"#dropdown_menu");
				    var aLi=show_nav.find("li");
				    var iframe_box = topWindow.find(messageform);	
				},
				creatIframe:function(href, titleName,id, pageid){
				var topWindow = $(window.parent.document);
				var show_nav = topWindow.find(settings.tabs);
				var id_name = $(settings.iframe).each(function(i) {
					$(this).attr('id', "Sort_link_" + i);
					if(i==0){
						$(this).addClass("index_iframe");
					}
				});
				var url=href;	 	  					
				var iframe_box = topWindow.find(settings.message);
				show_nav.find('li').removeClass("active");
				show_nav.append('<li class="name active"><span data-id="'+pageid+'" data-title="' + titleName + '" data-href="' + url + '">' + titleName + '</span><em class="close_icon"></em></li>');
				show_nav.find('li').css({height: settings.footerheight,lineHeight: settings.footerheight+"px"});
				$.learuntab.tabNavallwidth();	
				var iframeBox = iframe_box.find('.show_iframe:visible');
				iframeBox.hide().removeClass("selected");
				iframe_box.append('<div class="show_iframe selected" id="' + id_name + '" data-id="'+pageid+'" date-name="' + titleName + '" data-href="'+url+'"><div class="loading">'+loadinghtml+'</div><iframe class="simei_iframe" frameborder="0" src="' + href + '" data-href="' + url + '"></iframe></div>');
				var showBox = iframe_box.find('.show_iframe:visible');
			    var loadinghtml=showBox.find('.loading').html(settings.loading);		    
				showBox.find('iframe').attr("src", href).load(function() {showBox.find('.loading').hide();})	
				},
				//隐藏菜单栏
				closemenus:function(){
					$(this).hide();
				    showmenu.show().css("display", "block");
				    $('#bk-con-menu .radius').hide();
				    $(settings.menuModule).stop(true, true).delay(0).animate({
						width: 0,
						position: "relative"
					}, 300);
					messageform.animate({
						left: 0,
						width: $(window).width()
					}, 300);
					$(settings.menuModule).find('.title-menu').hide();		
				},
				//显示菜单栏
				showmenus:function(){
					$(this).hide();
				    closemenu.show(); 
					$(settings.menuModule).stop(true, true).delay(0).animate({
						width: settings.menuWidth,
						position: "relative"
					},200);
					menuform.animate({width: settings.menuWidth,},200);
					$(settings.menuModule).find('.title-menu').show();
					messageform.animate({
						left: settings.menuWidth,
						width: $(window).width()-settings.menuWidth
					}, 300);
				},
				menubtnhover:function(){
					var menuwidth=$(settings.slide).width();
			    if( menuwidth >= settings.menuWidth ){
				 $(this).addClass("display_btn");	
				closemenu.css("display","block");	
				}
			    else{
				$(this).removeClass("display_btn");
		     	closemenu.css("display","none");	
				 }	
				},
				prompt: function() {
					$(".Promp_plate").stop(true, true).delay(0).remove(); //提示框
				},
				//触发事件
			    init: function() {
			        page.on('click', $.learuntab.clickBombbox);//点击弹出层
			        $(settings.clickname).on('click', $.learuntab.clickframe);//点击打开选项卡
			        closemenu.on('click', $.learuntab.closemenus);
			        showmenu.on('click', $.learuntab.showmenus);
			        prompname.on('click', $.learuntab.prompt); //提示框关闭
			        $.learuntab.pattern();
			        $(window).resize(function() {$.learuntab.pattern(); });//
			        $(settings.menuModule).hover( function(){
			        $.learuntab.menubtnhover();	
			        },function(){
			   		$(this).removeClass("display_btn");
				    closemenu.css("display","none");
			        });  
			    }
			};
		    //初始化加载
			$(function() {
				$.learuntab.init();
			});
			settings.expand(thisBox,settings);
		})
	}	
})(jQuery);