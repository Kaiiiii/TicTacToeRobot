import java.util.Scanner;

import Exceptions.InvalidMoveException;
import Exceptions.SameMoveException;
import GameElements.Board;
import GameElements.Player;

public class Game {
	private static Player nextPlayer (Player firstPlayer, Player secondPlayer, Player currPlayer){
		return currPlayer.equals(firstPlayer) ? secondPlayer : firstPlayer;
	}
	
	private static void printBoard(char[][] board){
		for (int i = 0; i < board.length; i++){
			System.out.print("|");
			for (int j = 0; j < board[i].length; j++){
				if (board[i][j] == Character.MIN_VALUE) System.out.print(" |");
				else System.out.print(Character.toString(board[i][j]) + '|'); 
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
	
				if (row < 0 || row > 2 || column < 0 || column > 2){
					throw new InvalidMoveException("rows: 0 - 2", "columns: 0 - 2");
				}
				board.move(currPlayer, row, column);
				currPlayer = nextPlayer(firstPlayer, secondPlayer, currPlayer);
				
			} catch(SameMoveException sme){
				System.out.println(sme.getMessage());
			} catch(Exception e){
				System.out.println(e.getMessage());
			}
			
			printBoard(board.getGameState());
		}
		
		System.out.println();
		System.out.println(Character.toString(board.gameWinner(firstPlayer, secondPlayer).getChar()) + " wins!");
	}
}