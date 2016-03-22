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
	 * Also identifies when there is an event of castling.
	 * 
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
		if(this.getTeam().equals("white")){
			if(this.getFirstMoveMade() == 0 && this.getCastlingMoveMade() == 0){
				
					if(Board.isEmpty(rank, file+1) && Board.isEmpty(rank, file+2) && (!Board.isEmpty(rank, file+3) && Board.board[rank][file+3].getType().equals("rook") && Board.board[rank][file+3].getFirstMoveMade() == 0) && Board.board[rank][file+3].getTeam().equals(this.getTeam())){
						this.addPosition(rank, file+2 , posList);
					} 
					
					if(Board.isEmpty(rank, file-1) && Board.isEmpty(rank, file-2) && Board.isEmpty(rank, file-3) && (!Board.isEmpty(rank, file-4) && Board.board[rank][file-4].getType().equals("rook") && Board.board[rank][file-4].getFirstMoveMade() == 0) && Board.board[rank][file-4].getTeam().equals(this.getTeam())){
						this.addPosition(rank, file-2, posList);
					}
			}
		}else{
			if(this.getFirstMoveMade() == 0 && this.getCastlingMoveMade() == 0){
				if(Board.isEmpty(rank, file-1) && Board.isEmpty(rank, file-2) && Board.isEmpty(rank, file-3) && (!Board.isEmpty(rank, file-4) && Board.board[rank][file-4].getType().equals("rook") && Board.board[rank][file-4].getFirstMoveMade() == 0) && Board.board[rank][file-4].getTeam().equals(this.getTeam())){
					this.addPosition(rank, file-2 , posList);
				}
				
				if(Board.isEmpty(rank, file+1) && Board.isEmpty(rank, file+2) && (!Board.isEmpty(rank, file+3) && Board.board[rank][file+3].getType().equals("rook") && Board.board[rank][file+3].getFirstMoveMade() == 0) && Board.board[rank][file+3].getTeam().equals(this.getTeam())){
					this.addPosition(rank, file+2, posList);
				}
			}
		}
		
		return posList;
	}

}
