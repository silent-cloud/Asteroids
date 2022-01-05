package commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

/**
 * Command class that undoes the last action
 * taken. No implementation has been given yet.
 * 
 * @author John Lorenz Salva
 * @version "%I%, %G%"
 */

public class UndoActionCommand extends Command
{

	/**
	 * Class Constructor
	 */
	
	public UndoActionCommand()
	{
		super("Undo");
	}
	
	/**
	 * Undoes the last action taken when invoked.
	 */

	@Override
	public void actionPerformed(ActionEvent evt)
	{
		System.out.println("Undo Action Button was clicked.");
	}
}
