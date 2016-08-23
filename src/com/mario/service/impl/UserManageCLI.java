package com.mario.service.impl;

import java.util.List;
import java.util.Scanner;

import com.mario.model.Palace;
import com.mario.model.User;
import com.mario.persistence.UserDao;
import com.mario.persistence.impl.UserDaoHsql;
import com.mario.service.UserManagement;
import com.mario.utils.HSQLDBConnection;

public class UserManageCLI implements UserManagement {

	UserDao userdao;

	public UserManageCLI(HSQLDBConnection db) {
		userdao = new UserDaoHsql(db);
	}

	private void displayUsers(List<User> users) {
		printHeader();
		int i = 0;
		for (User user : users) {
			System.out.println(++i + ")   " + user.getName() + "\t" + user.getGems() + "\t" + user.getHealth());
		}
		System.out.println(++i+ ")   create New user");
		System.out.println(++i+ ")   Delete user");
		System.out.flush();
	}

	private void printHeader() {
		System.out.println("Select a user from the following");
		System.out.println("----------------------------------");
		System.out.println("\tName\tGems\tHealth");
		System.out.println("----------------------------------");
		System.out.flush();
	}

	public User selectUser() {
		List<User> users = userdao.getUsers();
		if (users != null && users.size() > 0) {
			displayUsers(users);
			Scanner scan = new Scanner(System.in);
			int selected = scan.nextInt();
			if (selected > 0 && selected <= users.size()) {
				return users.get(selected - 1);
			}else if(selected==users.size()+1){
				deleteUser();
				return selectUser();
			}else{
				System.out.println("Invalid option.Try again");
				return selectUser();
			}
		}
		return addUser();
	}

	private void deleteUser() {
		Scanner scan = new Scanner(System.in);
		System.out.println("To confirm deletion type the name of user.To cancel enter #cancel");
		String name = scan.nextLine();
		userdao.deleteUser(name);
	}

	@Override
	public User addUser() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Let's get introduced");
		System.out.println("What should I call you?");
		String name = scan.nextLine();
		User selectedUser = new User(name, 1,0, 100, new Palace());
		userdao.persistUser(selectedUser);
		return selectedUser;
	}

	@Override
	public void saveUser(User player) {
		userdao.updateUser(player);
		System.out.println("Goodbye! Your game is saved");
	}

}
