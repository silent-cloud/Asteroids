package com.mycompany.a3;

import objects.GameObject;

/**
 * The ICollection interface defines the methods
 * to hold the collection of game objects.
 *
 * @author  John Lorenz Salva
 * @version "%I%, %G%"
 */

public interface ICollection
{
	/**
	 * Adds a game object to the collection
	 * 
	 * @param 	obj the game object to be stored in the collection
	 */
	
	void add(GameObject obj);
	
	/**
	 * Gets the iterator for the collection
	 * 
	 * @return	an iterator on the collection of game objects.
	 */
	
	IIterator getIterator();
}
