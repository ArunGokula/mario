package com.mario.model;

public class User {

	String name;
	GeoMap map;
	Integer level;
	Integer gems;
	Integer health;
	public User(String name, int gems, int health, Palace palace) {
		this.name=name;
		this.gems=gems;
		this.health=health;
		this.map=palace;
	}
	public void modifyHealth(int delta){
		health+=delta;
	}
	public void modifyGems(int number){
		gems+=number;
	}
	
	public void explore(){
		map.print();
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public GeoMap getMap() {
		return map;
	}
	public void setMap(GeoMap map) {
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
	void save(){
		
	}
}
