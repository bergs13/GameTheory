package defs.general;

import java.util.Observable;

/* 
 * Basisklasse für alle Spiele 
 */
public class Game extends Observable {
	// Members
	private String name = "";
	private Player firstPlayer = new Player(true);
	private Player secondPlayer = new Player(false);

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
		gameState.setStartState(getFirstPlayer());
		return true;
	}

	public void play() {

	}

	protected Player getFirstPlayer() {
		return firstPlayer;
	}

	protected Player getSecondPlayer() {
		return secondPlayer;
	}
}
