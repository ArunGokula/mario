package com.mario.core;

import java.util.Scanner;

import com.mario.model.GameCharacter;
import com.mario.model.User;
import com.mario.persistence.RiddleDao;
import com.mario.persistence.impl.RiddleDaoImpl;
import com.mario.utils.HSQLDBConnection;

public class MarioGameRules implements GameRules {

	RiddleDao riddlesDao;

	public MarioGameRules(HSQLDBConnection db) {
		riddlesDao = new RiddleDaoImpl(db);
	}

	@Override
	public boolean apply(User player) {
		if (player.getMap().getCurrentRoom().getOccupiedBy() == GameCharacter.PRINCESS) {
			System.out.println("Princess is in this room.You won!!");
			return true;
		} else if (player.getMap().getCurrentRoom().getOccupiedBy() == GameCharacter.MONSTER) {
			System.out.println("There is monster in this room");
			printHelp();
			System.out.println();
			System.out.println(riddlesDao.getRiddle(player.getLevel()));
			String userAnswer = "";
			do {
				System.out.print("\nType your Answer/Command:");
				System.out.flush();
				Scanner scan = new Scanner(System.in);
				userAnswer = scan.nextLine();
				if (userAnswer.equalsIgnoreCase("#giveup")) {
					double healthLost = fightMonster();
					player.modifyHealth((int) healthLost);
					player.IncLevel();
					if(player.getHealth()==0){
						System.out.println("You died.You lost the game");
						return true;
					}
					break;
				} else if (userAnswer.equalsIgnoreCase("#pay")) {
					if (player.getGems() == 0) {
						System.out.println("You have no Gems.You can fight by typing #giveup if you cannot answer");
					} else {
						player.modifyGems(-1);
						System.out.println(riddlesDao.getRiddleHint(player.getLevel()));
					}
				} else if (riddlesDao.checkRiddleAnswer(userAnswer, player.getLevel())) {
					player.IncLevel();
					player.modifyGems(1);
					System.out.println("Correct Answer! Monster gave you one gem");
					break;
				} else {
					System.out.println("Wrong Answer! Try again");
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

	public void printHelp() {
		System.out.println("You have to answer monster riddle.\n" + "If you answer correctly you will get gems.\n"
				+ "It will give you hints if you pay gems by typing '#pay' \n" + "You can type '#giveup' to fight the monster\n");
	}

}
