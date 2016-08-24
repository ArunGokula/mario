package com.mario.service;

import java.util.List;

import com.mario.model.User;

public interface UIservice {
	void printStory();
	void printInstructions();
	void displayFinishedGameMessage();
	void displayUnFinishedGameMessage();
	void hitWallMessage();
	void displayExploreError();
	String readUserInputString();
	int readUserInputInt();
	void destroy();
	void displayUsers(List<User> users);
	void displayInvalidOptionMessage();
	void confirmUserDeletion();
	void userIntroMessage();
	void exitMessage();
}
