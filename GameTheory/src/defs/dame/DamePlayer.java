package defs.dame;

import defs.dame.DameConstants.Piece;
import defs.general.Player;
import defs.general.Algorithms;
import defs.general.GameState;

public class DamePlayer extends Player {

    // Members

    Piece playersPiece = Piece.EMPTY;

    // Constructors
    public DamePlayer(boolean isHuman, Piece playersPiece) {
        super(isHuman);
        this.playersPiece = playersPiece;
    }

    public DamePlayer(Piece playersPiece) {
        super();
        this.playersPiece = playersPiece;
    }

    public Piece getPlayersPiece() {
        return playersPiece;
    }

    public DameMove getMove(DameGameState gameState) {
        DameMove move = null;
        DameGameState bestChildState = gameState;
         // Only perform "background" move if player is no human player
        if (!this.getIsHuman()) {
            if (gameState.isTerminal()) return (DameMove)bestChildState.getPlayedMove();
            bestChildState = (DameGameState)gameState.getChildStates().get(0);
            bestChildState.setValue(-101);
            int alpha = -100;
            int beta = 100;
            int depht = 4;
            
            for (GameState childState : gameState.getChildStates()){
                int value = Algorithms.alphaBeta(new DameEvaluator(
                                    this.playersPiece,
                                    ((DamePlayer) gameState.getPlayerToWait()).playersPiece), childState, depht, -beta, alpha);
                childState.setValue(value);
                //System.out.println(childState.getValue());
                bestChildState = childState.getValue() >= bestChildState.getValue() ? (DameGameState)childState : bestChildState;
            }
            for (GameState childState : gameState.getChildStates()){
                
                bestChildState = childState.getValue() >= bestChildState.getValue() ? (DameGameState)childState : bestChildState;
            }
        }
        DameMove bestmove = (DameMove)bestChildState.getPlayedMove();
//        System.out.println("from " + bestmove.getMovement()[0]
//                        + " , " + bestmove.getMovement()[1]
//                        + " to " + bestmove.getMovement()[2]
//                        + " , " + bestmove.getMovement()[3]);
        return (DameMove)gameState.getChildMoves().get(0);//bestChildState.getPlayedMove();
    }
}
