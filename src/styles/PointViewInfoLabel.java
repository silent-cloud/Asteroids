package styles;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Label;

/**
 * PointViewInfoLabel class defines a style of the information labels
 * to be used in the PointsView container
 * 
 * @author John Lorenz Salva
 * @version "%I%, %G%"
 */

public class PointViewInfoLabel extends Label
{
	/**
	 * Class Constructor
	 * 
	 * @param s	the message the label contains
	 */
	
	public PointViewInfoLabel(String s)
	{
		super(s);
		getAllStyles().setBgTransparency(255);
		getAllStyles().setBgColor(ColorUtil.rgb(33, 33, 33));
		getAllStyles().setFgColor(ColorUtil.rgb(243, 252, 127));
	}
}
