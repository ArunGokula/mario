package com.mario.model;

import java.io.Serializable;

public class Room implements Serializable{
	public GameCharacter getOccupiedBy() {
		return occupiedBy;
	}

	public void setOccupiedBy(GameCharacter you) {
		this.occupiedBy = you;
	}

	GameCharacter occupiedBy;
}
