package defs.general;

import java.util.List;

public class GenericCell<T> {
	// Members
	private GenericRow<T> row = null;
	private GenericColumn<T> column = null;
	private T cellValue = null;

	// Constructors
	public GenericCell(GenericRow<T> row, GenericColumn<T> column, T cellValue) {
		this.row = row;
		this.column = column;
		this.cellValue = cellValue;
	}

	// Methods
	public GenericRow<T> getRow() {
		return this.row;
	}

	public int getRowIndex() {
		List<GenericRow<T>> rows = this.row.getTable().getRows();
		if (rows.contains(this.row)) {
			return rows.indexOf(this.row);
		}
		return Integer.MIN_VALUE;
	}

	public GenericColumn<T> getColumn() {
		return this.column;
	}

	public int getColumnIndex() {
		List<GenericColumn<T>> columns = this.row.getColumns();
		if (columns.contains(this.column)) {
			return columns.indexOf(this.column);
		}
		return Integer.MIN_VALUE;
	}

	public T getCellValue() {
		return this.cellValue;
	}

	public void setCellValue(T newCellValue) {
		this.cellValue = newCellValue;
	}
}
