package com.ivanmoreno.user.domain.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ivanmoreno.commons.domain.model.entity.Entity;
import com.ivanmoreno.commons.domain.service.BaseService;
import com.ivanmoreno.user.common.DuplicateUserException;
import com.ivanmoreno.user.common.InvalidUserException;
import com.ivanmoreno.user.domain.model.entity.User;
import com.ivanmoreno.user.domain.repository.UserRepository;

@Service("userService")
public class UserServiceImpl extends BaseService<User, String> implements UserService{

	private UserRepository<User, String> userRepo;
	
	public UserServiceImpl(UserRepository<User, String> repo) {
		super(repo);
		this.userRepo = repo;
	}

	@Override
	public void addUser(User user) throws Exception {
		if(user.getName() == null || user.getName().equals("")) {
			Object[] args = {"User with null or empty name"};
			throw new InvalidUserException("invalidUser", args);
		}
		
		if(userRepo.containsName(user.getName())) {
			Object[] args = {user.getName()};
			throw new DuplicateUserException("duplicateUser", args);
		}
		super.add(user);
	}
	
	@Override
	public void update(User user) throws Exception {
		userRepo.update(user);
	}

	@Override
	public void delete(String id) throws Exception {
		userRepo.remove(id);
	}

	@Override
	public Entity<String> findById(String id) throws Exception {
		return userRepo.get(id);
	}

	@Override
	public Collection<User> findByName(String name) throws Exception {
		return userRepo.findByName(name);
	}

	@Override
	public Collection<User> findByCriteria(Map<String, List<String>> name) throws Exception {
		throw new UnsupportedOperationException("Not supported yet");
	}

}
