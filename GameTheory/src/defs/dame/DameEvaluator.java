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
    int[][] evaluatorTable = new int[DameConstants.SQUARESPERSIDE][DameConstants.SQUARESPERSIDE];

    // Constructors
    public DameEvaluator(Piece ownPiece, Piece opponentPiece) {
        this.ownPiece = ownPiece;
        this.opponentPiece = opponentPiece;
        createEvaluatorTable();
        
    }

    // Methods
    @Override
    public int evaluate(GameState gameState) {
        DameTable table = ((DameGameState) gameState).getDameTable();
        int result = 0;
        if (gameState.isTerminal()) {
            if (((DamePlayer) gameState.getPlayerToMove()).getPlayersPiece() == this.opponentPiece) {
                result = 100;
            } else {
                result = -100;
            }
        } 
        else {
            int factor = 5;
            result += factor*countPieces(table, ownPiece);
            result += (-factor)*countPieces(table, opponentPiece);
//            if(((DamePlayer)gameState.getPlayerToMove()).playersPiece == ownPiece){
//               result += gameState.getAllMoves().size()/10; 
//            }
//            else{
//                result -= gameState.getAllMoves().size()/10;
//            }
            result += 0.5*(this.evaluatePositions(table, ownPiece)-this.evaluatePositions(table, opponentPiece));
        }
        return result;
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
    private int evaluatePositions(DameTable table, Piece pieceToEvaluate){
        int value = 0;
        List<GenericRow<Piece>> rows = table.getRows();
        for (GenericRow<Piece> row : rows) {
            List<GenericCell<Piece>> cells = row.getCells();
            for (GenericCell<Piece> cell : cells) {
                Piece cellValue = cell.getCellValue();
                if (pieceToEvaluate == cellValue) {
                    value += evaluatorTable[cell.getRowIndex()][cell.getColumnIndex()];
                }
                if (cellValue != pieceToEvaluate && cellValue != Piece.EMPTY){
                    value -= evaluatorTable[cell.getRowIndex()][cell.getColumnIndex()];
                }
            }
        }
        return value;
    }
    private void createEvaluatorTable(){
        //4 3 2 3 4
        //3 2 1 2 3
        //2 1 1 1 2
        //2 1 1 1 2
        //4 2 1 2 4
        int[] rowA = {5,4,4,4,5};
        int[] rowB = {4,2,1,2,4};
        int[] rowC = {4,1,1,1,4};
        int[] rowD = {4,2,1,2,4};
        int[] rowE = {5,3,3,3,5};
        
    
        if(ownPiece == Piece.BLACK){
            this.evaluatorTable[0] = rowA;
            this.evaluatorTable[1] = rowB;
            this.evaluatorTable[2] = rowC;
            this.evaluatorTable[3] = rowD;
            this.evaluatorTable[4] = rowE;
        }
        else{
            this.evaluatorTable[0] = rowE;
            this.evaluatorTable[1] = rowD;
            this.evaluatorTable[2] = rowC;
            this.evaluatorTable[3] = rowB;
            this.evaluatorTable[4] = rowA;
        }    
        
    }
}
