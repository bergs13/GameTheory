package defs.general;

import java.util.ArrayList;
import java.util.List;

public class GenericRow<T> {
	// Members
	private GenericTable<T> table = null;
	private List<GenericColumn<T>> columns = null;
	private List<GenericCell<T>> cells = null;

	// Constructors
	public GenericRow() {
		this.columns = new ArrayList<GenericColumn<T>>();
		this.cells = new ArrayList<GenericCell<T>>();
	}

	// Methods
	public void addColumn(GenericColumn<T> column, T cellValue) {
		column.setRow(this);
		this.columns.add(column);
		this.cells.add(new GenericCell<T>(this, column, cellValue));
	}

	public void removeColumn(GenericColumn<T> column) {
		if (null == column) {
			return;
		}

		// Remove column if in row
		if (this.columns.contains(column)) {
			this.columns.remove(column);
		}

		// Find cell for row and column and remove it
		GenericCell<T> foundCell = null;
		for (GenericCell<T> cell : this.cells) {
			if (cell.getRow() == this && cell.getColumn() == column) {
				foundCell = cell;
				break;
			}
		}
		if (null != foundCell) {
			this.cells.remove(foundCell);
		}
	}

	public void clearColumns() {
		this.columns.clear();
		// clear cells too
		this.cells.clear();
	}

	public GenericTable<T> getTable() {
		return this.table;
	}

	public void setTable(GenericTable<T> table) {
		this.table = table;
	}

	public List<GenericColumn<T>> getColumns() {
		return this.columns;
	}

	public List<GenericCell<T>> getCells() {
		return this.cells;
	}

	public GenericCell<T> getCellByColumn(GenericColumn<T> column) {
		if (null == column) {
			return null;
		}

		GenericCell<T> foundCell = null;
		for (GenericCell<T> cell : this.cells) {
			if (cell.getColumn() == column) {
				foundCell = cell;
				break;
			}
		}
		return foundCell;
	}
}
