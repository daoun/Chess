package chess;

public class Board {
	static Piece[][] board;
	
	//Check if a position in board is empty
	public static boolean isEmpty(int rank, int file){
		if(board[rank][file].equals(null)){
			return true;
		}else
			return false;
	}
	
	
}
