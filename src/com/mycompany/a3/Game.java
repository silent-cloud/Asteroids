package com.mycompany.a3;

import java.util.Random;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.util.UITimer;

import commands.*;
import sounds.AstExpSound;
import sounds.BGMusic;
import sounds.LoseSound;
import sounds.MLMoveSound;
import sounds.NPSLaunchSound;
import sounds.PMLaunchSound;
import styles.CommandButton;

/**
 * Game is the controller of the game world data
 * and visual representation of the game.
 *
 * @author  John Lorenz Salva
 * @version "%I%, %G%"
 */

public class Game extends Form implements Runnable
{
	private GameWorld gw;			//Physical Game World
	private MapView mv;				//Graphical Representation of the Game World
	private PointsView pv;			//Container for maintaining text information of the player values
	private UITimer timer;			//UI Timer running on the Game Form
	private final int TIME = 20;	//The time it takes to repeatedly call the run method
	private int height;				//The height of the game map
	private int width;				//The width of the game map
	
	//Control Panel Buttons
	private Button asteroid;
	private Button spaceStation;
	private Button playerShip;
	private Button incPSSpeed;
	private Button decPSSpeed;
	private Button turnPSLeft;
	private Button turnPSRight;
	private Button turnMSLLeft;
	private Button turnMSLRight;
	private Button firePSMissile;
	private Button jump;
	
	//Control Panel Button commands
	private Command addAstCom;
	private Command addSSCom;
	private Command addPSCom;
	private Command incPSSpeedCom;
	private Command decPSSpeedCom;
	private Command turnPSLeftCom;
	private Command turnPSRightCom;
	private Command turnMSLLeftCom;
	private Command turnMSLRightCom;
	private Command firePSMissileCom;
	private Command jumpCom;
	
	private Container west;			//Control panel container
	private Toolbar toolbar;		//Toolbar for additional settings
	
	/**
	* Class Constructor 
	*/
	
	public Game()
	{
		setLayout(new BorderLayout());
		
		west = new Container(BoxLayout.y());
		west.getAllStyles().setBorder(Border.createLineBorder(4, ColorUtil.BLACK));
		west.setScrollableY(true);
		
		toolbar = new Toolbar();
		
		gw = new GameWorld();
		pv = new PointsView();
		mv = new MapView(gw);
		gw.addObserver(mv);
		gw.addObserver(pv);
		BGMusic.getMusic().play();
		
		setToolbar(toolbar);
		toolbar.setTitle("Asteroids");
		Command newGame = new NewGameCommand();
		toolbar.addCommandToSideMenu(newGame);
		Command saveGame = new SaveGameCommand();
		toolbar.addCommandToSideMenu(saveGame);
		Command undoAction = new UndoActionCommand();
		toolbar.addCommandToSideMenu(undoAction);
		Command checkSound = new CheckSoundCommand(gw);
		CheckBox soundBox = new CheckBox("Sound");
		soundBox.setSelected(true);
		soundBox.setCommand(checkSound);
		toolbar.addComponentToSideMenu(soundBox);
		Command aboutGame = new AboutGameCommand();
		toolbar.addCommandToSideMenu(aboutGame);
		Command quitGame = new QuitCommand();
		addKeyListener('Q', quitGame);
		toolbar.addCommandToSideMenu(quitGame);
		
		Command printGame = new PrintGameCommand(gw);
		addKeyListener('P', printGame);
		Command map = new MapCommand(gw);
		addKeyListener('m', map);
		
		addButtons();
		preloadSounds();

		this.add(BorderLayout.WEST, west);
		this.add(BorderLayout.NORTH, pv);
		this.add(BorderLayout.CENTER, mv);
		
		show();
		height = mv.getHeight();
		width = mv.getWidth();
		gw.setMaxHeight(height);
		gw.setMaxWidth(width);
		
		timer = new UITimer(this);
		startTime();
	}
	
	/**
	 * Creates the buttons commands.
	 * Adds the buttons to the GUI. 
	 */
	
	private void addButtons()
	{
		addAstCom = new AddAsteroidCommand(gw);
		asteroid = new CommandButton(addAstCom);
		west.add(asteroid);
		
		addSSCom = new AddSSCommand(gw);
		spaceStation = new CommandButton(addSSCom);
		west.add(spaceStation);
		
		addPSCom = new AddPSCommand(gw);
		playerShip = new CommandButton(addPSCom);
		west.add(playerShip);
		
		incPSSpeedCom = new IncPSSpeedCommand(gw);
		addKeyListener(-91, incPSSpeedCom);
		addKeyListener('i', incPSSpeedCom);
		incPSSpeed = new CommandButton(incPSSpeedCom);
		west.add(incPSSpeed);
		
		decPSSpeedCom = new DecPSSpeedCommand(gw);
		addKeyListener(-92, decPSSpeedCom);
		addKeyListener('d', decPSSpeedCom);
		decPSSpeed = new CommandButton(decPSSpeedCom);
		west.add(decPSSpeed);
		
		turnPSLeftCom = new TurnPSLeftCommand(gw);
		addKeyListener(-93, turnPSLeftCom);
		addKeyListener('l', turnPSLeftCom);
		turnPSLeft = new CommandButton(turnPSLeftCom);
		west.add(turnPSLeft);
		
		turnPSRightCom = new TurnPSRightCommand(gw);
		addKeyListener(-94, turnPSRightCom);
		addKeyListener('r', turnPSRightCom);
		turnPSRight = new CommandButton(turnPSRightCom);
		west.add(turnPSRight);
		
		turnMSLLeftCom = new TurnMSLLeftCommand(gw);
		addKeyListener(44, turnMSLLeftCom);
		turnMSLLeft = new CommandButton(turnMSLLeftCom);
		west.add(turnMSLLeft);
		
		turnMSLRightCom = new TurnMSLRightCommand(gw);
		addKeyListener(46, turnMSLRightCom);
		turnMSLRight = new CommandButton(turnMSLRightCom);
		west.add(turnMSLRight);
		
		firePSMissileCom = new FirePSMissileCommand(gw);
		addKeyListener(-90, firePSMissileCom);
		firePSMissile = new CommandButton(firePSMissileCom);
		west.add(firePSMissile);
		
		jumpCom = new JumpCommand(gw);
		addKeyListener('j', jumpCom);
		jump = new CommandButton(jumpCom);
		west.add(jump);
		
		Command gmCom = new GameModeCommand(this, gw);
		Button gm = new CommandButton(gmCom);
		west.add(gm);
		
		Command refuelCom = new RefuelCommand(gw);
		Button refuel = new CommandButton(refuelCom);
		west.add(refuel);
	}
	
