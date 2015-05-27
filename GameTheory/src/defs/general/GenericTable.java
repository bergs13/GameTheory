package defs.general;

import java.util.ArrayList;
import java.util.List;

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
}
