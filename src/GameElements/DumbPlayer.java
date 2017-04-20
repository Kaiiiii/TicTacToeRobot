package GameElements;

import Exceptions.SameMoveException;
import GameElements.AbstractClasses.Player;

public class DumbPlayer extends Player{
	public DumbPlayer(char playerChar){
		super(playerChar, 20, -30, 60, -30, 100, -100); 
	}
}
