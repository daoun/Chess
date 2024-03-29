package chess;

/**
 * Board class creates the game board. The board consists of 8 rows, aka file, and 8 columns, aka rank. 
 * 
 * @author Capki Kim, Daoun Oh
 *
 */
public class Board {
	/** 2D array of pieces that contains all of the pieces that are in the game */
	public static Piece[][] board = new Piece[8][8];
	
	/**
	 * Initializes the board to its initial set up.
	 */
	public static void init(){
		
		board[0][0] = new Rook("rook","black",0,0);
		board[0][1] = new Knight("knight","black",0,1);
		board[0][2] = new Bishop("bishop","black",0,2);
		board[0][3] = new Queen("queen","black",0,3);
		board[0][4] = new King("king","black",0,4);
		board[0][5] = new Bishop("bishop","black",0,5);
		board[0][6] = new Knight("knight","black",0,6);
		board[0][7] = new Rook("rook","black",0,7);
		board[1][0] = new Pawn("pawn","black",1,0);
		board[1][1] = new Pawn("pawn","black",1,1);
		board[1][2] = new Pawn("pawn","black",1,2);
		board[1][3] = new Pawn("pawn","black",1,3);
		board[1][4] = new Pawn("pawn","black",1,4);
		board[1][5] = new Pawn("pawn","black",1,5);
		board[1][6] = new Pawn("pawn","black",1,6);
		board[1][7] = new Pawn("pawn","black",1,7);
		board[7][0] = new Rook("rook","white",7,0);
		board[7][1] = new Knight("knight","white",7,1);
		board[7][2] = new Bishop("bishop","white",7,2);
		board[7][3] = new Queen("queen","white",7,3);;
		board[7][4] = new King("king","white",7,4);
		board[7][5] = new Bishop("bishop","white",7,5);
		board[7][6] = new Knight("knight","white",7,6);
		board[7][7] = new Rook("rook","white",7,7);
		board[6][0] = new Pawn("pawn","white",6,0);
		board[6][1] = new Pawn("pawn","white",6,1);
		board[6][2] = new Pawn("pawn","white",6,2);
		board[6][3] = new Pawn("pawn","white",6,3);
		board[6][4] = new Pawn("pawn","white",6,4);
		board[6][5] = new Pawn("pawn","white",6,5);
		board[6][6] = new Pawn("pawn","white",6,6);
		board[6][7] = new Pawn("pawn","white",6,7);
	}
	
	/**
	 * Prints the current state of the board out to the console.
	 */
	public static void drawBoard(){
		
		String type = "";
		String team = "";
		int count = 8;
		
		for(int i = 0 ; i < board.length ; i++){
			for(int j = 0 ; j < board.length ; j++){
				
				//draw black and white spaces
				if(Board.isEmpty(i,j)){
					if((i + j) % 2 == 0){
						System.out.print("   ");
						continue;
					}else{
						System.out.print("## ");
						continue;
					}
				}
			
				//draw pieces
				type = board[i][j].getType();
				team = board[i][j].getTeam();
				
				if(team.equals("black")){
					switch(type){
						case "rook" : 
							System.out.print("bR ");
							break;
						case "knight" : 
							System.out.print("bN ");
							break;
						case "bishop" : 
							System.out.print("bB ");
							break;
						case "king" : 
							System.out.print("bK ");
							break;
						case "queen" : 
							System.out.print("bQ ");
							break;
						case "pawn" : 
							System.out.print("bp ");
							break;
					}
				}else {
					switch(type){
						case "rook" : 
							System.out.print("wR ");
							break;
						case "knight" : 
							System.out.print("wN ");
							break;
						case "bishop" : 
							System.out.print("wB ");
							break;
						case "king" : 
							System.out.print("wK ");
							break;
						case "queen" : 
							System.out.print("wQ ");
							break;
						case "pawn" : 
							System.out.print("wp ");
							break;
					}
				}
			}
			
			// Print row numbers
			if(count != 0){
				System.out.println(count);
				count--;
			}
		}
		
		System.out.println(" a  b  c  d  e  f  g  h");
		System.out.println();
	}
	
	/**
	 * Checks if a position in the board is empty.
	 * @param rank the vertical position of the piece
	 * @param file the horizontal position of the piece
	 * @return true if the position is empty,
	 * 		false otherwise
	 */
	public static boolean isEmpty(int rank, int file){
		if(rank > 7 && rank < 0 && file > 7 && file < 0 ){
			return true;
		}
		if(board[rank][file] == null){
			return true;
		}else
 			return false;
	}
}
