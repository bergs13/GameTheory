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
	Piece oponentPiece;

	// Constructors
	public DameEvaluator(Piece ownPiece, Piece oponentPiece) {
		this.ownPiece = ownPiece;
		this.oponentPiece = oponentPiece;
	}

	// Methods
	@Override
	public int evaluate(GameState gameState) {
		DameTable table = ((DameGameState) gameState).getGameTable();
		int ownPieceCount = countPieces(table, ownPiece);
		int oponentPieceCount = countPieces(table, oponentPiece);
		return ownPieceCount - oponentPieceCount;
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
