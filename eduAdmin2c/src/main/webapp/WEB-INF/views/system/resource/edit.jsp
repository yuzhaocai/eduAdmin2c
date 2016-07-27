<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<form id="resource_form" class="easyui-form" data-options="novalidate:true">
	<div class="table-wrap">
		<table class="form-table">
			<tr>
				<td width="20%" style="text-align: right;"><em class="required">*</em>资源名称：</td>
				<td><input name="name" class="easyui-textbox" data-options="width:200,height:26,required:true"/></td>
			</tr>
			<tr>
				<td style="text-align: right;"><em class="required">*</em>资源类型：</td>
				<td>
					<select class="easyui-combobox" name="type" data-options="width:200,height:26,required:true">
					    <option value="0">菜单</option>
					    <option value="1">按钮</option>
					</select>
				</td>
			</tr>
			<tr>
				<td style="text-align: right;"><em class="required">*</em>父级资源：</td>
				<td><input name="parentId" id="res_parent_id" class="easyui-combotree" data-options="width:200,height:26,required:true"/></td>
			</tr>
			<tr>
				<td style="text-align: right;"><em class="required">*</em>资源路径：</td>
				<td><input name="url" class="easyui-textbox" data-options="width:200,height:26,required:true"/></td>
			</tr>
			<tr>
				<td style="text-align: right"><em class="required">*</em>权限代码：</td>
				<td><input name="permission" class="easyui-textbox" data-options="width:200,height:26,required:true"/></td>
			</tr>
			<tr>
				<td style="text-align: right;">排序：</td>
				<td><input name="priority" class="easyui-numberspinner" value="99" data-options="width:80,height:26,min:1,max:99,required:true,editable:true"></td>
			</tr>
			<tr>
				<td style="text-align: right;">图标：</td>
				<td><div style="position: relative;"><span id="res_small_icon" class="icon icon-note" style="position: absolute;left: 5px;top: 5px;z-index: 99;">&nbsp;</span><input name="iconCls" id="res_icon_cls" class="easyui-textbox" value="icon-note" data-options="width:200,height:26,required:true,editable:false" style="padding-left: 25px;"/><a id="res_selecticon" href="javascript:;" plain="true" class="easyui-linkbutton" icon="icon-search" title="选择图标"></a></div></td>
			</tr>
			<tr>
				<td style="text-align: right">是否显示：</td>
				<td>
					<input type="radio" name="status" value="0" checked="checked"/>是
					&nbsp;
					<input type="radio" name="status" value="1"/>否
				</td>
			</tr>
			<tr>
				<td style="text-align: right;vertical-align: top;">描述：</td>
				<td><input name="description" class="easyui-textbox" data-options="width:360,height:60,multiline:true"/></td>
			</tr>
		</table>
	</div>
	<input type="hidden" name="id"/>
</form>

