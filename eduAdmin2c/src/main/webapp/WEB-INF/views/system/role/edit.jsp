<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<form id="role_form" class="easyui-form" data-options="novalidate:true">
	<div class="table-wrap">
		<table class="form-table">
			<tr>
				<td width="20%" style="text-align: right;"><em class="required">*</em>角色名称：</td>
				<td><input name="name" class="easyui-textbox" data-options="width:200,height:26,required:true"/></td>
			</tr>
			<tr>
				<td style="text-align: right;vertical-align: top;">角色描述：</td>
				<td><input name="description" class="easyui-textbox" data-options="width:320,height:80,multiline:true"/></td>
			</tr>
		</table>
	</div>
	<input type="hidden" name="id" id="role_id"/>
</form>

