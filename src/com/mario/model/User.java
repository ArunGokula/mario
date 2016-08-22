package com.mario.model;

import java.util.Scanner;

import com.mario.utils.Constants;

public class User {

	String name;
	Palace map;
	Integer level;
	Integer gems;
	Integer health;

	public User(String name, int gems, int health, Palace palace) {
		this.name = name;
		this.gems = gems;
		this.health = health;
		this.map = palace;
	}

	public void modifyHealth(int delta) {
		health += delta;
	}

	public void modifyGems(int number) {
		gems += number;
	}

	public void explore() {
		map.print();
		System.out.println(
				"Let's Go.\nWhich direction do you want to move?\n" + "NORTH(N/n) , EAST(E/e),WEST(W/w),SOUTH(S/s)\n" + "Type Quit/Q/q to exit");
		Scanner scan = new Scanner(System.in);
		while (true) {
			String cmd = scan.nextLine();
			if (cmd.equalsIgnoreCase("QUIT") || cmd.equalsIgnoreCase("Q")) {
				this.save();
				break;
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
						displayError();
				}
			}
			map.print();
			System.out.flush();
		}
		scan.close();
	}

	private void moveNorth() {
		if(map.getCurrentRoomX()==0){
			hitWallMessage();
		}else{
			map.IncOrDecCurrentRoomX(-1);
		}
		
	}

	private void moveSouth() {
		if(map.getCurrentRoomX()==Constants.rows-1){
			hitWallMessage();
		}else{
			map.IncOrDecCurrentRoomX(1);
		}
		
	}

	private void moveWest() {
		if(map.getCurrentRoomY()==0){
			hitWallMessage();
		}else{
			map.IncOrDecCurrentRoomY(-1);
		}
		
	}

	private void moveEast() {
		if(map.getCurrentRoomY()==Constants.columns-1){
			hitWallMessage();
		}else{
			map.IncOrDecCurrentRoomY(1);
		}
	}

	private void hitWallMessage() {
		System.out.println("Ouch!I hit a wall.Choose Different direction");
	}

	private void displayError() {
		System.err.println("Invalid input.Only N/E/W/S/Q are allowed");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getGems() {
		return gems;
	}

	public Integer getHealth() {
		return health;
	}

	void save() {
		
		System.out.println("GoodBye!Your game is saved");
	}
}
