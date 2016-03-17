package chess;

import java.util.ArrayList;
import java.util.List;

public class King extends Piece{
	
	int madeFirstMove = 0;

	public King(String type, String team, int rank, int file){
		super(type,team,rank,file);
	}
	
	@Override
	public List<Position> availablePositions() {
		
		List<Position> posList = new ArrayList<Position>();
		
		int rank = this.getRank();
		int file = this.getFile();
		
		
		
		
		
		return null;
	}

}
