package defs.dame;

import defs.general.GenericCell;
import defs.dame.DameConstants.Piece;
import defs.general.Move;

/**
 *
 * @author Thunderchild
 */
public class DameMove extends Move {

	private GenericCell<Piece> pieceToCapture = null;

	public DameMove(Object move) {
		super(move);
	}
        public DameMove(Object move, GenericCell<Piece> toCapture){
            super(move);
            this.pieceToCapture = toCapture; 
        }
	@Override
	public void executeMove(Object state) {
		DameTable dameTable = (DameTable) state;
		int[] movement = (int[]) getMove();
		dameTable.moveValue(movement[0], movement[1], movement[2], movement[3],
				Piece.EMPTY);
	}

	public void capturePiece(GenericCell<Piece> cell) {
		this.pieceToCapture = cell;
	}

	public boolean canCapture() {
		return (pieceToCapture != null);
	}
        public int[] getMovement(){
            return (int[]) getMove();
        }
}
