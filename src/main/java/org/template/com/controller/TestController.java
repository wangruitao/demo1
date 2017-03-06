package org.template.com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.template.com.model.Users;
import org.template.com.service.TestService;

@RestController
@RequestMapping("/test")
public class TestController {

	@Autowired
	TestService testService;
	
	
	@RequestMapping("/insert")
	public boolean insert() {
		boolean isSuc = testService.insert("tyest");
		return isSuc;
		
	}
	
	@RequestMapping("/query/{id}")
	public Users queryEntry(@PathVariable("id") Long id) {
		return testService.queryEntry(id);
	}
}
