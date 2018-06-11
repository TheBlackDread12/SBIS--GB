package com.holmesm.games.pieces;

public interface MovementReactor {

	/**
	 * React to a piece moving through your square; returns true if the rest of movement should be halted
	 * @param movingPiece
	 * @param isTerminalSquare
	 * @return
	 */
	public boolean react(GamePiece movingPiece, boolean isTerminalSquare);
}
