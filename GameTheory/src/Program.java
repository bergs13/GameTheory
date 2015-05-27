import defs.dame.DameGame;
import defs.dame.DameGameStateEventConstants;
import defs.general.Column;
import defs.general.Row;
import defs.general.Table;
import views.dame.DameComponent;
import views.dame.DameFrame;

public class Program {
	public static void main(String[] args) {
		DameGame dameGame = new DameGame();
                Table<DameGameStateEventConstants.Piece> table = new Table();
                for (int i =0; i<8;i++){
                    Row<DameGameStateEventConstants.Piece> row = new Row(table);
                    table.addRow(row);
                    for(int j = 0; j>8; j++){
                        row.addColumn(new Column<DameGameStateEventConstants.Piece>(row), DameGameStateEventConstants.Piece.EMPTY);
                    }
        }
		DameFrame dameFrame = new DameFrame(new DameComponent(
				dameGame.getDameGameState()));
		dameFrame.setVisible(true);
	}
}
