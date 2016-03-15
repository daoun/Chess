package chess;

import java.util.List;

public abstract class Piece {
	
	private String type;
	private String team;
	private int rank;
	private int file;
	
	public Piece(String type, String team, int rank, int file){
		this.type = type;
		this.team = team;
		this.rank = rank;
		this.file = file;
	}
	
	public abstract List<Position> availablePositions(); 
	
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
	
	
}
