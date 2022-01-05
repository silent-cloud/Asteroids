package objects;

/**
 * MovableObject class describes how all movable objects should move.
 *
 * @author  John Lorenz Salva
 * @version "%I%, %G%"
 */

public abstract class MovableObject extends GameObject implements IMovable
{
	protected final int MIN_SPEED = 0;		//Minimum speed for movable objects
	protected final int MIN_NPO_SPEED = 50;	//Minimum speed for non-player objects
	protected final int MAX_SPEED = 200;	//Maximum speed for movable objects
	protected final int MIN_DIR = 0;		//Minimum direction for movable objects
	protected final int MAX_DIR = 359;		//Maximum direction for movable objects
	
	private int speed;						//Object's current speed
	private int direction;					//Object's current direction
	
	/**
	 * Class Constructor
	 */
	
	public MovableObject()
	{
		super();
		speed = 0;
		direction = 0;
	}
	
	/**
	 * Gets the movable game object's current speed.
	 * 
	 * @return the speed of the movable game object
	 */
	
	public int getSpeed()
	{
		return speed;
	}
	
	/**
	 * Gets the movable game object's current direction.
	 * 
	 * @return the direction of the movable game object
	 */
	
	public int getDirection()
	{
		return direction;
	}
	
	/**
	 * Updates the current speed of the movable game object.
	 * 
	 * @param s the new speed value for the movable game object.
	 * 			Cannot be negative nor be above maximum speed.
	 */
	
	public void setSpeed(int s)
	{
		if (s > MAX_SPEED && !(this instanceof Missile))
		{
			speed = MAX_SPEED;
		} else if (s < MIN_SPEED) {
			speed = MIN_SPEED;
		} else {
			speed = s;
		}
	}
	
	/**
	 * Updates the current direction of the movable game object
	 * 
	 * @param d the new direction value for the movable game object.
	 * 			Cannot be negative nor be above 360 degrees.
	 */
	
	public void setDirection(int d)
	{
		if (d < MIN_DIR)
			direction = d + 360;
		else if (d > MAX_DIR)
			direction = d - 360;
		else
			direction = d;
	}
	
	/**
	 * Dictates how a movable game object should move in the game world.
	 * 
	 * @see Assignment # 3.pdf
	 */
	
	public void move(int t)
	{
		int theta = DIR_CALC - direction;
		double distance = speed * (t / 1000.0);
		setLocationX(getLocationX() + ((Math.cos(Math.toRadians(theta))) * distance));
		setLocationY(getLocationY() + ((Math.sin(Math.toRadians(theta))) * distance));
	}

}
