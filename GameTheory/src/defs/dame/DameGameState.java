package defs.dame;

import java.util.List;

import defs.general.GameState;
import defs.general.Move;
import defs.general.Player;
import defs.dame.DamePlayer;

/**
 *
 * @author Thunderchild
 */
public class DameGameState extends GameState {

	private DameTable dameTable = null;

	public DameGameState() {
	}

	public DameGameState(GameState parentState, Move move, DameTable dameTable) {
		super(parentState, move);
		this.dameTable = dameTable;
		super.setPlayerToMove(parentState.getPlayerToWait());
		super.setPlayerToWait(parentState.getPlayerToMove());
	}

	// Methods
	// Base overrides
	@Override
	public void findPossibleMoves() {
		setChildMoves(this.dameTable
				.getAllPossibleMoves(((DamePlayer) getPlayerToMove())
						.getPlayersPiece()));
	}

	@Override
	public void createChildStates() {
		super.createChildStates();
		List<GameState> childStates = getChildStates();
		for (Move move : getAllMoves()) {
			childStates.add(new DameGameState(this, move, this.dameTable));
		}
	}

	@Override
	public boolean isTerminal() {
		return (getAllMoves().isEmpty());
	}

	@Override
	public GameState doMove(Move move) {
		DameGameState gstate = new DameGameState(this, move, this.dameTable);
		return gstate;
	}

	public void setStartState(Player firstPlayer, Player secondPlayer,
			DameTable dameTable) {
		super.setStartState(firstPlayer, secondPlayer);
		this.dameTable = dameTable;
	}

	public DameTable getDameTable() {
		return this.dameTable;
	}
}
