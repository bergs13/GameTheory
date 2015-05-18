package defs.general;

import java.util.ArrayList;

/**
 *
 * @author Thunderchild
 */
public class GameState {

    private Move playedMove;
    private ArrayList<Move> childMoves;
    private GameState parentState;
    private ArrayList<GameState> childStates;
    private Player playerToMove;
    
    public GameState(){
        
    }
    public GameState(GameState parentState, Move move) {
        this.parentState = parentState;
        this.playedMove = move;
    }
    //Set Startstate when start node
    public void setStartState(Player firstPlayer) {
        this.parentState = null;
        this.playerToMove = firstPlayer;
        
    }
    //all valid moves
    public ArrayList getAllMoves() {
       if (childMoves == null){
           findPossibleMoves();
       }
        return childMoves;
    }
    //Is move valid
    public boolean possibleMove(Move move) {
        return getAllMoves().contains(move);
    }
        
    //execute a move
    public GameState doMove(Move move) 
    {
       GameState gstate = new GameState(this, move);
       return gstate;
    }
    //undo move that lead to this state
    public GameState undoMove() {
        return parentState;
    }
    //
    public ArrayList<GameState> getChildStates() {
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

    public void createChildStates() {
        childStates = new ArrayList();
    }

    public void findPossibleMoves() {
        childMoves = new ArrayList();
    }

    public ArrayList<Move> getChildMoves() {
        return childMoves;
    }

    public void setChildMoves(ArrayList<Move> childMoves) {
        this.childMoves = childMoves;
    }
    
}
