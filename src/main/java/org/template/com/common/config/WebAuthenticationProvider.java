package org.template.com.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.template.com.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class WebAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private UserService userService;

	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
		String username = token.getName();
		// 从数据库找到的用户
		UserDetails userDetails = null;
		if (username != null) {
			userDetails = userService.loadUserByUsername(username);
		}

		if (userDetails == null) {
			log.error(username + "用户名/密码无效");
			throw new UsernameNotFoundException("用户名/密码无效");
		} else if (!userDetails.isEnabled()) {
			log.error(username + "用户已被禁用");
			throw new DisabledException("用户已被禁用");
		} else if (!userDetails.isAccountNonExpired()) {
			log.error(username + "账号已过期");
			throw new AccountExpiredException("账号已过期");
		} else if (!userDetails.isAccountNonLocked()) {
			log.error(username + "账号已被锁定");
			throw new LockedException("账号已被锁定");
		} else if (!userDetails.isCredentialsNonExpired()) {
			log.error(username + "凭证已过期");
			throw new LockedException("凭证已过期");
		}
		// 数据库用户的密码
		String password = userDetails.getPassword();
		// 与authentication里面的credentials相比较
		
		if (!password.equals(token.getCredentials())) {
//		if (!password.equals(EncryptUtils.encodeMD5String(token.getCredentials().toString()))) {
			throw new BadCredentialsException("无效的用户名或密码");
		}
		// 授权
		return new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// 返回true后才会执行上面的authenticate方法,这步能确保authentication能正确转换类型
		return UsernamePasswordAuthenticationToken.class.equals(authentication);
	}

	
}
