package com.digiplug.persistence.dao;

import java.util.List;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.digiplug.persistence.entities.User;

@Named("defaultUserDao")
public class DefaultUserDao implements UserDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void delete(User user) {
		this.entityManager.remove(user);
	}

	@Override
	public List<User> findAll() {
		TypedQuery<User> query = this.entityManager.createNamedQuery(User.QUERY_FIND_ALL, User.class);

		return query.getResultList();
	}

	@Override
	public User findUserById(Long id) {
		return this.entityManager.find(User.class, id);
	}

	@Override
	public User persist(User user) {
		this.entityManager.persist(user);
		return user;
	}

	@Override
	public User update(User user) {
		return this.entityManager.merge(user);
	}

}
