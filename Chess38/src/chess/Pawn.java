package chess;

import java.util.ArrayList;

/**
 * Pawn class implements the rules of the pawn piece. 
 * The main purpose for this class is to get all of the possible available positions that the pawn piece can move to. 
 * @author Capki Kim, Daoun Oh
 *
 */
public class Pawn extends Piece {
	
	/**
	 * Default constructor. 
	 * @param type 	indicates what type of piece it is
	 * @param team	indicates if team is black or white
	 * @param rank	indicates the vertical position of a piece 
	 * @param file	indicates the horizontal position of a piece 
	 */
	public Pawn(String type, String team, int rank, int file){
		super(type,team,rank,file);
	}
	
	/**
	 * Finds the possible movable positions of a pawn.
	 * @return	list of positions that a pawn can move to 
	 */
	public ArrayList<Position> availablePositions() {
		
		//Initialize list
		ArrayList<Position> posList = new ArrayList<Position>();
		int rank = this.getRank();
		int file = this.getFile();
		String team = this.getTeam();
		
		//White Pawn
		if(team.equals("white") && Chess.turn == 1){
			if(this.getFirstMoveMade() == 0){ //Check for pawn that hasn't made a move yet
				if(Board.isEmpty(rank-1,file)){
					posList.add(new Position(rank-1,file));
					if(Board.isEmpty(rank-2, file)){ //Pawn can move forward 2 positions if vacant
						posList.add(new Position(rank-2,file));
					}
				}
				//this.setFirstMoveMade(1);
				return posList;
			}else{
				
				if(Board.isEmpty(rank-1,file)){
					posList.add(new Position(rank-1,file));
				}
				
				if(file == 0){ // if pawn is on the left edge
					if(!Board.isEmpty(rank-1, file+1) && Board.board[rank-1][file+1].getTeam().equals("black")){
						posList.add(new Position(rank-1,file+1));
						return posList;
					}
				}else if(file == 7){ //if pawn is on the right edge
					if(!Board.isEmpty(rank-1, file-1) && Board.board[rank-1][file-1].getTeam().equals("black")){
						posList.add(new Position(rank-1,file-1));
						return posList;
					}
				}else if(!Board.isEmpty(rank-1, file-1) && Board.board[rank-1][file-1].getTeam().equals("black")){
						posList.add(new Position(rank-1,file-1));
						if(!Board.isEmpty(rank-1, file+1) && Board.board[rank-1][file+1].getTeam().equals("black")){
							posList.add(new Position(rank-1,file+1));
						}
						return posList;
				}else if(!Board.isEmpty(rank-1, file+1) && Board.board[rank-1][file+1].getTeam().equals("black")){
					posList.add(new Position(rank-1,file+1));
				}
				
			}
		}else if(team.equals("black") && Chess.turn == 0) { //Black Pawn
			if(this.getFirstMoveMade() == 0){ //Check for pawn that hasn't made a move yet
				if(Board.isEmpty(rank+1,file)){
					posList.add(new Position(rank+1,file));
					if(Board.isEmpty(rank+2, file)){ //Pawn can move forward 2 positions if vacant
						posList.add(new Position(rank+2,file));
					}
				}
				//this.setFirstMoveMade(1);
				return posList;
			}else {
				
				if(Board.isEmpty(rank+1,file)){
					posList.add(new Position(rank+1,file));
				}
				
				if(file == 0){ // if pawn is on the left edge
					if(!Board.isEmpty(rank+1, file+1) && Board.board[rank+1][file+1].getTeam().equals("white")){
						posList.add(new Position(rank+1,file+1));
						return posList;
					}
				}else if(file == 7){ //if pawn is on the right edge
					if(!Board.isEmpty(rank+1, file-1) && Board.board[rank+1][file-1].getTeam().equals("white")){
						posList.add(new Position(rank+1,file-1));
						return posList;
					}
				}else if(!Board.isEmpty(rank+1, file+1) && Board.board[rank+1][file+1].getTeam().equals("white")){
						posList.add(new Position(rank+1,file+1));
						if(!Board.isEmpty(rank+1, file-1) && Board.board[rank+1][file-1].getTeam().equals("white")){
							posList.add(new Position(rank+1,file-1));
						}
						return posList;
				}else if(!Board.isEmpty(rank+1, file-1) && Board.board[rank+1][file-1].getTeam().equals("white")){
					posList.add(new Position(rank+1,file-1));
				}
			}
		}		
		
		return posList;
	}

	
	

}