import defs.dame.DameGame;
import defs.dame.DameGameState;
import defs.dame.DameMove;
import defs.dame.DamePlayer;
import views.dame.DameComponent;
import views.dame.DameFrame;

public class Program {

	public static void main(String[] args) {
		boolean consoleMode = true; // (args[0].equalsIgnoreCase("c"));
		if (!consoleMode) {
			DameFrame dameFrame = new DameFrame(new DameComponent(
					new DameGame()));
			dameFrame.setVisible(true);
		} else {
			DameGame game = new DameGame();
			DameGameState gameState = game.getGameState();
			gameState.getPlayerToMove().setIsHuman(false);
			gameState.getPlayerToWait().setIsHuman(false);
			((DamePlayer) gameState.getPlayerToMove()).setDepthToEvaluate(6);
			((DamePlayer) gameState.getPlayerToWait()).setDepthToEvaluate(6);

			System.out.println(gameState.tableToString());
			int i = 0;
			int movesSinceCapture = 0;
			do {
				DameMove move = ((DamePlayer) gameState.getPlayerToMove())
						.getMove(gameState);
				gameState = (DameGameState) gameState.doMove(move);
				if (move.canCapture()) {
					movesSinceCapture = 0;
				} else {
					movesSinceCapture++;
				}
				i++;
				System.out.println("Move "
						+ i
						+ ":\nPlayer "
						+ ((DamePlayer) gameState.getPlayerToMove())
								.getPlayersPiece() + " did Move " + "from "
						+ move.getMovement()[0] + " , " + move.getMovement()[1]
						+ " to " + move.getMovement()[2] + " , "
						+ move.getMovement()[3]);
				System.out.println("Table:\n" + gameState.tableToString());
				System.out.println(movesSinceCapture
						+ " Moves since last capture");

			} while (!gameState.isTerminal() && movesSinceCapture <= 100);
			System.out.println("finished in " + i + " rounds");
		}
	}
}
