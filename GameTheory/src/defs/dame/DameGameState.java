
package defs.dame;

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
        }

	// Methods
	// Base overrides
	@Override
	public void findPossibleMoves() {
		setChildMoves(this.dameTable.getAllPossibleMoves(((DamePlayer)super.getPlayerToMove()).getPlayersPiece()));
	}

	@Override
	public void createChildStates() {
		for (Move move : getAllMoves()) {
			getChildStates().add(new GameState(this, move));
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

	public void setStartState(Player firstPlayer, DameTable dameTable) {
		super.setStartState(firstPlayer);
		this.dameTable = dameTable;
	}

	public DameTable getDameTable() {
		return this.dameTable;
	}
}
