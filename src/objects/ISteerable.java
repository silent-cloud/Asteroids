package objects;

/**
 * ISteerable class interface enforces steerable object direction changes.
 *
 * @author  John Lorenz Salva
 * @version "%I%, %G%" 
 */

public interface ISteerable
{
	final int TURN_LEFT = 20;		//Direction change value for turning counterclockwise
	final int TURN_RIGHT = -20;		//Direction change value for turning clockwise
	
	/**
	 * Enforces a steerable game object to turn left
	 */
	
	void turnLeft();
	
	/**
	 * Enforces a steerable game object to turn right
	 */
	
	void turnRight();
}
