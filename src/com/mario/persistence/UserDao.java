package com.mario.persistence;

import java.util.List;

import com.mario.model.User;

public interface UserDao {
	public List<User> getUsers();
	public void persistUser(User toSave);
	public void updateUser(User player);
	public void deleteUser(String name);
}
