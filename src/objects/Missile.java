package objects;

/**
 * Missile class describes how missiles should function.
 *
 * @author  John Lorenz Salva
 * @version "%I%, %G%" 
 */

public abstract class Missile extends MovableObject implements IMovable
{
	private int fuelLevel;						//Fuel level of the missile
	private final int INITIAL_FUEL_LEVEL = 10;	//The starting fuel level once launched.
	private final int SPEED_INCREASE = 100;		//Added speed so that the missile travels faster than the ship
	private boolean speedFinalized;				//Flag for checking if the speed has been set
	private boolean directionFinalized;			//Flag for checking if the direction has been set
	
	/**
	 * Class Constructor
	 * 
	 * @param s the speed of the ship from where the missile was launched
	 * @param d the direction of the ship from where the missile was launched
	 */
	
	public Missile(int s, int d)
	{
		setSpeed(s + SPEED_INCREASE);
		setDirection(d);				
		fuelLevel = INITIAL_FUEL_LEVEL;
	}
	
	/**
	 * Updates the fuel level value for the missile
	 * 
	 * @param f	the new fuel level value
	 */
	
	public void setFuelLevel(int f)
	{
		fuelLevel = f;
	}
	
	/**
	 * Gets the fuel level of the missile.
	 * 
	 * @return the fuel level of the missile.
	 */
	
	public int getFuelLevel()
	{
		return fuelLevel;
	}
	
	/**
	 * Decrements the fuel level of the missile by one.
	 */
	
	public void decFuelLevel()
	{
		fuelLevel--;
	}
	
	/**
	 * Finalizes that the missile's speed so it can no longer
	 * be changed once constructed.
	 * 
	 * @param s the speed of the ship where the missile was launched from
	 */
	
	@Override
	public void setSpeed(int s)
	{
		if(!speedFinalized)
			super.setSpeed(s);
	}
	
	/**
	 * Finalizes the missile's direction so it can no longer
	 * be changed once constructed.
	 * 
	 * @param d the direction of the ship where the missile was launched from
	 */
	
	@Override
	public void setDirection(int d)
	{
		if (!directionFinalized)
			super.setDirection(d);
	}
}
