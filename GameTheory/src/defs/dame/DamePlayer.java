package defs.dame;

import defs.dame.DameConstants.Piece;
import defs.general.Player;
import defs.general.Algorithms;
import defs.general.GameState;

public class DamePlayer extends Player {

	// Members
	Piece playersPiece = Piece.EMPTY;
	private int depthToEvaluate = 4;

	// Constructors

	public DamePlayer(boolean isHuman, Piece playersPiece) {
		super(isHuman);
		this.playersPiece = playersPiece;
	}

	public DamePlayer(Piece playersPiece) {
		super();
		this.playersPiece = playersPiece;
	}

	// get the next move
	public DameMove getMove(DameGameState gameState) {
		DameGameState bestChildState = gameState;
		// Only perform "background" move if player is no human player
		if (!this.getIsHuman()) {
			if (gameState.isTerminal()) {
				return (DameMove) bestChildState.getPlayedMove();
			}
			bestChildState = (DameGameState) gameState.getChildStates().get(0);
			bestChildState.setValue(Integer.MIN_VALUE);
			int alpha = -100;
			int beta = 100;
			int depht = this.depthToEvaluate;

			if (this.getDepthToEvaluate() <= 1) {
				return (DameMove) gameState.getChildMoves().get(0);
			}
			for (GameState childState : gameState.getChildStates()) {
				int value = -Algorithms
						.alphaBeta(
								new DameEvaluator(
										this.playersPiece,
										((DamePlayer) gameState
												.getPlayerToWait()).playersPiece),
								childState, depht, -beta, -alpha);
				childState.setValue(value);
				bestChildState = childState.getValue() >= bestChildState
						.getValue() ? (DameGameState) childState
						: (DameGameState)bestChildState;
			}
			for (GameState childState : gameState.getChildStates()) {

				bestChildState = childState.getValue() >= bestChildState
						.getValue() ? (DameGameState) childState
						: bestChildState;
			}
		}
		DameMove bestmove = (DameMove) bestChildState.getPlayedMove();
		System.out.println("best Value is:" + bestChildState.getValue());
		return bestmove;
	}

	// getters and setters
	public Piece getPlayersPiece() {
		return playersPiece;
	}

	public int getDepthToEvaluate() {
		return depthToEvaluate;
	}

	public void setDepthToEvaluate(int depthToSet) {
		if (depthToSet >= 20) {
			this.depthToEvaluate = 20;
		} else {
			this.depthToEvaluate = depthToSet;
		}
	}
}
