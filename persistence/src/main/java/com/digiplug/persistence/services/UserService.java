package com.digiplug.persistence.services;

import com.digiplug.persistence.entities.User;

public interface UserService {

	User findUserById(Long id);

	User persist(User user);

}
