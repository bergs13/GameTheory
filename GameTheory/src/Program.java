
import defs.dame.DameConstants;
import defs.dame.DameGame;
import defs.dame.DameGameState;
import defs.dame.DameMove;
import defs.dame.DamePlayer;
import defs.general.GenericCell;
import defs.general.GenericRow;
import views.dame.DameComponent;
import views.dame.DameFrame;

public class Program {

    public static void main(String[] args) {
        boolean consoleMode = false;
        if (!consoleMode) {
            DameFrame dameFrame = new DameFrame(new DameComponent(new DameGame()));
            dameFrame.setVisible(true);
        } else {
            DameGame game = new DameGame();
            DameGameState gameState = game.getGameState();
            gameState.getPlayerToMove().setIsHuman(false);
            gameState.getPlayerToWait().setIsHuman(false);
            System.out.println(gameState.tableToString());
            do {
                DameMove move = ((DamePlayer) gameState.getPlayerToMove()).getMove(gameState);
                //System.out.println("PossibleMoves are: " + gameState.allPossibleMovesToString());
                System.out.println("Table:");
                System.out.println(gameState.tableToString());
                System.out.println("Player "
                        + ((DamePlayer) gameState.getPlayerToMove()).getPlayersPiece()
                        + " did Move "
                        + "from " + move.getMovement()[0]
                        + " , " + move.getMovement()[1]
                        + " to " + move.getMovement()[2]
                        + " , " + move.getMovement()[3]);
                gameState = (DameGameState) gameState.doMove(move);
            } while (!gameState.isTerminal());
            System.out.println("finished");
        }
    }
}
