/**
 * 动态设置datagrid的列的宽度
 */
function fixWidth(percent) {  
	return document.body.clientWidth * percent/100 ;
}

/**
 * 获取url路径中参数
 */
function getUrlParam(name) {
    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r!=null) {
        return r[2];
    }
    return "";
}

/**
 * 格式化字符串
 */
function formatString(str) {
	for ( var i = 0; i < arguments.length - 1; i++) {
		str = str.replace("{" + i + "}", arguments[i + 1]);
	}
	return str;
};


