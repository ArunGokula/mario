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
		}
		return false;
	}

}
