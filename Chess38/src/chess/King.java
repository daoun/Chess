package chess;

import java.util.ArrayList;
import java.util.List;
/**
 * King class implements the rules of the king piece. 
 * The main purpose for this class is to get all of the possible available positions that the king piece can move to. 
 * @author Capki Kim, Daoun Oh
 *
 */
public class King extends Piece{
	
	int madeFirstMove = 0;
	int castlingMoveMade = 0;

	/**
	 * Default constructor. 
	 * @param type 	indicates what type of piece it is
	 * @param team	indicates if team is black or white
	 * @param rank	indicates the vertical position of a piece 
	 * @param file	indicates the horizontal position of a piece 
	 */
	public King(String type, String team, int rank, int file){
		super(type,team,rank,file);
	}
	
	/**
	 * Finds the possible movable positions of a king.
	 * @return	list of positions that a king can move to 
	 */
	public List<Position> availablePositions() {
		
		List<Position> posList = new ArrayList<Position>();
		
		int rank = this.getRank();
		int file = this.getFile();
		
		if(!(rank - 1 < 0 || file - 1 < 0)){
			this.addPosition(rank - 1, file - 1, posList);
		}
		
		if(!(rank - 1 < 0)){
			this.addPosition(rank - 1, file, posList);
		}
		
		if(!(rank - 1 < 0 || file + 1 > 7)){
			this.addPosition(rank - 1, file + 1, posList);
		}
		
		if(!(file - 1 < 0)){
			this.addPosition(rank, file - 1, posList);
		}
		
		if(!(file + 1 > 7)){
			this.addPosition(rank, file + 1, posList);
		}
		
		if(!(rank + 1 > 7 || file - 1 < 0)){
			this.addPosition(rank + 1, file - 1, posList);
		}
		
		if(!(rank + 1 > 7)){
			this.addPosition(rank + 1, file, posList);
		}
		
		if(!(rank + 1 > 7 || file + 1 > 7)){
			this.addPosition(rank + 1, file + 1, posList);
		}
		
		//Castling
		
		return posList;
	}

}
