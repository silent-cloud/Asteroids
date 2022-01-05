package com.mycompany.a3;

import java.util.Observable;
import java.util.Random;
import java.util.Vector;

import objects.Asteroid;
import objects.GameObject;
import objects.ICollider;
import objects.IMovable;
import objects.Missile;
import objects.MissileLauncher;
import objects.NonPlayerShip;
import objects.PlayerMissile;
import objects.PlayerShip;
import objects.Ship;
import objects.SpaceStation;
import objects.SteerableMissileLauncher;
import sounds.AstExpSound;
import sounds.PMLaunchSound;

/**
 * Instance of this class implements all the commands and
 * controls the game data.
 *
 * @author  John Lorenz Salva
 * @version "%I%, %G%"
 */

public class GameWorld extends Observable implements IGameWorld
{
	//Game constant values needed for playability
	private final int AST_POINTS = 50;
	private final int NPS_POINTS = 100;
	private final int MAX_MISSILE_COUNT = 10;
	private final int MISSILE_LIFE_CYCLE = 20;
	
	//Game map dimensions
	private double height;
	private double width;
	
	private GameObjectCollection collection;	//Collection of Game objects
	private int playerScore;					//Player's overall score
	private int tickCounter;					//Elapsed time measured in ticks
	private int missileCount;					//Amount of player missiles left
	private int lifeCount;						//Amount of lives the player has
	private boolean lose;						//Flag for deciding whether to end the game or not
	private boolean sound;						//Flag for deciding whether sound is off or on
	private boolean start;						//Flag for deciding when the game has started or not
	private boolean paused;						//Flag for deciding if the game is paused or not
	
	private Random rand;		//Random variable for objects that need to randomize their stats.
	
	/**
	 * Class Constructor
	 */

	public GameWorld()
	{
		collection = new GameObjectCollection();
		init();
	}
	
	/**
	 * Initializes the variables needed to start the game.
	 */
	
	public void init()
	{
		playerScore = 0;
		tickCounter = 0;
		lose = false;
		sound = true;
		rand = new Random();
	}
	
	/**
	 * Adds an Asteroid object.
	 */
	
	public void addAsteroid()
	{
		Asteroid asteroid = new Asteroid();
		asteroid.setMaxLocationX(width);
		asteroid.setMaxLocationY(height);
		asteroid.setLocationX(rand.nextDouble() * width);
		asteroid.setLocationY(rand.nextDouble() * height);
		collection.add(asteroid);
		System.out.println("Game object: ASTEROID created.");
		this.setChanged();
		this.notifyObservers(new GameWorldProxy(this));
	}
	
	/**
	 * Adds a NonPlayerShip object.
	 */
	
	public void addNPS()
	{
		NonPlayerShip nps = new NonPlayerShip();
		nps.setMaxLocationX(width);
		nps.setMaxLocationY(height);
		nps.setLocationX(rand.nextDouble() * width);
		nps.setLocationY(rand.nextDouble() * height);
		collection.add(nps);
		System.out.println("Game objects: NON_PLAYER_SHIP created. MISSILE_LAUNCHER created.");
		this.setChanged();
		this.notifyObservers(new GameWorldProxy(this));
	}
	
	/**
	 * Adds a SpaceStation object.
	 */
	
	public void addSpaceStation()
	{
		SpaceStation ss = new SpaceStation();
		ss.setMaxLocationX(width);
		ss.setMaxLocationY(height);
		ss.setLocationX(rand.nextDouble() * width);
		ss.setLocationY(rand.nextDouble() * height);
		collection.add(ss);
		System.out.println("Game object: SPACE_STATION created.");
		this.setChanged();
		this.notifyObservers(new GameWorldProxy(this));
	}
	
	/**
	 * Adds a PlayerShip object.
	 */
	
