package views.dame;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import defs.dame.DameGameState;
import defs.dame.DameConstants;
import defs.dame.DameConstants.Piece;
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
		this.setLayout(new GridLayout(0, 1));
		JPanel contentPanel = new JPanel(new GridLayout(0, 1));
		// move panel with input boxes and button for move
		contentPanel.add(getMovePanel());
		// game field panel for displaying the game field
		contentPanel.add(getGameFieldPanel());
		this.add(contentPanel);
		// // paint vertex components
		// Dimension size;
		// Point p;
		// for (VertexComponent<V> comp : this.vertexVertexComponents.values())
		// {
		// size = comp.getPreferredSize();
		// p = comp.getCircleCenterLocation();
		// comp.setBounds(p.x - GraphFormat.LOCATIONCENTERMODIFIER, p.y
		// - GraphFormat.LOCATIONCENTERMODIFIER, size.width,
		// size.height);
		// comp.getVertexComponentModel().updateGraphFormat(
		// model.getGraphFormat());
		// this.add(comp);
		// }
		// // paint edges
		// Graphics2D graphPanelGraphics = (Graphics2D) g;
		// Iterator<Edge<E>> itE = model.getGraph().edges();
		// while (itE.hasNext()) {
		// if (null != graphPanelGraphics) {
		// EdgePainter.paintEdge(model.getGraphFormat(), itE.next(),
		// (Graphics2D) g);
		// }
		// }
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

	private JPanel getGameFieldPanel() {
		JPanel gameFieldPanel = new JPanel(new GridLayout(0, 1));
		GenericTable<Piece> table = this.dameGameState.getGameTable();
		for (GenericRow<Piece> row : table.getRows()) {
			for (GenericColumn<Piece> column : row.getColumns()) {
				GenericCell<Piece> columnCell = row.getCellByColumn(column);
				Piece cellValue = columnCell.getCellValue();
				if (cellValue == Piece.BLACK) {

				} else if (cellValue == Piece.WHITE) {

				} else if (cellValue == Piece.EMPTY) {

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
			if (eventConstant.equals(DameConstants.STONEMOVED)
					|| eventConstant
							.equals(DameConstants.STARTSTATESET)) {
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
