package chess;

import java.util.List;

public class Rook extends Piece {
	
	public Rook(String type, String team, int rank, int file){
		super(type,team,rank,file);
	}
	
	@Override
	public List<Position> availablePositions() {
		
		return this.findStraight();
	}

}
