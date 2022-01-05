package commands;

import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

/**
 * Command class that executes when the sound check box
 * is ticked or not ticked.
 * 
 * @author John Lorenz Salva
 * @version "%I%, %G%"
 */

public class CheckSoundCommand extends Command
{
	private GameWorld gw;
	
	/**
	 * Class Constructor
	 * 
	 * @param gw the game world of the game
	 */
	
	public CheckSoundCommand(GameWorld gw)
	{
		super("Sound");
		this.gw = gw;
	}
	
	/**
	 * Creates a non-player ship object when invoked.
	 */
	
	@Override
	public void actionPerformed(ActionEvent evt)
	{
		System.out.println("Sound check box was clicked.");
		boolean isOn = ((CheckBox)evt.getComponent()).isSelected();
		if (isOn)
		{
			gw.setSoundVal(true);
		} else {
			gw.setSoundVal(false);
		}
	}
}
