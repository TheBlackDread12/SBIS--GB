package com.holmesm.games.abilities.effects;

import java.util.Iterator;
import java.util.List;

import com.holmesm.games.board.Square;
import com.holmesm.games.board.path.Path;
import com.holmesm.games.pieces.GamePiece;
import com.holmesm.games.pieces.MovementReactor;

public class Move extends Effect {

	public Move() {
		super("");
	}
	
	public void affect(EffectContext context) {
		
		Path path = context.getPath();
		if(path == null ) {
			throw new RuntimeException("Path is necessary for this effect");
			
		}
		GamePiece user = context.getUser();
		if(user==null) {
			throw new RuntimeException("User is necessary for this effect");
		}
		
		Iterator<Square> squares = context.getPath().getInOrderSquares();
		Square finalSpace = path.getDestination();
		while(squares.hasNext()) {
			Square next = squares.next();
			List<GamePiece> pieces = next.getContent();
			boolean frozen = false;
			for(GamePiece piece:pieces) {
				if(piece instanceof MovementReactor) {
					frozen = ((MovementReactor)piece).react(user, squares.hasNext()) || frozen;
				}
			}
			if(frozen) {
				finalSpace = next;
				break;
			}
			
		}
		user.getGameSpace().removeContent(user);
		user.setGameSpace(finalSpace);
		finalSpace.addContent(user);
	}

}
