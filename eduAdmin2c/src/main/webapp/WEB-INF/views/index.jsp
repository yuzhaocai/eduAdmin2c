<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>
<% 
	String path = request.getContextPath();     
	String basePath =request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;  
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>课吧后台管理系统</title>
<link id="easyuiTheme" rel="stylesheet" type="text/css" href="<%=basePath%>/static/jquery-easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>/static/styles/themes/default/style.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>/static/styles/icon.css"/>
<link rel="stylesheet" type="text/css" href="<%=basePath%>/static/styles/base.css">
<script type="text/javascript" src="<%=basePath%>/static/jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/static/jquery-easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/static/jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=basePath%>/static/scripts/jquery-easyui.ext.js"></script>
<script type="text/javascript" src="<%=basePath%>/static/scripts/function.js"></script>
<script type="text/javascript">
	var basePath = '<%=basePath%>';
</script>
</head>

<body class="easyui-layout">
	<!-- loading -->
	<div class="loading">  
		<div style="position: relative;top: 48%">
			<img alt="加载中..." src="<%=basePath%>/static/images/loading.gif">
		</div>
	</div>
	
	<!-- north -->
	<div data-options="region:'north',border:false,split:false" style="height:80px;background: url('static/images/header_bg.png') no-repeat right;background-size: cover; -moz-background-size: cover;">
		<div id="userBasicInfo" style="position: absolute;right: 5px;top:10px;">
			[<strong>超级管理员</strong>]，欢迎你！您使用[<strong>192.168.1.100</strong>]IP登录！
		</div>
		<div style="position: absolute; right: 5px; bottom: 10px; ">
			<a href="javascript:void(0);" class="easyui-menubutton"
				data-options="menu:'#layout_north_pfMenu',iconCls:'icon-ok'">更换皮肤</a>
			<a href="javascript:void(0);" class="easyui-menubutton"
				data-options="menu:'#layout_north_kzmbMenu',iconCls:'icon-help'">控制面板</a>
		</div>
		<div id="layout_north_pfMenu" style="width: 120px; display: none;">
			<div onclick="changeTheme('default');">default</div>
			<div onclick="changeTheme('gray');">gray</div>
			<div onclick="changeTheme('black');">black</div>
			<div onclick="changeTheme('bootstrap');">bootstrap</div>
			<div onclick="changeTheme('metro');">metro</div>
		</div>
		<div id="layout_north_kzmbMenu" style="width: 100px; display: none;">
			<div onclick="editPassword();">修改密码</div>
			<div onclick="showAbout();">联系管理员</div>
			<div class="menu-sep"></div>
			<div onclick="logoutFun();">退出系统</div>
		</div>
	</div>
	
	<!-- south -->
    <div data-options="region:'south',border:false,split:false" style="height:30px;">
    	<p class="copyright">
			Copyright © 2015-2016 Perfect Keku Co.,Ltd. All rights reserved.
			session:<%=session%>
    	</p>
    </div>
    
    <!-- west -->
    <div data-options="region:'west',split:true,minWidth:180,title:'导航菜单'" style="width:200px;">
    	<div class="easyui-accordion" data-options="border:false,fit:true"> 
        	<div title="系统管理" data-options="iconCls:'icon-cog'" style="padding:5px;">  	
    			<ul class="easyui-tree side-tree">
                    <li iconCls="icon-chart_organisation"><a href="javascript:void(0)" data-icon="icon-chart_organisation" data-link="<%=basePath%>/system/organization" iframe="1">部门管理</a></li>
                	<li iconCls="icon-book"><a href="javascript:void(0)" data-icon="icon-book" data-link="<%=basePath%>/system/resource" iframe="1">资源管理</a></li>
                    <li iconCls="icon-user_gray"><a href="javascript:void(0)" data-icon="icon-user_gray" data-link="<%=basePath%>/system/role" iframe="1">角色管理</a></li>
                    <li iconCls="icon-users"><a href="javascript:void(0)" data-icon="icon-users" data-link="<%=basePath%>/system/user" iframe="1">用户管理</a></li>
                </ul>
            </div>
            <div title="老师审核" data-options="iconCls:'icon-set1'" style="padding:5px;">  	
    			<ul class="easyui-tree side-tree">
                	<li iconCls="icon-cog"><a href="javascript:void(0)" data-icon="icon-cog" data-link="<%=basePath%>/review/school" iframe="1">身份认证审核</a></li>
                	<li iconCls="icon-book"><a href="javascript:void(0)" data-icon="icon-book" data-link="<%=basePath%>/review/teacher" iframe="1">学历认证审核</a></li>
                	<li iconCls="icon-book"><a href="javascript:void(0)" data-icon="icon-book" data-link="<%=basePath%>/review/teacher" iframe="1">教师证认证审核</a></li>
                	<li iconCls="icon-book"><a href="javascript:void(0)" data-icon="icon-book" data-link="<%=basePath%>/review/teacher" iframe="1">其他认证审核</a></li>
                </ul>
            </div>
            <div title="学校审核" data-options="iconCls:'icon-application-form-edit'" style="padding:5px;">  	
    			<ul class="easyui-tree side-tree">
                	<li iconCls="icon-cog"><a href="javascript:void(0)" data-icon="icon-cog" data-link="<%=basePath%>/review/school" iframe="1">身份证审核</a></li>
                	<li iconCls="icon-book"><a href="javascript:void(0)" data-icon="icon-book" data-link="<%=basePath%>/review/teacher" iframe="1">学历证审核</a></li>
                	<li iconCls="icon-book"><a href="javascript:void(0)" data-icon="icon-book" data-link="<%=basePath%>/review/teacher" iframe="1">教师证审核</a></li>
                	<li iconCls="icon-book"><a href="javascript:void(0)" data-icon="icon-book" data-link="<%=basePath%>/review/teacher" iframe="1">其他审核</a></li>
                </ul>
            </div>
        </div>
    </div>
    
    <!-- center -->
    <div data-options="region:'center'">
    	 <div id="main_tabs" class="easyui-tabs" data-options="border:false,fit:true">  
            <div title="首页" data-options="href:'temp/layout-1.html',closable:false,iconCls:'icon-tip',cls:'pd3'"></div>
        </div>
    </div>
    
    <script type="text/javascript">
	    function closes() {  
	        $(".loading").fadeOut("normal", function () {  
	            $(this).remove();  
	        });  
	    }  
	   
	    var pc;  
	    $.parser.onComplete = function () {  
	        if (pc) clearTimeout(pc);  
	        pc = setTimeout(closes, 500);  
	    }  
	    
	    $(function(){
			$('.side-tree li').bind("click",function(){
				var title = $(this).find('a').text();
				var url = $(this).find('a').attr('data-link');
				var iconCls = $(this).find('a').attr('data-icon');
				var iframe = $(this).find('a').attr('iframe')==1?true:false;
				addTab(title,url,iconCls,iframe);
			});	
		})
		
		/**
		* 载入树形菜单 
		*/
		$('#side_tree').tree({
			url:'temp/menu.php',
			cache:false,
			onClick:function(node){
				var url = node.attributes['url'];
				if(url==null || url == ""){
					return false;
				}
				else{
					addTab(node.text, url, '', node.attributes['iframe']);
				}
			}
		});
		
		/**
		*  选项卡初始化
		*/
		$('#main_tabs').tabs({
			tools:[{
				iconCls:'icon-reload',
				fit:true,
				plain:true,
				handler:function(){
					var tab = $("#main_tabs").tabs('getSelected');
					var href = tab.panel("options").href;
					if(href){
						tab.panel('refresh');
					} else {
						var panel = tab.panel('panel');
						var frame = panel.find('iframe');
						try {
							if (frame.length > 0) {
								for ( var i = 0; i < frame.length; i++) {
									frame[i].contentWindow.document.write('');
									frame[i].contentWindow.close();
									frame[i].src = frame[i].src;
								}
								if ($.browser.msie) {
									CollectGarbage();
								}
							}
						} catch (e) {
						}
					}
				}
			}]
		});
		
		/**
		*  添加选项卡
		*/
		function addTab(title, href, iconCls, iframe){
			var tabPanel = $('#main_tabs');
			var content = '<iframe scrolling="auto" frameborder="0"  src="'+ href +'" style="width:100%;height:100%;"></iframe>';
			if(!tabPanel.tabs('exists',title)){
				if(iframe){
					tabPanel.tabs('add',{
						title:title,
						content:content,
						iconCls:iconCls,
						fit:true,
						closable:true
					});
				}
				else{
					tabPanel.tabs('add',{
						title:title,
						href:href,
						iconCls:iconCls,
						fit:true,
						closable:true
					});
				}
			} else
			{
				tabPanel.tabs('select',title);
				var tab = tabPanel.tabs('getSelected');
				if(iframe){
					tabPanel.tabs('update',{
						tab:tab,
						options:{
							content:content
						}
					});
				} else {
					tab.panel('refresh', href);
				}
			}
		}
		
		/**
		*  移除选项卡
		*/
		function removeTab(){
			var tabPanel = $('#main_tabs');
			var tab = tabPanel.tabs('getSelected');
			if (tab){
				var index = tabPanel.tabs('getTabIndex', tab);
				tabPanel.tabs('close', index);
			}
		}
		
		/**
		*	更换EasyUI主题的方法
		*/
		function changeTheme (themeName) {
			var $easyuiTheme = $('#easyuiTheme');
			var url = $easyuiTheme.attr('href');
			var href = url.substring(0, url.indexOf('themes')) + 'themes/'
					+ themeName + '/easyui.css';
			$easyuiTheme.attr('href', href);
			var $iframe = $('iframe');
			if ($iframe.length > 0) {
				for ( var i = 0; i < $iframe.length; i++) {
					var ifr = $iframe[i];
					$(ifr).contents().find('#easyuiTheme').attr('href', href);
				}
			}
		};
    </script>
</body>
</html>