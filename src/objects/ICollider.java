package objects;

/**
 * ICollider interface enforces the collision mechanics
 * of game objects.
 * 
 * @author 	jsjoh
 * @version %I%, %G%
 */

public interface ICollider
{
	/**
	 * Checks for object collision between this collider object
	 * and the other collider object.
	 * 
	 * @param obj	the other object that this object collides with
	 * @return		the flag that determines whether the game object
	 * 				collides with the other object
	 */
	
	public boolean collidesWith(ICollider obj);
	
	/**
	 * Performs a response to the collision of this object on to
	 * the other object.
	 * 
	 * @param obj	the other object that this object collides with
	 */
	
	public void handleCollision(ICollider obj);
}
