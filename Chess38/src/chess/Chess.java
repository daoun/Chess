package chess;

import java.util.Scanner;

public class Chess {

	public static void main(String[] args) {
		
		//Initialize board
		Board.init();
		
		//Initialize player
		Player bPlayer = new Player("black");
		Player wPlayer = new Player("white");
		
		// 0 indicates black's turn, 1 indicates white's turn
		int turn = 1;
		
		//Scanner to read user input
		Scanner scanner = new Scanner(System.in);
		String read = "";
		
		//
		boolean wrongInput = true;
		
		
		do {
			//Draw board
			Board.drawBoard();
			
			if(turn == 1){
				while(wrongInput){
					System.out.println("White's move: ");
					checkInput(scanner.nextLine());
				}
				
			}
			
		}while(true);
		
		//Board.drawBoard();
	}
	
	//Method to check whether user input is correctly formatted
	public static boolean checkInput(String input){
		return false;
	}

}
