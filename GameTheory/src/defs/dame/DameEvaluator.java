/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package defs.dame;

import defs.dame.DameGameStateEventConstants.Piece;
import defs.general.Cell;
import defs.general.Evaluator;
import defs.general.GameState;
import defs.general.Row;
import defs.general.Table;
import java.util.List;

/**
 *
 * @author Thunderchild
 */
public class DameEvaluator extends Evaluator {

    private Table<Piece> table = null;

    @Override
    public int evaluate(GameState gameState) {
        table = ((DameGameState) gameState).getGameTable();
        int result = 0;
        result = this.getOwnPieces() - this.getOpponentsPieces();
        return result;
    }

    private int getOwnPieces() {
        List<Row<Piece>> rows = table.getRows();
        for (Row<Piece> row : rows) {
            List<Cell<Piece>> cells = row.getCells();
            for (Cell<Piece> cell : cells) {
                cell.getCellValue();
            }
        }
        return 0;
    }

    private int getOpponentsPieces() {
        List<Row<Piece>> rows = table.getRows();
        for (Row<Piece> row : rows) {
            List<Cell<Piece>> cells = row.getCells();
            for (Cell<Piece> cell : cells) {
                cell.getCellValue();
            }
        }
        return 0;
    }
}
