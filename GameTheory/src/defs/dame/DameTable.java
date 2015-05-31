package defs.dame;

import defs.dame.DameConstants.Piece;
import defs.general.GenericCell;
import defs.general.GenericTable;

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
}
