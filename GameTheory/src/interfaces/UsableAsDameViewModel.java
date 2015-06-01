package interfaces;

import defs.dame.DameTable;

public interface UsableAsDameViewModel<T> {
	DameTable getDameTable();

	void movePiece(int sourceRowIndex, int sourceColumnIndex,
			int targetRowIndex, int targetColumnIndex);
}
