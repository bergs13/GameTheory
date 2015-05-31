/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package defs.dame;

import defs.general.GenericCell;
import defs.general.GenericColumn;
import defs.dame.DameConstants.Piece;
import defs.general.Move;
import defs.general.GenericRow;
import defs.general.GenericTable;

import java.util.List;

/**
 *
 * @author Thunderchild
 */
public class DameMove extends Move {

	int[] movement;
	private GenericCell<Piece> pieceToCapture = null;

	public DameMove(Object move) {
		super(move);
		movement = (int[]) move;
	}
	@Override
	public void executeMove(Object state) {
		DameTable dameTable = (DameTable) state;
		dameTable.moveValue(movement[0], movement[1], movement[2], movement[3],
				Piece.EMPTY);
	}

	public void capturePiece(GenericCell<Piece> cell) {
		this.pieceToCapture = cell;
	}

	public boolean capturePiece() {
		return (pieceToCapture != null);
	}
}
