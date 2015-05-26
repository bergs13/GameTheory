import defs.dame.DameGame;
import defs.general.GenericRow;
import defs.general.GenericTable;
import views.dame.DameComponent;
import views.dame.DameFrame;

public class Program {
	public static void main(String[] args) {
		DameGame dameGame = new DameGame();
                GenericTable<Integer> table = new GenericTable<Integer>();
                for (int i =0; i<8;i++){
                    GenericRow<Integer> row = new GenericRow<Integer>(table);
                    table.addRow(row);
                    for(int j = 0; j>8; j++){
                        //row.addColumn(null, table);
                    }
        }
		DameFrame dameFrame = new DameFrame(new DameComponent(
				dameGame.getDameGameState()));
		dameFrame.setVisible(true);
	}
}
