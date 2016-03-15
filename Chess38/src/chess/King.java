package chess;

import java.util.ArrayList;
import java.util.List;

public class King extends Piece{

	public King(String type, String team, int rank, int file){
		super(type,team,rank,file);
	}
	
	@Override
	public List<Position> availablePositions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int addPosition(Position pos, List<Position> posList) {
		// TODO Auto-generated method stub
		return 0;
	}

}
