package com.mario.service;

import java.util.List;

import com.mario.model.User;

public interface UserManagement {

	public User selectUser();
	public User addUser();
	public void saveUser(User player);
}
