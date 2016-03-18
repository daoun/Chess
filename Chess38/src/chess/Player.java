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
	public int movePiece(int fromRank, int fromFile, int toRank, int toFile, char pawnPromotionTo){
		
		Piece piece = Board.board[fromRank][fromFile];
		if(piece.canMove(toRank, toFile)){
			
			//Check for invalid promotion request
			if(pawnPromotionTo != 'a'){
				if(piece.getType().equals("pawn")){
					if(piece.getTeam().equals("white")){ //White pawn
						if(toRank != 0){
							Chess.pawnPromotionTo = 'a';
							piece.setFirstMoveMade(2);
							return 0;
						}
					}else{
						if(toRank != 7){
							Chess.pawnPromotionTo = 'a';
							piece.setFirstMoveMade(2);
							return 0;
						}
					}
				}else{
					return 0; //invalid input
				}
			}
			
			//Pawn Promotion
			if(piece.getType().equals("pawn")){ // Pawn
				if(piece.getTeam().equals("white")){ //White pawn
					if(toRank == 0){
						Board.board[fromRank][fromFile] = null;
						if(!Board.isEmpty(toRank,toFile)){ //end contains an opponent piece
							pawnPromotionTranslator("white",pawnPromotionTo, toRank, toFile);
							return 2;
						}else{
							return 1;
						}
					}
				}else{ //Black pawn
					if(toRank == 7){
						Board.board[fromRank][fromFile] = null;
						if(!Board.isEmpty(toRank,toFile)){ //end contains an opponent piece
							pawnPromotionTranslator("black",pawnPromotionTo, toRank, toFile);
							return 2;
						}else{
							return 1;
						}
					}
				}
			}
	
			//Check Rook & King for firstMoveMade | used for castling
			if(piece.getType().equals("rook") || piece.getType().equals("king")){
				piece.setFirstMoveMade(1);
			}
			
			//Check that pawn has made a move
			if(piece.getType().equals("pawn")){
				piece.setFirstMoveMade(1);
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
	
	//Checks if a pawn is eligible for pawn promotion
	public void pawnPromotion(int toRank, int toFile, String team){
		if(team.equals("black") && toRank == 0){
			
		}
	}
	
	public void pawnPromotionTranslator(String team, char pawnPromotionTo, int toRank, int toFile){
		
		switch(pawnPromotionTo)
		{
		case 'r':
			Board.board[toRank][toFile] = new Rook("rook",team,toRank,toFile);
			break;
		case 'n':
			Board.board[toRank][toFile] = new Knight("knight",team,toRank,toFile);
			break; 
		case 'b':
			Board.board[toRank][toFile] = new Bishop("bishop",team,toRank,toFile);
			break;
		default:
			Board.board[toRank][toFile] = new Queen("queen",team,toRank,toFile);
		}
		
		Chess.pawnPromotionTo = 'a';
	}
	
}
