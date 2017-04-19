package GameElements;

public class SmartPlayer extends DumbPlayer{
	public SmartPlayer (char playerChar){
		super(playerChar);
	}
	
	//TODO: mocked this
	public int[] scaleWeights() {
		int[] newWeights = { 0, 0, 0, 0, 0, 0 };
		return newWeights;
	}
}
