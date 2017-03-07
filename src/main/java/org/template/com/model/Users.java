package org.template.com.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class Users implements UserDetails{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String name;
	private String logName;
	private String userPassword;
	private Integer userLevel;
	private Integer userLoc;
	
	private List<Role> roles;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for(Role r : roles) {
			authorities.add(new SimpleGrantedAuthority(r.getRoleCode()));
		}
		return authorities;
	}
	@Override
	public String getPassword() {
		
		return this.getPassword();
	}
	@Override
	public String getUsername() {
		return this.getLogName();
	}
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	@Override
	public boolean isEnabled() {
		return true;
	}

}