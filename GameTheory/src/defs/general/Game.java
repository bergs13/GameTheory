package defs.general;

/* 
 * Basisklasse für alle Spiele 
 */
public class Game {
	// Members
	private String name = "";
	protected Player firstPlayer = new HumanPlayer();
	protected Player secondPlayer = new COMPlayer();

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
