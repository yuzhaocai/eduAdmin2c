<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<form id="user_form" class="easyui-form" data-options="novalidate:true">
	<div class="table-wrap">
		<table class="form-table">
			<tr>
				<td width="20%" style="text-align: right;"><em class="required">*</em>用户名：</td>
				<td><input name="username" class="easyui-textbox" data-options="width:200,height:26,required:true"/></td>
			</tr>
			<tr>
				<td style="text-align: right;"><em class="required">*</em>密码：</td>
				<td><input name="password" type="password" class="easyui-textbox" data-options="width:200,height:26,required:true"/></td>
			</tr>
			<tr>
				<td style="text-align: right;"><em class="required">*</em>确认密码：</td>
				<td><input name="confirmPassword" type="password" class="easyui-textbox" data-options="width:200,height:26,required:true"/></td>
			</tr>
			<tr>
				<td style="text-align: right"><em class="required">*</em>姓名：</td>
				<td><input name="realname" class="easyui-textbox" data-options="width:200,height:26,required:true"/></td>
			</tr>
			<tr>
				<td style="text-align: right;">邮箱：</td>
				<td><input name="email" class="easyui-textbox" data-options="width:200,height:26"/></td>
			</tr>
			<tr>
				<td style="text-align: right;">电话：</td>
				<td><input name="phone" class="easyui-textbox" data-options="width:200,height:26"/></td>
			</tr>
			<tr>
				<td style="text-align: right">是否有效：</td>
				<td>
					<input type="radio" name="status" value="0" checked="checked"/>是
					&nbsp;
					<input type="radio" name="status" value="1"/>否
				</td>
			</tr>
		</table>
	</div>
</form>

