package com.mycompany.a3;

import java.util.Observable;
import java.util.Observer;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;

import styles.PointViewDataLabel;
import styles.PointViewInfoLabel;

/**
 * PointsView is a visual representation of the game world
 * state data.
 *
 * @author  John Lorenz Salva
 * @version "%I%, %G%"
 */

public class PointsView extends Container implements Observer 
{
	private Label pointsValue;		//Player Score label
	private Label soundValue;		//Game Sound Status label
	private Label missileValue;		//Amount of missiles the player has left
	private Label timeValue;		//Amount of time elapsed, measured in ticks
	private Label lifeValue;		//Amount of lives the player has left
	
	/**
	 * Class Constructor
	 */
	
	public PointsView()
	{
		getAllStyles().setBorder(Border.createLineBorder(4, ColorUtil.BLACK));
		getAllStyles().setBgTransparency(255);
		getAllStyles().setBgColor(ColorUtil.rgb(33, 33, 33));
		
		Container labelCon = new Container();
		labelCon.setLayout(new BoxLayout(BoxLayout.X_AXIS));
		
		Label pointsLabel = new PointViewInfoLabel("Points : ");
		pointsValue = new PointViewDataLabel("0");
		pointsValue.getAllStyles().setPaddingRight(2);

		Label missileLabel = new PointViewInfoLabel("Missiles Left: ");
		missileValue = new PointViewDataLabel("0");
		missileValue.getAllStyles().setPaddingRight(2);
		
		Label lifeLabel = new PointViewInfoLabel("Lives left: ");
		lifeValue = new PointViewDataLabel("0");
		lifeValue.getAllStyles().setPaddingRight(2);
		
		Label soundLabel = new PointViewInfoLabel("Sound : ");
		soundValue = new PointViewDataLabel("ON");
		soundValue.getAllStyles().setPaddingRight(2);
		
		Label timeLabel = new PointViewInfoLabel("Time (in ticks): ");
		timeValue = new PointViewDataLabel("0");
		timeValue.getAllStyles().setPaddingRight(2);
		
		labelCon.add(pointsLabel);
		labelCon.add(pointsValue);
		labelCon.add(missileLabel);
		labelCon.add(missileValue);
		labelCon.add(lifeLabel);
		labelCon.add(lifeValue);
		labelCon.add(soundLabel);
		labelCon.add(soundValue);
		labelCon.add(timeLabel);
		labelCon.add(timeValue);
		add(labelCon);
	}
	
	/**
	 * Updates the state values of the game.
	 * 
	 * @param observable	the game world which is observed by PointsView
	 * @param data			a copy of the game world methods that contain
	 * 						limited access to prevent manipulation
	 */
	
	@Override
	public void update(Observable observable, Object data)
	{
		IGameWorld gw = (IGameWorld) data;
		String soundText = (gw.getSoundVal()) ? "ON" : "OFF";
		this.pointsValue.setText("" + gw.getPlayerScore());
		this.missileValue.setText("" + gw.getMissileCount());
		this.lifeValue.setText("" + gw.getLifeCount());
		this.soundValue.setText("" + soundText);
		this.timeValue.setText("" + gw.getTime());
		this.repaint();
	}

}
