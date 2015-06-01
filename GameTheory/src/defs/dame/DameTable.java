package defs.dame;

import java.util.ArrayList;
import java.util.List;

import defs.dame.DameConstants.Piece;
import defs.general.GenericCell;
import defs.general.GenericColumn;
import defs.general.GenericRow;
import defs.general.GenericTable;
import defs.general.Move;

public class DameTable extends GenericTable<Piece> {

	public DameTable() {
		super();
	}

	@Override
	public boolean moveAllowed(GenericCell<Piece> sourceCell,
			GenericCell<Piece> targetCell) {

		// continue only if base move allowed
		if (super.moveAllowed(sourceCell, targetCell)) {

			// Set the horizontal and vertical difference for checks
			int rowIndexDifference = Math.abs(sourceCell.getRowIndex()
					- targetCell.getRowIndex());
			int columnIndexDifference = Math.abs(sourceCell.getColumnIndex()
					- targetCell.getColumnIndex());

			// check dame move allowed
			// Base check: source piece not empty and target piece empty
			if (sourceCell.getCellValue() != Piece.EMPTY
					&& targetCell.getCellValue() == Piece.EMPTY) {

				// Case 1: Move one space
				if (rowIndexDifference <= 1
						&& columnIndexDifference <= 1) {
					return true;
				}
                                    
				// Case 2: capture piece
				else if (Math.abs(columnIndexDifference%2) == 0
						&& Math.abs(rowIndexDifference%2) == 0 ) {
					return true;
				}
			}
			return false;
		}

		return false;
	}

	@Override
	public void moveValue(int sourceRowIndex, int sourceColumnIndex,
			int targetRowIndex, int targetColumnIndex, Piece emptyValue) {
		// Perform move from base
		super.moveValue(sourceRowIndex, sourceColumnIndex, targetRowIndex,
				targetColumnIndex, emptyValue);

		// Perform remove (stones in between)
		int rowIndexForRemove = Integer.MIN_VALUE;
		int columnIndexForRemove = Integer.MIN_VALUE;
		// Set the horizontal and vertical difference for checks
		int rowDifference = Math.abs(sourceRowIndex - targetRowIndex);
		int columnDifference = Math.abs(sourceColumnIndex - targetColumnIndex);
		// case 1: stone between in same row
		if (sourceRowIndex == targetRowIndex && columnDifference == 2) {
			// one of both rows
			rowIndexForRemove = sourceRowIndex;
			// index between columns
			columnIndexForRemove = sourceColumnIndex < targetColumnIndex ? sourceColumnIndex + 1
					: targetColumnIndex + 1;
		}
		// case 2: stone between in same column
		else if (sourceColumnIndex == targetColumnIndex && rowDifference == 2) {
			// one of both columns
			columnIndexForRemove = sourceColumnIndex;
			// index between rows
			rowIndexForRemove = sourceRowIndex < targetRowIndex ? sourceRowIndex + 1
					: targetRowIndex + 1;
		}
		// Remove piece if piece identified
		if (rowIndexForRemove >= 0 && columnIndexForRemove >= 0) {
			/* Piece removedPiece = */removeValue(rowIndexForRemove,
					columnIndexForRemove, emptyValue);
		}
	}

	public List<Move> getAllPossibleMoves() {
		List<Move> allPossibleMoves = new ArrayList<Move>();
		List<GenericRow<Piece>> rows = getRows();
		for (GenericRow<Piece> row : rows) {
			List<GenericColumn<Piece>> columns = row.getColumns();
			for (GenericColumn<Piece> column : columns) {
				GenericCell<Piece> sourceCell = row.getCellByColumn(column);
				for (int i = -1; i <= 1; i++) {
					for (int j = -1; j <= 1; j++) {
						GenericCell<Piece> targetCell = findCell(
								rows.indexOf(row) + i, columns.indexOf(column)
										+ j);
						if (moveAllowed(sourceCell, targetCell)) {
							int[] movement = { rows.indexOf(row),
									rows.indexOf(column), i, j };
							allPossibleMoves.add(new DameMove(movement));
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
						// getChildMoves().add(move);
					}
				}
			}
		}
		return allPossibleMoves;
	}
}
