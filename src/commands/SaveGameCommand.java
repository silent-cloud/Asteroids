package commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

/**
 * Command class that saves the game.
 * No implementation has been given yet.
 * 
 * @author John Lorenz Salva
 * @version "%I%, %G%"
 */

public class SaveGameCommand extends Command 
{
	/**
	 * Class Constructor
	 */
	
	public SaveGameCommand()
	{
		super("Save");
	}
	
	/**
	 * Saves the game when invoked.
	 */
	
	@Override
	public void actionPerformed(ActionEvent evt)
	{
		System.out.println("Save Game Button was clicked.");
	}
}
