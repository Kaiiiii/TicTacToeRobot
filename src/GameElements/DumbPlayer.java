package GameElements;

import GameElements.AbstractClasses.Player;

public class DumbPlayer extends Player {
	public DumbPlayer(char playerChar) {
		super(playerChar, 0.5, -0.5, 0.5, -0.5, 0.5, -0.5);
	}
}
