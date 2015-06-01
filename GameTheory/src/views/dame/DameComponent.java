package views.dame;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

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
		contentPanel.add(getMovePanel());

		// add game field panel for displaying the game field to content panel
		dameGameFieldPanel.setTable(this.dameGame.getDameTable());
		contentPanel.add(dameGameFieldPanel);
		// dameGameFieldPanel.repaint();
		// add content panel to component
		this.add(contentPanel);
	}

	private JPanel getMovePanel() {
		JPanel movePanel = new JPanel(new GridLayout(0, 2));
		movePanel.add(new JLabel("Source Row:"));
		JTextField rowSourceTF = GetDameTextField();
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

	private JTextField GetDameTextField() {
		JTextField f = new JTextField("1");
		f.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				checkAndFixDameInput(f);
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

	public void checkAndFixDameInput(JTextField inputField) {
		// String text = inputField.getText();
		// if (null != text) {
		// boolean textIsDigits = true;
		// for (char c : text.toCharArray()) {
		// if (!Character.isDigit(c)) {
		// textIsDigits = false;
		// break;
		// }
		// }
		// if (textIsDigits) {
		// int number = Integer.parseInt(text);
		// if (number <= 0) {
		// number = 1;
		// } else if (number > DameConstants.SQUARESPERSIDE) {
		// number = DameConstants.SQUARESPERSIDE;
		// }
		// inputField.setText("" + number);
		// }
		// }
	}
}
