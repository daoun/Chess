package chess;

public class Player {
	String[] pieces = new String[16];
	String team;
	
	public Player(String team){
		
		this.team = team;
		
		pieces[0] = "rook";
		pieces[1] = "knight";
		pieces[2] = "bishop";
		pieces[3] = "queen";
		pieces[4] = "king";
		pieces[5] = "bishop";
		pieces[6] = "knight";
		pieces[7] = "rook";
		pieces[8] = "pawn";
		pieces[9] = "pawn";
		pieces[10] = "pawn";
		pieces[11] = "pawn";
		pieces[12] = "pawn";
		pieces[13] = "pawn";
		pieces[14] = "pawn";
		pieces[15] = "pawn";
		
		
	}
	
	// movePiece moves the target piece to the location that the player indicated
	public int movePiece(int fromRank, int fromFile, int toRank, int toFile){
		
		Piece piece = Board.board[fromRank][fromFile];
		if(piece.canMove(toRank, toFile)){
	
			//Check Rook & King for firstMoveMade | used for castling
			if(Board.board[fromRank][fromFile].getType().equals("rook") || Board.board[fromRank][fromFile].getType().equals("king")){
				Board.board[fromRank][fromFile].setMadeFirstMove(1);
			}
			
			if(!Board.isEmpty(toRank, toFile)){ //if there is an opponent piece at the destination, remove the piece from the player
				Board.board[toRank][toFile] = Board.board[fromRank][fromFile];
				Board.board[toRank][toFile].setRank(toRank);
				Board.board[toRank][toFile].setFile(toFile);
				Board.board[fromRank][fromFile] = null;
				return 2; //Replaced an opponent's piece
			}else{
			
				Board.board[toRank][toFile] = Board.board[fromRank][fromFile];
				Board.board[toRank][toFile].setRank(toRank);
				Board.board[toRank][toFile].setFile(toFile);
				Board.board[fromRank][fromFile] = null;
				return 1; //Moved piece to empty space
			}
			
		}else{
			return 0; //Cannot move piece
		}
		
	}
	
	//remove piece from player
	public void removePiece(Piece p){
		for(int i = 0 ; i < pieces.length ; i++){
			if(p.getType().equals(pieces[i])){
				pieces[i] = null;
				return;
			}
		}
	}
	
}
