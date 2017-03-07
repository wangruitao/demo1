package org.template.com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.template.com.mapper.RoleMapper;
import org.template.com.mapper.UserMapper;
import org.template.com.model.Role;
import org.template.com.model.Users;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserService implements UserDetailsService {

	@Autowired
	UserMapper userMapper;
	@Autowired
	RoleMapper roleMapper;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users user = userMapper.loadUserByUsername(username);
		if(user == null) {
			log.error("用户不存在");
			throw new RuntimeException("用户不存在");
		}
		List<Role> roles = roleMapper.loadRolesByUserName(username);
		user.setRoles(roles);
		return user;
	}

}
