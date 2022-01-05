package commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

/**
 * Command class that creates an asteroid
 * for the game world.
 * 
 * @author John Lorenz Salva
 * @version "%I%, %G%"
 */

public class AddAsteroidCommand extends Command
{
	private GameWorld gw;

	/**
	 * Class Constructor
	 * 
	 * @param gw the game world of the game
	 */
	
	public AddAsteroidCommand(GameWorld gw)
	{
		super("+ Asteroid");
		this.gw = gw;
	}
	
	/**
	 * Creates an asteroid object when invoked.
	 */

	@Override
	public void actionPerformed(ActionEvent evt)
	{
		if (evt.getKeyEvent() != -1)
		{
			System.out.println("+ Asteroid button was clicked.");
			gw.addAsteroid();
		}
	}
}
