package defs.general;

import java.util.ArrayList;
import java.util.List;

public class Row<T> {
	// Members
	private Table<T> table = null;
	private List<Column<T>> columns = null;
	private List<Cell<T>> cells = null;

	// Constructors
	public Row(Table<T> table) {
		this.table = table;
		this.columns = new ArrayList<Column<T>>();
		this.cells = new ArrayList<Cell<T>>();
	}

	// Methods
	public void addColumn(Column<T> column, T cellValue) {
		this.columns.add(column);
		this.cells.add(new Cell<T>(this, column, cellValue));
	}

	public void removeColumn(Column<T> column) {
		if (null == column) {
			return;
		}

		// Remove column if in row
		if (this.columns.contains(column)) {
			this.columns.remove(column);
		}

		// Find cell for row and column and remove it
		Cell<T> foundCell = null;
		for (Cell<T> cell : this.cells) {
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

	public Table<T> getTable() {
		return this.table;
	}

	public List<Column<T>> getColumns() {
		return this.columns;
	}

	public List<Cell<T>> getCells() {
		return this.cells;
	}
	
	public Cell<T> getCellByColumn(Column<T> column)
	{
		if(null == column)
		{
			return null;
		}
		
		Cell<T> foundCell = null;
		for(Cell<T> cell : this.cells)
		{
			if(cell.getColumn() == column)
			{
				foundCell = cell;
				break;
			}
		}
		return foundCell;
	}
}
