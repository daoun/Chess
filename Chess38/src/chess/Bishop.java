package chess;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece {
	
	public Bishop(String type, String team, int rank, int file){
		super(type,team,rank,file);
	}

	@Override
	public List<Position> availablePositions() {
		
		//Initialize list
		List<Position> posList = new ArrayList<Position>();
		
		
		
		int x = this.getRank();
		int y = this.getFile();
		
		
		/*	
	 	BiFunction<Integer, Integer, Integer> upperleft = (x, y) -> {
			posList.add(new Position(file,rank));
			return 0;
		};
		*/
		
		
		//Check upper-left diagonal
		while(x > 0 && y > 0){
			x--;
			y--;
			
			//Add positions
			if(addPosition(new Position(x,y),posList) == 0){
				break; // break out of loop if position contains an opponent or ally piece
			}
		}
		
		//Initialize again to original position
		x = this.getRank();
		y = this.getFile();
		
		//Check upper-right diagonal
		while(x < 8 && y > 0){
			x++;
			y--;
			
			//Add positions
			if(addPosition(new Position(x,y),posList) == 0){
				break; // break out of loop if position contains an opponent or ally piece
			}
		}
		
		//Initialize again to original position
		x = this.getRank();
		y = this.getFile();
				
		//Check lower-left diagonal
		while(x > 0 && y < 8){
			x--;
			y++;
			
			//Add positions
			if(addPosition(new Position(x,y),posList) == 0){
				break; // break out of loop if position contains an opponent or ally piece
			}
		}				
		
		//Initialize again to original position
		x = this.getRank();
		y = this.getFile();
		
		//Check lower-left diagonal
		while(x < 8 && y < 8){
			x++;
			y++;
			
			//Add positions
			if(addPosition(new Position(x,y),posList) == 0){
				break; // break out of loop if position contains an opponent or ally piece
			}
		}		
		
		return posList;
		
	}
	@Override
	public int addPosition(Position pos, List<Position> posList){
		//Add empty position
		if(Board.isEmpty(pos)){
			posList.add(pos);
			return 1;
		}else if(!(Board.board[pos.getRank()][pos.getFile()].getTeam().equals(this.getTeam()))){ 
			//Add position that contains opponent's piece
			posList.add(pos);
			return 0;
		}else{ //Break if position contains ally piece
			return 0;
		}
	}
	
}
