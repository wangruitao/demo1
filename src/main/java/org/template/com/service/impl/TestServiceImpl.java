package org.template.com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.template.com.mapper.TestMapper;
import org.template.com.model.Users;
import org.template.com.service.TestService;

@Service
public class TestServiceImpl implements TestService {

	@Autowired
	private TestMapper testDao;
	
	@Override
	public boolean insert(String name) {
		
		return testDao.insert(name) > 0 ? true : false;
	}

	@Override
	public Users queryEntry(Long id) {
		return testDao.queryEntry(id);
	}

}
