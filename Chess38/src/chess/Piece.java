package chess;

import java.util.ArrayList;
import java.util.List;
/**
 * Piece is an abstract class that acts as a base to all of the different pieces in the game of chess. It contains many helper functions to help different pieces to find possible positions it can move to. There is one abstract method called availablePositions(), which is implemented in the subclasses.
 * 
 * @author Capki Kim, Daoun Oh
 *
 */
public abstract class Piece {
	
	private String type;
	private String team;
	private int rank;
	private int file;
	private int firstMoveMade = 0;
	private int castlingMoveMade = 0;
	private int pawnTwoAdvance = 0;

	/**
	 * Default constructor.
	 * @param type 	indicates what type of piece it is
	 * @param team	indicates if team is black or white
	 * @param rank	indicates the vertical position of a piece 
	 * @param file	indicates the horizontal position of a piece 
	 */
	public Piece(String type, String team, int rank, int file){
		this.type = type;
		this.team = team;
		this.rank = rank;
		this.file = file;
	}
	
	/**
	 * Abstract method for subclasses to implement to find the available positions of pieces that it can move to 
	 * @return	list of positions that a piece can move to 
	 */
	public abstract List<Position> availablePositions(); 
	
	/**
	 * Adds the position to the list if it is valid
	 * @param x	the file of a piece
	 * @param y the rank of a piece
	 * @param posList list that contains the possible positions that a piece can move to
	 * @return 1 if the position is an empty position,
	 * 		0 if the position contains a piece from the opposing team
	 */
	public int addPosition(int x, int y, List<Position> posList){
		//Add empty position
		if(Board.isEmpty(x,y)){
			posList.add(new Position(x,y));
			return 1;
		}else if(!(Board.board[x][y].getTeam().equals(this.getTeam()))){ 
			//Add position that contains opponent's piece
			posList.add(new Position(x,y));
			return 0;
		}else{ //Break if position contains ally piece
			return 0;
		}
	}
	
	/**
	 * Checks if the piece can move to a target location.
	 * @param rank the vertical position of the piece
	 * @param file the horizontal position of the piece
	 * @return true if the piece is able to move to the given position,
	 * 			false otherwise
	 */
	public boolean canMove(int rank, int file){
		List<Position> availablePos = this.availablePositions();
		
		boolean canMove = false;
		
		//Check to see if target position is valid
		for(Position p : availablePos){
			if(p.getRank() == rank && p.getFile() == file){
				canMove = true;
			}
		}
		
		//if the piece cannot move to target location
		if(canMove == false){
			return false;
		}
		
		//save the piece temporarily
		Piece tempPiece = null;
		int fromRank = this.getRank();
		int fromFile = this.getFile();
		if(!Board.isEmpty(rank, file)){
			tempPiece = Board.board[rank][file];
		}
		
		//Move the piece
		Board.board[rank][file] = Board.board[fromRank][fromFile];
		Board.board[rank][file].setRank(rank);
		Board.board[rank][file].setFile(file);
		Board.board[fromRank][fromFile] = null;
		
		String team = this.getTeam();
		
		//Temporarily move piece from the player
		if(tempPiece != null){
			if(team.equals("black")){
				Chess.wPlayer.removePiece(rank, file);
			}else{
				Chess.bPlayer.removePiece(rank, file);
			}
		}
		
		//See if any of the opponent pieces can get to the King
		if(check(team)){
			canMove = false;
		}
		
		//if moving the piece results in the opponent being in check
		if(team.equals("white")){
			if(check("black")){
				if(checkMate("black")){
					Chess.gameStatus = 0;
				}
				Chess.bCheck = 1;
			}
		}else{
			if(check("white")){
				if(checkMate("white")){
					Chess.gameStatus = 0;
				}
				Chess.wCheck = 1;
				
			}
		}
		
		//Move the piece(s) back
		Board.board[fromRank][fromFile] = Board.board[rank][file];
		Board.board[fromRank][fromFile].setRank(fromRank);
		Board.board[fromRank][fromFile].setFile(fromFile);
		Board.board[rank][file] = null;
		
		//Give piece back to player & board
		if(tempPiece != null){
			Board.board[rank][file] = tempPiece;
			if(team.equals("black")){
				Chess.wPlayer.addPiece(tempPiece);
			}else{
				Chess.bPlayer.addPiece(tempPiece);
			}
		}
		
		return canMove;
	}
	
