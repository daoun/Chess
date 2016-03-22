package chess;

/**
 * Player class creates a player for the game. 
 * 
 * @author Capki Kim, Daoun Oh
 *
 */
public class Player {
	/** array of pieces that the player has */
	public Piece[] pieces = new Piece[16];
	
	/** indicates which team the player is on */
	public String team;
	
	/**
	 * Default constructor.
	 * @param team a string representation of the player's team 
	 */
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
	
	/**
	 * Moves the target piece to the location that the player indicated.
	 * @param fromRank vertical position of the specified piece
	 * @param fromFile horizontal position of the specified piece
	 * @param toRank vertical position of the target position
	 * @param toFile horizontal position of the target position
	 * @param pawnPromotionTo indicates which piece the player wants to promote its pawn
	 * @return 0 cannot move the piece to target location, <p>
	 * 		1 can move the piece to an empty location, <p>
	 * 		2 can move the piece and remove an opponent's piece
	 */
	public int movePiece(int fromRank, int fromFile, int toRank, int toFile, char pawnPromotionTo){
		
		Piece piece = Board.board[fromRank][fromFile];
		if(piece.canMove(toRank, toFile)){
			
			//En passant
			if(piece.getType().equals("pawn")){
				if(piece.getTeam().equals("white")){
					if(toFile == piece.getFile()-1 && toRank == piece.getRank()-1 && (!Board.isEmpty(piece.getRank(),piece.getFile()-1) && Board.board[piece.getRank()][piece.getFile()-1].getPawnTwoAdvance() == 1 )){
						
						Board.board[toRank][toFile] = piece; //move pawn
						Board.board[piece.getRank()][piece.getFile()] = null;
						Board.board[piece.getRank()][piece.getFile()-1] = null; //remove opponent pawn
						Chess.bPlayer.removePiece(piece.getRank(), piece.getFile()-1); //remove pawn from opponent
						Board.board[toRank][toFile].setRank(toRank);
						Board.board[toRank][toFile].setFile(toFile);
						return 1;
						
						
					}else if(toFile == piece.getFile()+1 && toRank == piece.getRank()-1 && (!Board.isEmpty(piece.getRank(),piece.getFile()+1) && Board.board[piece.getRank()][piece.getFile()+1].getPawnTwoAdvance() == 1 )){
						Board.board[toRank][toFile] = piece; //move pawn
						Board.board[piece.getRank()][piece.getFile()] = null;
						Board.board[piece.getRank()][piece.getFile()+1] = null; //remove opponent pawn
						Chess.bPlayer.removePiece(piece.getRank(), piece.getFile()+1); //remove pawn from opponent
						Board.board[toRank][toFile].setRank(toRank);
						Board.board[toRank][toFile].setFile(toFile);
						return 1;
						
					}
				}else{
					if(toFile == piece.getFile()-1 && toRank == piece.getRank()+1 && (!Board.isEmpty(piece.getRank(),piece.getFile()-1) && Board.board[piece.getRank()][piece.getFile()-1].getPawnTwoAdvance() == 1 )){
						
						Board.board[toRank][toFile] = piece; //move pawn
						Board.board[piece.getRank()][piece.getFile()] = null;
						Board.board[piece.getRank()][piece.getFile()-1] = null; //remove opponent pawn
						Chess.bPlayer.removePiece(piece.getRank(), piece.getFile()-1); //remove pawn from opponent
						Board.board[toRank][toFile].setRank(toRank);
						Board.board[toRank][toFile].setFile(toFile);
						return 1;
						
		
						
					}else if(toFile == piece.getFile()+1 && toRank == piece.getRank()+1 && (!Board.isEmpty(piece.getRank(),piece.getFile()+1) && Board.board[piece.getRank()][piece.getFile()+1].getPawnTwoAdvance() == 1 )){
						Board.board[toRank][toFile] = piece; //move pawn
						Board.board[piece.getRank()][piece.getFile()] = null;
						Board.board[piece.getRank()][piece.getFile()+1] = null; //remove opponent pawn
						Chess.bPlayer.removePiece(piece.getRank(), piece.getFile()+1); //remove pawn from opponent
						Board.board[toRank][toFile].setRank(toRank);
						Board.board[toRank][toFile].setFile(toFile);
						return 1;
					}
				}
			}
			
			resetPawnTwoAdvance();
			
			//See which pawn pieces are eligible for en passant
			if(piece.getType().equals("pawn")){
				if(piece.getTeam().equals("white")){
					if(toRank == piece.getRank() - 2){
						piece.setPawnTwoAdvance(1);
					}
				}else{
					if(toRank == piece.getRank() + 2){
						piece.setPawnTwoAdvance(1);
					}
				}
			}
			
			
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
						Chess.wPlayer.removePiece(fromRank, fromFile); // remove original piece from player
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
						Chess.wPlayer.removePiece(fromRank, fromFile); // remove original piece from player
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
			
			//Castling
			if(piece.getType().equals("king")){
				if(piece.getCastlingMoveMade() == 0 && piece.getFirstMoveMade() == 0){
					if(piece.getTeam().equals("white")){
						if(toRank == 7 && toFile == 6){
					
							Board.board[7][6] = piece; //move king
							Board.board[7][6].setFile(6);
							Board.board[7][4] = null;
							
							Board.board[7][5] = Board.board[7][7]; //move rook
							Board.board[7][5].setFile(5);
							Board.board[7][7] = null;
							
							Board.board[7][5].setFirstMoveMade(1);
							piece.setCastlingMoveMade(1);
							piece.setFirstMoveMade(1);
							
							return 1;
						}else if(toRank == 7 && toFile == 2){
							
							Board.board[7][2] = piece; //move king
							Board.board[7][2].setFile(2);
							Board.board[7][4] = null;
							
							Board.board[7][3] = Board.board[7][0]; //move rook
							Board.board[7][3].setFile(3);
							Board.board[7][0] = null;
							
							Board.board[7][3].setFirstMoveMade(1);
							piece.setCastlingMoveMade(1);
							piece.setFirstMoveMade(1);
							
							return 1;
						}
						
					} else if(piece.getTeam().equals("black")){
						if(toRank == 0 && toFile == 2){
						
							Board.board[0][2] = piece; //move king
							Board.board[0][2].setFile(2);
							Board.board[0][4] = null;
							
							Board.board[0][3] = Board.board[0][0]; //move rook
							Board.board[0][3].setFile(3);
							Board.board[0][0] = null;
							
							Board.board[0][3].setFirstMoveMade(1);
							piece.setCastlingMoveMade(1);
							piece.setFirstMoveMade(1);
							
							return 1;
						} else if(toRank == 0 && toFile == 6){
							
							Board.board[0][6] = piece; //move king
							Board.board[0][6].setFile(6);
							Board.board[0][4] = null;
							
							Board.board[0][5] = Board.board[0][0]; //move rook
							Board.board[0][5].setFile(5);
							Board.board[0][7] = null;
							
							Board.board[0][5].setFirstMoveMade(1);
							piece.setCastlingMoveMade(1);
							piece.setFirstMoveMade(1);
							
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
	
	/**
	 * Resets all the values of pawnTwoAdvance to 0 since En passant is only legal on the opponent's next move immediately following the first pawn's 
	 */
	public void resetPawnTwoAdvance(){
		for(Piece p : pieces){
			if(p != null){
				if(p.getType().equals("pawn")){
					p.setPawnTwoAdvance(0);
				}
			}
		}
	}
	
	/**
	 * Removes the specified piece from the player.
	 * @param rank vertical position of the specified piece
	 * @param file horizontal position of the specified piece
	 */
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
	
	/**
	 * Adds the specified piece to player
	 * @param p piece to be added to the player
	 */
	public void addPiece(Piece p){
		for(int i = 0 ; i < pieces.length ; i++){
			if(pieces[i] == null){
				pieces[i] = p;
				return;
			}
		}
	}
	
	/**
	 * Pawn promotion translator creates a new instance of the piece the pawn is promoting to.
	 * @param team indicates the team
	 * @param pawnPromotionTo indicates what piece the pawn is promoting to
	 * @param toRank vertical position of the new piece
	 * @param toFile horizontal position of the new piece
	 */
	public void pawnPromotionTranslator(String team, char pawnPromotionTo, int toRank, int toFile){
		
		switch(pawnPromotionTo){
			case 'r':
				Board.board[toRank][toFile] = new Rook("rook",team,toRank,toFile);
				if(team.equals("white")){
					Chess.wPlayer.addPiece(Board.board[toRank][toFile]);
				}else{
					Chess.bPlayer.addPiece(Board.board[toRank][toFile]);
				}
				break;
			case 'n':
				Board.board[toRank][toFile] = new Knight("knight",team,toRank,toFile);
				if(team.equals("white")){
					Chess.wPlayer.addPiece(Board.board[toRank][toFile]);
				}else{
					Chess.bPlayer.addPiece(Board.board[toRank][toFile]);
				}
				break; 
			case 'b':
				Board.board[toRank][toFile] = new Bishop("bishop",team,toRank,toFile);
				if(team.equals("white")){
					Chess.wPlayer.addPiece(Board.board[toRank][toFile]);
				}else{
					Chess.bPlayer.addPiece(Board.board[toRank][toFile]);
				}
				break;
			default:
				Board.board[toRank][toFile] = new Queen("queen",team,toRank,toFile);
				if(team.equals("white")){
					Chess.wPlayer.addPiece(Board.board[toRank][toFile]);
				}else{
					Chess.bPlayer.addPiece(Board.board[toRank][toFile]);
				}
		}
		
		Chess.pawnPromotionTo = 'a';
	}
	
}
