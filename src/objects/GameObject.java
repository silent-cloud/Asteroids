package objects;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.geom.Point2D;
import java.util.Random;
import java.util.Vector;

/**
 * GameObject class describes the functionality and behaviors of the
 * game objects in the game.
 *
 * @author  John Lorenz Salva
 * @version "%I%, %G%"
 */

public abstract class GameObject
{
	protected final double MIN_LOCATION = 0.0;		//Minimum location of where the object can be
	private Vector<GameObject> colVec;				//Game object collision vector
	private int bCirRad;							//Game object bounding circle
	private int size;								//Game object size
	private double maxX;							//Max x-coordinate location of where the object can be
	private double maxY;							//Max y-coordinate location of where the object can be
	private Point2D location;						//Current location of the object in Point2D form
	private int color;								//The color of the object
	protected Random rand = new Random();			//Random variable for objects that need to randomize their stats
	private boolean hasMaxXSet;						//Boolean flag for determining if the maximum x-coordinate has been set
	private boolean hasMaxYSet;						//Boolean flag for determining if the maximum y-coordinate has been set
	private boolean removed;						//Boolean flag for determining if the game object is removed from the game world
	
	/**
	 * Class Constructor
	 */
	
	public GameObject()
	{
		location = new Point2D(0,0);
		colVec = new Vector<GameObject>();
	}
	
	/**
	 * Gets the x-coordinate location of the game object.
	 * 
	 * @return the x-coordinate of the game object
	 */
	
	public double getLocationX()
	{
		return location.getX();
	}
	
	/**
	 * Gets the y-coordinate location of the game object.
	 * 
	 * @return the y-coordinate of the game object
	 */
	
	public double getLocationY()
	{
		return location.getY();
	}
	
	/**
	 * Gets the maximum x-coordinate location of the game object.
	 * 
	 * @return the maximum x-coordinate of the game object
	 */
	
	public double getMaxLocationX()
	{
		return maxX;
	}
	
	/**
	 * Gets the maximum y-coordinate location of the game object.
	 * 
	 * @return the maximum y-coordinate of the game object
	 */
	
	public double getMaxLocationY()
	{
		return maxY;
	}
	
	/**
	 * Gets the bounding circle radius of the game object.
	 * 
	 * @return	the bounding circle radius of the game object
	 */
	
	public int getBCirRad()
	{
		return bCirRad;
	}
	
	/**
	 * Gets the flag for determining if the object should be
	 * removed from the game world
	 * 
	 * @return	the remove flag to remove the game object
	 * 			from the game world.
	 */
	
	public boolean isRemoved()
	{
		return removed;
	}
	
	/**
	 * Gets the size of the game object.
	 * 
	 * @return	the size of the game object, in integer
	 */
	
	public int getSize()
	{
		return size;
	}
	
	/**
	 * Gets the collision vector of the game object.
	 * 
	 * @return	the collision vector of the game object
	 */
	
	public Vector<GameObject> getColVec()
	{
		return colVec;
	}
	
	/**
	 * Gets the Codename One integer color of the game object.
	 * 
	 * @return the color of the game object, given by Codename One
	 */
	
	public int getColor()
	{
		return color;
	}
	
	/**
	 * Updates the x-coordinate location for the game object.
	 * 
	 * @param x	the new x-coordinate location of the object
	 */
	
	public void setLocationX(double x)
	{
		if (x < MIN_LOCATION)
			setLocationX(getMaxLocationX());
		else if (x > maxX)
			setLocationX(MIN_LOCATION);
		else
			location.setX(x);
			
	}
	
	/**
	 * Updates the y-coordinate location for the game object.
	 * 
	 * @param y	the new y-coordinate location of the object.
	 */
	
	public void setLocationY(double y)
	{
		if (y < MIN_LOCATION)
			setLocationY(getMaxLocationY());
		else if (y > maxY)
			setLocationY(MIN_LOCATION);
		else
			location.setY(y);
			
	}
	
	/**
	 * Updates the maximum x-coordinate the game object can travel to.
	 * If the max x-coordinate location has not been set, set the maximum
	 * value.
	 * 
	 * @param x	the maximum x-coordinate of the game world
	 */
	
	public void setMaxLocationX(double x)
	{
		if (!hasMaxXSet)
		{
			maxX = x;
			hasMaxXSet = true;
		}
	}
	
	/**
	 * Updates the maximum y-coordinate the game object can travel to.
	 * If the max y-coordinate location has not been set, set the maximum
	 * value.
	 * 
	 * @param y	the maximum y-coordinate of the game world
	 */
	
	public void setMaxLocationY(double y)
	{
		if (!hasMaxYSet)
		{
			maxY = y;
			hasMaxYSet = true;
		}
	}
	
	/**
	 * Updates the bounding circle radius of the
	 * game object.
	 * 
	 * @param r	the radius of the game object's bounding circle
	 */
	
	public void setBCirRad(int r)
	{
		bCirRad = r;
	}
	
	/**
	 * Updates the game object size.
	 * 
	 * @param s	the size of the game object
	 */
	
	public void setSize(int s)
	{
		size = s;
	}
	
	/**
	 * Updates the flag determining whether the game
	 * object should be removed.
	 * 
	 * @param r	the new flag value to indicate removal
	 */
	
	public void setRemoved(boolean r)
	{
		removed = r;
	}
	
	/**
	 * Updates the collision vector of the game object
	 * 
	 * @param cv	the new collision vector of the game object
	 */
	
	public void setColVec(Vector<GameObject> cv)
	{
		colVec = cv;
	}
	
	/**
	 * Updates the color for the object using Codename One's
	 * rgb calculation method.
	 * 
	 * @param r	the amount of red in the object color
	 * @param g	the amount of green in the object color
	 * @param b	the amount of blue in the object color
	 */
	
	public void setColor(int r, int g, int b)
	{
		color = ColorUtil.rgb(r, g, b);
	}
	
	/**
	 * Updates the color of the object to be a simple color.
	 * Use Codename One's ColorUtil constant values for colors.
	 * 
	 * @param c	the simple color given by ColorUtil constant colors.
	 */
	
	public void setColor(int c)
	{
		color = c;
	}
}
