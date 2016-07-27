
/**
* 设置未来(全局)的AJAX请求默认选项
* 主要设置了AJAX请求遇到Session过期的情况
*/
$.ajaxSetup({
	type: 'POST',
	complete: function(xhr,status) {
		var sessionStatus = xhr.getResponseHeader('sessionstatus');
		if(sessionStatus == 'timeout') {
			var top = getTopWinow();
			var yes = confirm('由于您长时间没有操作, session已过期, 请重新登录.');
			if (yes) {
				top.location.href = contextPath+'/login.do';            
			}
		}
	}
});

/**
* 在页面中任何嵌套层次的窗口中获取顶层窗口
* @return 当前页面的顶层窗口对象
*/
function getTopWinow(){
	var p = window;
	while(p != p.parent){
		p = p.parent;
	}
	return p;
}

/**
* 更改easyui加载panel时的提示文字
 * 
 */
$.extend($.fn.panel.defaults, {
	loadingMessage : '加载中....'
});

/**
 * 更改easyui加载grid时的提示文字
 * 
 */
$.extend($.fn.datagrid.defaults, {
	loadMsg : '数据加载中....'
});

/**
 * panel关闭时回收内存，主要用于layout使用iframe嵌入网页时的内存泄漏问题
 * 
 */
$.extend($.fn.panel.defaults, {
	onBeforeDestroy : function() {
		var frame = $('iframe', this);
		try {
			if (frame.length > 0) {
				for (var i = 0; i < frame.length; i++) {
					frame[i].src = '';
					frame[i].contentWindow.document.write('');
					frame[i].contentWindow.close();
				}
				frame.remove();
				if (navigator.userAgent.indexOf("MSIE") > 0) {// IE特有回收内存方法
					try {
						CollectGarbage();
					} catch (e) {
					}
				}
			}
		} catch (e) {
		}
	}
});

/**
 * 自定义验证规则
 */
$.extend($.fn.validatebox.defaults.rules,{
	username: {
		validator:function(value){
			return /^[a-z0-9_-]{6,16}$/.test(value);
		},
		message:"用户名由6-16位字母、数字和下划线组成."
	},
	password:{
		validator:function(value){
			return /^[a-z0-9_-]{6,18}$/.test(value);
		},
		message:"密码由6-18位字母、数字和下划线组成."
	},
    equalTo: {  
        validator:function(value,param){  
            return $(param[0]).val() == value;  
        },  
        message:"两次输入密码不匹配."
    },
    selectValid: {
    	validator:function(value,param){
    		if($("input[name="+param[0]+"]").val() == ""){
    			return false;
    		} else {
    			return true;
    		}
    	},
    	message:"改选项为必选项."
    }
});


/**
 * 防止panel、window、dialog组件超出浏览器边界
 * 
 */
onMove = {
	onMove : function(left, top) {
		var l = left;
		var t = top;
		if (l < 1) {
			l = 1;
		}
		if (t < 1) {
			t = 1;
		}
		var width = parseInt($(this).parent().css('width')) + 14;
		var height = parseInt($(this).parent().css('height')) + 14;
		var right = l + width;
		var buttom = t + height;
		var browserWidth = $(window).width();
		var browserHeight = $(window).height();
		if (right > browserWidth) {
			l = browserWidth - width;
		}
		if (buttom > browserHeight) {
			t = browserHeight - height;
		}
		$(this).parent().css({/* 修正面板位置 */
			left : l,
			top : t
		});
	}
};
$.extend($.fn.dialog.defaults, onMove);
$.extend($.fn.window.defaults, onMove);
$.extend($.fn.panel.defaults, onMove);

/**
 * 
 * 通用错误提示
 * 
 * 用于datagrid、treegrid、tree、combogrid、combobox、form加载数据出错时的操作
 * 
 */
onLoadError = {
	onLoadError : function(XMLHttpRequest) {
		if (parent.$ && parent.$.messager) {
			parent.$.messager.progress('close');
			parent.$.messager.alert('错误', XMLHttpRequest.responseText);
		} else {
			$.messager.progress('close');
			$.messager.alert('错误', XMLHttpRequest.responseText);
		}
	}
};
$.extend($.fn.datagrid.defaults, onLoadError);
$.extend($.fn.treegrid.defaults, onLoadError);
$.extend($.fn.tree.defaults, onLoadError);
$.extend($.fn.combogrid.defaults, onLoadError);
$.extend($.fn.combobox.defaults, onLoadError);
$.extend($.fn.form.defaults, onLoadError);

/**
 * 扩展combobox在自动补全模式时，检查用户输入的字符是否存在于下拉框中，如果不存在则清空用户输入
 * 
 * @author 孙宇
 * 
 * @requires jQuery,EasyUI
 */
