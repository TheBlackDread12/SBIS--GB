package com.holmesm.games.abilities;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.holmesm.games.abilities.effects.Effect;
import com.holmesm.games.board.Square;
import com.holmesm.games.board.path.Path;
import com.holmesm.games.board.path.PathBuilder;
import com.holmesm.games.movement.MovementPoints;
import com.holmesm.games.pieces.GamePiece;

public class Move extends Ability{

	private static String NAME = "Movement";
	private static List<Effect> EFFECTS = ImmutableList.<Effect>builder().add(new com.holmesm.games.abilities.effects.Move()).build();
	
	private MovementPoints points;
	
	public Move(MovementPoints points) {
		super(NAME, EFFECTS);
		this.points = points;
	}


	public List<Path> getPossiblePaths(GamePiece user){
		return PathBuilder.buildPossiblePaths(points, user.getGameSpace());
	}
	
	public List<GamePiece> getPossibleTargets(Square targetSquare){
		return Lists.newArrayList();
	}
	
}
