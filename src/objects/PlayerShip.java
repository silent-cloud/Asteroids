package objects;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

/**
 * PlayerShip class represents all the functions and stats of the player ship
 * 
 * @author  John Lorenz Salva
 * @version "%I%, %G%"
 */

public class PlayerShip extends Ship implements ISteerable, IMovable, IDrawable, ICollider
{
	private final int MAX_MISSILE_COUNT = 10;
	private final int INITIAL_SPEED = 0;						//Constant for the initial speed of the player
	private final int INITIAL_DIRECTION = 0;					//Constant for the initial direction of the player
	private final int SPEED_INC = 20;							//Constant for how much can the player speed increase
	private final int SPEED_DEC = 20;							//Constant for how much can the player speed decrease
	
	/**
	 * Constructor
	 */
	
	public PlayerShip()
	{
		super(new SteerableMissileLauncher(0, 0, 0, 0));
		setMissileCount(MAX_MISSILE_COUNT);
		setSize(80);
		setBCirRad(getSize() / 2);
		setSpeed(INITIAL_SPEED);
		setDirection(INITIAL_DIRECTION);
		setColor(ColorUtil.WHITE);
		getMissileLauncher().setSpeed(getSpeed());
		getMissileLauncher().setSize(getBCirRad());
		getMissileLauncher().setColor(ColorUtil.WHITE);
	}
	
	/**
	 * Updates the direction of the player ship towards the left
	 */
	
	public void turnLeft()
	{
		setDirection((getDirection() + TURN_LEFT));
	}
	
	/**
	 * Updates the direction of the player ship towards the right
	 */
	
	public void turnRight() 
	{
		setDirection((getDirection() + TURN_RIGHT));
	}
	
	/**
	 * Increases the speed of the player's ship. Cannot go above
	 * the maximum speed.
	 */
	
	public void incSpeed()
	{
		if (!((getSpeed() + SPEED_INC) > MAX_SPEED))
		{
			setSpeed(getSpeed() + SPEED_INC);
			getMissileLauncher().setSpeed(getSpeed() + SPEED_INC);
		} else {
			System.out.println("Cannot increase speed. Speed is now at maximum value possible.");
		}
	}
	
	/**
	 * Decreases the speed of the player's ship. Cannot go below
	 * 0.
	 */
	
	public void decSpeed()
	{
		if (!((getSpeed() - SPEED_INC) < MIN_SPEED))
		{
			setSpeed(getSpeed() - SPEED_DEC);
			getMissileLauncher().setSpeed(getSpeed() - SPEED_DEC);
		} else {
			System.out.println("Cannot decrease speed. The ship is now stationary.");
		}
	}
	
	@Override
	public boolean collidesWith(ICollider otherObject)
	{
		boolean result = false;
		int thisCenterX = (int)getLocationX();
		int thisCenterY = (int)getLocationY();
		int otherCenterX = (int)((GameObject)otherObject).getLocationX();
		int otherCenterY = (int)((GameObject)otherObject).getLocationY();
		int dx = thisCenterX - otherCenterX;
		int dy = thisCenterY - otherCenterY;
		int distBetween = ((dx * dx) + (dy * dy));
		int thisRadius = getBCirRad();
		int otherRadius = ((GameObject)otherObject).getBCirRad();
		int radiiSqr = (thisRadius*thisRadius + 2*otherRadius*thisRadius + otherRadius*otherRadius);
		if (distBetween <= radiiSqr)
			result = true;
		return result;
	}

	/**
	 * Removes the object from play when it collides with
	 * any other collidable object that aren't space stations
	 * or player missiles or renews its missile count when within
	 * the bounds of a space station.
	 * 
	 * @param otherObject	the object that collides with this object
	 */
	
	@Override
	public void handleCollision(ICollider otherObject) 
	{
		if (!(otherObject instanceof SpaceStation) && !(otherObject instanceof PlayerMissile))
		{
			setRemoved(true);
		} else if (otherObject instanceof SpaceStation) {
			setMissileCount(MAX_MISSILE_COUNT);
		}
	}
	
	/**
	 * Represents the player ship as a filled in triangle
	 * on the game map by using its size and location.
	 * 
	 * @param g				the graphics class of the game map
	 * @param pCmpRelPrnt	the location of the game map relative to the screen
	 */
	
	@Override
	public void draw(Graphics g, Point pCmpRelPrnt)
	{
 		getMissileLauncher().draw(g, pCmpRelPrnt);
		g.setColor(getColor());
		int s = getSize() / 2;
		int[] xPoints = new int[3];
		int[] yPoints = new int[3];
		xPoints[0] = pCmpRelPrnt.getX() + ((int)getLocationX());
		yPoints[0] = pCmpRelPrnt.getY() + ((int)getLocationY()) - s;
		xPoints[1] = pCmpRelPrnt.getX() + ((int)getLocationX()) - ((int)(s * (Math.sin(Math.toRadians(60)))));
		yPoints[1] = pCmpRelPrnt.getY() + ((int)getLocationY()) + ((int)(s * (Math.cos(Math.toRadians(60)))));
		xPoints[2] = pCmpRelPrnt.getX() + ((int)getLocationX()) + ((int)(s * (Math.sin(Math.toRadians(60)))));
		yPoints[2] = yPoints[1];
 		g.fillPolygon(xPoints, yPoints, 3);
	}
	
	/**
	 * String representation of the Player Ship
	 */
	
	@Override
	public String toString()
	{
		return (
				"Player Ship: location = " + ((Math.round(getLocationX() * 10.0) / 10.0)) + ", " + ((Math.round(getLocationY() * 10.0)) / 10.0) +
				" color = " + "[ " + ColorUtil.red(getColor()) + " , "  + ColorUtil.green(getColor()) + " , " + ColorUtil.blue(getColor()) + " ]" +
				" speed = " + Math.round(getSpeed()) +
				" direction = " + getDirection() +
				" missile launcher direction = " + getMissileLauncher().getDirection()
				);
	}

}
