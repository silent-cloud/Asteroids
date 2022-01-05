package commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

/**
 * Command class that creates a space station object
 * for the game world.
 * 
 * @author John Lorenz Salva
 * @version "%I%, %G%"
 */

public class AddSSCommand extends Command
{
	private GameWorld gw;

	/**
	 * Class Constructor
	 * 
	 * @param gw the game world of the game
	 */
	
	public AddSSCommand(GameWorld gw)
	{
		super("+ Space Station");
		this.gw = gw;
	}

	/**
	 * Creates a space station object when invoked.
	 */
	
	@Override
	public void actionPerformed(ActionEvent evt)
	{
		if (evt.getKeyEvent() != -1)
		{
			System.out.println("+ Space Station button was clicked.");
			gw.addSpaceStation();
		}
	}
}
