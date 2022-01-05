package styles;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Label;

/**
 * PointViewDataLabel class defines a style of the data labels
 * to be used in the PointsView container
 * 
 * @author John Lorenz Salva
 * @version "%I%, %G%"
 */

public class PointViewDataLabel extends Label
{
	/**
	 * Class Constructor
	 * 
	 * @param s	the message the label contains
	 */
	
	public PointViewDataLabel(String s)
	{
		super(s);
		getAllStyles().setBgTransparency(255);
		getAllStyles().setBgColor(ColorUtil.rgb(33, 33, 33));
		getAllStyles().setFgColor(ColorUtil.WHITE);
		getAllStyles().setPaddingRight(5);
	}
}