$.extend($.fn.combobox.defaults, {
	onHidePanel : function() {
		var _options = $(this).combobox('options');
		if (_options.mode == 'remote') {/* 如果是自动补全模式 */
			var _data = $(this).combobox('getData');/* 下拉框所有选项 */
			var _value = $(this).combobox('getValue');/* 用户输入的值 */
			var _b = false;/* 标识是否在下拉列表中找到了用户输入的字符 */
			for (var i = 0; i < _data.length; i++) {
				if (_data[i][_options.valueField] == _value) {
					_b = true;
				}
			}
			if (!_b) {/* 如果在下拉列表中没找到用户输入的字符 */
				$(this).combobox('setValue', '');
			}
		}
	}
});

/**
 * 扩展combogrid在自动补全模式时，检查用户输入的字符是否存在于下拉框中，如果不存在则清空用户输入
 * 
 * @author 孙宇
 * 
 * @requires jQuery,EasyUI
 */
$.extend($.fn.combogrid.defaults, {
	onHidePanel : function() {
		var _options = $(this).combogrid('options');
		if (_options.mode == 'remote') {/* 如果是自动补全模式 */
			var _data = $(this).combogrid('grid').datagrid('getData').rows;/* 下拉框所有选项 */
			var _value = $(this).combogrid('getValue');/* 用户输入的值 */
			var _b = false;/* 标识是否在下拉列表中找到了用户输入的字符 */
			for (var i = 0; i < _data.length; i++) {
				if (_data[i][_options.idField] == _value) {
					_b = true;
				}
			}
			if (!_b) {/* 如果在下拉列表中没找到用户输入的字符 */
				$(this).combogrid('setValue', '');
			}
		}
	}
});

/**
 * 扩展validatebox，添加新的验证功能
 * 
 * @author 孙宇
 * 
 * @requires jQuery,EasyUI
 */
$.extend($.fn.validatebox.defaults.rules, {
	eqPwd : {/* 验证两次密码是否一致功能 */
		validator : function(value, param) {
			return value == $(param[0]).val();
		},
		message : '密码不一致！'
	}
});

/**
 * 扩展tree和combotree，使其支持平滑数据格式
 * 
 * 
 */
loadFilter = {
	loadFilter : function(data, parent) {
		var opt = $(this).data().tree.options;
		var idField, textField, parentField;
		if (opt.parentField) {
			idField = opt.idField || 'id';
			textField = opt.textField || 'text';
			parentField = opt.parentField || 'pid';
			var i, l, treeData = [], tmpMap = [];
			for (i = 0, l = data.length; i < l; i++) {
				tmpMap[data[i][idField]] = data[i];
			}
			for (i = 0, l = data.length; i < l; i++) {
				if (tmpMap[data[i][parentField]] && data[i][idField] != data[i][parentField]) {
					if (!tmpMap[data[i][parentField]]['children'])
						tmpMap[data[i][parentField]]['children'] = [];
					data[i]['text'] = data[i][textField];
					tmpMap[data[i][parentField]]['children'].push(data[i]);
				} else {
					data[i]['text'] = data[i][textField];
					treeData.push(data[i]);
				}
			}
			return treeData;
		}
		return data;
	}
};
$.extend($.fn.combotree.defaults, loadFilter);
$.extend($.fn.tree.defaults, loadFilter);

/**
 * 扩展treegrid，使其支持平滑数据格式
 * 
 */
$.extend($.fn.treegrid.defaults, {
	loadFilter : function(data, parentId) {
		var opt = $(this).data().treegrid.options;
		var idField, treeField, parentField;
		if (opt.parentField) {
			idField = opt.idField || 'id';
			treeField = opt.textField || 'text';
			parentField = opt.parentField || 'pid';
			var i, l, treeData = [], tmpMap = [];
			for (i = 0, l = data.length; i < l; i++) {
				tmpMap[data[i][idField]] = data[i];
			}
			for (i = 0, l = data.length; i < l; i++) {
				if (tmpMap[data[i][parentField]] && data[i][idField] != data[i][parentField]) {
					if (!tmpMap[data[i][parentField]]['children'])
						tmpMap[data[i][parentField]]['children'] = [];
					data[i]['text'] = data[i][treeField];
					tmpMap[data[i][parentField]]['children'].push(data[i]);
				} else {
					data[i]['text'] = data[i][treeField];
					treeData.push(data[i]);
				}
			}
			return treeData;
		}
		return data;
	}
});


/**
 * 等同于原form的load方法，但是这个方法支持{data:{name:''}}形式的对象赋值
 */
