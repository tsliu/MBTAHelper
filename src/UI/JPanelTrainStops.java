package UI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

import Main.Constants;


/**JPanel for finding the names of all stops on a line
 * 
 *
 */
@SuppressWarnings("serial")
public class JPanelTrainStops extends JPanel {
	
	JPanelLineColorSelector m_lineSelector;
	JTextPane m_resultPane;
	
	/**Uses LineColorSelector;
	 * Sets up the ActionListeners for the Blue, Orange, and Red buttons
	 * Sets up the results panel in which all the names of the stops are printed
	 */
	public JPanelTrainStops(){
		super();
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		ActionListener action_red = new ActionListener(){
			@SuppressWarnings("static-access")
			@Override
			public void actionPerformed(ActionEvent e) {
				
				StringBuilder redStopsString = new StringBuilder();
				redStopsString.append("All Red Line Stops:\n");
				
				for(String s : Constants.DATA_STATIONS.getStationNames(Constants.LINE_COLOR_SELECTOR.RED)){
					redStopsString.append(s);
					redStopsString.append("\n");
				}
				
				m_resultPane.setText(redStopsString.toString());
			}
		};
		
		ActionListener action_blue = new ActionListener(){
			@SuppressWarnings("static-access")
			@Override
			public void actionPerformed(ActionEvent e) {
				
				StringBuilder blueStopsString = new StringBuilder();
				blueStopsString.append("All Blue Line Stops:\n");
				
				for(String s : Constants.DATA_STATIONS.getStationNames(Constants.LINE_COLOR_SELECTOR.BLUE)){
					blueStopsString.append(s);
					blueStopsString.append("\n");
				}
				
				m_resultPane.setText(blueStopsString.toString());
			}
		};
		
		ActionListener action_orange = new ActionListener(){
			@SuppressWarnings("static-access")
			@Override
			public void actionPerformed(ActionEvent e) {
				
				StringBuilder orangeStopsString = new StringBuilder();
				orangeStopsString.append("All Orange Line Stops:\n");
				
				for(String s : Constants.DATA_STATIONS.getStationNames(Constants.LINE_COLOR_SELECTOR.ORANGE)){
					orangeStopsString.append(s);
					orangeStopsString.append("\n");
				}
				
				m_resultPane.setText(orangeStopsString.toString());
			}
		};
		
		m_lineSelector = new JPanelLineColorSelector(action_red, action_blue, action_orange);
		m_resultPane = new JTextPane();
		m_resultPane.setEditable(false);
		m_resultPane.setBorder(BorderFactory.createTitledBorder(Constants.GROUP_RESULT_TITLE));
		
		this.add(m_lineSelector);
		this.add(new JScrollPane(m_resultPane));
	
	}

}
