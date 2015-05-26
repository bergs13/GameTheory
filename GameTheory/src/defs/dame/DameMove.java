/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package defs.dame;

import defs.general.Cell;
import defs.general.Column;
import defs.dame.DameGameStateEventConstants.Piece;
import defs.general.Move;
import defs.general.Row;
import defs.general.Table;

import java.util.List;

/**
 *
 * @author Thunderchild
 */
public class DameMove extends Move {

    int[] movement;
    private Cell<Piece> pieceToCapture = null;

    public DameMove(Object move) {
        super(move);
        movement = (int[])move;
    }

    public void executeMove(Table<Character> stateTable) {
        
        // Move the movement of the source cell to the target cell
        Cell<Character> sourceCell = null;
        Cell<Character> targetCell = null;
        List<Row<Character>> rows =  stateTable.getRows();
        for (Row<Character> row : rows) {
            if (rows.indexOf(row) == movement[0]) {
                List<Column<Character>> sourceRowColumns = row.getColumns();
                for (Column<Character> sourceRowColumn : sourceRowColumns) {
                    if (sourceRowColumns.indexOf(sourceRowColumn) == movement[1]) {
                        // source row and column combination identified
                        // find cell for column in source row
                        sourceCell = row.getCellByColumn(sourceRowColumn);
                    }
                }
            } else if (rows.indexOf(row) == movement[2]) {
                List<Column<Character>> targetRowColumns = row.getColumns();
                for (Column<Character> targetRowColumn : targetRowColumns) {
                    if (targetRowColumns.indexOf(targetRowColumn) == movement[3]) {
                        // target row and column combination identified
                        // find cell
                        targetCell = row.getCellByColumn(targetRowColumn);
                    }
                }
            }
        }

        // Only if both cells found
        if (null != sourceCell && null != targetCell) {
            targetCell.setCellValue(sourceCell.getCellValue());
            sourceCell.setCellValue(' ');
        }
    }

    public void capturePiece(Cell<Piece> cell) {
        this.pieceToCapture = cell;
    }
    public boolean capturePiece(){
        return (pieceToCapture != null);
    }
}
