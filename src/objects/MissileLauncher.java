package objects;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

import sounds.NPSLaunchSound;

/**
 * MissileLauncher class represents missile launchers for non-player ships.
 *
 * @author  John Lorenz Salva
 * @version "%I%, %G%"
 */

public class MissileLauncher extends MovableObject implements IMovable, IDrawable
{
	private Missile lastCreatedMissile;		//Keeps track of the last created missile
	
	/**
	 * Class Constructor
	 * 
	 * @param x	the ship's x-coordinate location
	 * @param y	the ship's y-coordinate location
	 * @param s	the ship's speed
	 * @param d	the ship's direction
	 */
	
	public MissileLauncher(double x, double y, int s, int d)
	{
		super();
		setLocationX(x);
		setLocationY(y);
		setSpeed(s);
		setDirection(d);
		setColor(ColorUtil.MAGENTA);
	}
	
	/**
	 * Creates a new missile object.
	 * Represents the ship's missile launcher launching a missile.
	 */
	
	public void launchMissile()
	{
		NPSLaunchSound.getNPSLaunchSound().play();
		NonPlayerMissile ms = new NonPlayerMissile(getSpeed(), getDirection());
		ms.setMaxLocationX(getMaxLocationX());
		ms.setMaxLocationY(getMaxLocationY());
		lastCreatedMissile = ms;
		int theta = 90 - getDirection();
		double distance = getSize() + (getSize() / 4);
		double x = getLocationX() + ((Math.cos(Math.toRadians(theta))) * distance);
		double y = getLocationY() + ((Math.sin(Math.toRadians(theta))) * distance);
		((GameObject)ms).setLocationX(x);
		((GameObject)ms).setLocationY(y);
	}
	
	/**
	* Updates the last created missile object to be added to
	* the game object collection.
	* 
	* @param ms	the previous missile launched from the missile launcher
	*/
	
	public void setLastCreatedMissile(Missile ms)
	{
		lastCreatedMissile = ms;
	}
	
	/**
	 * Gets the last created missile object to be added to
	 * the game object collection.
	 * 
	 * @return	the last created missile object
	 */
	public Missile getLastCreatedMissile()
	{
		return lastCreatedMissile;
	}
	
	/**
	 * Represents the missile launcher as a line
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
		int x = (int) (pCmpRelPrnt.getX() + getLocationX());
		int y = (int) (pCmpRelPrnt.getY() + getLocationY());
		
		double angle = Math.toRadians(90 - getDirection());
		double deltaX = Math.cos(angle);
		double deltaY = Math.sin(angle);
		g.drawLine(x, y, (int) (x + (s * deltaX)), (int) (y + (s * deltaY)));
	}
}
