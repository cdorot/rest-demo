package com.digiplug.persistence.services;

import java.util.List;

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
	public void delete(User user) {
		this.userDao.delete(user);
	}

	@Override
	@Transactional(readOnly = true)
	public List<User> findAll() {
		return this.userDao.findAll();
	}

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

	@Override
	public User update(User user) {
		return this.update(user);
	}

}
