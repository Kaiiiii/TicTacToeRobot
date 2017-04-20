package GameElements;

import Exceptions.SameMoveException;
import GameElements.AbstractClasses.Player;

public class DumbPlayer extends Player{
	public DumbPlayer(char playerChar){
		super(playerChar); 
		this.w1_doubleOwnRow = 20;
		this.w2_doubleEnemyRow = -30;
		this.w3_interceptRow = 60;
		this.w4_interceptedRow = -30;
		this.w5_completedOwnRow = 100;
		this.w6_completedEnemyRow = -100;
	}
}
