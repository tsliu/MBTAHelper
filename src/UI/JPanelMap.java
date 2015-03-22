package UI;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Main.Constants;

/**JPanel that displays a map of the MBTA T System.
 * 
 *
 */
@SuppressWarnings("serial")
public class JPanelMap extends JPanel{

	ImageIcon m_map;
	
	/**Sets up the map image;
	 * Sets up the JPanel to be scrollable
	 */
	public JPanelMap(){
		super();
		try {
			this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
			m_map = new ImageIcon(Constants.PATH_MAP);
			this.add(new JScrollPane(new JLabel(m_map)));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
}
