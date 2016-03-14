package chess;

import java.util.ArrayList;

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
	
	public abstract ArrayList<Position> availablePositions(); 
	
	public boolean canMove(int rank, int file){
		ArrayList<Position> availablePos = this.availablePositions();
		
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
