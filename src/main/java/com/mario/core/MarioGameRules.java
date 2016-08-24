package com.mario.core;

import java.sql.Connection;
import java.util.Scanner;

import com.mario.model.GameCharacter;
import com.mario.model.User;
import com.mario.persistence.RiddleDao;
import com.mario.persistence.impl.RiddleDaoImpl;
import com.mario.service.UIservice;
import com.mario.utils.Commands;

public class MarioGameRules implements GameRules {

	RiddleDao riddlesDao;
	UIservice ui;

	public MarioGameRules(Connection db, UIservice ui) {
		riddlesDao = new RiddleDaoImpl(db);
		this.ui = ui;
	}

	@Override
	public boolean apply(User player) {
		if (player.getMap().getCurrentRoom().getOccupiedBy() == GameCharacter.PRINCESS) {
			ui.displayWinMessage();
			return true;
		} else if (player.getMap().getCurrentRoom().getOccupiedBy() == GameCharacter.MONSTER) {
			ui.displayMonster();

			System.out.println();
			System.out.println(riddlesDao.getRiddle(player.getLevel()));
			String userAnswer = "";
			do {
				ui.displayAnswerPrompt();
				userAnswer = ui.readUserInputString();
				if (userAnswer.equalsIgnoreCase(Commands.GIVEUP)) {
					double healthLost = fightMonster();
					player.modifyHealth((int) healthLost);
					player.IncLevel();
					if (player.getHealth() == 0) {
						ui.displayLooseMessage();
						return true;
					}
					break;
				} else if (userAnswer.equalsIgnoreCase(Commands.HELP)) {
					ui.printHelp();
				} else if (userAnswer.equalsIgnoreCase(Commands.HELP)) {
					if (player.getGems() == 0) {
						ui.displayNoGemsMessage();
					} else {
						player.modifyGems(-1);
						System.out.println(riddlesDao.getRiddleHint(player.getLevel()));
					}
				} else if (riddlesDao.checkRiddleAnswer(userAnswer, player.getLevel())) {
					player.IncLevel();
					player.modifyGems(1);
					ui.displayCorrectAnswerMessage();
					break;
				} else {
					ui.displayInCorrectAnswerMessage();
				}
			} while (true);
			System.out.println(player);
		}
		return false;
	}

	private double fightMonster() {
		System.out.println("Fighting Monster...");
		return Math.random() * 25 - 40;
	}

}
