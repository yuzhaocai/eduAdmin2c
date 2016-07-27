<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<form id="organization_form" class="easyui-form" data-options="novalidate:true">
	<div class="table-wrap">
		<table class="form-table">
			<tr>
				<td width="20%" style="text-align: right;"><em class="required">*</em>机构名称：</td>
				<td><input name="name" class="easyui-textbox" data-options="width:200,height:26,required:true"/></td>
			</tr>
			<tr>
				<td style="text-align: right;"><em class="required">*</em>上级机构：</td>
				<td><input id="org_parent_id" name="parentId" class="easyui-combotree" data-options="width:200,height:26,required:true"/></td>
			</tr>
			<tr>
				<td style="text-align: right;">排序：</td>
				<td><input name="priority" class="easyui-numberspinner" data-options="width:200,height:26,required:true,min:0,max:99,editable:true" value="99"/></td>
			</tr>
			<tr>
				<td style="text-align: right">描述：</td>
				<td><input name="description" class="easyui-textbox" data-options="width:320,height:80,multiline:true"/></td>
			</tr>
		</table>
	</div>
	<input type="hidden" name="id"/>
</form>

