package com.class8.eduAdmin.shiro;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import com.class8.eduAdmin.bean.ResultMessage;
import com.class8.eduAdmin.util.Encodes;
import com.class8.eduAdmin.util.JsonUtil;
/**
 * 添加验证码的认证方式
 *
 */
public class CaptchaFormAuthenticationFilter extends FormAuthenticationFilter{
	
	public static final String DEFAULT_CAPTACHA_PARAM = "captacha";
	
	private String captachaParam;
	
	public CaptchaFormAuthenticationFilter(){
		captachaParam = DEFAULT_CAPTACHA_PARAM;
	}
	
	private static final Logger log = Logger.getLogger(CaptchaFormAuthenticationFilter.class);
	
	/**
	 * 重写onAcdessDenied方法，主要为区分ajax请求和非ajax请求
	 */
	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
	    if(isLoginRequest(request, response))
	    {
	        if(isLoginSubmission(request, response))
	        {
	            if(log.isTraceEnabled())
	                log.trace("Login submission detected.  Attempting to execute login.");
	            return executeLogin(request, response);
	        }
	        if(log.isTraceEnabled())
	            log.trace("Login page view.");
	        return true;
	    }
	    if(log.isTraceEnabled())
	        log.trace((new StringBuilder()).append("Attempting to access a path which requires authentication.  Forwarding to the Authentication url [").append(getLoginUrl()).append("]").toString());
	    if(!"XMLHttpRequest".equalsIgnoreCase(((HttpServletRequest) request).getHeader("X-Requested-With"))){
	    	saveRequestAndRedirectToLogin(request, response);
	    } else {
	    	ResultMessage resultMessage = new ResultMessage();
	    	resultMessage.setSuccess(false);
	    	resultMessage.setMessage("您尚未登录或登录时间过长,请重新登录!");
	    	
	    	PrintWriter out = response.getWriter();
	    	out.write(JsonUtil.toJson(resultMessage));
	    	out.close();
	    }
	    return false;
	}
	
	@Override
	protected AuthenticationToken createToken(ServletRequest request,ServletResponse response) {
		String username = getUsername(request);
		String password = getPassword(request);
		boolean rememberMe = isRememberMe(request);
        String host = getHost(request);
        String captacha = getCaptacha(request);
		return new CaptachaUsernamePasswordToken(username, password, rememberMe, host, captacha);
	}
	
	/**
	 * 重写redirectToLogin，在loginUrl后边添加returnUrl参数，用来记录未登录前要请求的url，也就是requestUrl
	 */
	@Override
	protected void redirectToLogin(ServletRequest request, ServletResponse response) throws IOException {
		String loginUrl = getLoginUrl();
		StringBuilder targetUrl = new StringBuilder(loginUrl);
		SavedRequest saveRequest = WebUtils.getSavedRequest(request);
		if(saveRequest != null){
			String returnUrl = saveRequest.getRequestUrl();	
			returnUrl = returnUrl.replace(WebUtils.toHttp(request).getContextPath(), "");
			targetUrl.append("?returnUrl=").append(Encodes.urlEncode(returnUrl));
		}
		WebUtils.issueRedirect(request, response, targetUrl.toString());
	}

	public String getCaptachaParam() {
		return captachaParam;
	}

	public void setCaptachaParam(String captachaParam) {
		this.captachaParam = captachaParam;
	}
	
	protected String getCaptacha(ServletRequest request){
		return WebUtils.getCleanParam(request, getCaptachaParam());
	}
	
}
