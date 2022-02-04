package com.ivanmoreno.user.domain.model.entity;

import com.ivanmoreno.commons.domain.model.entity.BaseEntity;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class User extends BaseEntity<String>{

	private String address;
	
	private String city;
	
	private String phoneNo;
	
	public User(String id, String name, String address, String city, String phoneNo) {
		super(id, name);
		this.address = address;
		this.city = city;
		this.phoneNo = phoneNo;
	}
	
	public User(String id, String name) {
		super(id, name);
	}
	
	public static User getDummyUser() {
		return new User(null, null);
	}
}
