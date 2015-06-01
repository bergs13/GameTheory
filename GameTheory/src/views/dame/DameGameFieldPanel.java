package views.dame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;

import javax.swing.JPanel;

import defs.dame.DameConstants;
import defs.dame.DameConstants.Piece;
import defs.general.GenericCell;
import defs.general.GenericColumn;
import defs.general.GenericRow;
import defs.general.GenericTable;

@SuppressWarnings("serial")
public class DameGameFieldPanel extends JPanel {
	// Members
	GenericTable<Piece> table = null;

	// Constructors
	public DameGameFieldPanel() {
	}

	// Methods
	public void setTable(GenericTable<Piece> table) {
		this.table = table;
	}

	// Painting Methods
	@Override
	public void paintComponent(Graphics g) {
		// Layout und Variablen für ganzes Spielfeld
		Graphics2D g2d = (Graphics2D) g;
		int rowIndex;
		int colIndex;
		int x;
		int y;
		// Spielfeld zeichnen (Zellen der Tabelle)
		List<GenericRow<Piece>> rows = this.table.getRows();
		for (GenericRow<Piece> row : rows) {
			// Index Row
			rowIndex = rows.indexOf(row);
			List<GenericColumn<Piece>> columns = row.getColumns();
			for (GenericColumn<Piece> column : columns) {
				// Index Column
				colIndex = columns.indexOf(column);

				// Aktuelle Zelle und aktuellen Wert ermitteln
				GenericCell<Piece> columnCell = row.getCellByColumn(column);
				Piece cellValue = columnCell.getCellValue();

				// Position für das Quadrat der aktuellen Zelle setzen
				x = 0 + colIndex * DameConstants.SINGLESQUARESIDESIZE;
				y = 0 + rowIndex * DameConstants.SINGLESQUARESIDESIZE;

				// Quadratfarbe für aktuelle Zelle ermitteln und Quadrat
				// zeichnen
				if (rowIndex % 2 == 0) {
					if (colIndex % 2 == 0) {
						g2d.setColor(DameConstants.LIGHTFIELDCOLOR);
					} else {
						g2d.setColor(DameConstants.DARKFIELDCOLOR);
					}
				} else {
					if (colIndex % 2 == 0) {
						g2d.setColor(DameConstants.DARKFIELDCOLOR);
					} else {
						g2d.setColor(DameConstants.LIGHTFIELDCOLOR);
					}
				}
				g2d.fillRect(x, y, DameConstants.SINGLESQUARESIDESIZE,
						DameConstants.SINGLESQUARESIDESIZE);
				// Wert für aktuelle Zelle ermitteln und zeichnen (Wenn nicht
				// leer)
				if (cellValue != Piece.EMPTY) {
					if (cellValue == Piece.WHITE) {
						g2d.setColor(Color.WHITE);
					} else if (cellValue == Piece.BLACK) {
						g2d.setColor(Color.BLACK);
					}
					// Ausgfüllter Kreis (sowohl schwarz als auch weiss)
					g2d.fillOval(
							x
									+ ((DameConstants.SINGLESQUARESIDESIZE - DameConstants.PIECEDIAMETER) / 2),
							y
									+ ((DameConstants.SINGLESQUARESIDESIZE - DameConstants.PIECEDIAMETER) / 2),
							DameConstants.PIECEDIAMETER,
							DameConstants.PIECEDIAMETER);
				}

			}
		}
	}
}