	public void addPS()
	{
		boolean psExists = collection.hasType(PlayerShip.class);
		//Check if a player ship has already been created and the game hasn't started
		if (!psExists && !start) {
			PlayerShip ps = new PlayerShip();
			ps.setMaxLocationX(width);
			ps.setMaxLocationY(height);
			ps.setLocationX(width / 2.0);
			ps.setLocationY(height / 2.0);
			lifeCount = 3;
			missileCount = ps.getMissileCount();
			collection.add(ps);
			System.out.println("Game objects: PLAYER_SHIP created. STEERABLE_MISSILE_LAUNCHER created.");
			start = true;
			this.setChanged();
			this.notifyObservers(new GameWorldProxy(this));
		//Check if a player hasn't been created and the game has started
		} else if (!psExists && start) { 
			PlayerShip ps = new PlayerShip();
			ps.setMaxLocationX(width);
			ps.setMaxLocationY(height);
			ps.setLocationX(width / 2.0);
			ps.setLocationY(height / 2.0);
			ps.setMissileCount(missileCount);
			collection.add(ps);
			System.out.println("Game objects: PLAYER_SHIP created. STEERABLE_MISSILE_LAUNCHER created.");
			this.setChanged();
			this.notifyObservers(new GameWorldProxy(this));
		//Player ship is not the game world
		} else {
			System.out.println("Cannot create object: PLAYER_SHIP. Only one PLAYER_SHIP may exist.");
		}
	}
	
	/**
	 * Increases the player's speed.
	 */
	
	public void incPSSpeed()
	{	
		boolean psExists = collection.hasType(PlayerShip.class);
		//Check if player ship has been created
		if (psExists)
		{
			PlayerShip player = (PlayerShip) getPlayer();
			int currentPSSpeed = player.getSpeed();
			//Increase the player ship speed if it isn't greater than the max speed
			player.incSpeed();
			if (currentPSSpeed != 15)
			{
				System.out.println("PLAYER_SHIP speed has increased.");
				this.setChanged();
				this.notifyObservers(new GameWorldProxy(this));
			}
		} else {
			System.out.println("Cannot increase PLAYER_SHIP speed. PLAYER_SHIP does not exist.");
		}
	}
	
	/**
	 * Decreases the player's speed.
	 */
	
	public void decPSSpeed()
	{	
		boolean psExists = collection.hasType(PlayerShip.class);
		//Check if the player ship has been created
		if (psExists)
		{
			PlayerShip player = (PlayerShip) getPlayer();
			int currentPSSpeed = player.getSpeed();
			//Decrease the player ship speed if it isn't 0
			player.decSpeed();
			if (currentPSSpeed != 0)
			{
				System.out.println("PLAYER_SHIP speed has decreased.");
				this.setChanged();
				this.notifyObservers(new GameWorldProxy(this));
			}
		} else {
			System.out.println("Cannot decrease PLAYER_SHIP speed. PLAYER_SHIP does not exist.");
		}
	}
	
	/**
	 * Turns the player ship left by a small amount.
	 */
	
	public void turnPSLeft()
	{	
		boolean psExists = collection.hasType(PlayerShip.class);
		//Check if the player ship has been created
		if (psExists)
		{
			//Turn the player ship left
			((PlayerShip)getPlayer()).turnLeft();
			System.out.println("PLAYER_SHIP has turned left.");
			this.setChanged();
			this.notifyObservers(new GameWorldProxy(this));
		} else {
			System.out.println("Cannot turn PLAYER_SHIP left. PLAYER_SHIP does not exist.");
		}
	}
	
	/**
	 * Turns the player ship right by a small amount.
	 */
	
	public void turnPSRight()
	{	
		boolean psExists = collection.hasType(PlayerShip.class);
		//Check if the player ship has been created
		if (psExists)
		{
			//Turn the player ship to the right
			((PlayerShip)getPlayer()).turnRight();
			System.out.println("PLAYER_SHIP has turned right.");
			this.setChanged();
			this.notifyObservers(new GameWorldProxy(this));
		} else {
			System.out.println("Cannot turn PLAYER_SHIP right. PLAYER_SHIP does not exist.");
		}
	}
	
	/**
	 * Turns the player's missile launcher counterclockwise by a small amount.
	 */
	
	public void turnPSMLauncherLeft()
	{	
		boolean psExists = collection.hasType(PlayerShip.class);
		//Check if player ship has been created
		if (psExists)
		{
			//Turn the player ship's missile launcher
			((SteerableMissileLauncher)((PlayerShip)getPlayer()).getMissileLauncher()).turnLeft();
			System.out.println("PLAYER_SHIP MISSILE_LAUNCHER has turned left.");
			this.setChanged();
			this.notifyObservers(new GameWorldProxy(this));
		} else {
			System.out.println("Cannot turn PLAYER_SHIP MISSILE_LAUNCHER. PLAYER_SHIP does not exist.");
		}
	}
	
