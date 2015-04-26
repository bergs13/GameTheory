package defs;

import java.util.ArrayList;
import java.util.ListIterator;


/**
 *
 * @author Thunderchild
 */
public class GameState {

    private ArrayList<Move> moveHistory;
    private ArrayList<Move> childMoves;
    private ArrayList<GameState> stateHistory;
    private ArrayList<GameState> childStates;
    private ListIterator<Move> moveIterator = childMoves.listIterator();

    public GameState(){
        
    }
    public GameState(ArrayList moves, ArrayList states, Move move) {
        this.moveHistory = moves;
        this.stateHistory = states;
    }
    //Set Startstate when start node
    public void setStartState() {
        this.moveHistory = new ArrayList();
        this.stateHistory = new ArrayList();
    }
    //all valid moves
    public ArrayList getAllMoves() {
       return childMoves;
    }
    //Is move valid
    public boolean possibleMove(Move move) {
        return true;
    }
        
    //execute a move
    public void doMove(Move move) {
        if (possibleMove(move)){
            
        }
        else{
            //do move
        }
    }
    //undo move that lead to this state
    public void undoMove() {

    }
    //
    public ArrayList getChildStates() {
        if(childStates == null){
            createChildStates();
        }
        return childStates;
    }

    public void getChild() {

    }

    public void firstPlayerToMove() {

    }

    public void secondPlayerToMove() {

    }

    public boolean isTerminal() {
        return !getChildStates().isEmpty();
    }

    public void firstPlayerToWin() {

    }

    public void secondPlayerToWin() {

    }

    public void draw() {

    }

    public ArrayList getMoveHistory() {
        
        return moveHistory;
    }

    public ArrayList getStateHistory() {
        
        return stateHistory;
    }

    private void createChildStates() {
        childStates = new ArrayList();
    }
}
