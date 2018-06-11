package com.holmesm.games.abilities;

import java.util.List;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.holmesm.games.abilities.effects.Effect;
import com.holmesm.games.board.Square;
import com.holmesm.games.board.path.Path;
import com.holmesm.games.pieces.GamePiece;

public abstract class Ability {

	private String name;
	private List<Effect> effects;
	
	
	public Ability(String name, List<Effect> effects) {
		this.name = name;
		this.effects = effects;
	}
	
	public String getName() {
		return name;
	}
	
	public List<Effect> getEffects(){
		return ImmutableList.copyOf(effects);
	}
	
	public List<Path> getPossiblePaths(GamePiece user){
		return Lists.newArrayList();
	}
	
	public List<GamePiece> getPossibleTargets(Square targetSquare){
		return Lists.newArrayList();
	}
	
}
