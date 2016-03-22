package chess;

import java.util.ArrayList;
import java.util.List;
/**
 * King class implements the rules of the knight piece. 
 * The main purpose for this class is to get all of the possible available positions that the knight piece can move to. 
 * @author Capki Kim, Daoun Oh
 *
 */
public class Knight extends Piece {

	/**
	 * Default constructor. 
	 * @param type 	indicates what type of piece it is
	 * @param team	indicates if team is black or white
	 * @param rank	indicates the vertical position of a piece 
	 * @param file	indicates the horizontal position of a piece 
	 */
	public Knight(String type, String team, int rank, int file){
		super(type,team,rank,file);
	}
	
	/**
	 * Finds the possible movable positions of a knight.
	 * @return	list of positions that a knight can move to 
	 */
public List<Position> availablePositions() {
		
		List<Position> posList = new ArrayList<Position>();

		
		int rank = this.getRank();
		int file = this.getFile();
		
		if(!(rank - 1 < 0 || file - 2 < 0)){
			this.addPosition(rank - 1, file - 2, posList);
		}
		
		if(!(rank - 2 < 0 || file - 1 < 0)){
			this.addPosition(rank - 2, file - 1, posList);
		}
		
		if(!(rank - 2 < 0 || file + 1 > 7)){
			this.addPosition(rank - 2, file + 1, posList);
		}
		
		if(!(rank - 1 < 0 || file + 2 > 7)){
			this.addPosition(rank - 1, file + 2, posList);
		}
		
		if(!(rank + 1 > 7 || file + 2 > 7)){
			this.addPosition(rank + 1, file + 2, posList);
		}
		
		if(!(rank + 2 > 7 || file + 1 > 7)){
			this.addPosition(rank + 2, file + 1, posList);
		}
		
		if(!(rank + 2 > 7 || file - 1 < 0)){
			this.addPosition(rank + 2, file - 1, posList);
		}
		
		if(!(rank + 1 > 7 || file - 2 < 0)){
			this.addPosition(rank + 1, file - 2, posList);
		}
		
		return posList;
		
	}

}
