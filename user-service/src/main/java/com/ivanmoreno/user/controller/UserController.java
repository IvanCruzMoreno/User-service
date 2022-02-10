package com.ivanmoreno.user.controller;

import java.util.Collection;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ivanmoreno.commons.domain.model.entity.Entity;
import com.ivanmoreno.user.common.DuplicateUserException;
import com.ivanmoreno.user.common.InvalidUserException;
import com.ivanmoreno.user.common.UserNotFoundException;
import com.ivanmoreno.user.domain.model.entity.User;
import com.ivanmoreno.user.domain.service.UserService;
import com.ivanmoreno.user.domain.valueobject.UserVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/v1/user")
public class UserController {


	private UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping
	public ResponseEntity<Collection<User>> findByName(@RequestParam String name) throws Exception {
		log.info(String.format("user-service findByName() invoked: { %s } for { %s } ", 
				userService.getClass().getName(), name));
		
		name = name.trim().toLowerCase();
		Collection<User> users;
		
		try {
			users = userService.findByName(name);
		} catch(UserNotFoundException ex) {
			log.error("Exception raised findByName REST Call", ex);
			throw ex;
		} catch (Exception ex) {
			log.error("Exception raised findByName REST Call", ex);
			throw ex;
		}
		
		return users == null ? ResponseEntity.noContent().build() : ResponseEntity.ok(users);
	}
	
	@GetMapping("/{user_id}")
	public ResponseEntity<Entity<String>> findById(@PathVariable(name = "user_id") String id) throws Exception {
		log.info(String.format("user-service findById() invoked:{ %s } for { %s } ",
				userService.getClass().getName(), id));
		
		id = id.trim();
		Entity<String> user;
		
		try {
			user = userService.findById(id);
		} catch (Exception e) {
			log.error("Exception raised findById REST Call", e);
			throw e;
		}
		return user != null ? ResponseEntity.ok(user) : ResponseEntity.noContent().build();
	}
	
	@PostMapping
	public ResponseEntity<User> add(@RequestBody UserVO userVO) throws Exception {
		
		log.info(String.format("user-service add() invoked:{ %s } for { %s } ",
				userService.getClass().getName(), userVO.getName()));
		
		System.out.println(userVO);
		
		User user = User.getDummyUser();
		BeanUtils.copyProperties(userVO, user);
		
		try {
			userService.addUser(user);
		} catch(DuplicateUserException | InvalidUserException e) {
			log.error("Exception raised add REST Call", e);
			throw e;
		} catch(Exception e) {
			log.error("Exception raised add REST Call", e);
			throw e;
		}
		
		return ResponseEntity.status(HttpStatus.CREATED).body(user);
	}
}
