<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<form id="user_form" class="easyui-form" data-options="novalidate:true">
	 <div id="userTab" class="easyui-tabs" data-options="border:false">
         <div title="基本信息" style="padding: 2px; overflow: hidden;" data-options="">
             <table class="form-table">
             	<tr>
					<td width="20%" style="text-align: right;"><em class="required">*</em>用户名：</td>
					<td><span id="username"></span></td>
				</tr>
				<tr>
					<td style="text-align: right"><em class="required">*</em>真实姓名：</td>
					<td><input name="realname" class="easyui-textbox" data-options="width:200,height:26,required:true"/></td>
				</tr>
				<tr>
					<td style="text-align: right"><em class="required">*</em>角色：</td>
					<td><input name="roleIds" id="role_ids" class="easyui-combobox" data-options="width:200,height:26,required:true,multiple:true,valueField:'id',textField:'name'"/></td>
				</tr>
				<tr>
					<td style="text-align: right"><em class="required">*</em>部门：</td>
					<td><input name="organizationId" id="organization_id" class="easyui-combotree" data-options="width:200,height:26,required:true,valueField:'id',textField:'text',lines:true"/></td>
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
         <div title="联系方式"  style="padding: 2px">
             <table class="form-table">
				<tr>
					<td style="text-align: right;">电话：</td>
					<td><input name="phone" class="easyui-textbox" data-options="width:200,height:26"/></td>
				</tr>
             	<tr>
					<td style="text-align: right;">邮箱：</td>
					<td><input name="email" class="easyui-textbox" data-options="width:200,height:26"/></td>
				</tr>
             </table>
         </div>
     </div>
     <input type="hidden" name="id"/>
</form>

