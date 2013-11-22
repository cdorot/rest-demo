package com.digiplug.persistence.dao;

import java.util.List;

import com.digiplug.persistence.entities.User;

public interface UserDao {

	void delete(User user);

	List<User> findAll();

	User findUserById(Long id);

	User persist(User user);

	User update(User user);

}
