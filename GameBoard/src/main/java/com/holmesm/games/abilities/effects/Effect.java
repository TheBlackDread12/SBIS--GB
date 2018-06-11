package com.holmesm.games.abilities.effects;

public abstract class Effect {

	private String name;
	
	public Effect(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public abstract void affect(EffectContext context);
	
}
