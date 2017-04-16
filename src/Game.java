import java.util.Scanner;

import Exceptions.InvalidMoveException;
import GameElements.Board;
import GameElements.Player;

public class Game {
	private static Player nextPlayer (Player firstPlayer, Player secondPlayer, Player currPlayer){
		return currPlayer.equals(firstPlayer) ? secondPlayer : firstPlayer;
	}
	
	private static void printBoard(char[][] board){
		for (int i = 0; i < board.length; i++){
			for (int j = 0; j < board[i].length; j++){
				if (board[i][j] == Character.MIN_VALUE) System.out.print(" \t");
				else System.out.print(Character.toString(board[i][j]) + '\t'); 
			}
			
			System.out.println();
		}
	}
	
	public static void main(String[] args){
		Board board = new Board();
		
		Player firstPlayer = new Player('o'); 
		Player secondPlayer = new Player('x'); 
		Player currPlayer = firstPlayer;
		
		Scanner input = new Scanner(System.in);
		
		while(!board.gameEnded()){
			try{
				System.out.println("Please enter the next move for player " + currPlayer.getChar() + ".");
				
				int row = Integer.parseInt(input.next());
				int column = Integer.parseInt(input.next());
	
				if (row < 0 || row > 3 || column < 0 || column > 3){
					throw new InvalidMoveException();
				}
				board.move(currPlayer, row, column);
			} catch(Exception e){
				System.out.println("blah");
				System.out.println(e.getMessage());
			}
			
			
			currPlayer = nextPlayer(firstPlayer, secondPlayer, currPlayer);
			printBoard(board.getGameState());
		}
		
		System.out.println();
		System.out.println(Character.toString(board.gameWinner(firstPlayer, secondPlayer).getChar()) + " wins!");
	}
}