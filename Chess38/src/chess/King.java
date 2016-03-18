package chess;

import java.util.ArrayList;
import java.util.List;

public class King extends Piece{
	
	int madeFirstMove = 0;
	int castlingMoveMade = 0;

	public King(String type, String team, int rank, int file){
		super(type,team,rank,file);
	}
	
	@Override
	public List<Position> availablePositions() {
		
		List<Position> posList = new ArrayList<Position>();
		
		int rank = this.getRank();
		int file = this.getFile();
		
		if(!(rank - 1 < 0 || file - 1 < 0)){
			this.addPosition(rank - 1, file - 1, posList);
		}
		
		if(!(rank - 1 < 0)){
			this.addPosition(rank - 1, file, posList);
		}
		
		if(!(rank - 1 < 0 || file + 1 > 7)){
			this.addPosition(rank - 1, file + 1, posList);
		}
		
		if(!(file - 1 < 0)){
			this.addPosition(rank, file - 1, posList);
		}
		
		if(!(file + 1 > 7)){
			this.addPosition(rank, file + 1, posList);
		}
		
		if(!(rank + 1 > 7 || file - 1 < 0)){
			this.addPosition(rank + 1, file - 1, posList);
		}
		
		if(!(rank + 1 > 7)){
			this.addPosition(rank + 1, file, posList);
		}
		
		if(!(rank + 1 > 7 || file + 1 > 7)){
			this.addPosition(rank + 1, file + 1, posList);
		}
		
		//Castling
		
		return posList;
	}

}
