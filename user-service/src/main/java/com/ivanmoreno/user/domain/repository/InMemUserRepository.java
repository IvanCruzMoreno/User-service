package com.ivanmoreno.user.domain.repository;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.ivanmoreno.user.common.UserNotFoundException;
import com.ivanmoreno.user.domain.model.entity.User;

@Repository("userRepository")
public class InMemUserRepository implements UserRepository<User, String> {

	private static final Map<String, User> entities;
	
	static {
		
		entities = new ConcurrentHashMap<>(Map.ofEntries(
				Map.entry("1", new User("1", "User Name 1", "Address 1", "City 1", "666")), 
				Map.entry("2", new User("2", "User Name 2", "Address 2", "City 2", "999"))));
	}
	
	@Override
	public void add(User entity) {
		entities.put(entity.getId(), entity);
	}

	@Override
	public void remove(String id) {
		entities.computeIfPresent(id, (k,v) -> entities.remove(k));
	}

	@Override
	public void update(User entity) {
		entities.computeIfPresent(entity.getId(), (k,v) -> entities.put(k, entity));
	}

	@Override
	public boolean contains(String id) {
		throw new UnsupportedOperationException("Not supported yet");
	}

	@Override
	public User get(String id) {
		return entities.get(id);
	}

	@Override
	public Collection<User> getAll() {
		return entities.values();
	}

	@Override
	public boolean containsName(String name) throws Exception {
		try {
			return !this.findByName(name).isEmpty();
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Collection<User> findByName(String name) throws Exception {
		
		List<User> users = entities.entrySet().stream()
				.filter(entry -> entry.getValue().getName().toLowerCase().contains(name))
				.map(entry -> entry.getValue())
				.collect(Collectors.toList());
		
		if(users != null && users.isEmpty()) {
			Object[] args = {name};
			throw new UserNotFoundException("userNotFound", args);
		}
		
		return users;
	}

}
