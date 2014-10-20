package com.lgh.sys.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import net.sf.cglib.core.TinyBitSet;

import com.lgh.common.entity.BaseEntity;

@Entity
@Table(name="user")
public class User extends BaseEntity implements Serializable {

	private static final long serialVersionUID = -998920945767477693L;
	
	@Column(name="name")
	private String name;
	@Column(name="password")
	private String password;
	@Column(name="realname")
	private String realname;
	@Column(name="role")
	private int role;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}
	

}
