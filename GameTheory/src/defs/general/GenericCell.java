package defs.general;

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

	public GenericColumn<T> getColumn() {
		return this.column;
	}

	public T getCellValue() {
		return this.cellValue;
	}

	public void setCellValue(T newCellValue) {
		this.cellValue = newCellValue;
	}
}
