package views.dame;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import defs.dame.DameConstants.DameEventConstants;
import defs.dame.DameGame;

@SuppressWarnings("serial")
public class DameComponent extends JComponent implements Observer {
	// Members
	DameGameSettingsPanel dameGameSettingsPanel = null;
	DameGameFieldPanel dameGameFieldPanel = new DameGameFieldPanel();
	DameGame dameGame = null;
	Runnable doWorkAfterRepaint = new Runnable() {
		@Override
		public void run() {
			checkPerformCPUMove();
		}
	};

	// Constructors
	public DameComponent(DameGame dameGame) {
		this.dameGame = dameGame;
		// gui is observer of model
		this.dameGame.addObserver(this);
	}

	// Painting methods
	private void refreshGameField() {
		this.dameGameSettingsPanel.setDameGame(this.dameGame);
		this.dameGameFieldPanel.setTable(this.dameGame.getDameTable());
		this.repaint();

		// if there is work after a repaint (explicitly)
		SwingUtilities.invokeLater(doWorkAfterRepaint);
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
		JPanel contentPanel = new JPanel(new GridLayout(0, 2));

		// add move panel with input boxes and button for move to content panel
		dameGameSettingsPanel = new DameGameSettingsPanel(this.dameGame);
		contentPanel.add(dameGameSettingsPanel);
		// contentPanel.add(getSettingAndMovePanel());

		// add game field panel for displaying the game field to content panel
		dameGameFieldPanel.setTable(this.dameGame.getDameTable());
		contentPanel.add(dameGameFieldPanel);

		// add content panel to component
		this.add(contentPanel);

		// if there is work after a repaint (explicitly)
		SwingUtilities.invokeLater(doWorkAfterRepaint);
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
	private void checkPerformCPUMove() {
		// Current playe is CPU?
		if (!dameGame.getGameState().getPlayerToMove().getIsHuman()) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
			// Perform CPU move
			dameGame.performCPUMove();
		}
	}

	public void moveStone(int sourceRowNumber, int sourceColumnNumber,
			int targetRowNumber, int targetColumnNumber) {
		dameGame.performManualMove(sourceRowNumber - 1, sourceColumnNumber - 1,
				targetRowNumber - 1, targetColumnNumber - 1);
	}
}
