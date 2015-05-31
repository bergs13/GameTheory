package defs.general;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 *
 * @author Thunderchild
 */
public class GameState extends Observable {

	private Move playedMove;
	private List<Move> childMoves;
	private GameState parentState;
	private List<GameState> childStates;
	private Player playerToMove;

	public GameState() {

	}

	public GameState(GameState parentState, Move move) {
		this.parentState = parentState;
		this.playedMove = move;
	}

	// Set Startstate when start node
	public void setStartState(Player firstPlayer) {
		this.parentState = null;
		this.playerToMove = firstPlayer;
	}

	// all valid moves
	public List<Move> getAllMoves() {
		if (childMoves == null) {
			findPossibleMoves();
		}
		return childMoves;
	}

	// Is move valid
	public boolean possibleMove(Move move) {
		return getAllMoves().contains(move);
	}

	// execute a move
	public GameState doMove(Move move) {
		GameState gstate = new GameState(this, move);
		return gstate;
	}

	// undo move that lead to this state
	public GameState undoMove() {
		return parentState;
	}

	//
	public List<GameState> getChildStates() {
		if (childStates == null) {
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
		childStates = new ArrayList<GameState>();
	}

	public void findPossibleMoves() {
		setChildMoves(new ArrayList<Move>());
	}

	public List<Move> getChildMoves() {
		return childMoves;
	}

	public void setChildMoves(List<Move> childMoves) {
		this.childMoves = childMoves;
	}

}
