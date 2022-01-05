package com.mycompany.a3;

import java.util.Vector;

import objects.GameObject;

/**
 * GameObjectCollection class holds the collection of game objects
 * for iteration.
 *
 * @author John Lorenz Salva
 * @version "%I%, %G%"
 */

public class GameObjectCollection implements ICollection
{
	private Vector<GameObject> collection;	//List of Game objects.
	
	/**
	 * GameObjectIterator provides a collection iterator to cycle
	 * between the game objects.
	 */
	
	private class GameObjectIterator implements IIterator
	{
		private int index;	//Iterator's current index
		
		/**
		 * Inner class constructor
		 */
		
		private GameObjectIterator()
		{
			index = -1;
		}
		
		/**
		 * Gets the next game object in the collection.
		 * 
		 * @return	the next game object in the collection
		 */
		
		@Override
		public GameObject getNext()
		{
			index++;
			return collection.get(index);
		}

		/**
		 * Checks if the collection still has more game objects
		 * to cycle through.
		 * 
		 * @return 	true if there are more objects to cycle through
		 * 			or false if there are no more objects
		 */
		
		@Override
		public boolean hasNext()
		{
			if (collection.size() <= 0)
				return false;
			if (index == collection.size() - 1)
				return false;
			return true;
		}
		
		/**
		 * Replaces the current collection of game objects with an updated copy
		 * where the game object to be removed has been removed.
		 * 
		 * @param	obj the game object to be removed from the collection
		 */
		
		public void remove(GameObject obj)
		{
			Vector<GameObject> copy = new Vector<GameObject>();
			for (int i = 0; i < collection.size(); i++)
				if (collection.get(i) != obj)
					copy.add(collection.get(i));
			collection = copy;
			index--;
			
		}
		
	}
	
	/**
	 * Class Constructor
	 */
	
	public GameObjectCollection()
	{
		collection = new Vector<GameObject>();
	}
	
	/**
	 * Adds a game object to the collection using
	 * Vector add method.
	 */
	
	@Override
	public void add(GameObject newObject)
	{
		collection.add(newObject);
	}
	
	/**
	 * Checks for the existence of a game object in the collection.
	 * 
	 * @param 	objType the game object type to check for
	 * @return 	true if the game object type exists or false
	 * 			if the game object type does not exist
	 */
	
	public boolean hasType(Class<?> objType)
	{
		boolean exists = false;
		IIterator iterCol = getIterator();
		while (iterCol.hasNext())
		{
			GameObject obj = iterCol.getNext();
			if (objType.isInstance(obj))
			{
				exists = true;
				break;
			}
		}
		return exists;
	}
	
	/**
	 * Gets the collection of game objects in the game world.
	 * 
	 * @return	the Vector list of the game objects
	 */
	
	public Vector<GameObject> getCollection()
	{
		return collection;
	}
	
	/**
	 * Gets the iterator that acts on the collection of game objects.
	 * 
	 * @return the iterator on the game object collection 
	 */
	
	public IIterator getIterator()
	{
		return new GameObjectIterator();
	}
}