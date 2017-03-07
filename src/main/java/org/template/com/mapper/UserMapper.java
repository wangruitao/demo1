package org.template.com.mapper;

import org.template.com.model.Users;

public interface UserMapper {

	public Users loadUserByUsername(String username);

}
