package defs.general;

public class Cell<T> {
	// Members
	private Row<T> row = null;
	private Column<T> column = null;
	private T cellValue = null;

	// Constructors
	public Cell(Row<T> row, Column<T> column, T cellValue) {
		this.row = row;
		this.column = column;
		this.cellValue = cellValue;
	}

	// Methods
	public Row<T> getRow() {
		return this.row;
	}

	public Column<T> getColumn() {
		return this.column;
	}

	public T getCellValue() {
		return this.cellValue;
	}

	public void setCellValue(T newCellValue) {
		this.cellValue = newCellValue;
	}
}
