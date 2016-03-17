package chess;

import java.util.Scanner;

public class Chess {

	static int turn = 1;
	
	public static void main(String[] args) {
		
		//Initialize board
		Board.init();
		
		//Initialize player
		Player bPlayer = new Player("black");
		Player wPlayer = new Player("white");
		
		//Scanner to read user input
		Scanner scanner = new Scanner(System.in);
		String read = "";
		
		//To check status of game
		int gameStatus = 1;
		
		//Variable used to check input
		boolean wrongInput;
		String team = "";
		
		//
		int fromX, fromY, toX, toY;
		
		//temp int
		int temp;
		
		
		do {
			//Draw board
			Board.drawBoard();
			wrongInput = true;
			
			if(turn == 1){
				System.out.print("White's move: ");
				team = "White";
			}else{
				System.out.print("Black's move: ");
				team = "Black";
			}
			
			//Check user input
			while(wrongInput){
				
				if(checkInput(read = scanner.nextLine())){
					System.out.println();
					fromX = translate(Character.getNumericValue(read.charAt(1)));
					fromY = translate(read.charAt(0));
					toX = translate(Character.getNumericValue(read.charAt(4)));
					toY = translate(read.charAt(3));
					if(!Board.isEmpty(fromX,fromY) && team.equals("White")){
						if((temp = wPlayer.movePiece(fromX, fromY, toX, toY)) == 1 || temp == 2){
							wrongInput = false;
							if(temp == 2){ 
								bPlayer.removePiece(Board.board[toX][toY]); //if white obtains an opponent piece, remove that piece from the black player
							}
						}else{
							printError(team);
						}
						
					}else if(!Board.isEmpty(fromX,fromY) && team.equals("Black")){
						if((temp = bPlayer.movePiece(fromX, fromY, toX, toY)) == 1 || temp == 2){
							wrongInput = false;
							if(temp == 2){ 
								wPlayer.removePiece(Board.board[toX][toY]); //if black obtains an opponent piece, remove that piece from the white player
							}
						}else{
							printError(team);
						}
					}else{
						printError(team);
					}
				}else{
					printError(team);
				}
			}
				
			if(turn == 1){
				turn = 0;
			}else
				turn = 1;
			
		}while(gameStatus == 1);
		
		scanner.close();
	}
	
	//Method to check whether user input is correctly formatted
	public static boolean checkInput(String input){
		
		input = input.toLowerCase();
		
		if(input.length() != 5){
			return false;
		}
		
		if(input.charAt(2) != ' '){
			return false;
		}
		
		if(checkAlpha(input.charAt(0)) && checkDigit(Character.getNumericValue(input.charAt(1))) && checkAlpha(input.charAt(3)) && checkDigit(Character.getNumericValue(input.charAt(4)))){
			return true;
		}else{
			return false;
		}
	}

	public static boolean checkAlpha(char c){
		if(Character.isLetter(c) && c == 'a' ||c == 'b' ||c == 'c' ||c == 'd' ||c == 'e' ||c == 'f' ||c == 'g' ||c == 'h'){
			return true;
		}else{
			return false;
		}
	}
	
	public static boolean checkDigit(int i){
		
		if(i > 8 || i < 1){
			return false;
		}else{
			return true;
		}
		
	}
	
	// Translate user input to corresponding coordinates on the board
	public static int translate(int i){
		switch(i){
			case 8:
				return 0;
			case 7:
				return 1;
			case 6:
				return 2;
			case 5:
				return 3;
			case 4:
				return 4;
			case 3:
				return 5;
			case 2:
				return 6;
			default:
				return 7;
		}
	}
	
	public static int translate(char c){
		
		switch(c){
			case 'a':
				return 0;
			case 'b':
				return 1;
			case 'c':
				return 2;
			case 'd':
				return 3;
			case 'e':
				return 4;
			case 'f':
				return 5;
			case 'g':
				return 6;
			default:
				return 7;
		}
	}
	
	public static void printError(String team){
		System.out.println("Illegal move, try again");
		System.out.print(team + "'s move: ");
	}
}
