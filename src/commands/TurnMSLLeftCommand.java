package commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

/**
 * Command class that turns the player missile
 * launcher to the left.
 * 
 * @author John Lorenz Salva
 * @version "%I%, %G%"
 */

public class TurnMSLLeftCommand extends Command
{
	private GameWorld gw;

	/**
	 * Class Constructor
	 * 
	 * @param gw the game world of the game
	 */
	
	public TurnMSLLeftCommand(GameWorld gw)
	{
		super("MSL Left");
		this.gw = gw;
	}
	
	/**
	 * Turns the player missile launcher to the 
	 * left when invoked.
	 */

	@Override
	public void actionPerformed(ActionEvent evt)
	{
		if (evt.getKeyEvent() != -1)
		{
			if (evt.getKeyEvent() == 44)
				System.out.println("< key was pressed.");
			else
				System.out.println("MSL Left Button was clicked.");
			gw.turnPSMLauncherLeft();
		}
	}
}
