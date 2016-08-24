package com.mario.service.impl;

import java.util.List;
import java.util.Scanner;

import com.mario.model.User;
import com.mario.service.UIservice;

public class ConsoleBasedUI implements UIservice {

	Scanner scan = new Scanner(System.in);

	@Override
	public void printStory() {
		System.out.println("You are going to enter a palace.\n" + "There are so many rooms in this palace.\n"
				+ "There is a princess locked up in one of those rooms, surrounded by monsters\n" + "Navigate to princess with N,E,W,S keys  \n"
				+ "Answer riddles asked by monster to pass each monster");
		System.out.println("\n--------------------------------------\n");

		System.out.flush();
	}

	@Override
	public void printInstructions() {
		System.out.println("\nWhich direction do you want to move?\n" + "NORTH(N/n) , EAST(E/e),WEST(W/w),SOUTH(S/s)\n" + "Type Quit/Q/q to exit");

	}

	@Override
	public void displayFinishedGameMessage() {
		System.out.println("Thanks for playing.Play again to solve harder riddles.");
	}

	@Override
	public void displayUnFinishedGameMessage() {
		System.out.println("Your game will be saved.You can resume the game by selecting same user");
	}

	@Override
	public void hitWallMessage() {
		System.out.println("Ouch!I hit a wall.Choose Different direction");
	}

	@Override
	public void displayExploreError() {
		System.err.println("Invalid input.Only N/E/W/S/Q are allowed");
	}

	@Override
	public String readUserInputString() {
		String input = scan.nextLine();
		return input;
	}

	@Override
	public int readUserInputInt() {
		int input = scan.nextInt();
		scan.nextLine();//takes care of \n character after number
		return input;
	}

	@Override
	public void destroy() {
		scan.close();
	}
	
	@Override
	public void displayUsers(List<User> users) {
		printHeader();
		int i = 0;
		for (User user : users) {
			System.out.println(++i + ")   " + user.getName() + "\t" + user.getGems() + "\t" + user.getHealth());
		}
		System.out.println(++i+ ")   create New user");
		System.out.println(++i+ ")   Delete A user");
		System.out.flush();
	}

	private void printHeader() {
		System.out.println("Select a user from the following");
		System.out.println("----------------------------------");
		System.out.println("\tName\tGems\tHealth");
		System.out.println("----------------------------------");
		System.out.flush();
	}

	@Override
	public void displayInvalidOptionMessage() {
		System.out.println("Invalid option.Try again");
	}

	@Override
	public void confirmUserDeletion() {
		System.out.println("To confirm deletion - enter the name of user.To cancel - enter #cancel");
	}

	@Override
	public void userIntroMessage() {
		System.out.println("Let's get introduced");
		System.out.println("What should I call you?");
	}

	@Override
	public void exitMessage() {
		System.out.println("Goodbye!");
	}
}
