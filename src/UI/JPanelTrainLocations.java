package UI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

import Function.TrainLocations;
import Main.Constants;


/**JPanel used for the (Find) Train Locations functionality 
 *
 */
@SuppressWarnings("serial")
public class JPanelTrainLocations extends JPanel {

	JPanelLineColorSelector m_lineSelector;
	JTextPane m_resultPane;
	
	/**Uses the LineColorSelector JPanel;
	 * Sets up the ActionListeners for the Blue, Orange, and Red line buttons;
	 * Sets up the result panel in which the train locations are printed
	 */
	public JPanelTrainLocations(){
		super();
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		ActionListener action_red = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				m_resultPane.setText(TrainLocations.getRedLineLocations(Constants.DATA_RED));
			}	
		};
		
		ActionListener action_blue = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				m_resultPane.setText(TrainLocations.getBlueLineLocations(Constants.DATA_BLUE));
			}
		};
		
		ActionListener action_orange = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				m_resultPane.setText(TrainLocations.getOrangeLineLocations(Constants.DATA_ORANGE));
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
