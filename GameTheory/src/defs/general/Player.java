package defs.general;


/*
 * Basisklasse f�r den Spieler
 */
public class Player {

    private GameState currentState;

    public Player() {
    }

    public Move getMove(){
        Object action = new Object();
        Move move = new Move(action);
        return move;        
    }
    public void doMove(Move move) {
        this.currentState.doMove(move);
    }

    public void setGamestate(GameState state) {
        this.currentState = state;
    }
}