$.extend($.fn.form.methods, {
	loadData : function(jq, data) {
		return jq.each(function() {
			load(this, data);
		});

		function load(target, data) {
			if (!$.data(target, 'form')) {
				$.data(target, 'form', {
					options : $.extend({}, $.fn.form.defaults)
				});
			}
			var opts = $.data(target, 'form').options;

			if (typeof data == 'string') {
				var param = {};
				if (opts.onBeforeLoad.call(target, param) == false)
					return;

				$.ajax({
					url : data,
					data : param,
					dataType : 'json',
					success : function(data) {
						_load(data);
					},
					error : function() {
						opts.onLoadError.apply(target, arguments);
					}
				});
			} else {
				_load(data);
			}
			function _load(data) {
				var form = $(target);
				var formFields = form.find("input[name],select[name],textarea[name]");
				formFields.each(function() {
					var name = this.name;
					var value = jQuery.proxy(function() {
						try {
							return eval('this.' + name);
						} catch (e) {
							return "";
						}
					}, data)();
					var rr = _checkField(name, value);
					if (!rr.length) {
						var f = form.find("input[numberboxName=\"" + name + "\"]");
						if (f.length) {
							f.numberbox("setValue", value);
						} else {
							$("input[name=\"" + name + "\"]", form).val(value);
							$("textarea[name=\"" + name + "\"]", form).val(value);
							$("select[name=\"" + name + "\"]", form).val(value);
						}
					}
					_loadCombo(name, value);
				});
				opts.onLoadSuccess.call(target, data);
				$(target).form("validate");
			}

			function _checkField(name, val) {
				var rr = $(target).find('input[name="' + name + '"][type=radio], input[name="' + name + '"][type=checkbox]');
				rr._propAttr('checked', false);
				rr.each(function() {
					var f = $(this);
					if (f.val() == String(val) || $.inArray(f.val(), val) >= 0) {
						f._propAttr('checked', true);
					}
				});
				return rr;
			}

			function _loadCombo(name, val) {
				var form = $(target);
				var cc = [ 'combobox', 'combotree', 'combogrid', 'datetimebox', 'datebox', 'combo' ];
				var c = form.find('[comboName="' + name + '"]');
				if (c.length) {
					for (var i = 0; i < cc.length; i++) {
						var type = cc[i];
						if (c.hasClass(type + '-f')) {
							if (c[type]('options').multiple) {
								c[type]('setValues', val);
							} else {
								c[type]('setValue', val);
							}
							return;
						}
					}
				}
			}
		}
	}
});


/**
 * 验证开始时间小于结束时间
 */
$.extend($.fn.validatebox.defaults.rules, {
	md: { 
		validator: function(value, param){ 
			var now = new Date();
			var y = now.getFullYear();
			var m = (now.getMonth()+1)>10?now.getMonth()+1:"0"+(now.getMonth()+1);
			var d = now.getDate()>10?now.getDate():"0"+now.getDate();
			
			var startTime = $(param[0]).timespinner('getValue')+":00"; 
			var endTime = value+":00";
			
			var startDate = new Date(Date.parse((y+"-"+m+"-"+d+" "+startTime).replace(/-/g,"/")));
			var endDate = new Date(Date.parse((y+"-"+m+"-"+d+" "+endTime).replace(/-/g,"/")));
			return endDate>startDate; 
		}, 
		message: '结束时间要大于开始时间！' 
	} 
}); 

/**
 * 创建一个模式化的dialog
 * 
 */
$.modalDialog = function(options) {
	var opts = $.extend({
		title : '&nbsp;',
		width : 640,
		height : 400,
		href:'',
		modal : true,
		onClose : function() {
			$.modalDialog.handler = undefined;
			$(this).dialog('destroy');
		}
	}, options);
	opts.modal = true;
	return $.modalDialog.handler = $('<div/>').dialog(opts);
};

var datagridView = $.extend({},$.fn.datagrid.defaults.view,{
	onAfterRender:function(target){
		$.fn.datagrid.defaults.view.onAfterRender.call(this,target);
		var opts = $(target).datagrid('options');
		var vc = $(target).datagrid('getPanel').children('div.datagrid-view');
		vc.children('div.datagrid-empty').remove();
		if (!$(target).datagrid('getRows').length){
			var d = $('<div class="datagrid-empty"></div>').html(opts.emptyMsg || 'no records').appendTo(vc);
			d.css({ position:'absolute',
				left:0,
				top:50,
				width:'100%',
				textAlign:'center'
			});
		}
	}
});

