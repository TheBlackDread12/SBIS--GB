package com.holmesm.games.movement;

import java.util.Map;
import java.util.Map.Entry;

import com.google.common.collect.Maps;

import lombok.Getter;

@Getter
public class MovementPoints{
	private Map<MovementType,Double> typeToCost;
	
	public MovementPoints() {
		typeToCost = Maps.newHashMap();
	}
	
	public void setMovementTypeCost(MovementType type, double cost) {
		typeToCost.put(type, cost);
	}
	
	public void addMovementTypeCost(MovementType type, double cost) {
		Double existingValue  = typeToCost.get(type);
		if(existingValue==null) {
			existingValue = 0.0;
		}
		typeToCost.put(type, existingValue+cost);
	}
	
	public boolean canPayWith(MovementPoints toPayWith) {
		Map<MovementType,Double> otherTypeToCost = toPayWith.getTypeToCost();
		if(otherTypeToCost.size() < typeToCost.size()) {
			return false;
		}
		for(Entry<MovementType,Double> cost:typeToCost.entrySet()) {
			Double pointsToPay = otherTypeToCost.get(cost.getKey());
			if(pointsToPay==null) {
				return false;
			}
			if(pointsToPay< cost.getValue()) {
				return false;
			}
		}
		return true;
	}
	
	public MovementPoints deepCopy() {
		MovementPoints copy = new MovementPoints();
		for(Entry<MovementType,Double> entry:typeToCost.entrySet()) {
			copy.setMovementTypeCost(entry.getKey(), entry.getValue());
		}
		return copy;
	}
	
	public MovementPoints combine(MovementPoints combineWith) {
		MovementPoints copy = combineWith.deepCopy();
		for(Entry<MovementType,Double> entry:typeToCost.entrySet()) {
			copy.addMovementTypeCost(entry.getKey(), entry.getValue());
		}
		return copy;
	}
}



