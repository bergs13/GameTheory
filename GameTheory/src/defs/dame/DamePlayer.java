package defs.dame;

import defs.dame.DameConstants.Piece;
import defs.general.Player;

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
}
