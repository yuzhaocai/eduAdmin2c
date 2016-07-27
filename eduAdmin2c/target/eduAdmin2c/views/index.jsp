<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	String path = request.getContextPath();     
	String basePath =request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>课吧后台管理系统</title>
<link rel="stylesheet" type="text/css" href="<%=basePath%>/static/jquery-easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>/static/jquery-easyui/themes/icon.css"/>
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
	<div data-options="region:'north',border:false,split:false" style="height:80px;">
		<div style="width:100%;height:100%;"></div>	
	</div>
	
	<!-- south -->
    <div data-options="region:'south',border:false,split:false" style="height:30px;">
    </div>
    
    <!-- west -->
    <div data-options="region:'west',split:true,minWidth:180,title:'导航菜单'" style="width:200px;">
    	<div class="easyui-accordion" data-options="border:false,fit:true"> 
        	<div title="快捷菜单" data-options="iconCls:'icon-application-cascade'" style="padding:5px;">  	
    			<ul class="easyui-tree side-tree">
                	<li iconCls="icon-chart-organisation"><a href="javascript:void(0)" data-icon="icon-chart-organisation" data-link="temp/layout-2.html" iframe="0">菜单导航</a></li>
                    <li iconCls="icon-users"><a href="javascript:void(0)" data-icon="icon-add" data-link="<%=basePath%>/views/user/list.jsp" iframe="0">用户管理</a></li>
                    <li iconCls="icon-user-group"><a href="javascript:void(0)" data-icon="icon-user-group" data-link="temp/layout-3.html" iframe="0">角色管理</a></li>
                    <li iconCls="icon-book"><a href="javascript:void(0)" data-icon="icon-book" data-link="temp/layout-3.html" iframe="1">数据字典</a></li>
                    <li iconCls="icon-cog"><a href="javascript:void(0)" data-icon="icon-cog" data-link="temp/layout-3.html" iframe="0">系统参数</a></li>
                    <li iconCls="icon-application-osx-error"><a href="javascript:void(0)" data-icon="icon-application-osx-error" data-link="temp/layout-3.html" iframe="0">操作日志</a></li>
                </ul>
            </div>
            <div title="内容管理" data-options="iconCls:'icon-application-form-edit'" style="padding:5px;">  	
    			<ul class="easyui-tree side-tree">
                	<li iconCls="icon-chart-organisation"><a href="javascript:void(0)" data-icon="icon-chart-organisation" data-link="layout-3.html" iframe="0">导航标题</a></li>
                    <li iconCls="icon-users"><a href="javascript:void(0)" data-icon="icon-users" data-link="temp/layout-3.html" iframe="0">导航标题</a></li>
                    <li iconCls="icon-user-group"><a href="javascript:void(0)" data-icon="icon-user-group" data-link="temp/layout-3.html" iframe="0">导航标题</a></li>
                    <li iconCls="icon-book"><a href="javascript:void(0)" data-icon="icon-book" data-link="temp/layout-3.html" iframe="0">导航标题</a></li>
                    <li iconCls="icon-cog"><a href="javascript:void(0)" data-icon="icon-cog" data-link="temp/layout-3.html" iframe="0">导航标题</a></li>
                    <li iconCls="icon-application-osx-error"><a href="javascript:void(0)" data-icon="icon-application-osx-error" data-link="temp/layout-3.html" iframe="0">导航标题</a></li>
                </ul>
            </div>
            <div title="商品管理" data-options="iconCls:'icon-creditcards'" style="padding:5px;">  	
    			<ul class="easyui-tree side-tree">
                	<li iconCls="icon-chart-organisation"><a href="javascript:void(0)" data-icon="icon-chart-organisation" data-link="layout-3.html" iframe="0">导航标题</a></li>
                    <li iconCls="icon-users"><a href="javascript:void(0)" data-icon="icon-users" data-link="temp/layout-3.html" iframe="0">导航标题</a></li>
                    <li iconCls="icon-user-group"><a href="javascript:void(0)" data-icon="icon-user-group" data-link="temp/layout-3.html" iframe="0">导航标题</a></li>
                    <li iconCls="icon-book"><a href="javascript:void(0)" data-icon="icon-book" data-link="temp/layout-3.html" iframe="0">导航标题</a></li>
                    <li iconCls="icon-cog"><a href="javascript:void(0)" data-icon="icon-cog" data-link="temp/layout-3.html" iframe="0">导航标题</a></li>
                    <li iconCls="icon-application-osx-error"><a href="javascript:void(0)" data-icon="icon-application-osx-error" data-link="temp/layout-3.html" iframe="0">导航标题</a></li>
                </ul>
            </div>
            <div title="系统设置" data-options="iconCls:'icon-wrench'" style="padding:5px;">  	
    			<ul class="easyui-tree side-tree">
                	<li iconCls="icon-chart-organisation"><a href="javascript:void(0)" data-icon="icon-chart-organisation" data-link="layout-3.html" iframe="0">导航标题</a></li>
                    <li iconCls="icon-users"><a href="javascript:void(0)" data-icon="icon-users" data-link="temp/layout-3.html" iframe="0">导航标题</a></li>
                    <li iconCls="icon-user-group"><a href="javascript:void(0)" data-icon="icon-user-group" data-link="temp/layout-3.html" iframe="0">导航标题</a></li>
                    <li iconCls="icon-book"><a href="javascript:void(0)" data-icon="icon-book" data-link="temp/layout-3.html" iframe="0">导航标题</a></li>
                    <li iconCls="icon-cog"><a href="javascript:void(0)" data-icon="icon-cog" data-link="temp/layout-3.html" iframe="0">导航标题</a></li>
                    <li iconCls="icon-application-osx-error"><a href="javascript:void(0)" data-icon="icon-application-osx-error" data-link="temp/layout-3.html" iframe="0">导航标题</a></li>
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
	        pc = setTimeout(closes, 1000);  
	    }  
	    
	    $(function(){
			$('.side-tree a').bind("click",function(){
				var title = $(this).text();
				var url = $(this).attr('data-link');
				var iconCls = $(this).attr('data-icon');
				var iframe = $(this).attr('iframe')==1?true:false;
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
    </script>
</body>
</html>