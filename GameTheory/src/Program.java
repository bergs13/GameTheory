import defs.dame.DameGame;
import defs.general.GenericRow;
import defs.general.GenericTable;
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
