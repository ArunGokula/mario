package com.mario.model;

import com.mario.service.UIservice;
import com.mario.service.impl.ConsoleBasedUI;
import com.mario.utils.Constants;

public class User {

	String name;
	Palace map;
	Integer level;
	Integer gems;
	Integer health;
	UIservice ui;

	@Override
	public String toString() {
		return this.getName() + "'s Health : " + this.getHealth() + " Gems: " + this.getGems();
	}
	
	public User(String name, int level,int gems, int health, Palace palace) {
		this.name = name;
		this.level=level;
		this.gems = gems;
		this.health = health;
		this.map = palace;
	}



	public User(String name) {
		this.name = name;
		this.level = 1;
		this.gems = 0;
		this.health=100;
		this.map = new Palace();
	}

	public boolean explore() {
		map.print();
		ui.printInstructions();
		String cmd = ui.readUserInputString();
		if (cmd.equalsIgnoreCase("QUIT") || cmd.equalsIgnoreCase("Q")) {
			return true;
		} else {
			switch (cmd.toUpperCase()) {
				case "NORTH":
				case "N":
					moveNorth();
					break;
				case "SOUTH":
				case "S":
					moveSouth();
					break;
				case "WEST":
				case "W":
					moveWest();
					break;
				case "EAST":
				case "E":
					moveEast();
					break;
				default:
					ui.displayExploreError();
			}
		}

		System.out.flush();
		return false;
	}

	private void moveNorth() {
		if (map.getCurrentRoomX() == 0) {
			ui.hitWallMessage();
		} else {
			map.IncOrDecCurrentRoomX(-1);
		}

	}

	private void moveSouth() {
		if (map.getCurrentRoomX() == Constants.rows - 1) {
			ui.hitWallMessage();
		} else {
			map.IncOrDecCurrentRoomX(1);
		}

	}

	private void moveWest() {
		if (map.getCurrentRoomY() == 0) {
			ui.hitWallMessage();
		} else {
			map.IncOrDecCurrentRoomY(-1);
		}

	}

	private void moveEast() {
		if (map.getCurrentRoomY() == Constants.columns - 1) {
			ui.hitWallMessage();
		} else {
			map.IncOrDecCurrentRoomY(1);
		}
	}

	
	
	public void modifyHealth(int delta) {
		health += delta;
		if(health<0)
			health=0;
	}

	public void modifyGems(int number) {
		gems += number;
	}

	public String getName() {
		return name;
	}

	public Palace getMap() {
		return map;
	}

	public void setMap(Palace map) {
		this.map = map;
	}

	public Integer getLevel() {
		return level;
	}

	public void IncLevel() {
		this.level++;
	}

	public Integer getGems() {
		return gems;
	}

	public Integer getHealth() {
		return health;
	}

	public void setUI(UIservice ui) {
		this.ui=ui;
	}

}
