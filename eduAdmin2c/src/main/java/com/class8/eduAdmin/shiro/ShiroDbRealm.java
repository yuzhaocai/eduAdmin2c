package com.class8.eduAdmin.shiro;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.class8.eduAdmin.constant.SecurityConst;
import com.class8.eduAdmin.constant.StatusConst;
import com.class8.eduAdmin.constant.UserStatusConst;
import com.class8.eduAdmin.model.Resource;
import com.class8.eduAdmin.model.User;
import com.class8.eduAdmin.service.IResourceService;
import com.class8.eduAdmin.service.IRoleResourceService;
import com.class8.eduAdmin.service.IUserRoleService;
import com.class8.eduAdmin.service.IUserService;
import com.class8.eduAdmin.util.Digests;
import com.class8.eduAdmin.util.Encodes;

public class ShiroDbRealm extends AuthorizingRealm {
	
	private static final int INTERATIONS = 1024;
	private static final int SALT_SIZE = 8;
	private static final String ALGORITHM = "SHA-1";
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private IUserRoleService userRoleService;
	
	@Autowired
	private IRoleResourceService roleResourceService;
	
	@Autowired
	private IResourceService resourceService;
	
	public ShiroDbRealm(){
		super();
		HashedCredentialsMatcher matcher = new HashedCredentialsMatcher(ALGORITHM);
		matcher.setHashIterations(INTERATIONS);

		setCredentialsMatcher(matcher);
	}
	
	/**
	 * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用.
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalcollection) {
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		ShiroUser shiroUser = (ShiroUser) principalcollection.fromRealm(getName()).iterator().next();
		
		List<Resource> resources = new ArrayList<Resource>();
		if(SecurityConst.ADMINISTRATOR.equals(shiroUser.getLoginName())){
			resources.addAll(resourceService.findActiveResources());
		} else {
			List<Integer> roleIds = userRoleService.findRoleIdsByUserId(shiroUser.getId());
			for (Integer roleId : roleIds) {
				List<Integer> resourceIds = roleResourceService.findResourceIdsByRoleId(roleId);
				if(!resourceIds.isEmpty()){
					resources.addAll(resourceService.findResources(resourceIds));
				}
			}
		}
		
		if(!resources.isEmpty()){
			Set<String> permissions = new HashSet<String>();
			for (Resource resource : resources) {
				if(StatusConst.NORMAL == resource.getStatus()){
					permissions.add(resource.getPermission());
				}
			}
			info.addStringPermissions(permissions);
			return info;
		} else {
			return null;
		}
		
	}
	
	/**
	 * 认证回调函数, 登录时调用.
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationtoken)throws AuthenticationException {
		CaptachaUsernamePasswordToken token = (CaptachaUsernamePasswordToken) authenticationtoken;
		
		String param = token.getCaptacha();
		String captacha = (String) SecurityUtils.getSubject().getSession().getAttribute(CaptchaFormAuthenticationFilter.DEFAULT_CAPTACHA_PARAM);
		
		if(!captacha.equalsIgnoreCase(param)){
			throw new IncorrectCaptchaException();
		}
		
		String username = token.getUsername();
		User user = userService.findUserByUsername(username);
		
		if(user == null){
			throw new UnknownAccountException();
		}
		
		if(UserStatusConst.LOCKED == user.getStatus()){
			throw new LockedAccountException();
		}
		
		byte[] salt = Encodes.decodeHex(user.getSalt());
		ShiroUser shiroUser = new ShiroUser(user.getId(),user.getUsername(),user);
		AuthenticationInfo info = new SimpleAuthenticationInfo(shiroUser,user.getPassword(),ByteSource.Util.bytes(salt),getName());
		
		return info;
	}
	
	/**
	 * 更新用户授权信息缓存.
	 */
	public void clearCachedAuthorizationInfo(String principal) {
		SimplePrincipalCollection principals = new SimplePrincipalCollection(principal, getName());
		clearCachedAuthorizationInfo(principals);
	}
	
	/**
	 * 清除所有用户授权信息缓存.
	 */
	public void clearAllCachedAuthorizationInfo() {
		Cache<Object, AuthorizationInfo> cache = getAuthorizationCache();
		if (cache != null) {
			for (Object key : cache.keys()) {
				cache.remove(key);
			}
		}
	}
	
	/**
	 * 对密码进行加密
	 */
	public HashPassword encrypt(String plainText) {
		HashPassword result = new HashPassword();
		byte[] salt = Digests.generateSalt(SALT_SIZE);
		result.setSalt(Encodes.encodeHex(salt));

		byte[] hashPassword = Digests.sha1(plainText.getBytes(), salt, INTERATIONS);
		result.setPassword(Encodes.encodeHex(hashPassword));
		return result;
	}

}
