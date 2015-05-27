import defs.dame.DameGame;
import views.dame.DameComponent;
import views.dame.DameFrame;

public class Program {
	public static void main(String[] args) {
		DameGame dameGame = new DameGame();
		DameFrame dameFrame = new DameFrame(new DameComponent(
				dameGame.getDameGameState()));
		dameFrame.setVisible(true);
	}
}
