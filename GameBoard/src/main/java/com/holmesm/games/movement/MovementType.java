package com.holmesm.games.movement;

public enum MovementType {
	VERTICAL,HORIZONTAL,GENERIC;
	
	public boolean compatible(MovementType otherType) {
		return this.equals(otherType) || GENERIC.equals(otherType);
	}
}
