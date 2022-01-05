package commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

/**
 * Command class that turns the player ship
 * to the left.
 * 
 * @author John Lorenz Salva
 * @version "%I%, %G%"
 */

public class TurnPSLeftCommand extends Command
{
	private GameWorld gw;

	/**
	 * Class Constructor
	 * 
	 * @param gw the game world of the game
	 */
	
	public TurnPSLeftCommand(GameWorld gw)
	{
		super("PS Left");
		this.gw = gw;
	}

	/**
	 * Turns the player ship to the left when invoked.
	 */
	
	@Override
	public void actionPerformed(ActionEvent evt)
	{
		if (evt.getKeyEvent() != -1)
		{
			if (evt.getKeyEvent() == -93)
				System.out.println("Left Arrow key was pressed.");
			else if (evt.getKeyEvent() == 'l')
				System.out.println("l key was pressed.");
			else
				System.out.println("PS Left button was clicked.");
			gw.turnPSLeft();
		}
	}
}
