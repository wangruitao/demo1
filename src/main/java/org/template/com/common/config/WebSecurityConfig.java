package org.template.com.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private WebAuthenticationProvider myAuthenticationProvider;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(myAuthenticationProvider);
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		// 设置不拦截规则
//		web.ignoring().antMatchers("/js/**","/css/**","/query*/**");
		
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().anyRequest().authenticated().and().formLogin()
		.loginPage("/login.html").defaultSuccessUrl("/index.html")
		.loginProcessingUrl("/login.html").failureUrl("/login.html")
				.permitAll().and().logout().permitAll();
		
		// 自定义注销
		http.logout().logoutUrl("/logout.html").logoutSuccessUrl("/login.html");
		// http.authorizeRequests().antMatchers("/",
		// "/home").permitAll().anyRequest().authenticated().and().formLogin()
		// .loginPage("/login").permitAll().and().logout().permitAll();
	}

}
