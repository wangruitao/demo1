package org.template.com.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.template.com.model.Users;
import org.template.com.service.UserService;
import org.template.com.vo.UserVo;

@Controller
public class HelloController extends BaseController {

    @RequestMapping("/index")
    public String index() {
    	System.out.println("登陆用户：" + getCurrentUser().getUsername());
        return "index";
    }

    @RequestMapping("/hello")
    public String hello() {
        return "hello";
    }
    
    @Autowired
	UserService userService;
	
	
	@RequestMapping("/insert")
	@ResponseBody
	public boolean insert() {
		boolean isSuc = userService.insert("tyest");
		return isSuc;
		
	}
	
	@RequestMapping(value = "/query/{id}")
	@ResponseBody
	public UserVo queryEntry(ModelMap model,@PathVariable("id") Long id) {
		Users u = userService.queryEntry(id);
		UserVo uv = new UserVo();
		BeanUtils.copyProperties(u, uv);
		return uv;
	}
	
	@RequestMapping(value = "/query1/{id}")
	@ResponseBody
	public UserVo queryEntry1(ModelMap model,@PathVariable("id") Long id) {
		Users u = userService.queryEntry(id);
		UserVo uv = new UserVo();
		BeanUtils.copyProperties(u, uv);
		return uv;
	}
	
	@RequestMapping(value = "/queryid/{id}")
	public String queryById(ModelMap model, @PathVariable("id") Long id) {
		model.put("us", userService.queryEntry(id));
		return "single";
	}

}
