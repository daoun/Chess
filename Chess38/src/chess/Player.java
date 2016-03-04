package chess;

public class Player {
	Piece[] pieces = new Piece[16];
	
	public Player(){
		
		pieces[0] = new King();
		pieces[1] = new Queen();
		pieces[2] = new Bishop();
		pieces[3] = new Bishop();
		pieces[4] = new Knight();
		pieces[5] = new Knight();
		pieces[6] = new Rook();
		pieces[7] = new Rook();
		pieces[8] = new Pawn();
		pieces[9] = new Pawn();
		pieces[10] = new Pawn();
		pieces[11] = new Pawn();
		pieces[12] = new Pawn();
		pieces[13] = new Pawn();
		pieces[14] = new Pawn();
		pieces[15] = new Pawn();
		
		
	}
	
	public void movePiece(){
		
	}
}
