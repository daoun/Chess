package chess;

import java.util.ArrayList;
import java.util.List;
/**
 * Queen class implements the rules of the queen piece. 
 * The main purpose for this class is to get all of the possible available positions that the queen piece can move to. 
 * @author Capki Kim, Daoun Oh
 *
 */
public class Queen extends Piece {

	/**
	 * Default constructor. 
	 * @param type 	indicates what type of piece it is
	 * @param team	indicates if team is black or white
	 * @param rank	indicates the vertical position of a piece 
	 * @param file	indicates the horizontal position of a piece 
	 */
	public Queen(String type, String team, int rank, int file){
		super(type,team,rank,file);
	}
	
	/**
	 * Finds the possible movable positions of a queen.
	 * @return	list of positions that a queen can move to 
	 */
	public List<Position> availablePositions() {
		
		List<Position> posList = new ArrayList<Position>();
		
		posList = this.findDiagonal();
		posList.addAll(this.findStraight());
		
		return posList;
		
	}
}
