package com.ivanmoreno.user.domain.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.ivanmoreno.commons.domain.model.entity.Entity;
import com.ivanmoreno.user.domain.model.entity.User;

public interface UserService {

	public void addUser(User user) throws Exception;
	
	public void update(User user) throws Exception;
	
	public void delete(String id) throws Exception;
	
	public Entity<String> findById(String id) throws Exception;
	
	public Collection<User> findByName(String name) throws Exception;
	
	public Collection<User> findByCriteria(Map<String, List<String>> name) throws Exception;
}
