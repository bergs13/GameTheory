package defs;


/* 
 * Basisklasse für alle Spiele 
 */
public class Game  {
	// Members
	private String name = "";

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
}
