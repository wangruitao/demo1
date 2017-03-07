package org.template.com;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.template.com.mapper.TestMapper;
import org.template.com.model.Users;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Demo1Application.class)
@WebAppConfiguration
public class Demo1ApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Autowired
	private TestMapper testMapper;
	
	@Test
	@Rollback
	public void findByName() throws Exception {
		Users u = testMapper.queryEntry(1L);
		Assert.assertEquals("王瑞涛", u.getName());
	}
	
	@Test
	@Rollback
	public void getOne() throws Exception {
		Users u = testMapper.getOne(1L);
		Assert.assertEquals("王瑞涛", u.getName());
	}
}
