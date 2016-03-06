package chess;

import java.util.ArrayList;

public class Bishop extends Piece {
	
	public Bishop(String type, String team, int rank, int file){
		super(type,team,rank,file);
	}

	@Override
	public ArrayList<Position> availablePositions() {
		
		//Initialize list
		ArrayList<Position> posList = new ArrayList<Position>();
		int x = this.getRank();
		int y = this.getRank();
		
		//Check upper-left diagonal
		while(x > 0 && y > 0){
			x--;
			y++;
			
			//Add positions
			if(addPosition(x,y,posList) == 0){
				break; // break out of loop if position contains an opponent or ally piece
			}
		}
		
		//Initialize again to original position
		x = this.getRank();
		y = this.getRank();
		
		//Check upper-right diagonal
		while(x < 8 && y > 0){
			x++;
			y--;
			
			//Add positions
			if(addPosition(x,y,posList) == 0){
				break; // break out of loop if position contains an opponent or ally piece
			}
		}
		
		//Initialize again to original position
		x = this.getRank();
		y = this.getRank();
				
		//Check lower-left diagonal
		while(x > 0 && y < 8){
			x--;
			y--;
			
			//Add positions
			if(addPosition(x,y,posList) == 0){
				break; // break out of loop if position contains an opponent or ally piece
			}
		}				
		
		//Initialize again to original position
		x = this.getRank();
		y = this.getRank();
		
		//Check lower-left diagonal
		while(x < 8 && y < 8){
			x++;
			y++;
			
			//Add positions
			if(addPosition(x,y,posList) == 0){
				break; // break out of loop if position contains an opponent or ally piece
			}
		}		
		
		return posList;
		
	}

	public int addPosition(int x, int y, ArrayList<Position> posList){
		//Add empty position
		if(Board.isEmpty(x,y)){
			posList.add(new Position(x,y));
			return 1;
		}else if(!(Board.board[x][y].getTeam().equals(this.getTeam()))){ //Add position that contains opponent's piece
			posList.add(new Position(x,y));
			return 0;
		}else{ //Break if position contains ally piece
			return 0;
		}
	}
	
}
