package com.lgh.sys.control;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lgh.sys.entity.User;
import com.lgh.sys.service.UserService;

@Scope("prototype")
@Controller
@Namespace("/user")
public class UserControl implements com.opensymphony.xwork2.Action {

	@Autowired
	private UserService userService;

	private User user;
	private String tip;
	

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	@Action(value = "regModel", results = {
			@Result(name = "success", location = "regSuccess.jsp", type = "redirect"),
			@Result(name = "error", location = "regFail.jsp", type = "redirect") })
	public String regModel() throws Exception {
		try {
			userService.save(user);
		} catch (Exception e) {
			return ERROR;
		}
		return SUCCESS;
	}
	
	@Action(value = "regGrapher", results = {
			@Result(name = "success", location = "regSuccess.jsp", type = "redirect"),
			@Result(name = "error", location = "regFail.jsp", type = "redirect") })
	public String regGrapher() throws Exception {
		try {
			userService.save(user);
		} catch (Exception e) {
			return ERROR;
		}
		return SUCCESS;
	}
	
	
	@Action(value = "login", results = {
			@Result(name = "admin", location = "loginAdmin.jsp", type = "redirect"),
			@Result(name = "model", location = "loginModel.jsp", type = "redirect"),
			@Result(name = "error", location = "loginFail.jsp", type = "redirect"),
			@Result(name = "grapher", location = "loginGrapher.jsp", type = "redirect") })
	public String login() throws Exception {
		int role = user.getRole();
		HttpSession session = ServletActionContext.getRequest().getSession();
		if(userService.existUser(user)){//根据用户名和密码和角色组合判断用户是否存在
			session.setAttribute("username", user.getName());
		try {
			switch (role) {
			case 0:
				session.setAttribute("role", role);
				return "admin";
			case 1:
				session.setAttribute("role", role);
				return "model";			
			case 2:
				session.setAttribute("role", role);
				return "grapher";			
			default:
				return "error";
			}
			
			
		} catch (Exception e) {
			return ERROR;
		}
		}
		return ERROR;
	}
	
	

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}



}
