import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import Exceptions.SameMoveException;
import GameElements.Board;
import GameElements.DumbPlayer;
import GameElements.AbstractClasses.Player;

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
		
		Player firstPlayer = new DumbPlayer('o'); 
		Player secondPlayer = new DumbPlayer('x'); 
		Player currPlayer = firstPlayer;
		
		Scanner input = new Scanner(System.in);
		
		while(!board.gameEnded()){
			try{
				System.out.println("Player " + currPlayer.getChar() + "'s turn!");
				int[] nextMove = currPlayer.nextMove(board);
				System.out.println(">> Player " + currPlayer.getChar() + "has chosen row " +  
									nextMove[0] + " col " + nextMove[1] + ".");
				
				board.move(currPlayer, nextMove[0], nextMove[1]);
				currPlayer = nextPlayer(firstPlayer, secondPlayer, currPlayer);
				
				TimeUnit.SECONDS.sleep(1);
			} catch(SameMoveException sme){
				System.out.println(sme.getMessage());
			} catch(Exception e){
				System.out.println(e.getMessage());
			}
			
			printBoard(board.getGameState());
		}
		
		System.out.print("\n>> ");
		Player winner = board.gameWinner(firstPlayer, secondPlayer);
		
		if (winner != null) System.out.println(Character.toString(winner.getChar()) + " wins!");
		else System.out.println("It's a draw!");
	}
}