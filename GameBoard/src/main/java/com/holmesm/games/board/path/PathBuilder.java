package com.holmesm.games.board.path;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.holmesm.games.board.Square;
import com.holmesm.games.movement.MovementPoints;

public class PathBuilder {

	public static List<Path> buildPossiblePaths(MovementPoints points, Square startingSpace){
		Path startingPath = new Path(startingSpace);
		List<Path> returnPaths = Lists.newArrayList();
		
		Set<Square> seenDestinations = Sets.newHashSet();
		
		Queue<Path> expandFrom = new LinkedList<Path>();
		expandFrom.add(startingPath);
		while(!expandFrom.isEmpty()) {
			Path path = expandFrom.remove();
			if(seenDestinations.contains(path.getDestination())) {
				continue;
			}
			Collection<Path> possiblePaths = path.getPathsToNextSquares();
			
			for(Path maybe:possiblePaths) {
				List<MovementPoints> cost = maybe.getCosts();
				for(MovementPoints aCost:cost) {
					if(aCost.canPayWith(points)) {
						expandFrom.add(maybe);
					}
				}
			}
			returnPaths.add(path);
			seenDestinations.add(path.getDestination());
		}
		
		return returnPaths;
	}
	
}