	/**
	 * Turns turns the player's missile launcher clockwise by a small amount.
	 */
	
	public void turnPSMLauncherRight()
	{	
		boolean psExists = collection.hasType(PlayerShip.class);
		//Check if player ship has been created
		if (psExists)
		{
			//Turn the player ship's missile launcher
			((SteerableMissileLauncher)((PlayerShip)getPlayer()).getMissileLauncher()).turnRight();
			System.out.println("PLAYER_SHIP MISSILE_LAUNCHER has turned right.");
			this.setChanged();
			this.notifyObservers(new GameWorldProxy(this));
		} else {
			System.out.println("Cannot turn PLAYER_SHIP MISSILE_LAUNCHER. PLAYER_SHIP does not exist.");
		}
	}
	
	/**
	 * Creates a PlayerMissile object and reduces the player's missile count by one.
	 */
	
	public void firePSMissile()
	{	
		boolean psExists = collection.hasType(PlayerShip.class);
		//Check if player ship has been created
		if (psExists)
		{
			//Fire the player missile
			//Check if the player ship has missiles available
			if (missileCount != 0)
			{
				((Ship)getPlayer()).shootMissile();
				PMLaunchSound.getPMLaunchSound().play();
				missileCount--;
				collection.add(((MissileLauncher)((Ship)getPlayer()).getMissileLauncher()).getLastCreatedMissile());
				System.out.println("PLAYER_SHIP has launched MISSILE object.");
				this.setChanged();
				this.notifyObservers(new GameWorldProxy(this));
			} else {
				System.out.println("PLAYER_SHIP has no missiles left.");
			}
		} else {
			System.out.println("Cannot fire PLAYER_SHIP MISSILE. PLAYER_SHIP does not exist.");
		}
	}
	
	/**
	 * Creates a NonPlayerMissile object and reduces the non player ship's missile count.
	 */
	
	public void launchNPSMissile(NonPlayerShip obj)
	{	
		//Launch this non-player ship's missile
		//Check if the current non-player ship has missiles
		if (((Ship)obj).checkMissileCount())
		{
			((Ship)obj).shootMissile();
			collection.add(((MissileLauncher)((Ship)obj).getMissileLauncher()).getLastCreatedMissile());
			System.out.println("A NON_PLAYER_SHIP has launched MISSILE object.");
			this.setChanged();
			this.notifyObservers(new GameWorldProxy(this));
		}
	}
	
	/**
	 * Places the player back to the initial spot.
	 */
	
	public void jumpPSback()
	{
		boolean psExists = collection.hasType(PlayerShip.class);
		//Check if the player ship exists
		if (psExists)
		{
			//Re-center the player ship location
			((PlayerShip)getPlayer()).setLocationX(width / 2.0);
			((PlayerShip)getPlayer()).setLocationY(height / 2.0);
			System.out.println("PLAYER_SHIP has jumped back to the center.");
			this.setChanged();
			this.notifyObservers(new GameWorldProxy(this));
		} else {
			System.out.println("Cannot jump PLAYER_SHIP back to center. PLAYER_SHIP does not exist.");
		}
	}
	
	/**
	 * Represents time for the game.
	 * All movable objects are told to move.
	 * All space stations are told to blink when the time is right.
	 * All missiles are to lose fuel level by one.
	 */
	
	public void tick(int t)
	{
		tickCounter++;
		moveObjects(t);
		detectCollision();
		performRemove();
		this.setChanged();
		this.notifyObservers(new GameWorldProxy(this));
	}
	
	/**
	 * Prints the score the player has earned,
	 * the amount of missiles that the player has, and how
	 * much time has elapsed.
	 */
	
	@Deprecated
	public void printGame()
	{
		if (collection.hasType(PlayerShip.class))
		{
			System.out.println("You have earned a score of " + playerScore);
			System.out.println("You have " + missileCount + " left.");
			System.out.println("You have " + lifeCount + " lives left.");
			System.out.println("Current Game Time: " + tickCounter + " ticks.");
		} else {
			System.out.println("Cannot find player ship. Game has not started.");
		}
	}
	
