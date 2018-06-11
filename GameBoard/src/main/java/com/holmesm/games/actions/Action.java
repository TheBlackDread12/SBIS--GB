package com.holmesm.games.actions;

import com.holmesm.games.abilities.Ability;
import com.holmesm.games.abilities.effects.Effect;
import com.holmesm.games.abilities.effects.EffectContext;

public class Action {

	private Ability ability;
	private EffectContext context;
	
	public Action(Ability ability,EffectContext context) {
		this.ability = ability;
		this.context = context;
	}

	public void enact() {
		for(Effect effect: ability.getEffects()) {
			effect.affect(context);
		}
	}
	
}
