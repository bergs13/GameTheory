package defs.general;

import java.util.Observable;

/* 
 * Basisklasse für alle Spiele 
 */
public class Game extends Observable {
	// Members
	private String name = "";
	protected Player firstPlayer = new Player();
	protected Player secondPlayer = new Player();

	// Konstruktoren
	public Game(String name) {
		this.name = name;
	}

	// Methoden
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return this.name;
	}

	public boolean setupGame() {
		GameState gameState = new GameState();
		gameState.setStartState(firstPlayer);
		return true;
	}
	public void play() {

	}
}
