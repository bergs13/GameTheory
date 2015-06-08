package defs.dame;

import defs.dame.DameConstants.Piece;
import defs.general.Player;
import defs.general.Algorithms;

public class DamePlayer extends Player {

    // Mermbers

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
        if (this.getIsHuman()) {
            //let the player choose his next move
        } else {
            move = (DameMove) Algorithms.alphaBeta2(new DameEvaluator(this.playersPiece, ((DamePlayer) gameState.getPlayerToWait()).playersPiece),
                    gameState, 4, -100, 100).getPlayedMove();
        }
        return move;
    }
}
