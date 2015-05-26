package views.dame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import defs.dame.DameConstants.*;
import defs.dame.DameConstants;
import defs.dame.DameGameState;
import defs.general.GenericCell;
import defs.general.GenericColumn;
import defs.general.GenericRow;
import defs.general.GenericTable;

@SuppressWarnings("serial")
public class DameComponent extends JComponent implements Observer {
	// Members
	DameGameState dameGameState = null;

	// Constructors
	public DameComponent(DameGameState dameGameState) {
		this.dameGameState = dameGameState;
	}

	// Painting methods
	private void refreshGameField() {
		this.repaint();
	}

	@Override
	public void paintComponent(Graphics g) {
		// clear all
		this.removeAll();

		// paint container
		super.paintComponent(g);

		// paint own stuff
		// set layout and create content panel
		this.setLayout(new GridLayout(0, 1));
		JPanel contentPanel = new JPanel(new GridLayout(0, 1));
		// add move panel with input boxes and button for move to content panel
		contentPanel.add(getMovePanel());
		// add game field panel for displaying the game field to content panel
		contentPanel.add(getGameFieldPanel((Graphics2D) g));
		// add content panel to component
		this.add(contentPanel);
	}

	private JPanel getMovePanel() {
		JPanel movePanel = new JPanel(new GridLayout(0, 2));
		movePanel.add(new JLabel("Source Row:"));
		JTextField rowSourceTF = new JTextField("1");
		movePanel.add(rowSourceTF);
		movePanel.add(new JLabel("Source Column:"));
		JTextField columnSourceTF = new JTextField("1");
		movePanel.add(columnSourceTF);
		movePanel.add(new JLabel("Target Row:"));
		JTextField rowTargetTF = new JTextField("1");
		movePanel.add(rowTargetTF);
		movePanel.add(new JLabel("Target Column:"));
		JTextField columnTargetTF = new JTextField("1");
		movePanel.add(columnTargetTF);
		JButton moveButton = new JButton("Move");
		moveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				moveStone(Integer.parseInt(rowSourceTF.getText()),
						Integer.parseInt(columnSourceTF.getText()),
						Integer.parseInt(rowTargetTF.getText()),
						Integer.parseInt(columnTargetTF.getText()));
			};
		});
		movePanel.add(moveButton);
		return movePanel;
	}

	private JPanel getGameFieldPanel(Graphics2D g2d) {
		// Layout und Variablen für ganzes Spielfeld
		JPanel gameFieldPanel = new JPanel(new GridLayout(0, 1));
		int rowIndex;
		int colIndex;
		int x;
		int y;
		// Spielfeld zeichnen (Zellen der Tabelle)
		// GenericTable<Piece> table = this.dameGameState.getGameTable();
		// Table for testing
		GenericTable<Piece> table = new GenericTable<Piece>();
		GenericRow<Piece> row1 = new GenericRow<Piece>();
		row1.addColumn(new GenericColumn<Piece>(), Piece.BLACK);
		row1.addColumn(new GenericColumn<Piece>(), Piece.WHITE);
		row1.addColumn(new GenericColumn<Piece>(), Piece.EMPTY);
		table.addRow(row1);
		GenericRow<Piece> row2 = new GenericRow<Piece>();
		row2.addColumn(new GenericColumn<Piece>(), Piece.BLACK);
		row2.addColumn(new GenericColumn<Piece>(), Piece.WHITE);
		row2.addColumn(new GenericColumn<Piece>(), Piece.EMPTY);
		table.addRow(row2);
		// Table for testing
		List<GenericRow<Piece>> rows = table.getRows();
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
				x = 0 + colIndex * DameConstants.SQUARESIDESIZE;
				y = 0 + rowIndex * DameConstants.SQUARESIDESIZE;

				// Quadrat für aktuelle Zelle zeichnen
				// Defaultfarbe schwarz (Für leer und schwarz)
				g2d.setColor(Color.BLACK);
				// Leere Zelle
				if (cellValue == Piece.EMPTY) {
					// Leer (Nicht ausgefüllt)
					g2d.drawRect(x, y, DameConstants.SQUARESIDESIZE,
							DameConstants.SQUARESIDESIZE);
				} else {
					// Farbe für weiss anpassen
					if (cellValue == Piece.WHITE) {
						// Farbe weiss (für weiss...)
						g2d.setColor(Color.WHITE);
					}
					// Ausgfüllt (sowohl schwarz als auch weiss)
					g2d.fillRect(x, y, DameConstants.SQUARESIDESIZE,
							DameConstants.SQUARESIDESIZE);
				}
			}
		}
		return gameFieldPanel;
	}

	// Observer methods
	@Override
	public void update(Observable observable, Object args) {
		if (String.class.isInstance(args)) {
			String eventConstant = (String) args;
			if (eventConstant.equals(DameEventConstants.STONEMOVED)
					|| eventConstant.equals(DameEventConstants.STARTSTATESET)) {
				// Refresh the game field
				refreshGameField();
			}
		}
	}

	// Control methods and events
	public void moveStone(int sourceRowIndex, int sourceColumnIndex,
			int targetRowIndex, int targetColumnIndex) {
		dameGameState.moveStone(sourceRowIndex, sourceColumnIndex,
				targetRowIndex, targetColumnIndex);
	}
}
