package chess;

import java.util.ArrayList;
import java.util.List;

public abstract class Piece {
	
	private String type;
	private String team;
	private int rank;
	private int file;
	private int firstMoveMade = 0;

	
	public Piece(String type, String team, int rank, int file){
		this.type = type;
		this.team = team;
		this.rank = rank;
		this.file = file;
	}
	
	// Abstract function for subclasses to implement to find the available positions of pieces
	public abstract List<Position> availablePositions(); 
	
	//Adds the position if it is possible
	public int addPosition(int x, int y, List<Position> posList){
		//Add empty position
		if(Board.isEmpty(x,y)){
			posList.add(new Position(x,y));
			return 1;
		}else if(!(Board.board[x][y].getTeam().equals(this.getTeam()))){ 
			//Add position that contains opponent's piece
			posList.add(new Position(x,y));
			return 0;
		}else{ //Break if position contains ally piece
			return 0;
		}
	}
	
	//Sees if the piece can move to a target location
	public boolean canMove(int rank, int file){
		List<Position> availablePos = this.availablePositions();
		
		boolean canMove = false;
		
		//Check to see if target position is valid
		for(Position p : availablePos){
			if(p.getRank() == rank && p.getFile() == file){
				canMove = true;
			}
		}
		
		//if the piece cannot move to target location
		if(canMove == false){
			return false;
		}
		
		//save the piece temporarily
		Piece tempPiece = null;
		int fromRank = this.getRank();
		int fromFile = this.getFile();
		if(!Board.isEmpty(rank, file)){
			tempPiece = Board.board[rank][file];
		}
		
		//Move the piece
		Board.board[rank][file] = Board.board[fromRank][fromFile];
		Board.board[rank][file].setRank(rank);
		Board.board[rank][file].setFile(file);
		Board.board[fromRank][fromFile] = null;
		
		String team = this.getTeam();
		
		//See if any of the opponent pieces can get to the King
		if(check(team)){
			canMove = false;
		}
		
		//if moving the piece results in the opponent being in check
		if(team.equals("white")){
			if(check("black")){
				Chess.bCheck = 1;
			}
		}else{
			if(check("white")){
				Chess.wCheck = 1;
			}
		}
		
		//Move the piece(s) back
		Board.board[fromRank][fromFile] = Board.board[rank][file];
		Board.board[fromRank][fromFile].setRank(fromRank);
		Board.board[fromRank][fromFile].setFile(fromFile);
		Board.board[rank][file] = null;
		
		if(tempPiece != null){
			Board.board[rank][file] = tempPiece;
		}
		
		return canMove;
	}
	
	//See if the King is in check
	public boolean check(String team){
		
		List<Position> positions = new ArrayList<Position>();
		
		int kingRank = 0;
		int kingFile = 0;
		
		if(team.equals("white")){
			
			//Find coordinate of white King
			for(int i = 0 ; i < Chess.wPlayer.pieces.length ; i++){
				if(Chess.wPlayer.pieces[i] != null){
					if(Chess.wPlayer.pieces[i].getType().equals("king")){
						kingRank = Chess.wPlayer.pieces[i].getRank();
						kingFile = Chess.wPlayer.pieces[i].getFile();
						break;
					}
				}
			}
			
			for(int i = 0; i < Chess.bPlayer.pieces.length ; i++){ //Check all the black pieces
				if(Chess.bPlayer.pieces[i] != null){
 					positions = Chess.bPlayer.pieces[i].availablePositions();
					for(Position p : positions){
						if(p.getRank() == kingRank && p.getFile() == kingFile){
							return true;
						}
					}
				}
			}
		}else{
			
			//Find coordinate of black King
			for(int i = 0 ; i < Chess.bPlayer.pieces.length ; i++){
				if(Chess.bPlayer.pieces[i] != null){
					if(Chess.bPlayer.pieces[i].getType().equals("king")){
						kingRank = Chess.bPlayer.pieces[i].getRank();
						kingFile = Chess.bPlayer.pieces[i].getFile();
					}
				}
			}
			
			for(int i = 0; i < Chess.wPlayer.pieces.length ; i++){ //Check all the white pieces
				if(Chess.wPlayer.pieces[i] != null){
					positions = Chess.wPlayer.pieces[i].availablePositions();
					for(Position p : positions){
						if(p.getRank() == kingRank && p.getFile() == kingFile){
							return true;
						}
					}
				}
			}
			
			
		}
		
		return false; //No opponent piece can get to the King even if the move is made
	}

