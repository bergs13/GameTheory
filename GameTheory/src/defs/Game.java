package defs;


/* 
 * Basisklasse für alle Spiele 
 */
public class Game  {
	// Members
	private String name = "";
        private Player firstPlayer;
        private Player secondPlayer;

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
        public boolean startGame(){
            GameState gameState = new GameState();
            gameState.setStartState(firstPlayer);
            return true;
        }
}
