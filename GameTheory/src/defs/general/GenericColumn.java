package defs.general;

public class GenericColumn<T> {
	// Members
	private GenericRow<T> row = null;

	// Constructors
	public GenericColumn() {
	}

	// Methods
	public GenericRow<T> getRow() {
		return this.row;
	}

	public void setRow(GenericRow<T> row) {
		this.row = row;
	}
}
