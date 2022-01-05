package objects;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

import sounds.MLMoveSound;

/**
* SteerableMissileLauncher class represents the player's missile launcher
*
* @author  John Lorenz Salva
* @version "%I%, %G%"
*/

public class SteerableMissileLauncher extends MissileLauncher implements ISteerable, IMovable
{
	
	/**
	 * Class Constructor
	 * 
	 * @param x	The player ship's current x-coordinate location
	 * @param y	The player ship's current y-coordinate location
	 * @param s	The player ship's current speed
	 * @param d	The player ship's current direction
	 */
	public SteerableMissileLauncher(double x, double y, int s, int d)
	{
		super(x, y, s, d);
		setColor(ColorUtil.LTGRAY);
	}
	
	/**
	* Creates a new player missile object.
	* Represents the player ship's missile launcher launching a missile.
	*/
	
	@Override
	public void launchMissile()
	{
		PlayerMissile pm = new PlayerMissile(getSpeed(), getDirection());
		pm.setMaxLocationX(getMaxLocationX());
		pm.setMaxLocationY(getMaxLocationY());
		setLastCreatedMissile(pm);
		int theta = 90 - getDirection();
		double distance = getSize() + (getSize() / 4);
		double x = getLocationX() + ((Math.cos(Math.toRadians(theta))) * distance);
		double y = getLocationY() + ((Math.sin(Math.toRadians(theta))) * distance);
		((GameObject)pm).setLocationX(x);
		((GameObject)pm).setLocationY(y);
	}
	
	/**
	 * Updates the direction of the missile launcher counterclockwise
	 */
	
	@Override
	public void turnLeft()
	{
		setDirection(getDirection() + TURN_LEFT);
		MLMoveSound.getMLMoveSound().play();
	}
	
	/**
	 * Updates the direction of the missile launcher clockwise
	 */
	
	@Override
	public void turnRight() 
	{
		setDirection(getDirection() + TURN_RIGHT);
		MLMoveSound.getMLMoveSound().play();
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
