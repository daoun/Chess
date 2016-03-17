package chess;

import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece {

	public Knight(String type, String team, int rank, int file){
		super(type,team,rank,file);
	}
	
	@Override
	public List<Position> availablePositions() {
		
		List<Position> posList = new ArrayList<Position>();

		
		int rank = this.getRank();
		int file = this.getFile();
		
		if(!(rank - 1 < 0 || file - 2 < 0)){
			this.addPosition(rank - 1, file - 2, posList);
		}
		
		if(!(rank - 2 < 0 || file - 1 < 0)){
			this.addPosition(rank - 2, file - 1, posList);
		}
		
		if(!(rank - 2 < 0 || file + 1 > 7)){
			this.addPosition(rank - 2, file + 1, posList);
		}
		
		if(!(rank - 1 < 0 || file + 2 > 7)){
			this.addPosition(rank - 1, file + 2, posList);
		}
		
		if(!(rank + 1 > 7 || file + 2 > 7)){
			this.addPosition(rank + 1, file + 2, posList);
		}
		
		if(!(rank + 2 > 7 || file + 1 > 7)){
			this.addPosition(rank + 2, file + 1, posList);
		}
		
		if(!(rank + 2 > 7 || file - 1 < 0)){
			this.addPosition(rank + 2, file - 1, posList);
		}
		
		if(!(rank + 1 > 7 || file - 2 < 0)){
			this.addPosition(rank + 1, file - 2, posList);
		}
		
		return posList;
	}




}
