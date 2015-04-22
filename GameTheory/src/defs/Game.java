package defs;

import interfaces.*;

/* 
 * Basisklasse für alle Spiele (Implementiert 'spielbar')
 */
public class Game implements PlayableGame {
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
