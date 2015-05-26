package defs.general;

public class GenericColumn<T> {
	// Members
	private GenericRow<T> row = null;

	// Constructors
	public GenericColumn(GenericRow<T> row) {
		this.row = row;
	}

	// Methods
	public GenericRow<T> getRow() {
		return this.row;
	}
}
