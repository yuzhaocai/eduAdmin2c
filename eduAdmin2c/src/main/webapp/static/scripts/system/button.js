$(function(){
	//初始化按钮列表
	grid.databind();
	
	//初始化按钮事件
	$('#reload_btn').click(search.query);
	$('#add_btn').click(ops.add);
	$('#edit_btn').click(ops.edit);
	$('#del_btn').click(ops.del);
	
});

//数据列表
var grid = {
	databind:function(){
		$("#datagrid").datagrid({
			url : basePath + '/system/button',
			title:'按钮列表',
			iconCls:'icon-list',
			method : 'post',
			border : true,
			fit: true,
			striped : true,
			rownumbers : true,
			singleSelect : true,
			idField : 'id',
			fitColumns:true,
			remoteSort:true,
			pageSize : 10,
			pageList : [ 10, 20, 30, 40, 50],
			emptyMsg: '无相关查询的记录！',
			view:datagridView,
			columns : [ [ 
			              { field:'ck',checkbox:true},
			              { width : fixWidth(15), title : '按钮ID', field : 'id',hidden:true},
			              { width : fixWidth(15), title : '权限名称', field : 'name'}, 
			              { width : fixWidth(20), title : '权限标识', field : 'code'},
			              { width : fixWidth(20), title : '图标', field : 'iconCls'},
			              { width : fixWidth(15), title : '排序', field : 'priority'},
			              { width : fixWidth(15), title : '说明', field : 'description'}
			           ] ],
			pagination:true,
			toolbar:'#toolbar'
		});
	},
	reload: function(param) {
		var _param = param || {};
		$("#datagrid").datagrid('clearSelections').datagrid('reload',_param);
    },
    selectRow: function() {
        return $("#datagrid").datagrid('getSelected');
    }
}