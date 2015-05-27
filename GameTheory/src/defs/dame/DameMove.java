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
        movement = (int[])move;
    }

    public void executeMove(GenericTable<Character> stateTable) {
        
        // Move the movement of the source cell to the target cell
        GenericCell<Character> sourceCell = null;
        GenericCell<Character> targetCell = null;
        List<GenericRow<Character>> rows =  stateTable.getRows();
        for (GenericRow<Character> row : rows) {
            if (rows.indexOf(row) == movement[0]) {
                List<GenericColumn<Character>> sourceRowColumns = row.getColumns();
                for (GenericColumn<Character> sourceRowColumn : sourceRowColumns) {
                    if (sourceRowColumns.indexOf(sourceRowColumn) == movement[1]) {
                        // source row and column combination identified
                        // find cell for column in source row
                        sourceCell = row.getCellByColumn(sourceRowColumn);
                    }
                }
            } else if (rows.indexOf(row) == movement[2]) {
                List<GenericColumn<Character>> targetRowColumns = row.getColumns();
                for (GenericColumn<Character> targetRowColumn : targetRowColumns) {
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

    public void capturePiece(GenericCell<Piece> cell) {
        this.pieceToCapture = cell;
    }
    public boolean capturePiece(){
        return (pieceToCapture != null);
    }
}
