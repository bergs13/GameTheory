package defs;


/*
 * Basisklasse f�r den Spieler
 */
public class Player {

    private GameState currentState;

    public Player() {
    }

    public void doMove(Move move) {
        this.currentState.doMove(move);
    }

    public void setGamestate(GameState state) {
        this.currentState = state;
    }
}
