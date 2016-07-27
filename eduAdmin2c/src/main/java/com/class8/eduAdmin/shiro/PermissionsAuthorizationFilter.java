package com.class8.eduAdmin.shiro;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.StringUtils;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import org.apache.shiro.web.util.WebUtils;

import com.class8.eduAdmin.bean.ResultMessage;
import com.class8.eduAdmin.util.JsonUtil;

/**
 * 自定义重写perms过滤器，主要实现如下功能：
 * 1.ajax请求访问时无权限，返回json字符串
 *
 */
public class PermissionsAuthorizationFilter extends AuthorizationFilter {

	@Override
	public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
			throws IOException
    {
        Subject subject = getSubject(request, response);
        String perms[] = (String[])(String[])mappedValue;
        boolean isPermitted = true;
        if(perms != null && perms.length > 0)
            if(perms.length == 1)
            {
                if(!subject.isPermitted(perms[0]))
                    isPermitted = false;
            } else
            if(!subject.isPermittedAll(perms))
                isPermitted = false;
        return isPermitted;
    }
	
	/**
	 * 重写onAccessDenied方法，主要为了解决ajax请求没有登录和无权限的问题
	 */
	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response)
			throws IOException
    {
        Subject subject = getSubject(request, response);
        if(subject.getPrincipal() == null)
        {	
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
        } else
        {	
        	if(!"XMLHttpRequest".equalsIgnoreCase(((HttpServletRequest) request).getHeader("X-Requested-With"))){
        		String unauthorizedUrl = getUnauthorizedUrl();
                if(StringUtils.hasText(unauthorizedUrl))
                    WebUtils.issueRedirect(request, response, unauthorizedUrl);
                else
                    WebUtils.toHttp(response).sendError(401);
    	    } else {
    	    	ResultMessage resultMessage = new ResultMessage();
    	    	resultMessage.setSuccess(false);
    	    	resultMessage.setMessage("对不起，您没有足够的权限执行该操作!");
    	    	
    	    	PrintWriter out = response.getWriter();
    	    	out.write(JsonUtil.toJson(resultMessage));
    	    	out.close();
    	    }
        }
        return false;
    }

}
