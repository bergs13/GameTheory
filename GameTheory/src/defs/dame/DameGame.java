/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package defs.dame;

import defs.dame.DameConstants.Piece;
import defs.general.Game;
import defs.general.GenericColumn;
import defs.general.GenericRow;

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
		DameTable table = new DameTable();
		// Alle leer
		for (int i = 0; i < 5; i++) {
			GenericRow<Piece> row = new GenericRow<Piece>();
			for (int j = 0; j < 5; j++) {
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
		// S S S S S
		// S S S S S
		// S S . W W
		// W W W W W
		// W W W W W

		this.dameGameState.setStartState(firstPlayer, table);
		return true;
	}

	public DameGameState getDameGameState() {
		return this.dameGameState;
	}
}
