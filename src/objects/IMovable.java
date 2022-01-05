package objects;

/**
 * IMovable interface enforces movable objects to move.
 *
 * @author  John Lorenz Salva
 * @version "%I%, %G%" 
 */

public interface IMovable 
{
	final int DIR_CALC = 90;	//Global Constant for calculating the location in where the object moves
	
	/**
	 * Enforces the movement of all movable game objects.
	 */
	
	void move(int t);
}
