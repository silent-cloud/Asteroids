package commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

/**
 * Command class that resets the location of
 * the player ship
 * 
 * @author John Lorenz Salva
 * @version "%I%, %G%"
 */

public class JumpCommand extends Command
{
	private GameWorld gw;

	/**
	 * Class Constructor
	 * 
	 * @param gw the game world of the game
	 */
	
	public JumpCommand(GameWorld gw)
	{
		super("Jump");
		this.gw = gw;
	}

	/**
	 * Resets the player ship location when invoked.
	 */
	
	@Override
	public void actionPerformed(ActionEvent evt)
	{
		if (evt.getKeyEvent() != -1)
		{
			if (evt.getKeyEvent() == 'j')
				System.out.println("j key was pressed.");
			else
				System.out.println("Jump button was clicked.");
			gw.jumpPSback();
		}
	}
}
