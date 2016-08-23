package com.mario;

import com.mario.core.MarioGameRules;
import com.mario.model.GameRules;
import com.mario.model.User;
import com.mario.service.UserManagement;
import com.mario.service.impl.UserManageCLI;
import com.mario.utils.DBConnection;

public class Game {

	public static void main(String[] args) {
		DBConnection db = new DBConnection();
		GameRules gameRules = new MarioGameRules();
		try {
			UserManagement usermanager = new UserManageCLI(db);

			printStory();
			User player = usermanager.selectUser();
			boolean gotResult = false;
			printInstructions();
			while (!gotResult) {
				if(player.explore()){
					break;
				}
				gotResult = gameRules.apply(player);
			}
			usermanager.saveUser(player);
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
	
	private static void printInstructions(){
		System.out.println(
				"Let's Go.\nWhich direction do you want to move?\n" + "NORTH(N/n) , EAST(E/e),WEST(W/w),SOUTH(S/s)\n" + "Type Quit/Q/q to exit");
		
	}

}
