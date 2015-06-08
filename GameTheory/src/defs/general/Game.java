package defs.general;

import java.util.Observable;

/* 
 * Basisklasse für alle Spiele 
 */
public class Game extends Observable {

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

    public boolean setupGame() {
        setFirstPlayer(new Player(true));
        setSecondPlayer(new Player(false));
        GameState gameState = new GameState();
        gameState.setStartState(getFirstPlayer(), getSecondPlayer());
        return true;
    }

    protected void setFirstPlayer(Player firstPlayer) {
        this.firstPlayer = firstPlayer;
    }

    protected void setSecondPlayer(Player secondPlayer) {
        this.secondPlayer = secondPlayer;
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
