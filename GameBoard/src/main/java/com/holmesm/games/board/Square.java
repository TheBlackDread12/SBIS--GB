package com.holmesm.games.board;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.holmesm.games.board.path.Path;
import com.holmesm.games.pieces.GamePiece;

public class Square {

	private List<GamePiece> occupiedBy;
	private Collection<Path> adjacent;
	
	
	public Square(){
		occupiedBy = Lists.newArrayList();
		adjacent = Sets.newHashSet();
	}
	public void addPathFromSpace(Path path){
		adjacent.add(path);
	}
	
	public Collection<Path> getAdjacentSpaces(){
		return adjacent;
	}
	
	public List<GamePiece> getContent(){
		return occupiedBy;
	}
	
	public void addContent(GamePiece gamePiece){
		occupiedBy.add(gamePiece);
		gamePiece.setGameSpace(this);
	}
	
	public boolean removeContent(GamePiece gamePiece) {
		gamePiece.setGameSpace(null);
		return occupiedBy.remove(gamePiece);
	}
	
	
}
