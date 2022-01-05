package commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

/**
 * Command class that creates a player ship object
 * for the game world.
 * 
 * @author John Lorenz Salva
 * @version "%I%, %G%"
 */

public class AddPSCommand extends Command
{
	private GameWorld gw;

	/**
	 * Class Constructor
	 * 
	 * @param gw the game world of the game
	 */
	
	public AddPSCommand(GameWorld gw)
	{
		super("+ PS");
		this.gw = gw;
	}
	
	/**
	 * Creates a player ship object when invoked.
	 */

	@Override
	public void actionPerformed(ActionEvent evt)
	{
		if (evt.getKeyEvent() != -1)
		{
			System.out.println("+ PS button was clicked.");
			gw.addPS();
		}
	}
}
