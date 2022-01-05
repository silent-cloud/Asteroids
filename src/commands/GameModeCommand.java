package commands;

import com.codename1.ui.Command;
import com.codename1.ui.Button;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.Game;
import com.mycompany.a3.GameWorld;

import sounds.BGMusic;

public class GameModeCommand extends Command
{
	private Game g;			//The current game form
	private GameWorld gw;	//The current game world
	private boolean paused;	//Flag value for pausing the game
	
	/**
	 * Class constructor
	 * 
	 * @param g		the current game form
	 * @param gw	the current game world
	 */
	
	public GameModeCommand(Game g, GameWorld gw)
	{
		super("Pause");
		this.g = g;
		this.gw = gw;
	}
	
	/**
	 * Changes the game mode between pause and play
	 * when the command is invoked
	 */
	
	@Override
	public void actionPerformed(ActionEvent evt)
	{
		if (evt.getKeyEvent() != -1)
		{
			if (!paused)
			{
				((Button)evt.getActualComponent()).setText("Play");
				g.stopTime();
				BGMusic.getMusic().pause();
				paused = true;
				gw.setPaused(paused);
				g.disableButtons();
			} else {
				((Button)evt.getActualComponent()).setText("Pause");
				g.startTime();
				BGMusic.getMusic().play();
				paused = false;
				gw.setPaused(paused);
				g.enableButtons();
			}	
			System.out.println("Gamemode changed.");
		}
	}

}
