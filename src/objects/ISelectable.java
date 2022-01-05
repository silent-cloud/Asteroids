package objects;

import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

/**
 * ISelectable interface enforces the functionalities
 * for selecting an object on the map.
 * 
 * @author jsjoh
 * @version %I%, %G%
 */

public interface ISelectable
{
	/**
	 * Updates the flag value for determining whether the
	 * game object has been selected.
	 * 
	 * @param yesNo	the flag value that determines if
	 * 				the game object is selected
	 */
	
	public void setSelected(boolean yesNo);
	
	/**
	 * Gets the flag value for determining whether the
	 * game object has been selected.
	 * 
	 * @return	the flag value that determins if the
	 * 			game object is selected
	 */
	public boolean isSelected();
	
	/**
	 * Checks whether the player selected within the
	 * bounds of the object shape.
	 * 
	 * @param pPtrRelPrnt	the player's press input relative to the screen
	 * @param pCmpRelPrnt	the map location relative to the screen
	 * @return				the flag value for determining whether the
	 * 						object pressed on or not
	 */
	
	public boolean contains(Point pPtrRelPrnt, Point pCmpRelPrnt);
	
	/**
	 * Draws the object on the game map
	 * 
	 * @param g				the graphics component of the game map
	 * @param pCmpRelPrnt	the map location relative to the screen
	 */
	
	public void draw(Graphics g, Point pCmpRelPrnt);
}
