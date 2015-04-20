package defs;

public class Game {
	
	String name = "";
	
	public Game(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return this.name;
	}
}
