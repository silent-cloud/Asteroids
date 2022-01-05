package commands;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.events.ActionEvent;

/**
 * Command class that constructs a dialog box
 * asking if the player wants to quit the game.
 * 
 * @author John Lorenz Salva
 * @version "%I%, %G%"
 */

public class QuitCommand extends Command
{
	/**
	 * Class Constructor
	 */
	
	public QuitCommand()
	{
		super("Quit");
	}
	
	/**
	 * Creates a dialog box, when invoked, asking 
	 * if the player wants to quit the game or not
	 */

	@Override
	public void actionPerformed(ActionEvent evt)
	{
		if (evt.getKeyEvent() == 'Q')
			System.out.println("Q key was pressed.");
		else
			System.out.println("Quit Button was clicked.");
		Boolean bOk= Dialog.show("Confirm quit", "Are you sure you want to quit?", "Ok", "Cancel");
		if (bOk)
		{
			Display.getInstance().exitApplication();
		}
	}
}
