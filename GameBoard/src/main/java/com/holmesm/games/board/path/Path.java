package com.holmesm.games.board.path;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.holmesm.games.board.Square;
import com.holmesm.games.movement.MovementPoints;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
/**
 * A path describes a destination and the possible costs to get there. Also keeps track of the squares that will be passed through on the way there. 
 * @author Balerion
 *
 */
public class Path {

	private Set<Square> destinations;
	private List<Square> inOrderDestinations;
	private Square startingPoint;
	private Square endingPoint;
	private List<MovementPoints> costs;
	
	
	public Path(Square startingLocation) {
		costs = Lists.newArrayList();
		inOrderDestinations = Lists.newLinkedList();
		inOrderDestinations.add(startingLocation);
		destinations = Sets.newHashSet(startingLocation);
		this.startingPoint = startingLocation;
		this.endingPoint = startingLocation;
	}
	public Path(Path startingPath) {
		destinations = Sets.newHashSet(startingPath.getDestinations());
		inOrderDestinations = Lists.newLinkedList(startingPath.getInOrderDestinations());
		costs = Lists.newArrayList();
		this.startingPoint = startingPath.getStartingPoint();
		this.endingPoint = startingPath.getEndingPoint();
	}
	
	public Iterator<Square> getInOrderSquares(){
		return inOrderDestinations.iterator();
	}
	
	public Square getDestination() {
		return endingPoint;
	}
	
	public void addDestination(Square square) {
		destinations.add(square);
		inOrderDestinations.add(square);
		this.endingPoint =square;
	}
	
	public void addRoute(MovementPoints cost) {
		costs.add(cost);
	}
	
	//Take the current movement paths, determine the new 
	public Collection<Path> getPathsToNextSquares() {
		Collection<Path> newPaths = Lists.newArrayList();
		for(Path path:endingPoint.getAdjacentSpaces()) {
			if(destinations.contains(path.getEndingPoint())) {
				continue;
			}
			Path newPath = new Path(this);
			newPath.addDestination(path.getEndingPoint());
			if(costs.isEmpty()) {
				for(MovementPoints otherCost:path.getCosts()) {
					newPath.addRoute(otherCost);
				}
			}
			else {
				for(MovementPoints currentCost:costs){
					for(MovementPoints otherCost:path.getCosts()) {
						newPath.addRoute(currentCost.combine(otherCost));
					}
				}
			}
			newPaths.add(newPath);
		}
		return newPaths;
		
	}
	
}
