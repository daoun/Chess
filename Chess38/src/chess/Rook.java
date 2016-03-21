package chess;

import java.util.List;

/**
 * Rook class implements the rules of the rook piece. 
 * The main purpose for this class is to get all of the possible available positions that the rook piece can move to. 
 * @author Capki Kim, Daoun Oh
 *
 */
public class Rook extends Piece {
	
	/**
	 * Default constructor. 
	 * @param type 	indicates what type of piece it is
	 * @param team	indicates if team is black or white
	 * @param rank	indicates the vertical position of a piece 
	 * @param file	indicates the horizontal position of a piece 
	 */
	public Rook(String type, String team, int rank, int file){
		super(type,team,rank,file);
	}
	
	/**
	 * Finds the possible movable positions of a rook.
	 * @return	list of positions that a rook can move to 
	 */
	public List<Position> availablePositions() {
		
		return this.findStraight();
	}

}
