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
        DameGameState bestChildState = new DameGameState();
            bestChildState.setValue(-101);
        // Only perform "background" move if player is no human player
        if (!this.getIsHuman()) {
            Algorithms.alphaBeta2(new DameEvaluator(
                                    this.playersPiece,
                                    ((DamePlayer) gameState.getPlayerToWait()).playersPiece),
                            gameState, 4, -100, 100);
            
            for (GameState childState : gameState.getChildStates()){
                bestChildState = childState.getValue() >= bestChildState.getValue() ? (DameGameState)childState : bestChildState;
            }
        }
        return (DameMove)bestChildState.getPlayedMove();
    }
}