	/**
	 * Checks for checkmate 
	 * @param team the team that is being checked for a checkmate
	 * @return true if checkmate,
	 * 		false otherwise
	 */
	public boolean checkMate(String team){
		
		//need to try every possible position that 'team' pieces can go to and if check is still there, it is checkmate
		
		List<Position> availablePos = new ArrayList<Position>();
		Piece tempPiece = null;
		int fromRank = 0;
		int fromFile = 0;
		boolean checkmate = true;
		
		if(team.equals("white")){
			for(int i = 0 ; i < Chess.wPlayer.pieces.length ; i++){
				
				if(Chess.wPlayer.pieces[i] != null){
						availablePos = Chess.wPlayer.pieces[i].availablePositions();
				}else{
					continue;
				}
					
				for(Position p : availablePos){
					//save the piece temporarily
					tempPiece = null;
					fromRank = Chess.wPlayer.pieces[i].getRank();
					fromFile = Chess.wPlayer.pieces[i].getFile();
					
					if(!Board.isEmpty(p.getRank(), p.getFile())){
						tempPiece = Board.board[p.getRank()][p.getFile()];
					}
					
					//Move the piece
					Board.board[p.getRank()][p.getFile()] = Board.board[fromRank][fromFile];
					Board.board[p.getRank()][p.getFile()].setRank(p.getRank());
					Board.board[p.getRank()][p.getFile()].setFile(p.getFile());
					Board.board[fromRank][fromFile] = null;
					
					//Temporarily move piece from the player
					if(tempPiece != null){
						if(team.equals("black")){
							Chess.wPlayer.removePiece(p.getRank(), p.getFile());
						}else{
							Chess.bPlayer.removePiece(p.getRank(), p.getFile());
						}
					}
					
					if(!check(team)){
						checkmate = false;
					}
					
					//Move the piece(s) back
					Board.board[fromRank][fromFile] = Board.board[p.getRank()][p.getFile()];
					Board.board[fromRank][fromFile].setRank(fromRank);
					Board.board[fromRank][fromFile].setFile(fromFile);
					Board.board[p.getRank()][p.getFile()] = null;
					
					//Give piece back to player & board
					if(tempPiece != null){
						Board.board[p.getRank()][p.getFile()] = tempPiece;
						if(team.equals("black")){
							Chess.wPlayer.addPiece(tempPiece);
						}else{
							Chess.bPlayer.addPiece(tempPiece);
						}
					}
					
					if(checkmate == false){
						return checkmate;
					}
					
				}
			}
		}else{
			for(int i = 0 ; i < Chess.bPlayer.pieces.length ; i++){

				if(Chess.bPlayer.pieces[i] != null){
					availablePos = Chess.bPlayer.pieces[i].availablePositions();
				}else{
					continue;
				}
				
				for(Position p : availablePos){
					//save the piece temporarily
					tempPiece = null;
					fromRank = Chess.bPlayer.pieces[i].getRank();
					fromFile = Chess.bPlayer.pieces[i].getFile();
					
					if(!Board.isEmpty(p.getRank(), p.getFile())){
						tempPiece = Board.board[p.getRank()][p.getFile()];
					}
					
					//Move the piece
					Board.board[p.getRank()][p.getFile()] = Board.board[fromRank][fromFile];
					Board.board[p.getRank()][p.getFile()].setRank(p.getRank());
					Board.board[p.getRank()][p.getFile()].setFile(p.getFile());
					Board.board[fromRank][fromFile] = null;
					
					//Temporarily move piece from the player
					if(tempPiece != null){
						if(team.equals("black")){
							Chess.wPlayer.removePiece(p.getRank(), p.getFile());
						}else{
							Chess.bPlayer.removePiece(p.getRank(), p.getFile());
						}
					}
					
					if(!check(team)){
						checkmate = false;
					}
					
					//Move the piece(s) back
					Board.board[fromRank][fromFile] = Board.board[p.getRank()][p.getFile()];
					Board.board[fromRank][fromFile].setRank(fromRank);
					Board.board[fromRank][fromFile].setFile(fromFile);
					Board.board[p.getRank()][p.getFile()] = null;
					
					//Give piece back to player & board
					if(tempPiece != null){
						Board.board[p.getRank()][p.getFile()] = tempPiece;
						if(team.equals("black")){
							Chess.wPlayer.addPiece(tempPiece);
						}else{
							Chess.bPlayer.addPiece(tempPiece);
						}
					}
					
					
					if(checkmate == false){
						return checkmate;
					}
					
				}
			}
		}
		
		return checkmate;
	}
	
