package commands;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;

/**
 * Command class that displays the info about the game
 * for the About button.
 * 
 * @author John Lorenz Salva
 * @version "%I%, %G%"
 */
public class AboutGameCommand extends Command 
{
	/**
	 * Class Constructor
	 */
	
	public AboutGameCommand()
	{
		super("About");
	}
	
	/**
	 * Constructs a dialog box to display the info about
	 * the game, author, and version number.
	 */

	@Override
	public void actionPerformed(ActionEvent evt)
	{
		System.out.println("About Button was clicked.");
		String body = "Author: John Lorenz Salva\nCSC 133, Spring 2019\nVersion: 1.0 indev";
		Dialog.show("About the Game", body, "Go Back", null);
	}
}
