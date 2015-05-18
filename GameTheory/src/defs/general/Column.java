package defs.general;

public class Column<T> {
	// Members
	private Row<T> row = null;

	// Constructors
	public Column(Row<T> row) {
		this.row = row;
	}

	// Methods
	public Row<T> getRow() {
		return this.row;
	}
}
