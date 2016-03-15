package chess;

import java.util.ArrayList;

public class Queen extends Piece {

	public Queen(String type, String team, int rank, int file){
		super(type,team,rank,file);
	}
	
	@Override
	public ArrayList<Position> availablePositions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int addPosition(int x, int y, ArrayList<Position> posList) {
		// TODO Auto-generated method stub
		return 0;
	}

	

}
