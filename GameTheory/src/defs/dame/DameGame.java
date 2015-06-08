/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package defs.dame;

import interfaces.UsableAsDameViewModel;
import defs.dame.DameConstants.DameEventConstants;
import defs.dame.DameConstants.Piece;
import defs.general.Game;
import defs.general.GenericColumn;
import defs.general.GenericRow;

/**
 *
 * @author Thunderchild
 */
public class DameGame extends Game implements UsableAsDameViewModel<Piece> {
	// Members
	DameGameState dameGameState = null;
	DameEvaluator evaluator = new DameEvaluator(Piece.BLACK, Piece.WHITE);

	// Constructors
	public DameGame() {
		super("Dame-Spiel");
		restartGame();
	}

	// Methods
	@Override
	public boolean setupGame() {
		// initialize table and set as start state
		DameTable table = new DameTable();
		// S S S S S
		// S S S S S
		// S S . W W
		// W W W W W
		// W W W W W
		for (int i = 0; i < DameConstants.SQUARESPERSIDE; i++) {
			GenericRow<Piece> row = new GenericRow<Piece>();
			for (int j = 0; j < DameConstants.SQUARESPERSIDE; j++) {
				GenericColumn<Piece> column = new GenericColumn<Piece>();
				Piece cellValue = Piece.EMPTY;
				if (i < 2) {
					cellValue = Piece.BLACK;
				} else if (i == 2) {
					if (j < 2) {
						cellValue = Piece.BLACK;
					} else if (j > 2) {
						cellValue = Piece.WHITE;
					}
				} else if (i > 2) {
					cellValue = Piece.WHITE;
				}
				row.addColumn(column, cellValue);
			}
			table.addRow(row);
		}
		super.setFirstPlayer(new DamePlayer(true, Piece.WHITE));
		super.setSecondPlayer(new DamePlayer(false, Piece.BLACK));
		this.dameGameState.setStartState(getFirstPlayer(), getSecondPlayer(), table);
		this.dameGameState.findPossibleMoves();

		// Notify view for update
		setChanged();
		notifyObservers(DameEventConstants.STARTSTATESET);

		return true;
	}

	private void restartGame() {
		this.dameGameState = new DameGameState();
		setupGame();
	}

	// UsableAsDameViewModel<Character> (interface) methods
	@Override
	public DameTable getDameTable() {
		return this.dameGameState.getDameTable();
	}

	@Override
	public void setPlayerIsHuman(boolean firstPlayerIsHuman,
			boolean secondPlayerIsHuman) {
		getFirstPlayer().setIsHuman(firstPlayerIsHuman);
		getSecondPlayer().setIsHuman(secondPlayerIsHuman);
		restartGame();
	}

	@Override
	public void movePiece(int sourceRowIndex, int sourceColumnIndex,
			int targetRowIndex, int targetColumnIndex) {
		int[] movement = { sourceRowIndex, sourceColumnIndex, targetRowIndex,
				targetColumnIndex };
		DameMove dMove = new DameMove(movement);
		this.dameGameState = (DameGameState) this.dameGameState.doMove(dMove);
		dMove.executeMove(this.dameGameState.getDameTable());

		// Notify view for update
		setChanged();
		notifyObservers(DameEventConstants.STONEMOVED);
	}
}
