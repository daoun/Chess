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
		
		//Check to see if target position is valid
		for(Position p : availablePos){
			if(p.getRank() == rank && p.getFile() == file){
				return true;
			}
		}
		
		return false;
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
