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

import defs.dame.DameConstants;
import defs.dame.DameConstants.DameEventConstants;
import defs.dame.DameGame;

@SuppressWarnings("serial")
public class DameComponent extends JComponent implements Observer {
	// Members
	DameGame dameGame = null;
	DameGameFieldPanel dameGameFieldPanel = new DameGameFieldPanel();

	// Constructors
	public DameComponent(DameGame dameGame) {
		this.dameGame = dameGame;
		// gui is observer of model
		this.dameGame.addObserver(this);
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
		JPanel contentPanel = new JPanel(new GridLayout(0, 2));

		// add move panel with input boxes and button for move to content panel
		contentPanel.add(getSettingAndMovePanel());

		// add game field panel for displaying the game field to content panel
		dameGameFieldPanel.setTable(this.dameGame.getDameTable());
		contentPanel.add(dameGameFieldPanel);
		// dameGameFieldPanel.repaint();
		// add content panel to component
		this.add(contentPanel);
	}

	private JPanel getSettingAndMovePanel() {
		JPanel movePanel = new JPanel(new GridLayout(0, 2));
		movePanel.add(new JLabel("Source Row:"));
		JTextField rowSourceTF = GetDameTextField();
		movePanel.add(rowSourceTF);
		movePanel.add(new JLabel("Source Column:"));
		JTextField columnSourceTF = GetDameTextField();
		movePanel.add(columnSourceTF);
		movePanel.add(new JLabel("Target Row:"));
		JTextField rowTargetTF = GetDameTextField();
		movePanel.add(rowTargetTF);
		movePanel.add(new JLabel("Target Column:"));
		JTextField columnTargetTF = GetDameTextField();
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
		movePanel.add(getPlayerSettingPanel());
		return movePanel;
	}

	private JPanel getPlayerSettingPanel() {
		JPanel playerSettingPanel = new JPanel(new GridLayout(0, 2));
		JCheckBox cb1 = new JCheckBox("Player 1 human?");
		playerSettingPanel.add(cb1);
		JCheckBox cb2 = new JCheckBox("Player 2 human?");
		ItemListener cbListener = new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent itemEvent) {
				dameGame.setPlayerIsHuman(cb1.isSelected(), cb2.isSelected());
			}
		};
		cb1.addItemListener(cbListener);
		cb2.addItemListener(cbListener);
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
				/* check and fix result */
				else {
					String textBefore = f.getText();
					String textAfter;
					if (null == textBefore || "" == textBefore) {
						textAfter = "" + c;
					} else {
						textAfter = textBefore + c;
					}
					int intTextAfter = null == textAfter || "" == textAfter ? 1
							: Integer.parseInt(textAfter);
					if (intTextAfter <= 0) {
						f.setText("" + 1);
						e.consume();
					} else if (intTextAfter > DameConstants.SQUARESPERSIDE) {
						f.setText("" + DameConstants.SQUARESPERSIDE);
						e.consume();
					}
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
	public void moveStone(int sourceRowNumber, int sourceColumnNumber,
			int targetRowNumber, int targetColumnNumber) {
		dameGame.movePiece(sourceRowNumber - 1, sourceColumnNumber - 1,
				targetRowNumber - 1, targetColumnNumber - 1);
	}
}
