package com.holmesm.games.board;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.Set;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.holmesm.games.board.location.CartesianLocation;
import com.holmesm.games.board.location.Location;
import com.holmesm.games.board.path.Path;
import com.holmesm.games.movement.MovementPoints;
import com.holmesm.games.movement.MovementType;

public class Board {

	private Map<Location,Square> gameBoard;
	private Map<Square,Location> reverseBoard;
	
	public Board() {
		gameBoard = Maps.newHashMap();
		reverseBoard = Maps.newHashMap();
	}
	
	/**
	 * Does not form any connections to other squares, simply makes it available at a given location
	 * @param square
	 * @param location
	 */
	public  void putSquare(Square square,Location location) {
		gameBoard.put(location, square);
		reverseBoard.put(square, location);
	}
	
	
	public Set<Entry<Location,Square>> getAllSquares(){
		return gameBoard.entrySet();
	}
	
	public Square getLocation(Location location) {
		Square s = gameBoard.get(location);
		if(s==null) {
			throw new RuntimeException("Location "+location+" does not exist");
		}
		return s;
	}
	
	public Location getLocationForSquare(Square square) {
		Location loc = reverseBoard.get(square);
		if(loc==null) {
			throw new RuntimeException("Square "+square+" does not exist");
		}
		return loc;
	}
	protected Square getNullableLocation(Location location) {
		return gameBoard.get(location);
	}
	
	
	public static Board createHexBoard(int height,int width) {
		Board b = new Board();
		MovementPoints points = new MovementPoints();
		points.addMovementTypeCost(MovementType.GENERIC, 1.0);
		for(int i=0;i<width;i++) {
			for(int j=0;j<height;j++) {
				CartesianLocation location = new CartesianLocation(i,j);
				Square square = new Square();

				b.putSquare(square, location);

				List<CartesianLocation> connected = buildHexConnections(location,height,width);
				for(Location l:connected) {
					Square s = b.getNullableLocation(l);
					if(s!=null) {
						createPath(points, square, s);
						createPath(points,s,square);
					}
				}
			}
		}
		return b;
	}

	private static void createPath(MovementPoints points, Square fromSquare, Square toSquare) {
		Path toPath = new Path(fromSquare);
		toPath.addRoute(points.deepCopy());
		toPath.addDestination(toSquare);
		fromSquare.addPathFromSpace(toPath);
	}
	
	private static List<CartesianLocation> buildHexConnections(CartesianLocation location,int height,int width){
		int x = location.getX();
		int y = location.getY();
		List<CartesianLocation> returnList = Lists.newArrayList();
		
		returnList.add(new CartesianLocation(x,y-1));
		returnList.add(new CartesianLocation(x,y+1));
		returnList.add(new CartesianLocation(x+1,y));
		returnList.add(new CartesianLocation(x-1,y));
		if(x%2==1) {
			returnList.add(new CartesianLocation(x-1,y+1));
			returnList.add(new CartesianLocation(x+1,y+1));
		}
		else {
			returnList.add(new CartesianLocation(x+1,y-1));
			returnList.add(new CartesianLocation(x-1,y-1));
		}

		return returnList.stream()
				.filter(loc-> loc.getX()>=0 && loc.getX()<width && loc.getY()>=0 && loc.getY()<height)
				.collect(Collectors.toList());
		
		
		/*
		 * 
		 * Even: 0,-1 0,+1  +1,0 -1,+1 +1,+1 -1,0
		 * Odd: 0,-1  0,+1  +1,0 +1,-1 -1,-1 -1,0
		 * 
		 */
	}
}
