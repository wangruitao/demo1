package org.template.com.service.impl;

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
import org.template.com.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserMapper userMapper;
	@Autowired
	RoleMapper roleMapper;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users user = userMapper.loadUserByUsername(username);
		if(user != null) {
			
			List<Role> roles = roleMapper.loadRolesByUserName(username);
			user.setRoles(roles);
		}
		return user;
	}
	
	@Override
	public boolean insert(String name) {
		
		return userMapper.insert(name) > 0 ? true : false;
	}

	@Override
	public Users queryEntry(Long id) {
		return userMapper.getOne(id);
	}

}
