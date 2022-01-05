package com.mycompany.a3;

import java.util.Observable;

import objects.NonPlayerShip;

/**
 * GameWorldProxy provides a limited access of the game world
 * methods so that any unauthorized classes cannot manipulate the data.
 * 
 * @author John Lorenz Salva
 * @version "%I%, %G%"
 */

public class GameWorldProxy extends Observable implements IGameWorld
{
	private GameWorld gw;
	
	public GameWorldProxy(GameWorld gw) 
	{
		this.gw = gw;
	}
	

	@Override
	public void init()
	{
		return;
	}

	@Override
	public void addAsteroid() 
	{
		return;
	}

	@Override
	public void addNPS() 
	{
		return;
	}

	@Override
	public void addSpaceStation()
	{
		return;
	}

	@Override
	public void addPS()
	{
		return;
	}

	@Override
	public void incPSSpeed() 
	{
		return;
	}

	@Override
	public void decPSSpeed()
	{
		return;
	}

	@Override
	public void turnPSLeft()
	{
		return;
	}

	@Override
	public void turnPSRight()
	{
		return;
	}

	@Override
	public void turnPSMLauncherLeft() 
	{
		return;
	}

	@Override
	public void turnPSMLauncherRight()
	{
		return;
	}

	@Override
	public void firePSMissile()
	{
		return;
	}

	@Override
	public void launchNPSMissile(NonPlayerShip obj)
	{
		return;
	}

	@Override
	public void jumpPSback()
	{
		return;
	}


	@Override
	public void tick(int t)
	{
		return;
	}
	
	@Override
	public void printGame()
	{
		return;
	}


	@Override
	public void map() 
	{
		return;
	}

	@Override
	public void setMaxHeight(int h)
	{
		return;
	}

	@Override
	public void setMaxWidth(int w)
	{
		return;
	}
	
	
	@Override
	public int getPlayerScore() 
	{
		return gw.getPlayerScore();
	}

	@Override
	public int getTime()
	{
		return gw.getTime();
	}
	
	@Override
	public int getMissileCount()
	{
		return gw.getMissileCount();
	}
	
	@Override
	public int getLifeCount()
	{
		return gw.getLifeCount();
	}

	@Override
	public void setSoundVal(boolean s)
	{
		return;
	}
	
	@Override
	public boolean getSoundVal()
	{
		return gw.getSoundVal();
	}
	
	@Override
	public GameObjectCollection getCollection()
	{
		return gw.getCollection();
	}


	@Override
	public void setPaused(boolean p) 
	{
		gw.setPaused(p);
	}


	@Override
	public boolean isPaused() 
	{
		return gw.isPaused();
	}

}
