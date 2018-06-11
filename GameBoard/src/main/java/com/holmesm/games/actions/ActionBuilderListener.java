package com.holmesm.games.actions;

import java.util.List;

import com.holmesm.games.board.path.Path;
import com.holmesm.games.pieces.GamePiece;

public interface ActionBuilderListener {
	
	public void selectGamePeice(List<GamePiece> possiblePieces, ActionBuilder callBack);
	public void selectPath(List<Path> possiblePaths, ActionBuilder callBack);
	public void actionComplete(Action action);
}
