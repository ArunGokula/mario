package com.mario;

import com.mario.model.User;
import com.mario.service.UserManagement;
import com.mario.service.impl.UserManageCLI;
import com.mario.utils.DBConnection;

public class Game {

	public static void main(String[] args) {
		DBConnection db = new DBConnection();

		try {
			UserManagement usermanager = new UserManageCLI(db);

			printStory();
			User player = usermanager.selectUser();
			player.explore();
		} finally {
			db.closeConnection();
		}
	}

	private static void printStory() {
		System.out.println("You are going to enter a palace.\n"
				+ "There are so many rooms in this palace.\nThere is a princess locked up in one of those rooms.\n"
				+ "Navigate to princess with N,E,W,S keys  ");
		System.out.println("\n--------------------------------------\n");
		System.out.flush();
	}

}
