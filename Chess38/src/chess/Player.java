package chess;

import java.util.ArrayList;

public class Player {
	Piece[] pieces = new Piece[16];
	int turn = 0; 
	
	public Player(String team){
		
		if(team.equals("black")){
			pieces[0] = new Rook("rook","black",0,0);
			pieces[1] = new Knight("knight","black",0,1);
			pieces[2] = new Bishop("bishop","black",0,2);
			pieces[3] = new Queen("queen","black",0,3);
			pieces[4] = new King("king","black",0,4);
			pieces[5] = new Bishop("bishop","black",0,5);
			pieces[6] = new Knight("knight","black",0,6);
			pieces[7] = new Rook("rook","black",0,7);
			pieces[8] = new Pawn("pawn","black",1,0);
			pieces[9] = new Pawn("pawn","black",1,1);
			pieces[10] = new Pawn("pawn","black",1,2);
			pieces[11] = new Pawn("pawn","black",1,3);
			pieces[12] = new Pawn("pawn","black",1,4);
			pieces[13] = new Pawn("pawn","black",1,5);
			pieces[14] = new Pawn("pawn","black",1,6);
			pieces[15] = new Pawn("pawn","black",1,7);
		}else {
			pieces[0] = new Rook("rook","white",0,0);
			pieces[1] = new Knight("knight","white",0,1);
			pieces[2] = new Bishop("bishop","white",0,2);
			pieces[3] = new Queen("queen","white",0,3);
			pieces[4] = new King("king","white",0,4);
			pieces[5] = new Bishop("bishop","white",0,5);
			pieces[6] = new Knight("knight","white",0,6);
			pieces[7] = new Rook("rook","white",0,7);
			pieces[8] = new Pawn("pawn","white",1,0);
			pieces[9] = new Pawn("pawn","white",1,1);
			pieces[10] = new Pawn("pawn","white",1,2);
			pieces[11] = new Pawn("pawn","white",1,3);
			pieces[12] = new Pawn("pawn","white",1,4);
			pieces[13] = new Pawn("pawn","white",1,5);
			pieces[14] = new Pawn("pawn","white",1,6);
			pieces[15] = new Pawn("pawn","white",1,7);
			
			//white turn first
			turn = 1;
		}
		
		
	}
	
	// movePiece moves the target piece to the location that the player indicated
	public boolean movePiece(int fromX, int fromY, int toX, int toY){
		
		Piece piece = Board.board[fromX][fromY];
		if(piece.canMove(toX, toY)){
			Board.board[toX][toY] = Board.board[fromX][fromY];
			Board.board[fromX][fromY] = null;
			return true;
		}else{
			return false;
		}
		
	}
}