	/**
	 * Checks if the King is in check
	 * @param team the team that check is called upon
	 * @return true if check otherwise false
	 */
	public boolean check(String team){
		
		List<Position> positions = new ArrayList<Position>();
		
		int kingRank = 0;
		int kingFile = 0;
		
		if(team.equals("white")){
			
			//Find coordinate of white King
			for(int i = 0 ; i < Chess.wPlayer.pieces.length ; i++){
				if(Chess.wPlayer.pieces[i] != null){
					if(Chess.wPlayer.pieces[i].getType().equals("king")){
						kingRank = Chess.wPlayer.pieces[i].getRank();
						kingFile = Chess.wPlayer.pieces[i].getFile();
						break;
					}
				}
			}
			
			for(int i = 0; i < Chess.bPlayer.pieces.length ; i++){ //Check all the black pieces
				if(Chess.bPlayer.pieces[i] != null){
 					positions = Chess.bPlayer.pieces[i].availablePositions();
					for(Position p : positions){
						if(p.getRank() == kingRank && p.getFile() == kingFile){
							return true;
						}
					}
				}
			}
		}else{
			
			//Find coordinate of black King
			for(int i = 0 ; i < Chess.bPlayer.pieces.length ; i++){
				if(Chess.bPlayer.pieces[i] != null){
					if(Chess.bPlayer.pieces[i].getType().equals("king")){
						kingRank = Chess.bPlayer.pieces[i].getRank();
						kingFile = Chess.bPlayer.pieces[i].getFile();
					}
				}
			}
			
			for(int i = 0; i < Chess.wPlayer.pieces.length ; i++){ //Check all the white pieces
				if(Chess.wPlayer.pieces[i] != null){
					positions = Chess.wPlayer.pieces[i].availablePositions();
					for(Position p : positions){
						if(p.getRank() == kingRank && p.getFile() == kingFile){
							return true;
						}
					}
				}
			}
			
			
		}
		
		return false; //No opponent piece can get to the King even if the move is made
	}

	/**
	 * Finds all diagonal available positions.
	 * Upper-left, upper-right, lower-left, and lower-right.
	 * @return list of all possible diagonal positions
	 */
	public List<Position> findDiagonal(){
	
		//Initialize list
		List<Position> posList = new ArrayList<Position>();
	
		int rank = this.getRank() - 1;
		int file = this.getFile() - 1;
	
		//Check upper-left diagonal
		while(rank >= 0 && file >= 0){
	
			//Add positions
			if(addPosition(rank,file,posList) == 0){
				break; // break out of loop if position contains an opponent or ally piece
			}
	
			rank--;
			file--;
		}
	
		//Initialize
		rank = this.getRank() + 1;
		file = this.getFile() - 1;
	
		//Check lower-left diagonal
		while(rank <= 7 && file >= 0){
	
			//Add positions
			if(addPosition(rank,file,posList) == 0){
				break; // break out of loop if position contains an opponent or ally piece
			}
	
			rank++;
			file--;
		}
	
		//Initialize
		rank = this.getRank() - 1;
		file = this.getFile() + 1;
	
		//Check lower-right diagonal
		while(rank >= 0 && file <= 7){
	
			//Add positions
			if(addPosition(rank,file,posList) == 0){
				break; // break out of loop if position contains an opponent or ally piece
			}
	
			rank--;
			file++;
		}				
	
		//Initialize
		rank = this.getRank() + 1;
		file = this.getFile() + 1;
	
		//Check lower-right diagonal
		while(rank <= 7 && file <= 7){
	
			//Add positions
			if(addPosition(rank,file,posList) == 0){
				break; // break out of loop if position contains an opponent or ally piece
			}
	
			rank++;
			file++;
		}		
	
		return posList;
	}

