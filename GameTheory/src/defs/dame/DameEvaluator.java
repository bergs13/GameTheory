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
import defs.general.GenericTable;
import java.util.List;

/**
 *
 * @author Thunderchild
 */
public class DameEvaluator extends Evaluator {

    private GenericTable<Piece> table = null;

    @Override
    public int evaluate(GameState gameState) {
        table = ((DameGameState) gameState).getGameTable();
        int result = 0;
        result = this.getOwnPieces() - this.getOpponentsPieces();
        return result;
    }

    private int getOwnPieces() {
        List<GenericRow<Piece>> rows = table.getRows();
        for (GenericRow<Piece> row : rows) {
            List<GenericCell<Piece>> cells = row.getCells();
            for (GenericCell<Piece> cell : cells) {
                cell.getCellValue();
            }
        }
        return 0;
    }

    private int getOpponentsPieces() {
        List<GenericRow<Piece>> rows = table.getRows();
        for (GenericRow<Piece> row : rows) {
            List<GenericCell<Piece>> cells = row.getCells();
            for (GenericCell<Piece> cell : cells) {
                cell.getCellValue();
            }
        }
        return 0;
    }
}
