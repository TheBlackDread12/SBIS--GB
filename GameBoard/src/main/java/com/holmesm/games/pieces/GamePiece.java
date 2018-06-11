package com.holmesm.games.pieces;

import java.util.List;
import java.util.Map;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.holmesm.games.abilities.Ability;
import com.holmesm.games.board.Square;
import com.holmesm.games.pieces.attributes.Attribute;

import lombok.NonNull;

public class GamePiece {
	private Square	currentGameSpace;
	private List<Ability> abilities;
	private Map<String,Attribute> attributes;
	
	public GamePiece() {
		abilities = Lists.newArrayList();
		attributes = Maps.newHashMap();
	}
	
	public void addAbility(Ability ability) {
		abilities.add(ability);
	}
	
	public List<Ability> getAbilities(){
		return ImmutableList.copyOf(abilities);
	}
	
	
	public void setGameSpace(Square square){
		this.currentGameSpace = square;
	}
	
	public Square getGameSpace(){
		return currentGameSpace;
	}
	
	public void removeFromGame() {
		currentGameSpace.removeContent(this);
		currentGameSpace = null;
	}
	
	
	public void addAttribute(@NonNull Attribute attribute) {
		
		attributes.put(attribute.getClass().getCanonicalName(), attribute);
	}
	
	public Attribute getAttribute(Class clazz) {
		return attributes.get(clazz.getCanonicalName());
	}
}
