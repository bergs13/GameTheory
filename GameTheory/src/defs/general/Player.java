package defs.general;

public class Player {
	// Members
	boolean isHuman = true;

	// Constructors
	public Player() {
	}

	public Player(boolean isHuman) {
		this.isHuman = isHuman;
	}

	// Methods
	public boolean getIsHuman() {
		return this.isHuman;
	}

	public void setIsHuman(boolean isHuman) {
		this.isHuman = isHuman;
	}
}
