package chess;

import java.util.ArrayList;

public class Pawn extends Piece {
	
	int firstMoveMade = 0;

	public Pawn(String type, String team, int rank, int file){
		super(type,team,rank,file);
	}
	
	@Override
	public ArrayList<Position> availablePositions() {
		
		//Initialize list
		ArrayList<Position> posList = new ArrayList<Position>();
		int x = this.getRank();
		int y = this.getRank();
		String team = this.getTeam();
		
		//White Pawn
		if(team.equals("white")){
			if(firstMoveMade == 0){ //Check for pawn that hasn't made a move yet
				if(Board.isEmpty(x,y-1)){
					posList.add(new Position(x,y-1));
					if(Board.isEmpty(x, y-2)){ //Pawn can move forward 2 positions if vacant
						posList.add(new Position(x,y-2));
					}
				}
				firstMoveMade = 1;
				return posList;
			}else if(Board.isEmpty(x,y-1)){
				posList.add(new Position(x,y-1));
				if(Board.isEmpty(x-1, y-1) && Board.board[x-1][y-1].getTeam().equals("black")){
					posList.add(new Position(x-1,y-1));
					if(Board.isEmpty(x+1, y-1) && Board.board[x+1][y-1].getTeam().equals("black")){
						posList.add(new Position(x+1,y-1));
					}
				}
				
				return posList;
			}
		}else {
			if(firstMoveMade == 0){ //Check for pawn that hasn't made a move yet
				if(Board.isEmpty(x,y+1)){
					posList.add(new Position(x,y+1));
					if(Board.isEmpty(x, y+2)){ //Pawn can move forward 2 positions if vacant
						posList.add(new Position(x,y+2));
					}
				}
				firstMoveMade = 1;
				return posList;
			}else if(Board.isEmpty(x,y+1)){
				posList.add(new Position(x,y+1));
				if(Board.isEmpty(x+1, y+1) && Board.board[x+1][y+1].getTeam().equals("black")){
					posList.add(new Position(x-1,y-1));
					if(Board.isEmpty(x+1, y-1) && Board.board[x+1][y-1].getTeam().equals("black")){
						posList.add(new Position(x+1,y-1));
					}
				}
				
				return posList;
			}
		}
		
		//Black Pawn
		
		
		return posList;
	}

	@Override
	public boolean canMove(int rank, int file) {
		// TODO Auto-generated method stub
		return false;
	}

}
