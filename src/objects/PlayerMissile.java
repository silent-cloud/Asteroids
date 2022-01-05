package objects;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

/**
 * PlayerMissile class represents the player ship's missile
 *
 * @author  John Lorenz Salva
 * @version "%I%, %G%"
 */

public class PlayerMissile extends Missile implements IMovable, IDrawable, ICollider, ISelectable
{	
	private boolean selected;
	
	/**
	 * Class Constructor
	 * 
	 * @param s the player ship's current speed
	 * @param d the player ship's current direction
	 */
	
	public PlayerMissile(int s, int d)
	{
		super(s, d);
		setSize(30);
		setBCirRad(((int)(Math.sqrt(((getSize() / 3) * (getSize() / 3)) + (getSize() * getSize()))) / 2));
		setColor(ColorUtil.CYAN);
	}

	@Override
	public void setSelected(boolean yesNo)
	{
		selected = yesNo;
	}

	@Override
	public boolean isSelected()
	{
		if (selected)
			return true;
		else
			return false;
	}

	@Override
	public boolean contains(Point pPtrRelPrnt, Point pCmpRelPrnt)
	{
		int ptrX = pPtrRelPrnt.getX();
		int ptrY = pPtrRelPrnt.getY();
		int cmpX = pCmpRelPrnt.getX();
		int cmpY = pCmpRelPrnt.getY();
		int s = getSize();
		boolean betweenX = ((ptrX >= (cmpX + ((int)getLocationX()) - (s / 6))) && (ptrX <= (cmpX + ((int)getLocationX()) + (s / 6))));
		boolean betweenY = ((ptrY >= (cmpY + ((int)getLocationY()) - (s / 2))) && (ptrY <= (cmpY + ((int)getLocationY()) + (s / 2))));
		if (betweenX && betweenY)
			return true;
		else
			return false;
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
	 * or the player ship
	 * 
	 * @param otherObject	the object that collides with this object
	 */
	
	@Override
	public void handleCollision(ICollider otherObject) 
	{
		if (!(otherObject instanceof SpaceStation) && !(otherObject instanceof PlayerShip))
			setRemoved(true);
	}
	
	/**
	 * Represents the player missile as a filled in rectangle
	 * on the game map by using its size and location
	 * 
	 * @param g				the graphics class of the game map
	 * @param pCmpRelPrnt	the location of the game map relative to the screen
	 */
	
	public void draw(Graphics g, Point pCmpRelPrnt)
	{
		if (!selected)
		{
			g.setColor(getColor());
			int s = getSize();
			g.fillRect(pCmpRelPrnt.getX() + ((int)getLocationX()) - (s / 6), pCmpRelPrnt.getY() + ((int)getLocationY()) - (s / 2), (s / 3), s);
		} else {
			g.setColor(ColorUtil.rgb(0, 190, 191));
			int s = getSize();
			g.fillRect(pCmpRelPrnt.getX() + ((int)getLocationX()) - (s / 6), pCmpRelPrnt.getY() + ((int)getLocationY()) - (s / 2), (s / 3), s);
		}
	}
	
	/**
	 * String representation of Missile object
	 */
	
	@Override
	public String toString()
	{
		return (
				"Player Missile: location = " + ((Math.round(getLocationX() * 10.0) / 10.0)) + ", " + ((Math.round(getLocationY() * 10.0)) / 10.0) +
				" color = " + "[ " + ColorUtil.red(getColor()) + " , "  + ColorUtil.green(getColor()) + " , " + ColorUtil.blue(getColor()) + " ]" +
				" speed = " + Math.round(getSpeed()) +
				" direction = " + getDirection() +
				" fuel level = " + getFuelLevel()
				);
	}
}
