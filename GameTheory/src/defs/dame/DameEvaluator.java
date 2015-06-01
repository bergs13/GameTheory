/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package defs.dame;

import defs.dame.DameConstants.Piece;
import defs.general.GenericCell;
import defs.general.Evaluator;
import defs.general.GameState;
import defs.general.GenericRow;

import java.util.List;

/**
 *
 * @author Thunderchild
 */
public class DameEvaluator extends Evaluator {
	// Members
	Piece ownPiece;
	Piece opponentPiece;

	// Constructors
	public DameEvaluator(Piece ownPiece, Piece opponentPiece) {
		this.ownPiece = ownPiece;
		this.opponentPiece = opponentPiece;
	}

	// Methods
	@Override
	public int evaluate(GameState gameState) {
		DameTable table = ((DameGameState) gameState).getDameTable();
		int ownPieceCount = countPieces(table, ownPiece);
		int opponentPieceCount = countPieces(table, opponentPiece);
		return ownPieceCount - opponentPieceCount;
	}

	private static int countPieces(DameTable table, Piece pieceToCount) {
		int pieceCount = 0;
		List<GenericRow<Piece>> rows = table.getRows();
		for (GenericRow<Piece> row : rows) {
			List<GenericCell<Piece>> cells = row.getCells();
			for (GenericCell<Piece> cell : cells) {
				Piece cellValue = cell.getCellValue();
				if (pieceToCount == cellValue) {
					pieceCount++;
				}
			}
		}
		return pieceCount;
	}
}
