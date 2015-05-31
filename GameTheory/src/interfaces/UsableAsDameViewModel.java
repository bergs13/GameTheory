package interfaces;

import defs.general.GenericTable;

public interface UsableAsDameViewModel<T> {
	GenericTable<T> getGameTable();
	void movePiece(int sourceRowIndex, int sourceColumnIndex, int targetRowIndex, int targetColumnIndex);
}