	/**
	 * Finds all straight available positions. 
	 * Up, down, right and left.
	 * @return list of all possible straight positions
	 */
	public List<Position> findStraight(){
		
		//Initialize list
		List<Position> posList = new ArrayList<Position>();

		int rank = this.getRank();
		int file = this.getFile() - 1;

		//Check left side
		while(file >= 0){

			//Add positions
			if(addPosition(rank,file,posList) == 0){
				break; // break out of loop if position contains an opponent or ally piece
			}
			
			file--;
		}

		//Initialize again to original position
		rank = this.getRank();
		file = this.getFile() + 1 ;

		//Check right side
		while(file <= 7){

			//Add positions
			if(addPosition(rank,file,posList) == 0){
				break; // break out of loop if position contains an opponent or ally piece
			}
			
			file++;
		}

		//Initialize again to original position
		rank = this.getRank() - 1;
		file = this.getFile();

		//Check upper side
		while(rank >= 0){

			//Add positions
			if(addPosition(rank,file,posList) == 0){
				break; // break out of loop if position contains an opponent or ally piece
			}
			
			rank--;
		}				

		//Initialize again to original position
		rank = this.getRank() + 1;
		file = this.getFile();

		//Check lower diagonal
		while(rank <= 7){

			//Add positions
			if(addPosition(rank,file,posList) == 0){
				break; // break out of loop if position contains an opponent or ally piece
			}
			rank++;
		}		

		return posList;
	}
	
	/**
	 * Overrides the equals method. Compares if the piece is equal with its type, file and rank.
	 * @param piece the piece it is comparing with
	 * @return true if type, file, and rank is equal, otherwise false.
	 */
	public boolean equals(Piece p){
		if(this.getType().equals(p.getType()) && this.getRank() == p.getRank() && this.getFile() == p.getFile()){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * Gets the type of the piece.
	 * @return a string representation of the type. 
	 * 		Possible types are bishop, knight, rook, pawn, king, or queen.
	 */
	public String getType() {
		return type;
	}

	/**
	 * Sets the type of a piece.
	 * @param type string representation of the type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Gets the team of the piece.
	 * @return a string representation of the team. 
	 * 		Possible teams are white or black.
	 */
	public String getTeam() {
		return team;
	}

	/**
	 * Sets the team of a piece. 
	 * @param team string representation of the team
	 */
	public void setTeam(String team) {
		this.team = team;
	}

	/**
	 * Gets the rank of the piece.
	 * @return a numerical representation of the rank of a piece.
	 * 		Possible range is 0-7.
	 */
	public int getRank() {
		return rank;
	}

	/**
	 * Sets the rank of a piece
	 * @param rank numerical representation of a rank of a piece.
	 */
	public void setRank(int rank) {
		this.rank = rank;
	}

	/**
	 * Gets the file of the piece.
	 * @return a numerical representation of a file of a piece.
	 * 		Possible range is 0-7.
	 */
	public int getFile() {
		return file;
	}

	/**
	 * Sets the file of a piece
	 * @param file numerical representation of a file of a piece.
	 */
	public void setFile(int file) {
		this.file = file;
	}
	
	/**
	 * Gets the value of the firstMoveMade field.
	 * @return value of firstMoveMade
	 */
	public int getFirstMoveMade() {
		return firstMoveMade;
	}

	/**
	 * Sets the value of the firstMoveMade field
	 * @param firstMoveMade indicator for first move made
	 */
	public void setFirstMoveMade(int firstMoveMade) {
		this.firstMoveMade = firstMoveMade;
	}
	/**
	 * Gets the value of the castling move made
	 * @return value of castlingMoveMade
	 */
	public int getCastlingMoveMade(){
		return this.castlingMoveMade;
	}
	
	/**
	 * Set the value of castlingMoveMade field
	 * @param move number to be set as the value of castlingMoveMade
	 */
	public void setCastlingMoveMade(int move){
		this.castlingMoveMade = move;
	}
	
	/**
	 * Gets the value of pawnTwoAdvance
	 * @return value of pawnTwoAdvance
	 */
	public int getPawnTwoAdvance() {
		return pawnTwoAdvance;
	}

	/**
	 * Set the value of pawnTwoAdvance field
	 * @param pawnTwoAdvance the number to be set as the value of pawnTwoAdvance 
	 */
	public void setPawnTwoAdvance(int pawnTwoAdvance) {
		this.pawnTwoAdvance = pawnTwoAdvance;
	}
}
