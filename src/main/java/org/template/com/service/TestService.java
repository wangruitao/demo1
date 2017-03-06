package org.template.com.service;

import org.template.com.model.Users;

public interface TestService {

	public boolean insert(String name);

	public Users queryEntry(Long id);
}
