package commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

import objects.PlayerMissile;

public class RefuelCommand extends Command
{
	private GameWorld gw;	//The current game world
	
	/**
	 * Class constructor
	 * 
	 * @param gw	the current game world
	 */
	
	public RefuelCommand(GameWorld gw)
	{
		super("Refuel");
		this.gw = gw;
	}
	
	/**
	 * Refuels a missile when the missile is selected
	 * and the game is paused when invoked.
	 */
	
	@Override
	public void actionPerformed(ActionEvent evt)
	{
		if (evt.getKeyEvent() != 1)
		{
			if (gw.isPaused())
			{
				for (int i = 0; i < gw.getCollection().getCollection().size(); i++)
				{
					if (gw.getCollection().getCollection().elementAt(i) instanceof PlayerMissile)
					{
						PlayerMissile missile = (PlayerMissile) gw.getCollection().getCollection().elementAt(i);
						if (missile.isSelected())
						{
							missile.setFuelLevel(10);
							break;
						}
					}
				}
			}
		}
	}
}
