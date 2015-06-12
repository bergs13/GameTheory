
import defs.dame.DameGame;
import defs.dame.DameGameState;
import defs.dame.DameMove;
import defs.dame.DamePlayer;
import views.dame.DameComponent;
import views.dame.DameFrame;

public class Program {

    public static void main(String[] args) {
        boolean consoleMode = true;
        if (!consoleMode) {
            DameFrame dameFrame = new DameFrame(new DameComponent(new DameGame()));
            dameFrame.setVisible(true);
        } else {
            DameGame game = new DameGame();
            DameGameState gameState = game.getGameState();
            gameState.getPlayerToMove().setIsHuman(false);
            gameState.getPlayerToWait().setIsHuman(false);
            System.out.println(gameState.tableToString());
            int i = 0;
            do {
                DameMove move = ((DamePlayer) gameState.getPlayerToMove()).getMove(gameState);
//                System.out.println("PossibleMoves are: " + gameState.allPossibleMovesToString());
                System.out.println("Player "
                        + ((DamePlayer) gameState.getPlayerToMove()).getPlayersPiece()
                        + " did Move "
                        + "from " + move.getMovement()[0]
                        + " , " + move.getMovement()[1]
                        + " to " + move.getMovement()[2]
                        + " , " + move.getMovement()[3]);
//                System.out.println(((DamePlayer)gameState.getPlayerToMove()).getPlayersPiece());
                gameState = (DameGameState) gameState.doMove(move);
//                gameState.createChildStates();
                System.out.println("Table:\n" + gameState.tableToString());
                i++;
            } while (!gameState.isTerminal());
            System.out.println("finished in " + i + " rounds");
        }
    }
}
