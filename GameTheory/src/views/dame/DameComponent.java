package views.dame;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import defs.dame.DameConstants;
import defs.dame.DameConstants.DameEventConstants;
import defs.dame.DameGame;
import defs.dame.DamePlayer;

@SuppressWarnings("serial")
public class DameComponent extends JComponent implements Observer {
	// Members
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
		contentPanel.add(getSettingAndMovePanel());

		// add game field panel for displaying the game field to content panel
		dameGameFieldPanel.setTable(this.dameGame.getDameTable());
		contentPanel.add(dameGameFieldPanel);

		// add content panel to component
		this.add(contentPanel);

		// if there is work after a repaint (explicitly)
		SwingUtilities.invokeLater(doWorkAfterRepaint);
	}

	private JPanel getSettingAndMovePanel() {
		// Set Layout to move panel
		JPanel movePanel = new JPanel(new GridLayout(0, 2));

		// Create components of move panel
		JPanel playerSettingPanel = getPlayerSettingPanel();
		JButton restartButton = new JButton("Restart");
		restartButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dameGame.restartGame(false);
			}
		});
		JTextField rowSourceTF = GetDameTextField();
		JTextField columnSourceTF = GetDameTextField();
		JTextField rowTargetTF = GetDameTextField();
		JTextField columnTargetTF = GetDameTextField();
		JButton moveButton = new JButton("Move");
		moveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// Input vor dem Move validieren und ggf. anpassen
				moveStone(getFixedNumber(rowSourceTF),
						getFixedNumber(columnSourceTF),
						getFixedNumber(rowTargetTF),
						getFixedNumber(columnTargetTF));
			}

			private int getFixedNumber(JTextField numberTF) {
				String numberText = numberTF.getText();
				char[] numberTextChars = numberText.toCharArray();
				boolean setNumberForInvalidInput = false;
				if (numberTextChars.length == 0) {
					setNumberForInvalidInput = true;
				} else {
					for (Character c : numberTextChars) {
						if (!Character.isDigit(c)) {
							setNumberForInvalidInput = true;
							break;
						}
					}
				}
				int number;
				if (setNumberForInvalidInput) {
					number = 1;
					numberText = "" + number;
					numberTF.setText(numberText);
				} else {
					number = Integer.parseInt(numberText);
					// Min row/column = 1
					if (number < 1) {
						number = 1;
						numberTF.setText("" + number);
					}
					// Max row/column = Anzahl Quadrate pro Seite
					else if (number > DameConstants.SQUARESPERSIDE) {
						number = DameConstants.SQUARESPERSIDE;
						numberTF.setText("" + DameConstants.SQUARESPERSIDE);
					}
				}
				return number;
			};
		});
		// only enabled if human has to move
		moveButton.setEnabled(dameGame.getGameState().getPlayerToMove()
				.getIsHuman());

		// Add created components to move panel
		movePanel.add(playerSettingPanel);
		movePanel.add(restartButton);
		movePanel.add(new JLabel("Source Row:"));
		movePanel.add(rowSourceTF);
		movePanel.add(new JLabel("Source Column:"));
		movePanel.add(columnSourceTF);
		movePanel.add(new JLabel("Target Row:"));
		movePanel.add(rowTargetTF);
		movePanel.add(new JLabel("Target Column:"));
		movePanel.add(columnTargetTF);
		movePanel.add(moveButton);

		return movePanel;
	}

	private JPanel getPlayerSettingPanel() {
		// Set layout to game setting panel
		JPanel playerSettingPanel = new JPanel(new GridLayout(0, 2));

		// Display player names
		JLabel playerOneLabel = new JLabel("Player 1");
		playerSettingPanel.add(playerOneLabel);
		JLabel playerTwoLabel = new JLabel("Player 2");
		playerSettingPanel.add(playerTwoLabel);

		// Display piece color of the players
		DamePlayer dFirstPlayer = (DamePlayer) dameGame.getFirstPlayer();
		JLabel playerOneInfoLabel = new JLabel(dFirstPlayer.getPlayersPiece()
				.toString());
		playerSettingPanel.add(playerOneInfoLabel);
		DamePlayer dSecondPlayer = (DamePlayer) dameGame.getSecondPlayer();
		JLabel playerTwoInfoLabel = new JLabel(dSecondPlayer.getPlayersPiece()
				.toString());
		playerSettingPanel.add(playerTwoInfoLabel);

		// Display checkboxes for players to choos wheter it is human or not
		// CPU controlled)
		JCheckBox cb1 = new JCheckBox("Human?");
		cb1.setSelected(dameGame.getFirstPlayer().getIsHuman());
		JCheckBox cb2 = new JCheckBox("Human?");
		cb2.setSelected(dameGame.getSecondPlayer().getIsHuman());
		ItemListener cbListener = new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent itemEvent) {
				dameGame.applyPlayerSettings(cb1.isSelected(), cb2.isSelected());
			}
		};
		cb1.addItemListener(cbListener);
		cb2.addItemListener(cbListener);
		playerSettingPanel.add(cb1);
		playerSettingPanel.add(cb2);

		return playerSettingPanel;
	}

	private JTextField GetDameTextField() {
		JTextField f = new JTextField("1");
		f.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				/* Avoid invalid input */
				if (c != KeyEvent.VK_BACK_SPACE && c != KeyEvent.VK_DELETE
						&& !Character.isDigit(c)) {
					getToolkit().beep();
					e.consume();
				}
			}
		});
		return f;
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
