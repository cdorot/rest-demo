package com.digiplug.persistence.services;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.digiplug.persistence.dao.UserDao;
import com.digiplug.persistence.entities.User;
import com.digiplug.persistence.exceptions.UnknownUserException;

@Service
@Transactional
public class DefaultUserService implements UserService {

	@Inject
	private UserDao userDao;

	@Override
	@Transactional(readOnly = true)
	public User findUserById(Long id) {
		User user = this.userDao.findUserById(id);

		if (null == user) {
			throw new UnknownUserException("No user found with id : " + id);
		}

		return user;
	}

	@Override
	public User persist(User user) {
		return this.userDao.persist(user);
	}

}
