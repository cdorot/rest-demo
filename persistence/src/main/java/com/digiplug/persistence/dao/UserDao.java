package com.digiplug.persistence.dao;

import com.digiplug.persistence.entities.User;

public interface UserDao {

	User findUserById(Long id);

	User persist(User user);

}
