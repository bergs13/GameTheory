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

	public boolean moveAllowed(GenericCell<T> sourceCell,
			GenericCell<T> targetCell) {
		return null != sourceCell && null != targetCell;
	}

	public void moveValue(GenericCell<T> sourceCell, GenericCell<T> targetCell,
			T emptyValue) {
		moveValue(sourceCell.getRowIndex(), sourceCell.getColumnIndex(),
				targetCell.getRowIndex(), targetCell.getColumnIndex(),
				emptyValue);
	}

	public boolean moveValue(int sourceRowIndex, int sourceColumnIndex,
			int targetRowIndex, int targetColumnIndex, T emptyValue) {
		// Find the two cells
		GenericCell<T> sourceCell = findCell(sourceRowIndex, sourceColumnIndex);
		GenericCell<T> targetCell = findCell(targetRowIndex, targetColumnIndex);

		// Move only if move is allowed
		if (moveAllowed(sourceCell, targetCell)) {

			// Move the cell value to the new cell
			targetCell.setCellValue(sourceCell.getCellValue());

			// Clear the cell value of the old cell
			sourceCell.setCellValue(emptyValue);

			return true;
		}
		return false;
	}

	public T removeValue(GenericCell<Piece> cell, T emptyValue) {
		return removeValue(cell.getRowIndex(), cell.getColumnIndex(),
				emptyValue);
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
