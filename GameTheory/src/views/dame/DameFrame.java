package views.dame;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class DameFrame extends JFrame {
	// Constructors
	public DameFrame(DameComponent dameComponent) {
		super("Dame-Spiel");
	    this.setSize(200,200);
		this.getContentPane().add(dameComponent);
	}
}
