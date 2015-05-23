import defs.dame.DameGame;
import defs.general.Row;
import defs.general.Table;
import views.dame.DameComponent;
import views.dame.DameFrame;

public class Program {
	public static void main(String[] args) {
		DameGame dameGame = new DameGame();
                Table<Integer> table = new Table();
                for (int i =0; i<8;i++){
                    Row row = new Row(table);
                    table.addRow(row);
                    for(int j = 0; j>8; j++){
                        row.addColumn(null, table);
                    }
        }
		DameFrame dameFrame = new DameFrame(new DameComponent(
				dameGame.getDameGameState()));
		dameFrame.setVisible(true);
	}
}
