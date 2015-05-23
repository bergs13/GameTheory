/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package defs.dame;

import defs.general.Game;
import defs.general.Table;

/**
 *
 * @author Thunderchild
 */
public class DameGame extends Game {
	DameGameState dameGameState = null;
	DameEvaluator evaluator = new DameEvaluator();

	public DameGame() {
		super("Dame-Spiel");
		this.dameGameState = new DameGameState();
		setupGame();
	}

	@Override
	public boolean setupGame() {
		// initialize table and set as start state
		Table<DameGameStateEventConstants.Piece> table = new Table<DameGameStateEventConstants.Piece>();
		this.dameGameState.setStartState(firstPlayer, table);
		return true;
	}
	
	public DameGameState getDameGameState()
	{
		return this.dameGameState;
	}
}
