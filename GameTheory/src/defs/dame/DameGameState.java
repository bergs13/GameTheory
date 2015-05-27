/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package defs.dame;

import defs.dame.DameConstants.Piece;
import defs.dame.DameConstants.*;
import defs.general.GenericCell;
import defs.general.GenericColumn;
import defs.general.GameState;
import defs.general.Move;
import defs.general.Player;
import defs.general.GenericRow;
import defs.general.GenericTable;
import interfaces.UsableAsDameViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Thunderchild
 */
public class DameGameState extends GameState implements
		UsableAsDameViewModel<Piece> {

	private GenericTable<Piece> gameTable = null;

	public DameGameState() {
	}

	public DameGameState(GameState parentState, Move move) {
		super(parentState, move);

	}

	// Methods
	// Base overrides
	@Override
	public void findPossibleMoves() {
		boolean canCapturePiece = false;
		List<GenericRow<Piece>> rows = gameTable.getRows();
		for (GenericRow<Piece> row : rows) {
			List<GenericColumn<Piece>> columns = row.getColumns();
			for (GenericColumn<Piece> column : columns) {
				GenericCell<Piece> currentCell = row.getCellByColumn(column);
				// if(currentCell.getCellValue() == ownpiec)
				for (int i = -1; i <= 1; i++) {
					for (int j = -1; j <= 1; j++) {
						GenericCell<Piece> cell = gameTable
								.getCellByRowAndColumn(rows.indexOf(row) + i,
										columns.indexOf(column) + j);
						if (cell != null && cell.getCellValue() == Piece.EMPTY) {
							int[] movement = { rows.indexOf(row),
									rows.indexOf(column), i, j };
							super.getChildMoves().add(new Move(movement));
						}
						// else if(cell != null && cell.getCellValue() ==
						// opponentscolor &&
						// getCellByRowAndColumn(rows.indexOf(row) + i+i,
						// columns.indexOf(column) + j +j).getCellValue ==
						// DameGameStateEventConstants.Piece.EMPTY){
						// if(getCellByRowAndColumn(rows.indexOf(row) + i+i,
						// columns.indexOf(column) + j +j).getCellValue ==
						// DameGameStateEventConstants.Piece.EMPTY){
						// Move move(rows.indexOf(row), rows.indexOf(column),
						// i+i, j+j);
						// move.capturePiece(cell);
						// canCapturePiece = true;
						// super.getChildMoves().add(move);
					}
				}
			}
		}
		if (canCapturePiece) {
			for (Move move : super.getAllMoves()) {
				if (!((DameMove) move).capturePiece()) {
					super.getAllMoves().remove(move);
				}
			}
		}

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
		return (this.getAllMoves().isEmpty()); // To change body of generated
												// methods,
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
		if (super.getAllMoves() == null) {
			this.findPossibleMoves();
		}
		return super.getAllMoves();
	}

	public void setStartState(Player firstPlayer, GenericTable<Piece> gameTable) {
		super.setStartState(firstPlayer);
		this.gameTable = gameTable;

		// Notify view for update
		setChanged();
		notifyObservers(DameEventConstants.STARTSTATESET);
	}

	// UsableAsDameViewModel<Character> (interface) methods
	@Override
	public GenericTable<Piece> getGameTable() {
		return this.gameTable;
	}

	@Override
	public void moveStone(int sourceRowIndex, int sourceColumnIndex,
			int targetRowIndex, int targetColumnIndex) {
		// Find the two cells
		GenericCell<Piece> sourceCell = findCell(sourceRowIndex,
				sourceColumnIndex);
		GenericCell<Piece> targetCell = findCell(targetRowIndex,
				targetColumnIndex);

		// Set the horizontal and vertical difference for checks
		int rowDifference = Math.abs(sourceRowIndex - targetRowIndex);
		int columnDifference = Math.abs(sourceColumnIndex - targetColumnIndex);

		// Move only if move is allowed
		if (moveAllowed(rowDifference, columnDifference, sourceCell, targetCell)) {

			// Move the cell value to the new cell
			targetCell.setCellValue(sourceCell.getCellValue());

			// Clear the cell value of the old cell
			sourceCell.setCellValue(Piece.EMPTY);

			// Remove stone between if there is one
			int rowIndexForRemove = Integer.MIN_VALUE;
			int columnIndexForRemove = Integer.MIN_VALUE;
			// case 1: stone between in same row
			if (sourceRowIndex == targetRowIndex && columnDifference == 2) {
				// one of both rows
				rowIndexForRemove = sourceRowIndex;
				// index between columns
				columnIndexForRemove = sourceColumnIndex < targetColumnIndex ? sourceColumnIndex + 1
						: targetColumnIndex + 1;
			}
			// case 2: stone between in same column
			else if (sourceColumnIndex == targetColumnIndex
					&& Math.abs(sourceRowIndex - targetRowIndex) == 2) {
				// one of both columns
				columnIndexForRemove = sourceColumnIndex;
				// index between rows
				rowIndexForRemove = sourceRowIndex < targetRowIndex ? sourceRowIndex + 1
						: targetRowIndex + 1;
			}
			// Remove stone if stone identified
			if (rowIndexForRemove >= 0 && columnIndexForRemove >= 0) {
				/* Piece removedPiece = */removeStone(rowIndexForRemove,
						columnIndexForRemove);
			}

			// Notify view for update
			setChanged();
			notifyObservers(DameEventConstants.STONEMOVED);
		}
	}

	private boolean moveAllowed(int rowIndexDifference,
			int columnIndexDifference, GenericCell<Piece> sourceCell,
			GenericCell<Piece> targetCell) {

		// Base check: cells set, source piece not empty and target piece empty
		if (null != sourceCell && null != targetCell
				&& sourceCell.getCellValue() != Piece.EMPTY
				&& targetCell.getCellValue() == Piece.EMPTY) {

			// Case 1: Same row with valid column difference
			if (rowIndexDifference == 0
					&& (columnIndexDifference == 1 || columnIndexDifference == 2)) {
				return true;
			}

			// Case 2: Same column with valid row difference
			else if (columnIndexDifference == 0
					&& (rowIndexDifference == 1 || rowIndexDifference == 2)) {
				return true;
			}
		}

		return false;
	}

	private Piece removeStone(int rowIndex, int columnIndex) {
		// Find the cell for remove
		GenericCell<Piece> foundCell = findCell(rowIndex, columnIndex);
		if (null != foundCell) {
			// Capture the removed piece
			Piece removedPiece = foundCell.getCellValue();

			// Clear the cell value of the removed piece
			foundCell.setCellValue(Piece.EMPTY);

			// Return the removed piece
			return removedPiece;
		}
		return null;
	}

	private GenericCell<Piece> findCell(int rowIndex, int columnIndex) {
		// find row of table
		List<GenericRow<Piece>> rows = this.gameTable.getRows();
		for (GenericRow<Piece> row : rows) {
			if (rows.indexOf(row) == rowIndex) {
				// find column of row
				List<GenericColumn<Piece>> rowColumns = row.getColumns();
				for (GenericColumn<Piece> rowColumn : rowColumns) {
					if (rowColumns.indexOf(rowColumn) == columnIndex) {
						// return cell of column
						return row.getCellByColumn(rowColumn);
					}
				}
			}
		}
		return null;
	}
}
