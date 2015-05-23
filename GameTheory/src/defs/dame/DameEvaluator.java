/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package defs.dame;

import defs.general.Cell;
import defs.general.Column;
import defs.general.Evaluator;
import defs.general.GameState;
import defs.general.Row;
import defs.general.Table;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Thunderchild
 */
public class DameEvaluator extends Evaluator {

    private Table table = null;

    @Override
    public int evaluate(GameState gameState) {
        table = ((DameGameState) gameState).getGameTable();
        int result = 0;
        result = this.getOwnPieces() - this.getOpponentsPieces();
        return result;
    }

    private int getOwnPieces() {
        List<Row> rows = table.getRows();
        for (Row row : rows) {
            List<Cell> cells = row.getCells();
            for (Cell cell : cells) {
                cell.getCellValue();
            }
        }
        return 0;
    }

    private int getOpponentsPieces() {
        List<Row> rows = table.getRows();
        for (Row row : rows) {
            List<Cell> cells = row.getCells();
            for (Cell cell : cells) {
                cell.getCellValue();
            }
        }
        return 0;
    }
}
