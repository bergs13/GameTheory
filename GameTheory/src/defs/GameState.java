package defs;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Thunderchild
 */
public class GameState {

    private List<Object> moves;
    private List<Object> states;

    public GameState(List moves, List states) {
        this.moves = moves;
        this.states = states;
    }

    public void setStartState() {

    }

    public void getAllMoves() {

    }

    public void possibleMove() {

    }

    public boolean hasNextMove() {
        return true;
    }

    public void getNextMove() {

    }

    public void doMove() {
        if (moves == null) {
            moves = new ArrayList();
        }
    }

    public void undoMove() {

    }

    public void getAllChildStates() {

    }

    public void hasNextChild() {

    }

    public void getNextChild() {

    }

    public void getChild() {

    }

    public void firstPlayerToMove() {

    }

    public void secondPlayerToMove() {

    }

    public boolean isTerminal() {
        return true;
    }

    public void firstPlayerToWin() {

    }

    public void secondPlayerToWin() {

    }

    public void draw() {

    }

    public List getMoveHistory() {
        if (moves == null) {
            moves = new ArrayList();
        }
        return moves;
    }

    public List getStateHistory() {
        if (states == null) {
            states = new ArrayList();
        }
        return states;
    }
}
