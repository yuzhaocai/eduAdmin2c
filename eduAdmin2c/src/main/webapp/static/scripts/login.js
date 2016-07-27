$(function(){
	var $form = $("form");
	var $username = $(".username");
	var $password = $(".password");
	var $captacha = $(".captacha");
	var $invalid = $(".invalid");
	var $message = $(".message");
	var $logging = $(".logging");
	
	$logging.bind("click",function(){
		if($username.val().replace(/(^\s*)|(\s*$)/g, "").length ==0){
			$username.focus();
			$invalid.show();
			$message.text("请输入用户名.");
		} else if($password.val().replace(/(^\s*)|(\s*$)/g, "").length ==0){
			$password.focus();
			$invalid.show();
			$message.text("请输入密码.");
		} else if($captacha.val().replace(/(^\s*)|(\s*$)/g, "").length ==0){
			$captacha.focus();
			$invalid.show();
			$message.text("请输入验证码.");
		} else {
			$invalid.hide();
			$logging.attr("disabled", true);
			$form.submit();
		}
	});
});

/**
 * 切换验证码
 */
function changeCaptacha(){
	var $captachaImage = $("#captachaImage");
	$captachaImage.attr("src",$captachaImage.attr("src").split("?")[0] + "?r=" + Math.random());
}