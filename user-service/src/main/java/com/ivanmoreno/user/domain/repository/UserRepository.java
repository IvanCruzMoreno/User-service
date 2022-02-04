package com.ivanmoreno.user.domain.repository;

import java.util.Collection;

import com.ivanmoreno.commons.domain.repository.Repository;

public interface UserRepository<E, ID> extends Repository<E, ID>{
	
	boolean containsName(String name) throws Exception;
	
	public Collection<E> findByName(String name) throws Exception;
}
