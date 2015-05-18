package defs.general;


/*
 * Basisklasse f�r den Spieler
 */
public class Player {

    public Player() {
    }

    public Move getMove(){
        Object action = new Object();
        Move move = new Move(action);
        return move;        
    }
}
