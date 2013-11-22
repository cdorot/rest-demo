package com.digiplug.persistence.services;

import java.util.List;

import com.digiplug.persistence.entities.User;

public interface UserService {

	void delete(User user);

	List<User> findAll();

	User findUserById(Long id);

	User persist(User user);

	User update(User user);

}
