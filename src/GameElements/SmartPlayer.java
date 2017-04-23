package GameElements;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import Exceptions.InvalidMoveException;
import Exceptions.SameMoveException;

public class SmartPlayer extends DumbPlayer {
	private static final double WINNING_SCORE = 100, LOSING_SCORE = -100, DRAW_SCORE = 0;

	private final double modifier = 0.4;

	public SmartPlayer(char playerChar) {
		super(playerChar);
	}

	public Board nextMove(Board boardState) throws SameMoveException, InvalidMoveException {
		Board nextBoard = super.nextMove(boardState);

		return nextBoard;
	}

	public void updateWeights(ArrayList<Board> history){
		// Vtrain: get training value of board --> actual value 
		// Vboard: get score of current board
		
		Queue<Double> trainingValues = this.getActualBoardValue(history);
		double error;
		int[] boardStateAnalysis;
		Board currBoard;
		
		for (int i = 0; i < history.size(); i += 2){
			currBoard = history.get(i);
			boardStateAnalysis = this.analyzeBoard(currBoard);
			error = trainingValues.remove() - this.getPredictedValue(currBoard);
			
			this.w1_doubleOwnRow += modifier * error * boardStateAnalysis[0];
			this.w2_doubleEnemyRow += modifier * error * boardStateAnalysis[1]; 
			this.w3_ownFreeRow += modifier * error * boardStateAnalysis[2];
			this.w4_enemyFreeRow += modifier * error * boardStateAnalysis[3];
			this.w5_interceptEnemyRow += modifier * error * boardStateAnalysis[4];
			this.w6_interceptedOwnRow += modifier * error * boardStateAnalysis[5];
		}
	}
	
	private Queue<Double> getActualBoardValue(ArrayList<Board> history){
		int historyCount = history.size();
		Queue<Double> historyScores = new LinkedList<Double>();
		
		for (int i = 0; i < historyCount; i += 2){			
			if (i + 2 >= historyCount - 1){
				/*
				 * This condition reflects the case where the next entry in the game history
				 * is the last state (i.e. the opponent's move has ended the game). 
				 */
				if (i + 1 == historyCount - 1 && history.get(i + 1).gameEnded()){				
					char winnerChar = history.get(i + 1).gameWinner();
					if (winnerChar == Character.MIN_VALUE){
						historyScores.add(DRAW_SCORE);
						continue;
					}
					historyScores.add(LOSING_SCORE);
					continue;
				}
				
				/*
				 * The following two cases are caught in this condition: 
				 * 	1. The successor state is the end of the game.
				 * 	2. The current state is the end of the game.
				 */
				Board currBoard = history.get(i);
				
				if (i + 2 == historyCount - 1){
					currBoard = history.get(i + 2);
				}
				
				char winnerChar = currBoard.gameWinner();
				
				if(winnerChar == this.playerChar){
					historyScores.add(WINNING_SCORE);
					continue;
				} 
				if (winnerChar == Character.MIN_VALUE){
					historyScores.add(DRAW_SCORE);
					continue;
				}
				historyScores.add(LOSING_SCORE);
				continue;
			}
			
			historyScores.add(this.getPredictedValue(history.get(i)));
		}
		
		return historyScores;
	}
	
}
