import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * A tic-tac-toe program with two players. Player 1 puts
 * down Os and player 2 puts down Xs. A winner is
 * declared when they get three of their shape in a row
 * vertically, horizontally, or diagonally.
* @author Matt Hynes
*/


public class XOX {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int player = 1;
		String[][] gameBoard = new String[3][3];
		boardClear(gameBoard);
		
		while(!winCheck(gameBoard)){
			System.out.println("Player " + player + ", please choose your spot on the board using the format: row column.");
			printBoard(gameBoard);
			int row = sc.nextInt();
			int col = sc.nextInt();
			try {
				if (player == 1){
					gameBoard[row][col] = " O";
					player = 2;
				}
				else if(player == 2){
					gameBoard[row][col] = " X";
					player = 1;
				}
			} catch(ArrayIndexOutOfBoundsException ex) {
				System.out.println("Location out of bounds! Try again.");
			  } 
		}
		printBoard(gameBoard); //Prints final image of game board
	}
	//clear the board by filling all spots with " _"
	public static void boardClear(String[][] gameBoard){
		for(int i = 0; i < gameBoard.length; i++){
			for(int j = 0; j < gameBoard[0].length; j++){
				gameBoard[i][j] = " _";
			}
		}
	}
	//print the board's current state
	public static void printBoard(String[][] gameBoard){
		System.out.printf("%3d %d %d%n", 0, 1, 2);
		for(int i = 0; i < gameBoard.length; i++){
			System.out.print(i);
			for(int j = 0; j < gameBoard[0].length; j++){
				System.out.print(gameBoard[i][j]);
			}
			System.out.println();
		}
	}
	//check if a winner has been found from the three win methods
	public static boolean winCheck(String[][] gameBoard){
		char vWin = verticalWin(gameBoard);
		char hWin = horizontalWin(gameBoard);
		char dWin = diagonalWin(gameBoard);
		
		if(vWin == 'X'){ System.out.println("Player 2 Wins!"); return true;}
		else if(vWin == 'O'){ System.out.println("Player 1 Wins!"); return true;}
		else if(hWin == 'O'){ System.out.println("Player 1 Wins!"); return true;}
		else if(dWin == 'O'){ System.out.println("Player 1 Wins!"); return true;}
		else if(hWin == 'X'){ System.out.println("Player 2 Wins!"); return true; }
		else if(dWin == 'X'){ System.out.println("Player 2 Wins!"); return true;}

		return false;
	}
	//check if a player has 3 spots filled vertically
	public static char verticalWin(String[][] gameBoard){
		for(int i = 0; i < gameBoard.length; i++){
			for(int j = 0; j < gameBoard[0].length; j++){
				if(gameBoard[0][j] == " X" && gameBoard[1][j] == " X" && gameBoard[2][j] == " X"){ //check every column for an X
					return 'X'; //return X to indicate player 2 has won
				}
				if(gameBoard[0][j] == " O" && gameBoard[1][j] == " O" && gameBoard[2][j] == " O"){
					return 'O';
				}
			}
		}
		return 'N'; //default return value so program will compile, returning it does nothing
	}
	//check if a player has 3 spots filled horizontally
	public static char horizontalWin(String[][] gameBoard){
		for(int i = 0; i < gameBoard.length; i++){
			for(int j = 0; j < gameBoard[0].length; j++){
				if(gameBoard[i][0] == " X" && gameBoard[i][1] == " X" && gameBoard[i][2] == " X"){ //check every row for an X
					return 'X';
				}
				if(gameBoard[i][0] == " O" && gameBoard[i][1] == " O" && gameBoard[i][2] == " O"){
					return 'O';
				}
			}
		}
		return 'N';
	}
	//check if a player has 3 spots filled diagonally
	public static char diagonalWin(String[][] gameBoard){
		for(int i = 0; i < gameBoard.length; i++){
			for(int j = 0; j < gameBoard[0].length; j++){
				if((gameBoard[0][0] == " X" && gameBoard[1][1] == " X" && gameBoard[2][2] == " X") || (gameBoard[0][2] == " X" && gameBoard[1][1] == " X" && gameBoard[2][0] == " X")){ //check both diagonals for an X
					return 'X';
				}
				if((gameBoard[0][0] == " O" && gameBoard[1][1] == " O" && gameBoard[2][2] == " O") || (gameBoard[0][2] == " O" && gameBoard[1][1] == " O" && gameBoard[2][0] == " O")){
					return 'O';
				}
			}
		}
		return 'N';
	}
}