package commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

/**
 * Command class that creates a new game.
 * No implementation has been provided yet.
 * 
 * @author John Lorenz Salva
 * @version "%I%, %G%"
 */

public class NewGameCommand extends Command
{

	/**
	 * Class Constructor
	 */
	
	public NewGameCommand()
	{
		super("New");
	}
	
	/**
	 * Creates a new game.
	 */
	
	@Override
	public void actionPerformed(ActionEvent evt)
	{
		System.out.println("New Game Button was clicked.");
	}
}
