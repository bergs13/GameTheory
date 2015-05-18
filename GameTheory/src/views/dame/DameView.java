package views.dame;

import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JComponent;

import defs.dame.DameGameState;
import defs.dame.DameGameStateEventConstants;

@SuppressWarnings("serial")
public class DameView extends JComponent implements Observer {
	// Members
	DameGameState dameGameState = null;

	// Constructors
	public DameView(DameGameState dameGameState) {
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

		// paint panel
		super.paintComponent(g);

		// // paint own stuff
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
	public void moveStone() {
//		dameGameState.moveStone(sourceRowIndex, sourceColumnIndex,
//				targetRowIndex, targetColumnIndex);
	}
}
