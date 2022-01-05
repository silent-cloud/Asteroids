package commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

/**
 * Command class that turns the player missile
 * launcher to the right.
 * 
 * @author John Lorenz Salva
 * @version "%I%, %G%"
 */

public class TurnMSLRightCommand extends Command
{
	private GameWorld gw;

	/**
	 * Class Constructor
	 * 
	 * @param gw the game world of the game
	 */
	
	public TurnMSLRightCommand(GameWorld gw)
	{
		super("MSL Right");
		this.gw = gw;
	}

	/**
	 * Turns the player missile launcher to the 
	 * right when invoked.
	 */
	
	@Override
	public void actionPerformed(ActionEvent evt)
	{
		if (evt.getKeyEvent() != -1)
		{
			if (evt.getKeyEvent() == 46)
				System.out.println("> key was pressed.");
			else
				System.out.println("MSL Right button was clicked.");
			gw.turnPSMLauncherRight();
		}
	}
}
