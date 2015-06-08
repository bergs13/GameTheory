/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package defs.nim;

import defs.general.GameState;
import defs.general.Move;
import defs.general.Player;

/**
 *
 * @author Thunderchild
 */
public class NimGameState extends GameState {

	public int matches;

	public NimGameState() {
	}

	public NimGameState(GameState parentState, Move move) {
		super(parentState, move);
		this.matches = ((NimGameState) parentState).matches;
		((NimMove) move).executeMove(this.matches);
	}

	@Override
	public void findPossibleMoves() {
		for (int i = 1; i <= Math.min(3, matches); i++) {
			getChildMoves().add(new NimMove(i));
		}

	}

	@Override
	public void createChildStates() {
		for (Move move : this.getAllMoves()) {
			getChildStates().add(new GameState(this, move));
		}
	}

	public void setStartState(Player firstPlayer, int startMatches) {
		super.setStartState(firstPlayer, new Player());
		this.matches = startMatches;
	}

}
