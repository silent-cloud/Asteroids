package objects;

/**
 * Ship class describes all the ship's functions and stats.
 *
 * @author  John Lorenz Salva
 * @version "%I%, %G%"
 */

public abstract class Ship extends MovableObject implements IMovable
{
	private Integer missileCount;			//The amount of missiles left in the ship
	private final MissileLauncher ml;	//The only missile launcher for the ship
	
	/**
	 * Constructor
	 * 
	 * @param ml The only missile launcher for the ship
	 */
	
	public Ship(MissileLauncher ml)
	{
		super();
		this.ml = ml;
	}
	
	/**
	 * Updates the ship's x-coordinate location and it's
	 * missile launcher's x-coordinate location together.
	 * 
	 * @param x	the x-coordinate location for the ship and it's
	 * 			missile launcher
	 */
	
	@Override
	public void setLocationX(double x)
	{
		super.setLocationX(x);
		ml.setLocationX(this.getLocationX());
	}
	
	/**
	 * Updates the ship's y-coordinate location and it's
	 * missile launcher's y-coordinate location together.
	 * 
	 * @param y	the y-coordinate location for the ship and it's
	 * 			missile launcher
	 */
	
	@Override
	public void setLocationY(double y)
	{
		super.setLocationY(y);
		ml.setLocationY(this.getLocationY());
	}
	
	/**
	 * Updates the ship's maximum x-coordinate location and it's
	 * missile launcher's maximum x-coordinate location together.
	 * 
	 * @param x	the x-coordinate location for the ship and it's
	 * 			missile launcher
	 */
	
	@Override
	public void setMaxLocationX(double x)
	{
		super.setMaxLocationX(x);
		ml.setMaxLocationX(x);
	}
	
	/**
	 * Updates the ship's maximum y-coordinate location and it's
	 * missile launcher's maximum y-coordinate location together.
	 * 
	 * @param y	the y-coordinate location for the ship and it's
	 * 			missile launcher
	 */
	
	@Override
	public void setMaxLocationY(double y)
	{
		super.setMaxLocationY(y);
		ml.setMaxLocationY(y);
	}
	
	/**
	 * Gets the missile launcher that corresponds with the ship.
	 * 
	 * @return the missile launcher of the ship
	 */
	
	public MissileLauncher getMissileLauncher()
	{
		return ml;
	}
	
	/**
	 * Gets the amount of missiles left in the ship.
	 *
	 * @return the amount of missiles in the ship
	 */
	
	public int getMissileCount()
	{
		return missileCount;
	}
	
	/**
	 * Updates the amount of missiles the ship has.
	 * 
	 * @param m	the amount of missiles to be set in the ship
	 */
	
	public void setMissileCount(int m)
	{
		missileCount = m;
	}
	
	/**
	 * Creates a new missile object depending on missile launcher type.
	 * If the ship has no missiles, an error message is given.
	 */
	
	public void shootMissile()
	{	
		getMissileLauncher().launchMissile();
		setMissileCount(getMissileCount() - 1);
	}
	
	/**
	 * Checks if the player ship has run out of missiles
	 * 
	 * @return boolean
	 */
	
	public boolean checkMissileCount()
	{
		if (getMissileCount() != 0)
			return true;
		else
			return false;
	}
	
	/**
	 * Dictates how the ship object should move in the game world
	 * 
	 * @see Assignment # 3.pdf
	 */
	
	@Override
	public void move(int t)
	{
		int theta = DIR_CALC - getDirection();
		double distance = getSpeed() * (t / 1000.0);
		double newX = getLocationX() + ((Math.cos(Math.toRadians(theta))) * distance);
		double newY = getLocationY() + ((Math.sin(Math.toRadians(theta))) * distance);
		setLocationX(newX);
		setLocationY(newY);
		getMissileLauncher().setLocationX(newX);
		getMissileLauncher().setLocationY(newY);
	}
}
