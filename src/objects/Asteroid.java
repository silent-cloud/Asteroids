package objects;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

/**
 * Asteroid class creates asteroid objects.
 *
 * @author  John Lorenz Salva
 * @version "%I%, %G%"
 */

public class Asteroid extends MovableObject implements IMovable, IDrawable, ICollider, ISelectable
{
	private final int MIN_SIZE = 40;		//Minimum size for an asteroid
	private final int MAX_SIZE = 80;		//Maximum size for an asteroid
	private boolean selected;
	
	/**
	 * Class Constructor
	 */
	
	public Asteroid()
	{
		super();
		setColor(ColorUtil.YELLOW);
		setSpeed(rand.nextInt(MAX_SPEED - MIN_NPO_SPEED + 1) + MIN_NPO_SPEED);
		setDirection(rand.nextInt(MAX_DIR + 1));
		setSize(rand.nextInt(MAX_SIZE - MIN_SIZE + 1) + MIN_SIZE);
		setBCirRad(getSize() / 2);
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
		int s = getSize() / 2;
		int ptrX = pPtrRelPrnt.getX();
		int ptrY = pPtrRelPrnt.getY();
		int cmpX = pCmpRelPrnt.getX() + ((int)getLocationX());
		int cmpY = pCmpRelPrnt.getY() + ((int)getLocationY());
		int radiusSqr = s * s;
		int selectedSpace = ((ptrX - cmpX) * (ptrX - cmpX)) + ((ptrY - cmpY) * (ptrY - cmpY));
		if (selectedSpace <= radiusSqr)
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
		int radiiSqr = ((thisRadius*thisRadius) + (2*otherRadius*thisRadius) + (otherRadius*otherRadius));
		if (distBetween <= radiiSqr)
			result = true;
		return result;
	}

	/**
	 * Removes the object from play when it collides with
	 * any other collidable object that aren't space stations.
	 * 
	 * @param otherObject	the object that collides with this object
	 */
	
	@Override
	public void handleCollision(ICollider otherObject) 
	{
		if (!(otherObject instanceof SpaceStation))
			setRemoved(true);
	}
	
	/**
	 * Represents the asteroid as a filled in circle
	 * on the game map by using its size and location. If selected,
	 * the asteroid is filled in with a different color.
	 * 
	 * @param g				the graphics class of the game map
	 * @param pCmpRelPrnt	the location of the game map relative to the screen
	 */
	
	@Override
	public void draw(Graphics g, Point pCmpRelPrnt)
	{
		if (!selected)
		{
			g.setColor(getColor());
			int s = getSize();
			g.fillArc(pCmpRelPrnt.getX() + ((int)getLocationX()) - (s / 2), pCmpRelPrnt.getY() + ((int)getLocationY()) - (s / 2), s, s, 0, 360);
		} else {
			g.setColor(ColorUtil.rgb(180, 187, 0));
			int s = getSize();
			g.fillArc(pCmpRelPrnt.getX() + ((int)getLocationX()) - (s / 2), pCmpRelPrnt.getY() + ((int)getLocationY()) - (s / 2), s, s, 0, 360);
		}
	}
	
	/**
	 * String representation of asteroid
	 */
	
	@Override
	public String toString()
	{
		return (
				"Asteroid: location = " + ((Math.round(getLocationX() * 10.0) / 10.0)) + ", " + ((Math.round(getLocationY() * 10.0)) / 10.0) +
				" color = " + "[ " + ColorUtil.red(getColor()) + " , "  + ColorUtil.green(getColor()) + " , " + ColorUtil.blue(getColor()) + " ]" +
				" speed = " + Math.round(getSpeed()) +
				" direction = " + getDirection() +
				" size = " + this.getSize()
				);
	}
}
