package gameelements;

public interface Board<T> {
	public void newGame(); 
	public T getGameState(); 
	public int[] move(int...position); 
}
