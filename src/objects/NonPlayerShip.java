package objects;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

/**
 * NonPlayerShip class represents non-player ships in the game
 *
 * @author  John Lorenz Salva
 * @version "%I%, %G%"
 */

public class NonPlayerShip extends Ship implements IMovable, IDrawable, ICollider, ISelectable
{
	private final int MAX_MISSILE_COUNT = 2;	//The maximum amount of missiles a non-player ship can hold
	private final int SIZE_SMALL = 80;			//Small size of a non-player ship
	private final int SIZE_LARGE = 160;			//Large size of a non-player ship
	private boolean selected;
	
	/**
	 * Class Constructor
	 */
	
	public NonPlayerShip()
	{
		super(new MissileLauncher(0, 0, 0, 0));
		setColor(ColorUtil.rgb(113, 242, 122));
		setMissileCount(MAX_MISSILE_COUNT);
		setSize(randomizeSize());
		setBCirRad(getSize() / 2);
		setSpeed(rand.nextInt(MAX_SPEED - MIN_NPO_SPEED + 1) + MIN_NPO_SPEED);
		setDirection(rand.nextInt(MAX_DIR + 1));
		getMissileLauncher().setDirection(this.getDirection());
		getMissileLauncher().setSpeed(this.getSpeed());
		getMissileLauncher().setColor(ColorUtil.BLUE);
		getMissileLauncher().setSize(getBCirRad() + 5);
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
		int s = getSize() / 2;
		int xPoint0 = cmpX + ((int)getLocationX());
		int yPoint0 = cmpY + ((int)getLocationY()) - s;
		int xPoint1 = cmpX + ((int)getLocationX()) - ((int)(s * (Math.sin(Math.toRadians(60)))));
		int yPoint1 = cmpY + ((int)getLocationY()) + ((int)(s * (Math.cos(Math.toRadians(60)))));
		int xPoint2 = cmpX + ((int)getLocationX()) + ((int)(s * (Math.sin(Math.toRadians(60)))));
		int slope1 = (yPoint1 - yPoint0) / (xPoint1 - xPoint0);
		int slope2 = (yPoint1 - yPoint0) / (xPoint2 - xPoint0);
		boolean inLine1 = ((ptrY - yPoint0) >= ((slope1 * (ptrX - xPoint0))));
		boolean inLine2 = ((ptrY - yPoint0) >= ((slope2 * (ptrX - xPoint0))));
		boolean inLine3 = (ptrY <= yPoint1);
		if (inLine1 && inLine2 && inLine3)
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
	 * or non-player missiles
	 * 
	 * @param otherObject	the object that collides with this object
	 */
	
	@Override
	public void handleCollision(ICollider otherObject) 
	{
		if (!(otherObject instanceof SpaceStation) && !(otherObject instanceof NonPlayerMissile))
			setRemoved(true);
		else if (otherObject instanceof SpaceStation)
			setMissileCount(MAX_MISSILE_COUNT);
	}
	
	/**
	 * Represents the non-player ship as a filled in triangle
	 * on the game map by using its size and location. If selected,
	 * the ship is filled in with a different color
	 * 
	 * @param g				the graphics class of the game map
	 * @param pCmpRelPrnt	the location of the game map relative to the screen
	 */
	
	@Override
	public void draw(Graphics g, Point pCmpRelPrnt)
	{
		if (!selected)
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
		} else {
	 		getMissileLauncher().draw(g, pCmpRelPrnt);
			g.setColor(ColorUtil.rgb(38, 180, 66));
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
	}
	
	/**
	 * Randomizes the size of the non-player ship
	 * 
	 * @return the size of the non-player ship
	 */
	
	private int randomizeSize()
	{
		int randomNum = rand.nextInt(2);
		if (randomNum == 0)
			return SIZE_SMALL;
		else
			return SIZE_LARGE;
	}

	/**
	 * String representation of a non-player ship object
	 */
	
	@Override
	public String toString()
	{
		return (
				"Non-Player Ship: location = " + ((Math.round(getLocationX() * 10.0) / 10.0)) + ", " + ((Math.round(getLocationY() * 10.0)) / 10.0) +
				" color = " + "[ " + ColorUtil.red(getColor()) + " , "  + ColorUtil.green(getColor()) + " , " + ColorUtil.blue(getColor()) + " ]" +
				" speed = " + Math.round(getSpeed()) +
				" direction = " + getDirection() +
				" size = " + getSize()
				);
	}

}
