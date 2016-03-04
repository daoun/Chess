package chess;

public abstract class Piece {
	String type;
	String team;
	int rank;
	int file;
	
	public abstract Position availablePositions(); 
	public abstract boolean canMove();
}
