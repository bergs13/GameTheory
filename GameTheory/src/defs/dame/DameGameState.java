package defs.dame;

import defs.dame.DameConstants.Piece;
import java.util.List;

import defs.general.GameState;
import defs.general.Move;
import defs.general.Player;
import defs.general.GenericCell;
import defs.general.GenericRow;

/**
 *
 * @author Thunderchild
 */
public class DameGameState extends GameState {

	private DameTable dameTable = null;

	public DameGameState() {
	}

	public DameGameState(GameState parentState, Move move, DameTable dameTable) {
		super(move);
		this.dameTable = dameTable;
		this.dameTable.moveValue(((DameMove) move).getMovement()[0],
				((DameMove) move).getMovement()[1],
				((DameMove) move).getMovement()[2],
				((DameMove) move).getMovement()[3], Piece.EMPTY);
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
			childStates.add(new DameGameState(this,move, new DameTable(
					this.dameTable)));
		}
	}

	@Override
	public boolean isTerminal() {
                return (this.getAllMoves().isEmpty());
	}

	@Override
	public DameGameState doMove(Move move) {
		return new DameGameState(this, move, this.dameTable);

	}

	public void setStartState(Player firstPlayer, Player secondPlayer,
			DameTable dameTable) {
		super.setStartState(firstPlayer, secondPlayer);
		this.dameTable = dameTable;
	}

	public DameTable getDameTable() {
		return this.dameTable;
	}

	public String allPossibleMovesToString() {
		String allMoves = "";
		for (Move move : super.getChildMoves()) {
			String moveString = "from " + ((DameMove) move).getMovement()[0]
					+ " , " + ((DameMove) move).getMovement()[1] + " to "
					+ ((DameMove) move).getMovement()[2] + " , "
					+ ((DameMove) move).getMovement()[3] + " : "
					+ ((DameMove) move).canCapture() + "\n";
			allMoves = allMoves.concat(moveString);
		}
		return allMoves;
	}

	public String tableToString() {
		String tableString = "";
		for (GenericRow<DameConstants.Piece> row : getDameTable().getRows()) {
			String rowString = "";
			for (GenericCell<Piece> cell : row.getCells()) {
				Piece piece = (Piece) cell.getCellValue();
				rowString = rowString.concat(" " + piece);
			}
			tableString = tableString.concat(rowString + "\n");
		}
		return tableString;
	}

}
