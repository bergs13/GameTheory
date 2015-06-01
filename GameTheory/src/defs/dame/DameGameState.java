/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package defs.dame;

import defs.dame.DameConstants.*;
import defs.general.GameState;
import defs.general.Move;
import defs.general.Player;
import interfaces.UsableAsDameViewModel;

/**
 *
 * @author Thunderchild
 */
public class DameGameState extends GameState implements
		UsableAsDameViewModel<Piece> {

	private DameTable dameTable = null;

	public DameGameState() {
	}

	public DameGameState(GameState parentState, Move move) {
		super(parentState, move);
	}

	// Methods
	// Base overrides
	@Override
	public void findPossibleMoves() {
		setChildMoves(this.dameTable.getAllPossibleMoves());
	}

	@Override
	public void createChildStates() {
		for (Move move : getAllMoves()) {
			getChildStates().add(new GameState(this, move));
		}
	}

	@Override
	public boolean isTerminal() {
		return (getAllMoves().isEmpty()); // To change body of generated
											// methods,
		// choose Tools | Templates.
	}

	public void setStartState(Player firstPlayer, DameTable dameTable) {
		super.setStartState(firstPlayer);
		this.dameTable = dameTable;

		// Notify view for update
		setChanged();
		notifyObservers(DameEventConstants.STARTSTATESET);
	}

	// UsableAsDameViewModel<Character> (interface) methods
	@Override
	public DameTable getGameTable() {
		return this.dameTable;
	}

	@Override
	public void movePiece(int sourceRowIndex, int sourceColumnIndex,
			int targetRowIndex, int targetColumnIndex) {
		this.dameTable.moveValue(sourceRowIndex, sourceColumnIndex,
				targetRowIndex, targetColumnIndex, Piece.EMPTY);

		// Notify view for update
		setChanged();
		notifyObservers(DameEventConstants.STONEMOVED);
	}
}
