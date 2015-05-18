/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package defs.dame;

import defs.general.Cell;
import defs.general.Column;
import defs.general.GameState;
import defs.general.Move;
import defs.general.Player;
import defs.general.Row;
import defs.general.Table;
import interfaces.UsableAsDameViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Thunderchild
 */
public class DameGameState extends GameState implements
		UsableAsDameViewModel<Character> {

	private Table<Character> gameTable = null;

	public DameGameState() {
	}

	public DameGameState(GameState parentState, Move move) {
		// super(parentState, move);
		// this.matches = ((DameGameState)parentState).matches;
		// ((DameMove)move).executeMove(this.matches);
	}

	// Methods
	// Base overrides
	@Override
	public void findPossibleMoves() {
		// for (int i = 1; i <= Math.min(3, matches); i++) {
		// super.getChildMoves().add(new DameMove(i));
		// }
	}

	@Override
	public void createChildStates() {
		for (Move move : this.getAllMoves()) {
			super.getChildStates().add(new GameState(this, move));
		}
	}

	@Override
	public void draw() {
		super.draw(); // To change body of generated methods, choose Tools |
						// Templates.
	}

	@Override
	public void secondPlayerToWin() {
		super.secondPlayerToWin(); // To change body of generated methods,
									// choose Tools | Templates.
	}

	@Override
	public void firstPlayerToWin() {
		super.firstPlayerToWin(); // To change body of generated methods, choose
									// Tools | Templates.
	}

	@Override
	public boolean isTerminal() {
		return super.isTerminal(); // To change body of generated methods,
									// choose Tools | Templates.
	}

	@Override
	public void secondPlayerToMove() {
		super.secondPlayerToMove(); // To change body of generated methods,
									// choose Tools | Templates.
	}

	@Override
	public void firstPlayerToMove() {
		super.firstPlayerToMove(); // To change body of generated methods,
									// choose Tools | Templates.
	}

	@Override
	public void getChild() {
		super.getChild(); // To change body of generated methods, choose Tools |
							// Templates.
	}

	@Override
	public ArrayList<GameState> getChildStates() {
		return super.getChildStates(); // To change body of generated methods,
										// choose Tools | Templates.
	}

	@Override
	public GameState undoMove() {
		return super.undoMove();
	}

	@Override
	public GameState doMove(Move move) {
		return super.doMove(move); // To change body of generated methods,
									// choose Tools | Templates.
	}

	@Override
	public boolean possibleMove(Move move) {
		return super.possibleMove(move); // To change body of generated methods,
											// choose Tools | Templates.
	}

	@Override
	public ArrayList<Move> getAllMoves() {
		return super.getAllMoves();
	}

	public void setStartState(Player firstPlayer, Table<Character> gameTable) {
		super.setStartState(firstPlayer);
		this.gameTable = gameTable;

		// Notify view for update
		setChanged();
		notifyObservers(DameGameStateEventConstants.STARTSTATESET);
	}

	// UsableAsDameViewModel<Character> (interface) methods
	@Override
	public Table<Character> getGameTable() {
		return this.gameTable;
	}

	@Override
	public void moveStone(int sourceRowIndex, int sourceColumnIndex,
			int targetRowIndex, int targetColumnIndex) {

		// Testausgabe
		System.out.println("" + sourceRowIndex + ";" + sourceColumnIndex + ";"
				+ targetRowIndex + ";" + targetColumnIndex);
		// Testausgabe

		// Move the value of the source cell to the target cell
		Cell<Character> sourceCell = null;
		Cell<Character> targetCell = null;
		List<Row<Character>> rows = this.gameTable.getRows();
		for (Row<Character> row : rows) {
			if (rows.indexOf(row) == sourceRowIndex) {
				List<Column<Character>> sourceRowColumns = row.getColumns();
				for (Column<Character> sourceRowColumn : sourceRowColumns) {
					if (sourceRowColumns.indexOf(sourceRowColumn) == sourceColumnIndex) {
						// source row and column combination identified
						// find cell for column in source row
						sourceCell = row.getCellByColumn(sourceRowColumn);
					}
				}
			} else if (rows.indexOf(row) == targetRowIndex) {
				List<Column<Character>> targetRowColumns = row.getColumns();
				for (Column<Character> targetRowColumn : targetRowColumns) {
					if (targetRowColumns.indexOf(targetRowColumn) == targetColumnIndex) {
						// target row and column combination identified
						// find cell
						targetCell = row.getCellByColumn(targetRowColumn);
					}
				}
			}
		}

		// Only if both cells found
		if (null != sourceCell && null != targetCell) {
			targetCell.setCellValue(sourceCell.getCellValue());
			sourceCell.setCellValue(' ');

			// Notify view for update
			setChanged();
			notifyObservers(DameGameStateEventConstants.STONEMOVED);
		}
	}
}
