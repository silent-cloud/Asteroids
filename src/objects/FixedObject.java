package objects;

/**
* FixedObject class sets behaviors for all fixed game objects
* in the game.
*
* @author  John Lorenz Salva
* @version "%I%, %G%"
*/

public abstract class FixedObject extends GameObject
{
	protected int ID = 1;			//Fixed Object unique IDs.
	private boolean finalLocationX;	//Flag for checking if the x-coordinate has been set
	private boolean finalLocationY;	//Flag for checking if the y-coordinate has been set
	
	/**
	 * Class Constructor
	 */
	public FixedObject()
	{
		super();
	}
	
	/**
	 * Finalizes the x-coordinate location of the fixed object
	 * by ignoring any values passed if the x-coordinate has 
	 * already been set.
	 * 
	 * @param x the x-coordinate location of the fixed object
	 */
	
	@Override
	public void setLocationX(double x)
	{
		if(!finalLocationX)
		{
			super.setLocationX(x);
			finalLocationX = true;
		}
	}
	
	/**
	 * Finalizes the y-coordinate location of the fixed object
	 * by ignoring any values passed if the y-coordinate has 
	 * already been set.
	 * 
	 * @param y the y-coordinate location of the fixed object
	 */
	
	@Override
	public void setLocationY(double y)
	{
		if(!finalLocationY)
		{
			super.setLocationY(y);
			finalLocationY = true;
		}
	}
}
