package com.mycompany.a3;

import objects.GameObject;

/**
 * The IIterator interface defines the methods
 * for iterating over a collection of game objects.
 *
 * @author  John Lorenz Salva
 * @version "%I%, %G%"
 */

public interface IIterator
{
	/**
	 * Gets the next game object in the collection.
	 * 
	 * @return	the next game object in the collection
	 */
	
	GameObject getNext();
	
	/**
	 * Checks if the collection still has more game objects
	 * to cycle through.
	 * 
	 * @return 	true if there are more objects to cycle through
	 * 			or false if there are no more objects
	 */
	
	boolean hasNext();
	
	/**
	 * Replaces the current collection of game objects with an updated copy
	 * where the game object to be removed has been removed.
	 * 
	 * @param	obj the game object to be removed from the collection
	 */
	
	void remove(GameObject obj);
}
