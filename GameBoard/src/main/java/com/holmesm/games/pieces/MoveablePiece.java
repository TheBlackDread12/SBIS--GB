package com.holmesm.games.pieces;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.holmesm.games.board.path.Path;
import com.holmesm.games.movement.MovementPoints;
import com.holmesm.games.movement.MovementType;

public class MoveablePiece {

	private MovementPoints movement;
	
	
	
	
	public boolean canTakePath(Path path){
		for(MovementPoints possibleCost:path.getCosts()) {
			if(possibleCost.canPayWith(movement)) {
				return true;
			}
		}
		return false;
	}
}



/*
so, have a couple of things to consider:
- There should be more than one way possible to pay the movement cost to reach a point
- you want to consider the entire path from one point to another, so you can have movement effects when entering points (for instance, getting mines to work)
- need to be able to find all the movement paths a piece can take in a turn
*/