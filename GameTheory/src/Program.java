import defs.dame.DameGame;
import views.dame.DameComponent;
import views.dame.DameFrame;

public class Program {
	public static void main(String[] args) {
		DameFrame dameFrame = new DameFrame(new DameComponent(new DameGame()));
		dameFrame.setVisible(true);
	}
}
