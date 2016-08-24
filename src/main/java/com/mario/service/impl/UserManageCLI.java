package com.mario.service.impl;

import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

import com.mario.model.Palace;
import com.mario.model.User;
import com.mario.persistence.UserDao;
import com.mario.persistence.impl.UserDaoHsql;
import com.mario.service.UIservice;
import com.mario.service.UserManagement;

public class UserManageCLI implements UserManagement {

	UserDao userdao;
	UIservice ui;

	public UserManageCLI(Connection db, UIservice ui) {
		userdao = new UserDaoHsql(db);
		this.ui=ui;
	}

	

	public User selectUser() {
		List<User> users = userdao.getUsers();
		if (users != null && users.size() > 0) {
			ui.displayUsers(users);
			
			int selected = ui.readUserInputInt();
			if (selected > 0 && selected <= users.size()) {
				return users.get(selected - 1);
			}else if(selected==users.size()+2){
				deleteUser();
				return selectUser();
			}else if(selected==users.size()+1){
				return addUser();
			}else{
				ui.displayInvalidOptionMessage();
			}
		}
		return addUser();
	}

	private void deleteUser() {
		ui.confirmUserDeletion();
		String name = ui.readUserInputString();
		userdao.deleteUser(name);
	}

	@Override
	public User addUser() {
		ui.userIntroMessage();
		String name = ui.readUserInputString();
		User selectedUser = new User(name);
		userdao.persistUser(selectedUser);
		return selectedUser;
	}

	@Override
	public void saveUser(User player) {
		userdao.updateUser(player);
		ui.exitMessage();
	}

}
