package commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

/**
 * Command class that prints the game world
 * map in text format for debugging purposes
 * 
 * @author John Lorenz Salva
 * @version "%I%, %G%"
 */

public class MapCommand extends Command
{
	private GameWorld gw;

	/**
	 * Class Constructor
	 * 
	 * @param gw the game world of the game
	 */
	
	public MapCommand(GameWorld gw)
	{
		super("Map");
		this.gw = gw;
	}

	/**
	 * Prints the game world map in text format to
	 * the console when invoked.
	 */
	
	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent evt)
	{
		if (evt.getKeyEvent() != -1)
		{
			if (evt.getKeyEvent() == 'm')
				System.out.println("m key was pressed.");
			gw.map();
		}
	}
}
