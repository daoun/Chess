package chess;

public class Player {
	Piece[] pieces = new Piece[16];
	String team;
	
	public Player(String team){
		
		this.team = team;
		
		if(team.equals("black")){
			pieces[0] = Board.board[0][0];
			pieces[1] = Board.board[0][1];
			pieces[2] = Board.board[0][2];
			pieces[3] = Board.board[0][3];
			pieces[4] = Board.board[0][4];
			pieces[5] = Board.board[0][5];
			pieces[6] = Board.board[0][6];
			pieces[7] = Board.board[0][7];
			pieces[8] = Board.board[1][0];
			pieces[9] = Board.board[1][1];
			pieces[10] = Board.board[1][2];
			pieces[11] = Board.board[1][3];
			pieces[12] = Board.board[1][4];
			pieces[13] = Board.board[1][5];
			pieces[14] = Board.board[1][6];
			pieces[15] = Board.board[1][7];
		}else{
			pieces[0] = Board.board[7][0];
			pieces[1] = Board.board[7][1];
			pieces[2] = Board.board[7][2];
			pieces[3] = Board.board[7][3];
			pieces[4] = Board.board[7][4];
			pieces[5] = Board.board[7][5];
			pieces[6] = Board.board[7][6];
			pieces[7] = Board.board[7][7];
			pieces[8] = Board.board[6][0];
			pieces[9] = Board.board[6][1];
			pieces[10] = Board.board[6][2];
			pieces[11] = Board.board[6][3];
			pieces[12] = Board.board[6][4];
			pieces[13] = Board.board[6][5];
			pieces[14] = Board.board[6][6];
			pieces[15] = Board.board[6][7];
		}
		
		
		
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
							pawnPromotionTranslator("white",pawnPromotionTo, toRank, toFile);
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
							pawnPromotionTranslator("white",pawnPromotionTo, toRank, toFile);
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
	public void removePiece(int rank, int file){
		for(int i = 0 ; i < pieces.length ; i++){
			if(pieces[i] != null){
				if(pieces[i].getRank() == rank && pieces[i].getFile() == file){
					pieces[i] = null;
					return;
				}
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
