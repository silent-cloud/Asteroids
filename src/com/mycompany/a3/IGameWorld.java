package com.mycompany.a3;

import objects.NonPlayerShip;

/**
 * The IGameWorld interface defines all the
 * GameWorld methods that can be called.
 *
 * @author  John Lorenz Salva
 * @version "%I%, %G%"
 */

public interface IGameWorld 
{
	void init();
	void addAsteroid();
	void addNPS();
	void addSpaceStation();
	void addPS();
	void incPSSpeed();
	void decPSSpeed();
	void turnPSLeft();
	void turnPSRight();
	void turnPSMLauncherLeft();
	void turnPSMLauncherRight();
	void firePSMissile();
	void launchNPSMissile(NonPlayerShip obj);
	void jumpPSback();
	void tick(int t);
	void printGame();
	void map();
	void setMaxHeight(int h);
	void setMaxWidth(int w);
	int getPlayerScore();
	int getTime();
	int getMissileCount();
	int getLifeCount();
	void setSoundVal(boolean s);
	boolean getSoundVal();
	void setPaused(boolean p);
	boolean isPaused();
	GameObjectCollection getCollection();
}