	/**
	 * Disables the buttons and removes the key listeners
	 * from the disabled commands
	 */
	
	public void disableButtons()
	{
		asteroid.setEnabled(false);
		spaceStation.setEnabled(false);
		playerShip.setEnabled(false);
		incPSSpeed.setEnabled(false);
		decPSSpeed.setEnabled(false);
		turnPSLeft.setEnabled(false);
		turnPSRight.setEnabled(false);
		turnMSLLeft.setEnabled(false);
		turnMSLRight.setEnabled(false);
		firePSMissile.setEnabled(false);
		jump.setEnabled(false);
		removeKeyListener(-91, incPSSpeedCom);
		removeKeyListener('i', incPSSpeedCom);
		removeKeyListener(-92, decPSSpeedCom);
		removeKeyListener('d', decPSSpeedCom);
		removeKeyListener(-93, turnPSLeftCom);
		removeKeyListener('l', turnPSLeftCom);
		removeKeyListener(-94, turnPSRightCom);
		removeKeyListener('r', turnPSRightCom);
		removeKeyListener(44, turnMSLLeftCom);
		removeKeyListener(46, turnMSLRightCom);
		removeKeyListener(-90, firePSMissileCom);
		removeKeyListener('j', jumpCom);
	}
	
	/**
	 * Enable the buttons and adds back the key listeners
	 * to the appropriate commands
	 */
	
	public void enableButtons()
	{
		asteroid.setEnabled(true);
		spaceStation.setEnabled(true);
		playerShip.setEnabled(true);
		incPSSpeed.setEnabled(true);
		decPSSpeed.setEnabled(true);
		turnPSLeft.setEnabled(true);
		turnPSRight.setEnabled(true);
		turnMSLLeft.setEnabled(true);
		turnMSLRight.setEnabled(true);
		firePSMissile.setEnabled(true);
		jump.setEnabled(true);
		addKeyListener(-91, incPSSpeedCom);
		addKeyListener('i', incPSSpeedCom);
		addKeyListener(-92, decPSSpeedCom);
		addKeyListener('d', decPSSpeedCom);
		addKeyListener(-93, turnPSLeftCom);
		addKeyListener('l', turnPSLeftCom);
		addKeyListener(-94, turnPSRightCom);
		addKeyListener('r', turnPSRightCom);
		addKeyListener(44, turnMSLLeftCom);
		addKeyListener(46, turnMSLRightCom);
		addKeyListener(-90, firePSMissileCom);
		addKeyListener('j', jumpCom);
	}
	
	/**
	 * Intializes the sounds before the gameplay
	 */
	
	public void preloadSounds()
	{
		AstExpSound.getAstExpSound();
		BGMusic.getMusic();
		LoseSound.getLoseSound();
		MLMoveSound.getMLMoveSound();
		NPSLaunchSound.getNPSLaunchSound();
		PMLaunchSound.getPMLaunchSound();
		gw.addObserver(AstExpSound.getAstExpSound());
		gw.addObserver(BGMusic.getMusic());
		gw.addObserver(LoseSound.getLoseSound());
		gw.addObserver(MLMoveSound.getMLMoveSound());
		gw.addObserver(NPSLaunchSound.getNPSLaunchSound());
		gw.addObserver(PMLaunchSound.getPMLaunchSound());
	}
	
	/**
	 * Animates the movement of objects in the map view
	 */
	
	@Override
	public void run() 
	{
		gw.tick(TIME);
		int roll = genRandInt(1, 100000);
		if (roll >= 500 && roll <= 600)
			gw.addNPS();
		mv.repaint();
		if (gw.hasLost())
		{
			LoseSound.getLoseSound().play();
			gw.tick(TIME);
			stopTime();
			boolean bOk = Dialog.show("You have a total score of " + gw.getPlayerScore() + "!", "Would you like to play again?", "Yes", "No");
			if (bOk)
			{
				gw.resetValues();
				startTime();
			}
		}
	}
	
	/**
	 * Initiates the timer running on the form
	 */
	
	public void startTime()
	{
		timer.schedule(TIME, true, this);
	}
	
	/**
	 * Stops the timer running on the form
	 */
	
	public void stopTime()
	{
		timer.cancel();
	}
	
	/**
	 * Generates a random integer to spawn a non-player ship
	 * 
	 * @param min	the minimum number in the range of integers
	 * @param max	the maximum number in the range of integers
	 * @return		the generated random integer
	 */
	
	private int genRandInt(int min, int max)
	{
		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}
}
