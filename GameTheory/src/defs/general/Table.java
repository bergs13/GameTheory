package defs.general;

import java.util.ArrayList;
import java.util.List;

public class Table<T> {
	// Members
	private List<Row<T>> rows = null;

	// Constructors
	public Table() {
		this.rows = new ArrayList<Row<T>>();
	}

	// Methods
	public void addRow(Row<T> row) {
		this.rows.add(row);
	}

	public void removeRow(Row<T> row) {
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
	
	public List<Row<T>> getRows()
	{
		return this.rows;
	}
}
