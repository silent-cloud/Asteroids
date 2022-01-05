package commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

/**
 * Command class that prints the game state
 * values to the console
 * 
 * @author John Lorenz Salva
 * @version "%I%, %G%"
 */

public class PrintGameCommand extends Command
{
	private GameWorld gw;

	/**
	 * Class Constructor
	 * 
	 * @param gw the game world of the game
	 */
	
	public PrintGameCommand(GameWorld gw)
	{
		super("Print Game");
		this.gw = gw;
	}
	
	/**
	 * Prints the game state values to the console when
	 * invoked.
	 */

	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent evt)
	{
		if (evt.getKeyEvent() != -1)
		{
			if (evt.getKeyEvent() == 'P')
				System.out.println("P key was pressed.");
			gw.printGame();
		}
	}
}
