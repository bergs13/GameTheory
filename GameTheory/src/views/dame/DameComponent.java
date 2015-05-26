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
import defs.dame.DameGameStateEventConstants;
import defs.dame.DameGameStateEventConstants.Piece;
import defs.general.Table;

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
		// move panel with input boxes and button for move
		this.setLayout(new GridLayout(0, 1));
		JPanel panel = new JPanel(new GridLayout(0, 2));
		panel.add(new JLabel("Source Row:"));
		JTextField rowSourceTF = new JTextField("1");
		panel.add(rowSourceTF);
		panel.add(new JLabel("Source Column:"));
		JTextField columnSourceTF = new JTextField("1");
		panel.add(columnSourceTF);
		panel.add(new JLabel("Target Row:"));
		JTextField rowTargetTF = new JTextField("1");
		panel.add(rowTargetTF);
		panel.add(new JLabel("Target Column:"));
		JTextField columnTargetTF = new JTextField("1");
		panel.add(columnTargetTF);
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
		panel.add(moveButton);
		this.add(panel);
		// game field
		Table<Piece> table = this.dameGameState.getGameTable();
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

	// Observer methods
	@Override
	public void update(Observable observable, Object args) {
		if (String.class.isInstance(args)) {
			String eventConstant = (String) args;
			if (eventConstant.equals(DameGameStateEventConstants.STONEMOVED)
					|| eventConstant
							.equals(DameGameStateEventConstants.STARTSTATESET)) {
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
