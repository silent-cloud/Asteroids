package objects;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

/**
* SpaceStation class represents the space stations in the game
*
* @author  John Lorenz Salva
* @version "%I%, %G%"
*/

public class SpaceStation extends FixedObject implements IDrawable, ICollider
{
	private final int ID;				//The unique ID for this particular space station
	private final int BLINK_RATE;		//The blink rate of the space station
	private boolean blinking;		//The indicator for whether the space station is on or off

	
	/**
	 * Constructor
	 */
	
	public SpaceStation()
	{
		super();
		this.ID = super.ID;
		super.ID++;
		setSize(40);
		setBCirRad(((int)(Math.sqrt(2) * getSize())) / 2);
		BLINK_RATE  = rand.nextInt(5) * 125;
		blinking = (BLINK_RATE == 0) ? true : false;
		setColor(ColorUtil.GRAY);
	}
	
	/**
	 * Gets the blink rate of the space station
	 * 
	 * @return	the blink rate of the space station
	 */
	
	public int getBlinkRate()
	{
		return BLINK_RATE;
	}
	
	/**
	 * Gets a flag of whether the space station is blinking or not
	 * 
	 * @return	the boolean flag for space station blink status
	 */
	
	public boolean isBlinking()
	{
		return blinking;
	}
	
	/**
	 * Updates the blink indicator value to tell when the space station is on or off
	 * 
	 * @param bi	the boolean flag to indicate whether the space station is on or off
	 */
	
	public void setBlink(boolean bi)
	{
		blinking = bi;
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

	@Override
	public void handleCollision(ICollider otherObject) 
	{
		return;
	}
	
	/**
	 * Represents the non-player missile as a filled in square
	 * on the game map by using its size and location
	 * 
	 * @param g				the graphics class of the game map
	 * @param pCmpRelPrnt	the location of the game map relative to the screen
	 */
	
	public void draw(Graphics g, Point pCmpRelPrnt)
	{
		if (!blinking)
		{
			g.setColor(getColor());
			int s = getSize();
			g.fillArc(pCmpRelPrnt.getX() + ((int)getLocationX()) - (s / 2), pCmpRelPrnt.getY() + ((int)getLocationY()) + (s / 2), s, s, 0, 360);
		} else {
			g.setColor(ColorUtil.WHITE);
			int s = getSize();
			g.fillArc(pCmpRelPrnt.getX() + ((int)getLocationX()) - (s / 2), pCmpRelPrnt.getY() + ((int)getLocationY()) + (s / 2), s, s, 0, 360);
		}
	}
	
	/**
	 * String representation of the space station object
	 */
	
	@Override
	public String toString()
	{
		return (
				"Space Station: location = " + ((Math.round(getLocationX() * 10.0)) / 10.0) + ", " + ((Math.round(getLocationY() * 10.0)) / 10.0) +
				" color = " + "[ " + ColorUtil.red(getColor()) + " , "  + ColorUtil.green(getColor()) + " , " + ColorUtil.blue(getColor()) + " ]" +
				" id = " + ID +
				" blink rate = " + BLINK_RATE +
				" blink on? = " + blinking
				);
	}
}
