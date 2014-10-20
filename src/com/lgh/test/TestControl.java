package com.lgh.test;

import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.lgh.common.tools.json.JsonUtil;
import com.lgh.sys.entity.User;
import com.opensymphony.xwork2.Action;

@Scope("prototype")
@Controller
@Namespace("/name")
public class TestControl implements Action {

	@Autowired
	private TestService testService;

	private User user;
	private String name;

	private List<User> List;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@org.apache.struts2.convention.annotation.Action(value = "test", results = {
			@Result(name = "success", location = "/index.jsp", type = "redirect"),
			@Result(name = "register", location = "/haha.jsp", type = "redirect") })
	public String test() throws Exception {
		List = testService.findBySome("1", User.class);
		user = testService.findBySome("1", User.class).get(0);
		name = user.getName();
		JsonUtil.outToJson(ServletActionContext.getResponse(), user);
		return SUCCESS;
	}

	@org.apache.struts2.convention.annotation.Action(value = "/haha/register", results = {
			@Result(name = "success", location = "/haha.jsp", type = "redirect"),
			@Result(name = "register", location = "haha.jsp", type = "redirect") })
	public String register() throws Exception {
		List = testService.findBySome("1", User.class);
		user = testService.findBySome("1", User.class).get(0);
		name = user.getName();
		// JsonUtil.outToJson(ServletActionContext.getResponse(), user);
		return "success";
	}

	@Override
	public String execute() throws Exception {
		User user = new User();
		user.setName("hahaahh");
		user.setPassword("999");
		testService.addUser(user);
		return null;
	}

}