var treegridView = $.extend({},$.fn.treegrid.defaults.view,{
	onAfterRender:function(target){
		$.fn.treegrid.defaults.view.onAfterRender.call(this,target);
		var opts = $(target).treegrid('options');
		var vc = $(target).treegrid('getPanel').children('div.treegrid-view');
		vc.children('div.treegrid-empty').remove();
		if (!$(target).treegrid('getRows').length){
			var d = $('<div class="treegrid-empty"></div>').html(opts.emptyMsg || 'no records').appendTo(vc);
			d.css({ position:'absolute',
				left:0,
				top:50,
				width:'100%',
				textAlign:'center'
			});
		}
	}
});

/**
 * 序列化form表单
 */
$.serializeObject = function(form) {
	var o = {};
	$.each(form.serializeArray(), function(index) {
		if (this['value'] != undefined && this['value'].length > 0) {
			if (o[this['name']]) {
				o[this['name']] = o[this['name']] + "," + this['value'];
			} else {
				o[this['name']] = this['value'];
			}
		}
	});
	return o;
};


/**
 * ajax加载提示
 */
function loading(){ 
    $("<div class='ajax-loading-mask'></div>").css({
    	display:"block",
    	width:"100%",
    	height:$(window).height()
    	}).appendTo("body"); 
    $("<div class='ajax-loading-mask-msg'></div>").css({
    	display:"block",
    	left:($(window.document.body).outerWidth(true) - 190) / 2,
    	top:($(window).height() - 45) / 2}).html("正在处理，请稍候。。。").appendTo("body"); 
} 

/**
 * ajax加载提示结束
 */
function loaded(){ 
     $(".ajax-loading-mask").remove(); 
     $(".ajax-loading-mask-msg").remove();             
}

/**
 * ajax提交数据
 */
$.ajaxSubmit = function(options){
	options = $.extend({
		url:'',
		dialog:null,
		datagrid:null,
		treegrid:null,
		formId:''
	},options);
	if($("#"+options.formId).form("validate")){
		var data =  $.serializeObject($("#"+ fromId));
		$.ajax({
			url: url,
            type: "post",
            dataType: "json",
            data:data,
            beforeSend:function(){
            	loading();
            },
            success: function (result) {
               if(result.code == 0){
            	   $.messager.alert('提示', result.message, 'info');
            	   if(options.datagrid){
            		   options.datagrid.datagrid('reload');
            	   }
            	   if(options.treegrid){
            		   options.treegrid.treegrid('reload');
            	   }
              	   options.dialog.dialog('destroy');
               } else {
            	   $.messager.alert('提示', result.message, 'error');
               }
            },
            complete:function(){
            	loaded();
            }
		});
	}
}

/**
 * ajax删除
 */
$.ajaxDelete = function(options){
	options = $.extend({
		url:'',
		idField:'',
		datagrid:null
	},options);
	var ids = new Array();
	var checkedRows = options.datagrid.datagrid("getChecked");
	if(!checkedRows.length>0){
		$.messager.alert('提示', "请选择要删除的信息！", 'error');
	} else {
		$.messager.confirm("确认", "您确定要删除吗？", function (r){
			if(r){
				for(var i = 0;i<checkedRows.length;i++){
					ids.push(checkedRows[i][options.idField]);
				}
				$.ajax({
			        url: options.url,
			        type: "post",
			        dataType: "json",
			        data:{"ids":ids.join(",")},
			        beforeSend:function(){
			        	loading();
			        },
			        success: function (result) {
			        	alert(result);
			           if(result.success){
			        	   parent.$.messager.alert('提示', result.msg, 'info');
			           } else {
			        	   parent.$.messager.alert('提示', result.msg, 'error');
			           }
			           datagrid.datagrid("reload",serializeObject($("#searchForm")));
			        },
			        complete:function(){
			        	loaded();
			        }
			    });
			}
		});
	}
}


function ajaxDeleteToTreegrid(treegrid,url,data){
	$.ajax({
        url: url,
        type: "post",
        dataType: "json",
        data:data,
        beforeSend:parent.ajaxLoading(),
        success: function (result) {
           parent.ajaxLoadEnd();
           if(result.success){
        	   parent.$.messager.alert('提示', result.msg, 'info');
           } else {
        	   parent.$.messager.alert('提示', result.msg, 'error');
           }
           treegrid.treegrid("reload",serializeObject($("#searchForm")));
        }
    });
}

//成功提示
function successPrompt(msg){
	parent.$.messager.show({
		title : "提示",
		msg : msg,
		showSpeed:400,
		timeout : 1000*2,
		style:{
	        right:'', 
	        top:document.body.scrollTop+document.documentElement.scrollTop, 
	        bottom:'' 
		}
	});
}

//失败提示
function errorPrompt(msg){
	parent.$.messager.alert('提示', result.msg, 'error');
}

