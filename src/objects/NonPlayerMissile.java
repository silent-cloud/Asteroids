package objects;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

/**
 * NonPlayerMissile class represents a missile shot from a non-player ship
 *
 * @author  John Lorenz Salva
 * @version "%I%, %G%" 
 */

public class NonPlayerMissile extends Missile implements IMovable, IDrawable, ICollider
{
	/**
	 * Constructor
	 * 
	 * @param s	the current speed of the ship
	 * @param d	the current direction of the ship
	 */
	
	public NonPlayerMissile(int s, int d)
	{
		super(s, d);
		setSize(30);
		setBCirRad(((int)(Math.sqrt(((getSize() / 3) * (getSize() / 3)) + (getSize() * getSize()))) / 2));
		setColor(ColorUtil.GREEN);
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
	 * or non-player ships
	 * 
	 * @param otherObject	the object that collides with this object
	 */

	@Override
	public void handleCollision(ICollider otherObject) 
	{
		if (!(otherObject instanceof SpaceStation) && !(otherObject instanceof NonPlayerShip))
			setRemoved(true);
	}
	
	/**
	 * Represents the non-player missile as a filled in rectangle
	 * on the game map by using its size and location
	 * 
	 * @param g				the graphics class of the game map
	 * @param pCmpRelPrnt	the location of the game map relative to the screen
	 */
	
	@Override
	public void draw(Graphics g, Point pCmpRelPrnt)
	{
		g.setColor(getColor());
		int s = getSize();
		g.fillRect(pCmpRelPrnt.getX() + ((int)getLocationX()) - (s / 6), pCmpRelPrnt.getY() + ((int)getLocationY()) - (s / 2), (s / 3), s);
	}
	
	/**
	 * String representation of the NonPlayerMissile object
	 */
	
	@Override
	public String toString()
	{
		return (
				"Non Player Missile: location = " + ((Math.round(getLocationX() * 10.0) / 10.0)) + ", " + ((Math.round(getLocationY() * 10.0)) / 10.0) +
				" color = " + "[ " + ColorUtil.red(getColor()) + " , "  + ColorUtil.green(getColor()) + " , " + ColorUtil.blue(getColor()) + " ]" +
				" speed = " + Math.round(getSpeed()) +
				" direction = " + getDirection() +
				" fuel level = " + getFuelLevel()
				);
	}
}