	/**
	 * Represents the map of the game.
	 * Outputs all string values of the objects in
	 * the collection.
	 */
	
	@Deprecated
	public void map()
	{
		if (collection.getCollection().size() != 0)
		{
			IIterator iterCol = collection.getIterator();
			while (iterCol.hasNext())
			{
				GameObject obj = iterCol.getNext();
				System.out.println(obj);
			}
			System.out.println();
		} else {
			System.out.println("No objects created.");
		}
	}
	
	/**
	 * Updates the max height for any object in the game world.
	 * 
	 * @param h the height of the game world view
	 */
	
	public void setMaxHeight(int h)
	{
		double convertH = h / 1.0;
		height = convertH;
	}
	
	/**
	 * Updates the max width for any object in the game world.
	 * 
	 * @param w the width of the game world view
	 */
	
	public void setMaxWidth(int w)
	{
		double convertW = w / 1.0;
		width = convertW;
	}
	
	/**
	 * Updates the flag for pausing the game
	 * 
	 * @param p	the new flag value for pausing the game
	 */
	
	public void setPaused(boolean p)
	{
		paused = p;
	}
	
	/**
	 * Gets the player's current score.
	 * 
	 * @return the player's current score
	 */
	
	public int getPlayerScore()
	{
		return playerScore;
	}
	
	/**
	 * Gets the current game time (in ticks).
	 * 
	 *  @return the current time, measured in ticks
	 */
	
	public int getTime()
	{
		return tickCounter;
	}
	
	/**
	 * Gets how many missiles the player has left.
	 * 
	 * @return the amount of missiles the player has left
	 */
	
	public int getMissileCount()
	{
		return missileCount;
	}
	
	/**
	 * Gets how many lives the player has left.
	 * 
	 * @return the amount of lives the player has left
	 */
	
	public int getLifeCount()
	{
		return lifeCount;
	}
	
	/**
	 * Updates the status of the sound.
	 * 
	 * @param s the sound status given by the checkbox action event
	 */
	
	public void setSoundVal(boolean s)
	{
		sound = s;
		this.setChanged();
		this.notifyObservers(new GameWorldProxy(this));
	}
	
	/**
	 * Gets the sound status in readable format.
	 * 
	 *  @return the amount of missiles the player has left
	 */
	
	public boolean getSoundVal()
	{
		return sound;
	}
	
	/**
	 * Gets the collection of game objects in the game world.
	 * 
	 * @return the game world's collection of objects
	 */
	
	public GameObjectCollection getCollection()
	{
		return collection;
	}
	
	/**
	 * Gets the flag determining whether the player has 
	 * lost the game or not
	 * 
	 * @return the flag value for losing the game
	 */
	
	public boolean hasLost()
	{
		return lose;
	}
	
	/**
	 * Gets the flag value for determining whether the game is
	 * paused or not
	 * 
	 * @return the flag value for pausing the game
	 */
	
	public boolean isPaused()
	{
		return paused;
	}
	
	/**
	 * Prints out the final message once the player
	 * has lost. Resets the collection, player score, and
	 * life count for a new game.
	 */
	
	public void resetValues()
	{
		playerScore = 0;
		tickCounter = 0;
		missileCount = 0;
		lifeCount = 0;
		lose = false;
		start = false;
		collection.getCollection().removeAllElements();
		this.setChanged();
		this.notifyObservers(new GameWorldProxy(this));
	}
	
	/**
	 * Iterates through each object to perform movement
	 * or blinking based on the time elapsed
	 * 
	 * @param t	the amount of time passed, measured in milliseconds
	 */
	
