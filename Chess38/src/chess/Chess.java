package chess;

import java.util.Scanner;

public class Chess {

	static int turn = 1;

	static char pawnPromotionTo = 'a';
	
	static int requestForDraw = 0;
	
	static int bCheck = 0;
	
	static int wCheck = 0;

	//Declare players
	static Player bPlayer;
	static Player wPlayer;
	
	public static void main(String[] args) {
		
		//Initialize board
		Board.init();
		
		//Initialize players
		bPlayer = new Player("black");
		wPlayer = new Player("white");
		
				
		//Scanner to read user input
		Scanner scanner = new Scanner(System.in);
		String read = "";
		
		//To check status of game
		int gameStatus = 1;
		
		//Variable used to check input
		boolean wrongInput;
		String team = "";
		
		//Starting/Destination coordinates
		int fromX, fromY, toX, toY;
		
		//temp int
		int temp, temp2;
		
		
		do {
			//Draw board
			Board.drawBoard();
			wrongInput = true;
			
			//
			
			for(int i = 0 ; i < 16 ; i++){
				if(team.equals("White")){
					if(Chess.bPlayer.pieces[i] != null){
						System.out.print(bPlayer.pieces[i].getType() + " ");
					}
				}else{
					if(Chess.wPlayer.pieces[i] != null){
						System.out.print(wPlayer.pieces[i].getType() + " ");
					}
				}
			}
			
			System.out.println();
			//
			
			if(turn == 1){
				if(wCheck == 1){
					System.out.println("Check");
				}
				System.out.print("White's move: ");
				team = "White";
			}else{
				if(bCheck == 1){
					System.out.println("Check");
				}
				System.out.print("Black's move: ");
				team = "Black";
			}
			
			//Check user input
			while(wrongInput){
				
				if((temp2 = checkInput(read = scanner.nextLine().toLowerCase())) == 1 || temp2 == 2 || temp2 == 5){
					
					if(temp2 == 2){
						requestForDraw = 1;
					}else
						requestForDraw = 0;
					
					if(temp2 == 5){
						pawnPromotionTo = read.charAt(6);
					}
					
					
					System.out.println();
					fromX = translate(Character.getNumericValue(read.charAt(1)));
					fromY = translate(read.charAt(0));
					toX = translate(Character.getNumericValue(read.charAt(4)));
					toY = translate(read.charAt(3));
					if(!Board.isEmpty(fromX,fromY) && team.equals("White")){
						if((temp = wPlayer.movePiece(fromX, fromY, toX, toY, pawnPromotionTo)) == 1 || temp == 2){
							wrongInput = false;
							if(temp == 2){ 
								bPlayer.removePiece(toX,toY); //if white obtains an opponent piece, remove that piece from the black player
							}
						}else{
							printError(team);
						}
						
					}else if(!Board.isEmpty(fromX,fromY) && team.equals("Black")){
						if((temp = bPlayer.movePiece(fromX, fromY, toX, toY, pawnPromotionTo)) == 1 || temp == 2){
							wrongInput = false;
							if(temp == 2){ 
								wPlayer.removePiece(toX,toY); //if black obtains an opponent piece, remove that piece from the white player
							}
						}else{
							printError(team);
						}
					}else{
						printError(team);
					}
				}else if(temp2 == 3 && requestForDraw == 1){ //accepting draw
					System.out.println("Game over: The game has ended in a draw.");
					return;
				}else if(temp2 == 4){ //resigning
					System.out.println("Game over: "+ team + " player has resigned.");
					return;
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
	public static int checkInput(String input){
		
		input = input.toLowerCase();
				
		//Requesting a draw
		if(input.length() == 11 && checkAlpha(input.charAt(0)) && checkDigit(Character.getNumericValue(input.charAt(1))) && checkAlpha(input.charAt(3)) && checkDigit(Character.getNumericValue(input.charAt(4))) && input.substring(6,11).equals("draw?")){
			return 2;
		}
		
		if(input.equals("resign")){
			return 4;
		}
		
		//Accepting a draw
		if(input.equals("draw")){
			return 3;
		}
		
		if(input.length() < 5){
			return 0;
		}
		
		//Input for promotion
		if(input.length() != 5){
			if(checkAlpha(input.charAt(0)) && checkDigit(Character.getNumericValue(input.charAt(1))) && checkAlpha(input.charAt(3)) && checkDigit(Character.getNumericValue(input.charAt(4))) && input.length() == 7 && input.charAt(2) == ' ' && input.charAt(5) == ' ' && checkPawnPromotion(input.charAt(6))){
				return 5;
			}
			
			return 0;
		}
		
		if(input.charAt(2) != ' '){
			return 0;
		}
		
		if(checkAlpha(input.charAt(0)) && checkDigit(Character.getNumericValue(input.charAt(1))) && checkAlpha(input.charAt(3)) && checkDigit(Character.getNumericValue(input.charAt(4)))){
			return 1;
		}else{
			return 0;
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
	
	//Check if player indicated a valid promotion
	public static boolean checkPawnPromotion(char c){
		if(c == 'r' || c == 'n' || c == 'q' || c == 'b'){
			return true;
		}else
			return false;
	}
	
	public static void printError(String team){
		System.out.println("Illegal move, try again");
		System.out.print(team + "'s move: ");
	}
}
