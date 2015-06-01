/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package defs.dame;

import defs.general.GameState;
import defs.general.Move;
import defs.general.Player;

/**
 *
 * @author Thunderchild
 */
public class DameGameState extends GameState {

	private DameTable dameTable = null;

	public DameGameState() {
	}

	public DameGameState(GameState parentState, Move move, DameTable dameTable) {
		super(parentState, move);
		this.dameTable = dameTable;
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

	@Override
	public GameState doMove(Move move) {
		DameGameState gstate = new DameGameState(this, move, this.dameTable);
		return gstate;
	}

	public void setStartState(Player firstPlayer, DameTable dameTable) {
		super.setStartState(firstPlayer);
		this.dameTable = dameTable;
	}

	public DameTable getDameTable() {
		return this.dameTable;
	}
}
