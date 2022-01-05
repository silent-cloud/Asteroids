package com.mycompany.a3;

import java.util.Observable;
import java.util.Observer;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;
import com.codename1.ui.plaf.Border;

import objects.GameObject;
import objects.IDrawable;
import objects.ISelectable;

/**
 * MapView is a visual representation of the game world
 * object's data.
 *
 * @author  John Lorenz Salva
 * @version "%I%, %G%"
 */

public class MapView extends Container implements Observer 
{
	private GameWorld gw;
	/**
	 * Class Constructor
	 */
	
	public MapView(GameWorld gw)
	{
		this.gw = gw;
		getAllStyles().setBorder(Border.createLineBorder(4, ColorUtil.BLACK));
		getAllStyles().setBgTransparency(255);
		getAllStyles().setBgColor(ColorUtil.rgb(33, 33, 33));
	}
	
	/**
	 * Draws all the game objects on the map container
	 * 
	 * @param g	the graphics component of the map container
	 */
	
	@Override
	public void paint(Graphics g)
	{
		super.paint(g);
		Point p = new Point(0, 0);
		p.setX(getX());
		p.setY(getY());
		if (gw.getCollection().getCollection().size() != 0)
		{
			IIterator iterCol = gw.getCollection().getIterator();
			while (iterCol.hasNext())
			{
				GameObject obj = iterCol.getNext();
				if (!gw.isPaused())
				{
					if (obj instanceof ISelectable)
					{
						ISelectable sObj = (ISelectable) obj;
						sObj.setSelected(false);
					}
					IDrawable dObj = (IDrawable) obj;
					dObj.draw(g, p);
				} else {
					IDrawable dObj = (IDrawable) obj;
					dObj.draw(g, p);
				}
			}
		}
	}
	
	/**
	 * Updates the visual representation of the game.
	 * 
	 * @param observable	the game world which is observed by MapView
	 * @param data			a copy of the game world methods that contain
	 * 						limited access to prevent manipulation
	 */
	
	@Override
	public void update(Observable observable, Object data) 
	{
		repaint();
	}
	
	/**
	 * Adds an ability to select selectable game objects on the map
	 * when the game is paused.
	 * 
	 * @param x	the x-coordinate of where the player pressed
	 * @param y the y-coordinate of where the player pressed 
	 */
	
	@Override
	public void pointerPressed(int x, int y)
	{
		if (gw.isPaused())
		{
			x = x - getParent().getAbsoluteX();
			y = y - getParent().getAbsoluteY();
			Point pPtrRelPrnt = new Point(x, y);
			Point pCmpRelPrnt = new Point(getX(), getY());
			for (int i = 0; i < gw.getCollection().getCollection().size(); i++)
			{
				if (gw.getCollection().getCollection().elementAt(i) instanceof ISelectable)
				{
					ISelectable obj = (ISelectable) gw.getCollection().getCollection().elementAt(i);
					if (obj.contains(pPtrRelPrnt, pCmpRelPrnt))
						obj.setSelected(true);
					else
						obj.setSelected(false);
				}
			}
			repaint();
		}
	}
}
