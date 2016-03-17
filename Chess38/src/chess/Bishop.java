package chess;

import java.util.List;

public class Bishop extends Piece {
	
	public Bishop(String type, String team, int rank, int file){
		super(type,team,rank,file);
	}

	@Override
	public List<Position> availablePositions() {
		
		return this.findDiagonal();
		
	}
	
}
