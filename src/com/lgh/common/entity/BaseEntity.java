package com.lgh.common.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;

@MappedSuperclass
public abstract class BaseEntity{
	@Id
	@GenericGenerator(name = "generator", strategy = "native")
	@GeneratedValue(generator = "generator")
	@Column(name = "id")
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
