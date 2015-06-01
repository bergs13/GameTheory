package interfaces;

import defs.dame.DameTable;

public interface UsableAsDameViewModel<T> {
	DameTable getDameTable();

	void setPlayerIsHuman(boolean firstPlayerIsHuman,
			boolean secondPlayerIsHuman);

	void movePiece(int sourceRowIndex, int sourceColumnIndex,
			int targetRowIndex, int targetColumnIndex);
}