	private void moveObjects(int t)
	{
		IIterator iterCol = collection.getIterator();
		//Move every movable object
		while(iterCol.hasNext())
		{
			GameObject obj = iterCol.getNext();
			if (obj instanceof IMovable)
			{
				//Decrement the fuel in the missile
				if ((tickCounter % MISSILE_LIFE_CYCLE == 0) && obj instanceof Missile)
				{
					((Missile)obj).decFuelLevel();
					//Remove the missile when the fuel level is zero
					if (((Missile)obj).getFuelLevel() == 0)
					{
						iterCol.remove(obj);
					//Keep moving the missile if not
					} else {
						IMovable movObj = ((IMovable)obj);
						movObj.move(t);
					}
				//Move the non-player ship and determine if it will shoot
				} else if (obj instanceof NonPlayerShip) {
					NonPlayerShip nps = (NonPlayerShip) obj;
					int roll = rand.nextInt((100000 - 1) + 1) + 1;
					if (roll > 600 && roll < 700)
						launchNPSMissile(nps);
					IMovable movObj = ((IMovable)obj);
					movObj.move(t);
				//Move every other object
				} else {	
					IMovable movObj = ((IMovable)obj);
					movObj.move(t);
				}
			//Determine if the space station is blinking based on the time
			} else if (obj instanceof SpaceStation) {
				if (((SpaceStation)obj).getBlinkRate() != 0)
				{
					if (tickCounter % ((SpaceStation)obj).getBlinkRate() == 0)
					{
						((SpaceStation)obj).setBlink(true);
					} else {
						((SpaceStation)obj).setBlink(false);
					}
				}
			}
		}
	}
	
	/**
	 * Detects whether there are any collisions between all
	 * game objects.
	 */
	
	private void detectCollision()
	{
		IIterator iterCol = collection.getIterator();
		while(iterCol.hasNext())
		{
			//Object selected from the list
			GameObject obj = iterCol.getNext();
			//Get its collision vector
			Vector<GameObject> objColVec = obj.getColVec();
			if (obj instanceof ICollider)
			{
				ICollider curObj = (ICollider) obj;
				IIterator colObjs = collection.getIterator();
				//Cycle through other objects in the list
				while(colObjs.hasNext())
				{
					//Other object in the list
					GameObject nextObj = colObjs.getNext();
					//That object's collision vector
					Vector<GameObject> nextObjColVec = nextObj.getColVec();
					ICollider otherObj = (ICollider) nextObj;
					if (otherObj != curObj) 
					{
						//Check for collision
						if (curObj.collidesWith(otherObj))
						{
							//Check if the selected object already collides with the other object 
							if (!(objColVec.contains(nextObj)))
							{
								//Check the object types to perform additional functionality
								if ((obj instanceof PlayerMissile && nextObj instanceof Asteroid) || (obj instanceof Asteroid && nextObj instanceof PlayerMissile))
								{
									playerScore += AST_POINTS;
									AstExpSound.getAstExpSound().play();
								} else if ((obj instanceof PlayerMissile && nextObj instanceof NonPlayerShip) || (obj instanceof NonPlayerShip && nextObj instanceof PlayerMissile)) {
									playerScore += NPS_POINTS;
								} else if ((obj instanceof PlayerShip && nextObj instanceof SpaceStation) || (obj instanceof SpaceStation && nextObj instanceof PlayerShip)) {
									missileCount = MAX_MISSILE_COUNT;
								}
								//Handle the collisions and add them to each others collision vectors
								curObj.handleCollision(otherObj);
								objColVec.add(nextObj);
								otherObj.handleCollision(curObj);
								nextObjColVec.add(obj);
							}
						//Check if the selected object no longer collides with the other objects
						} else if (objColVec.contains(nextObj)) {
							objColVec.remove(nextObj);
							nextObjColVec.remove(obj);
						}
					}
				}
			}
		}
	}
	
	/**
	 * Removes any objects marked to be removed from
	 * the collection of game objects.
	 */
	
	private void performRemove()
	{
		IIterator iterCol = collection.getIterator();
		while(iterCol.hasNext())
		{
			GameObject obj = iterCol.getNext();
			if (obj.isRemoved())
			{
				iterCol.remove(obj);
				if (obj instanceof PlayerShip)
				{
					lifeCount--;
					if (lifeCount == 0)
						lose = true;
					else
						addPS();
				}
			}
		}
	}
	
	/**
	 * Gets the player object in the collection	
	 * 
	 * @return the player ship in the collection
	 */
	private GameObject getPlayer()
	{
		IIterator iterCol = collection.getIterator();
		while (iterCol.hasNext())
		{
			GameObject obj = iterCol.getNext();
			if (obj instanceof PlayerShip)
				return ((GameObject)obj);
		}
		return null;
	}
}
