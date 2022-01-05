package styles;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.plaf.Border;

/**
 * CommandButton class defines a style of the command buttons
 * to be used in the west container that holds all the button
 * commands for the game world.
 * 
 * @author John Lorenz Salva
 * @version "%I%, %G%"
 */

public class CommandButton extends Button
{
	/**
	 * Class Constructor
	 * 
	 * @param s	the command to be attached to the button
	 */
	
	public CommandButton(Command c)
	{
		super(c);
		getPressedStyle().setBgTransparency(255);
		getPressedStyle().setBgColor(ColorUtil.WHITE);
		getDisabledStyle().setBgTransparency(255);
		getDisabledStyle().setBgColor(ColorUtil.rgb(125, 123, 119));
		getUnselectedStyle().setBgTransparency(255);
		getUnselectedStyle().setBgColor(ColorUtil.rgb(168, 168, 168));
		getUnselectedStyle().setBorder(Border.createLineBorder(4, ColorUtil.GRAY));
		getSelectedStyle().setBgTransparency(255);
		getSelectedStyle().setBgColor(ColorUtil.rgb(168, 168, 168));
		getSelectedStyle().setBorder(Border.createLineBorder(4, ColorUtil.GRAY));
	}
}
