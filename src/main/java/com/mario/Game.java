package com.mario;

import java.sql.Connection;

import com.mario.core.GameRules;
import com.mario.core.MarioGameRules;
import com.mario.model.Palace;
import com.mario.model.User;
import com.mario.persistence.impl.HSQLDBConnection;
import com.mario.service.UIservice;
import com.mario.service.UserManagement;
import com.mario.service.impl.ConsoleBasedUI;
import com.mario.service.impl.UserManageCLI;

public class Game {

	public static void main(String[] args) {
		Connection db = HSQLDBConnection.getConnection();
		try {
			UIservice ui = new ConsoleBasedUI();
			ui.printStory();
			UserManagement usermanager = new UserManageCLI(db, ui);
			GameRules gameRules = new MarioGameRules(db, ui);

			User player = usermanager.selectUser();
			player.setUI(ui);
			boolean gotResult = false;
			System.out.println("Let's go!!");
			while (!gotResult) {
				if (player.explore()) { // returns true if user wants to quit
					break;
				}
				gotResult = gameRules.apply(player);
			}
			if (gotResult) {
				player.setMap(new Palace());
				player.modifyHealth(100-player.getHealth());
				ui.displayFinishedGameMessage();
			} else {
				ui.displayUnFinishedGameMessage();
			}
			usermanager.saveUser(player);
		} finally {
			HSQLDBConnection.closeConnection();
		}
	}
}
