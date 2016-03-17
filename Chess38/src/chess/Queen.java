package chess;

import java.util.ArrayList;
import java.util.List;

public class Queen extends Piece {

	public Queen(String type, String team, int rank, int file){
		super(type,team,rank,file);
	}
	
	@Override
	public List<Position> availablePositions() {
		
		List<Position> posList = new ArrayList<Position>();
		
		posList = this.findDiagonal();
		posList.addAll(this.findStraight());
		
		return posList;
		
	}


	

}
