package views.dame;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import defs.dame.DameConstants;
import defs.dame.DameGame;
import defs.dame.DamePlayer;
import defs.general.Player;

@SuppressWarnings("serial")
public class DameGameSettingsPanel extends JPanel {
	// Members
	DameGame dameGame = null;
	// Updatable control
	JLabel playerOneInfoLabel = null;
	JLabel playerTwoInfoLabel = null;
	JButton moveButton = null;
	ArrayList<JTextField> dameTextFields = new ArrayList<JTextField>();

	// Constructors
	public DameGameSettingsPanel(DameGame dameGame) {
		super(new GridLayout(0, 2));

		this.dameGame = dameGame;

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
		dameTextFields.add(rowSourceTF);
		JTextField columnSourceTF = GetDameTextField();
		dameTextFields.add(columnSourceTF);
		JTextField rowTargetTF = GetDameTextField();
		dameTextFields.add(rowTargetTF);
		JTextField columnTargetTF = GetDameTextField();
		dameTextFields.add(columnTargetTF);
		moveButton = new JButton("Move");
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
		this.add(playerSettingPanel);
		this.add(restartButton);
		this.add(new JLabel("Source Row:"));
		this.add(rowSourceTF);
		this.add(new JLabel("Source Column:"));
		this.add(columnSourceTF);
		this.add(new JLabel("Target Row:"));
		this.add(rowTargetTF);
		this.add(new JLabel("Target Column:"));
		this.add(columnTargetTF);
		this.add(moveButton);
	}

	// Methods
	private JPanel getPlayerSettingPanel() {
		// Set layout to game setting panel
		JPanel playerSettingPanel = new JPanel(new GridLayout(0, 2));

		// Display player names
		JLabel playerOneLabel = new JLabel("Player 1");
		playerSettingPanel.add(playerOneLabel);
		JLabel playerTwoLabel = new JLabel("Player 2");
		playerSettingPanel.add(playerTwoLabel);

		// Display piece color of the players
		Player playerToMove = dameGame.getGameState().getPlayerToMove();
		DamePlayer dFirstPlayer = (DamePlayer) dameGame.getFirstPlayer();
		playerOneInfoLabel = new JLabel(dFirstPlayer.getPlayersPiece()
				.toString()
				+ (playerToMove.equals(dFirstPlayer) ? " (Active)" : ""));
		playerSettingPanel.add(playerOneInfoLabel);
		DamePlayer dSecondPlayer = (DamePlayer) dameGame.getSecondPlayer();
		playerTwoInfoLabel = new JLabel(dSecondPlayer.getPlayersPiece()
				.toString()
				+ (playerToMove.equals(dSecondPlayer) ? " (Active)" : ""));
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

	public void setDameGame(DameGame dameGame) {
		this.dameGame = dameGame;
		updateGameinformation();
	}

	private void updateGameinformation() {
		// update updatable controls
		// infos
		Player playerToMove = dameGame.getGameState().getPlayerToMove();
		DamePlayer dFirstPlayer = (DamePlayer) dameGame.getFirstPlayer();
		playerOneInfoLabel.setText(dFirstPlayer.getPlayersPiece().toString()
				+ (playerToMove.equals(dFirstPlayer) ? " (Active)" : ""));
		DamePlayer dSecondPlayer = (DamePlayer) dameGame.getSecondPlayer();
		playerTwoInfoLabel.setText(dSecondPlayer.getPlayersPiece().toString()
				+ (playerToMove.equals(dSecondPlayer) ? " (Active)" : ""));
		// button and input fields enabled
		boolean enabled = this.dameGame.getGameState().getPlayerToMove()
				.getIsHuman();
		for (JTextField f : dameTextFields) {
			f.setEnabled(enabled);
		}
		moveButton.setEnabled(enabled);
	}

	public void moveStone(int sourceRowNumber, int sourceColumnNumber,
			int targetRowNumber, int targetColumnNumber) {
		dameGame.performManualMove(sourceRowNumber - 1, sourceColumnNumber - 1,
				targetRowNumber - 1, targetColumnNumber - 1);
	}
}
