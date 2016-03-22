package chess;

import java.util.Scanner;
/**
 * Chess class begins the game of chess. 
 * @author Capki Kim, Daoun Oh
 * 
 */
public class Chess {

	/** indicates which team's turn it is */
	public static int turn = 1;

	/** indicates which piece the pawn is promoting to */
	public static char pawnPromotionTo = 'a';
	
	/** indicates if there was a request for a draw */
	public static int requestForDraw = 0;
	
	/** indicates if there is a check on black team */
	public static int bCheck = 0;
	
	/** indicates if there is a check on white team */
	public static int wCheck = 0;
	
	/** indicates if the game is still going on */
	public static int gameStatus = 1;

	/** initialize black player */
	public static Player bPlayer;
	
	/** initialize white player */
	public static Player wPlayer;
	
	/**
	 * Main method starts the game. 
	 * During the game, if there are special events such as resign, draw, check, checkmate, and pawn promotion, it is caught in this method. 
	 * Also identifies illegal moves or input, and if a piece catches another piece.
	 * Lastly, identifies the winner of the game. 
	 * 
	 * @param args any command line arguments
	 */
	public static void main(String[] args) {
		
		//Initialize board
		Board.init();
		
		//Initialize players
		bPlayer = new Player("black");
		wPlayer = new Player("white");
			
		//Scanner to read user input
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		String read = "";
		
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
			
			if(turn == 1){
				if(wCheck == 1){
					System.out.println("Check");
					wCheck = 0;
				}
				System.out.print("White's move: ");
				System.out.println();
				team = "White";
			}else{
				if(bCheck == 1){
					System.out.println("Check");
					bCheck = 0;
				}
				System.out.print("Black's move: ");
				System.out.println();
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
		} while(gameStatus == 1);
		
		System.out.println("Checkmate");
		
		if(turn == 0){
			System.out.println("White wins");
		}else
			System.out.println("Black wins");
		
		scanner.close();
	}
	
	/**
	 * Method to check whether user input is correctly formatted. 
	 * Identifies resign and a draw, and invalid moves.
	 * 
	 * @param input two sets of positions 
	 * @return 4 player resigns from the game, <p>
	 * 		3 player accepts the draw, <p>
	 * 		2 player offers to end the game in a draw, <p>
	 * 		1 valid input, <p>
	 * 		0 invalid input
	 */
	public static int checkInput(String input){
		
		input = input.toLowerCase();
				
		//Requesting a draw
		if(input.length() == 11 && checkAlpha(input.charAt(0)) && checkDigit(Character.getNumericValue(input.charAt(1))) && checkAlpha(input.charAt(3)) && checkDigit(Character.getNumericValue(input.charAt(4))) && input.substring(6,11).equals("draw?")){
			return 2;
		}
		
		// Accepting resign
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
	
	/**
	 * Checks if input value for file is correct
	 * @param ch input file value
	 * @return true if value is valid (between a-h)
	 * 		false otherwise
	 */
	public static boolean checkAlpha(char ch){
		if(Character.isLetter(ch) && ch == 'a' ||ch == 'b' ||ch == 'c' ||ch == 'd' ||ch == 'e' ||ch == 'f' ||ch == 'g' ||ch == 'h'){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * Checks if input value for rank is correct
	 * @param digit input rank value
	 * @return true if value is valid (between 1-8)
	 * 		false otherwise
	 */
	public static boolean checkDigit(int digit){
		if(digit > 8 || digit < 1){
			return false;
		}else{
			return true;
		}
	}
	
	/**
	 * Translate user input rank value to corresponding coordinates on the board
	 * @param i input rank value
	 * @return translated rank value 
	 */
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
	
	/**
	 * Translate user input file value to corresponding coordinates on the board
	 * @param c input file value
	 * @return translated file value
	 */
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
	
	/**
	 * Checks if player indicated a valid promotion
	 * @param c requested promotion
	 * @return true if requested promotion is valid, <p>
	 * 		false otherwise
	 */
	public static boolean checkPawnPromotion(char c){
		if(c == 'r' || c == 'n' || c == 'q' || c == 'b'){
			return true;
		}else
			return false;
	}
	
	/**
	 * Prints the error message for an illegal move
	 * @param team indicates what team created the error
	 */
	public static void printError(String team){
		System.out.println("Illegal move, try again");
		System.out.print(team + "'s move: ");
	}
}
