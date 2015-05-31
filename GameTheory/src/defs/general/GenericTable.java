package defs.general;

import java.util.ArrayList;
import java.util.List;

import defs.dame.DameConstants.Piece;

public class GenericTable<T> {

	// Members

	private List<GenericRow<T>> rows = null;

	// Constructors
	public GenericTable() {
		this.rows = new ArrayList<GenericRow<T>>();
	}

	// Methods
	public void addRow(GenericRow<T> row) {
		row.setTable(this);
		this.rows.add(row);
	}

	public void removeRow(GenericRow<T> row) {
		if (null == row) {
			return;
		}

		if (this.rows.contains(row)) {
			this.rows.remove(row);
		}
	}

	public void clearRows() {
		this.rows.clear();
	}

	public List<GenericRow<T>> getRows() {
		return this.rows;
	}

	public GenericCell<T> getCellByRowAndColumn(int rowIndex, int columnIndex) {
		for (GenericRow<T> row : rows) {
			if (rows.indexOf(row) == rowIndex) {
				List<GenericColumn<T>> columns = row.getColumns();
				for (GenericColumn<T> column : columns) {
					if (columns.indexOf(column) == columnIndex) {
						return row.getCellByColumn(column);
					}
				}

			}
		}
		return null;
	}

	public GenericCell<T> findCell(int rowIndex, int columnIndex) {
		// find row of table
		List<GenericRow<T>> rows = this.getRows();
		for (GenericRow<T> row : rows) {
			if (rows.indexOf(row) == rowIndex) {
				// find column of row
				List<GenericColumn<T>> rowColumns = row.getColumns();
				for (GenericColumn<T> rowColumn : rowColumns) {
					if (rowColumns.indexOf(rowColumn) == columnIndex) {
						// return cell of column
						return row.getCellByColumn(rowColumn);
					}
				}
			}
		}
		return null;
	}

	public boolean moveAllowed(int rowIndexDifference,
			int columnIndexDifference, GenericCell<T> sourceCell,
			GenericCell<T> targetCell) {

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

	public void moveValue(int sourceRowIndex, int sourceColumnIndex,
			int targetRowIndex, int targetColumnIndex, T emptyValue) {
		// Find the two cells
		GenericCell<T> sourceCell = findCell(sourceRowIndex, sourceColumnIndex);
		GenericCell<T> targetCell = findCell(targetRowIndex, targetColumnIndex);

		// Set the horizontal and vertical difference for checks
		int rowDifference = Math.abs(sourceRowIndex - targetRowIndex);
		int columnDifference = Math.abs(sourceColumnIndex - targetColumnIndex);

		// Move only if move is allowed
		if (moveAllowed(rowDifference, columnDifference, sourceCell, targetCell)) {

			// Move the cell value to the new cell
			targetCell.setCellValue(sourceCell.getCellValue());

			// Clear the cell value of the old cell
			sourceCell.setCellValue(emptyValue);

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
			// Remove piece if piece identified
			if (rowIndexForRemove >= 0 && columnIndexForRemove >= 0) {
				/* Piece removedPiece = */removeValue(rowIndexForRemove,
						columnIndexForRemove, emptyValue);
			}
		}
	}

	public T removeValue(int rowIndex, int columnIndex, T emptyValue) {
		// Find the cell for the value remove
		GenericCell<T> foundCell = findCell(rowIndex, columnIndex);
		if (null != foundCell) {
			// Capture the removed value
			T removedValue = foundCell.getCellValue();

			// Clear the cell value
			foundCell.setCellValue(emptyValue);

			// Return the removed value
			return removedValue;
		}
		return null;
	}
}
