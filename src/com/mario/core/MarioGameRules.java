package com.mario.core;

import com.mario.model.GameCharacter;
import com.mario.model.GameRules;
import com.mario.model.User;

public class MarioGameRules implements GameRules {

	@Override
	public boolean apply(User player) {
		if(player.getMap().getCurrentRoom().getOccupiedBy()==GameCharacter.PRINCESS){
			System.out.println("You won!!");
			return true;
		}else if(player.getMap().getCurrentRoom().getOccupiedBy()==GameCharacter.MONSTER){
			System.out.println("There is monster in this room");
			printHelp();
		}
		return false;
	}
	
	public void printHelp(){
		System.out.println("You have to answer monster riddle.\n"
				+ "If you answer correctly you will get gems.\n"
				+ "It will give you hints if you pay gems by typing '#pay' \n"
				+ "You can type '#giveup' to fight the monster\n");
	}

}
