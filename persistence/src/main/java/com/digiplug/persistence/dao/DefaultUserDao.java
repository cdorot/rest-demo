package com.digiplug.persistence.dao;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.digiplug.persistence.entities.User;

@Named("defaultUserDao")
public class DefaultUserDao implements UserDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public User findUserById(Long id) {
		return this.entityManager.find(User.class, id);
	}

	@Override
	public User persist(User user) {
		this.entityManager.persist(user);
		return user;
	}

}
