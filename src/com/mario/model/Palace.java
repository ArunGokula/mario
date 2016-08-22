package com.mario.model;

import com.mario.utils.Constants;

public class Palace extends GeoMap{

	Room[][] rooms = new Room[Constants.rows][Constants.columns];

	@Override
	void print() {
		for(int c=0;c<Constants.columns;c++){
			System.out.print(" _ _ ");
		}
		System.out.println();
		for (int i = 0; i < rooms.length; i++) {
			for (int j = 0; j < rooms[i].length; j++) {
				System.out.print("|_ _|");
			}
			System.out.println();
		}
	}
}
