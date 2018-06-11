package com.holmesm.games.actions;

import java.util.List;

import com.holmesm.games.abilities.Ability;
import com.holmesm.games.abilities.effects.EffectContext;
import com.holmesm.games.board.path.Path;
import com.holmesm.games.pieces.GamePiece;

public class ActionBuilder {

	
	private ActionBuilderListener listener;
	
	private Ability ability;
	private List<Path> possiblePaths;
	private List<GamePiece> possibleTargets;
	private EffectContext setContext;
	private boolean complete;
	
	
	
	public ActionBuilder(Ability ability, GamePiece user, ActionBuilderListener listener) {
		complete = false;
		this.ability = ability;
		setContext = new EffectContext();
		setContext.setUser(user);
		
		this.possiblePaths = ability.getPossiblePaths(user);
		this.listener = listener;
		listener.selectPath(possiblePaths, this);
	}
	
	public void pathSelected(Path path) {
		if(!possiblePaths.contains(path)) {
			throw new RuntimeException("Cannot select a path outside the possibilities");
		}
		setContext.setPath(path);
		possibleTargets = ability.getPossibleTargets(path!=null?path.getDestination():null);
		listener.selectGamePeice(possibleTargets, this);
		
	}
	
	public void targetsSelected(List<GamePiece> selectedTargets) {
		for(GamePiece target:selectedTargets) {
			if(!possibleTargets.contains(target)) {
				throw new RuntimeException("Cannot select targets outside the possibilities");
			}
		}
		setContext.setTargets(selectedTargets);
		complete = true;
		listener.actionComplete(getAction());
	}
	
	public Action getAction() {
		if(!complete) {
			throw new RuntimeException("Have not yet finished building the action");
		}
		return new Action(ability,setContext);
	}
	
	
}
