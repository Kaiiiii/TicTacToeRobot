package GameElements;

import Exceptions.SameMoveException;
import GameElements.AbstractClasses.Player;

public class DumbPlayer extends Player{
	//Get own double row 
	//Get enemy double row 
	//Get own intercept 
	//Get enemy intercept
	
	/*Get double row
	 * returns [int, int, int, int]
	 * doubleOwnRow, doubleEnemyRow, interceptRow, interceptedRow
	 * count symbol on each row :D :D :D :D :D :D :D  
	*/
	
	public DumbPlayer(char playerChar){
		super(playerChar); 
		this.w1_doubleOwnRow = 20;
		this.w2_doubleEnemyRow = -30;
		this.w3_interceptRow = 60;
		this.w4_interceptedRow = -30;
		this.w5_completedOwnRow = 50;
		this.w6_completedEnemyRow = -50;
	}
}