	//Find diagonal available positions
	public List<Position> findDiagonal(){
		
		//Initialize list
		List<Position> posList = new ArrayList<Position>();
		
		int rank = this.getRank() - 1;
		int file = this.getFile() - 1;
		
		//Check upper-left diagonal
		while(rank >= 0 && file >= 0){
			
			//Add positions
			if(addPosition(rank,file,posList) == 0){
				break; // break out of loop if position contains an opponent or ally piece
			}
			
			rank--;
			file--;
		}
		
		//Initialize
		rank = this.getRank() + 1;
		file = this.getFile() - 1;
		
		//Check lower-left diagonal
		while(rank <= 7 && file >= 0){
			
			//Add positions
			if(addPosition(rank,file,posList) == 0){
				break; // break out of loop if position contains an opponent or ally piece
			}
			
			rank++;
			file--;
		}
		
		//Initialize
		rank = this.getRank() - 1;
		file = this.getFile() + 1;
				
		//Check lower-right diagonal
		while(rank >= 0 && file <= 7){
			
			//Add positions
			if(addPosition(rank,file,posList) == 0){
				break; // break out of loop if position contains an opponent or ally piece
			}
			
			rank--;
			file++;
		}				
		
		//Initialize
		rank = this.getRank() + 1;
		file = this.getFile() + 1;
		
		//Check lower-right diagonal
		while(rank <= 7 && file <= 7){
			
			//Add positions
			if(addPosition(rank,file,posList) == 0){
				break; // break out of loop if position contains an opponent or ally piece
			}
			
			rank++;
			file++;
		}		
		
		return posList;
	}
	
	public List<Position> findStraight(){
		
		//Initialize list
		List<Position> posList = new ArrayList<Position>();

		int rank = this.getRank();
		int file = this.getFile() - 1;

		//Check left side
		while(file >= 0){

			//Add positions
			if(addPosition(rank,file,posList) == 0){
				break; // break out of loop if position contains an opponent or ally piece
			}
			
			file--;
		}

		//Initialize again to original position
		rank = this.getRank();
		file = this.getFile() + 1 ;

		//Check right side
		while(file <= 7){

			//Add positions
			if(addPosition(rank,file,posList) == 0){
				break; // break out of loop if position contains an opponent or ally piece
			}
			
			file++;
		}

		//Initialize again to original position
		rank = this.getRank() - 1;
		file = this.getFile();

		//Check upper side
		while(rank >= 0){

			//Add positions
			if(addPosition(rank,file,posList) == 0){
				break; // break out of loop if position contains an opponent or ally piece
			}
			
			rank--;
		}				

		//Initialize again to original position
		rank = this.getRank() + 1;
		file = this.getFile();

		//Check lower diagonal
		while(rank <= 7){

			//Add positions
			if(addPosition(rank,file,posList) == 0){
				break; // break out of loop if position contains an opponent or ally piece
			}
			rank++;
		}		

		return posList;
	}	
	
	public boolean equals(Piece p){
		if(this.getType().equals(p.getType()) && this.getRank() == p.getRank() && this.getFile() == p.getFile()){
			return true;
		}else{
			return false;
		}
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public int getFile() {
		return file;
	}

	public void setFile(int file) {
		this.file = file;
	}
	
	public int getFirstMoveMade() {
		return firstMoveMade;
	}

	public void setFirstMoveMade(int firstMoveMade) {
		this.firstMoveMade = firstMoveMade;
	}
}
