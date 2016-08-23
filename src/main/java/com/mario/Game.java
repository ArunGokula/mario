package com.mario;

import java.sql.Connection;

import com.mario.core.GameRules;
import com.mario.core.MarioGameRules;
import com.mario.model.Palace;
import com.mario.model.User;
import com.mario.service.UserManagement;
import com.mario.service.impl.UserManageCLI;
import com.mario.utils.HSQLDBConnection;

public class Game {

	public static void main(String[] args) {
		Connection db =  HSQLDBConnection.getConnection();
		GameRules gameRules = new MarioGameRules(db);
		try {
			UserManagement usermanager = new UserManageCLI(db);

			printStory();
			User player = usermanager.selectUser();
			boolean gotResult = false;
			System.out.println("Let's go!!");
			while (!gotResult) {
				printInstructions();
				if(player.explore()){ // returns true if user wants to quit
					break;
				}
				gotResult = gameRules.apply(player);
			}
			if(gotResult){
				player.setMap(new Palace());
				System.out.println("Thanks for playing.Play again to solve harder riddles.");
			}else{
				System.out.println("Your game will be saved.You can resume the game by selecting same user");
			}
			usermanager.saveUser(player);
		} finally {
			HSQLDBConnection.closeConnection();
		}
	}

	private static void printStory() {
		System.out.println("You are going to enter a palace.\n"
				+ "There are so many rooms in this palace.\n"
				+ "There is a princess locked up in one of those rooms, surrounded by monsters\n"
				+ "Navigate to princess with N,E,W,S keys  \n"
				+ "Answer riddles asked by monster to pass each monster");
		System.out.println("\n--------------------------------------\n");
		
		System.out.flush();
	}
	
	private static void printInstructions(){
		System.out.println(
				"\nWhich direction do you want to move?\n" + "NORTH(N/n) , EAST(E/e),WEST(W/w),SOUTH(S/s)\n" + "Type Quit/Q/q to exit");
		
	}

}
