package UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

import Function.FindRoutes;
import Function.TripAttributes;
import Main.Constants;

/**JPanel for the Plan Trip (Find Routes) functionality
 * 
 *
 */
@SuppressWarnings("serial")
public class JPanelPlanTrip extends JPanel {

	JPanelTripSelector m_tripSelector;
	JList m_list_available;
	JList m_list_selected;
	DefaultListModel m_availableModel;
	DefaultListModel m_selectedModel;
	JButton m_b_go;
	JTextPane m_result;
	//JTimePicker m_time_start;
	
	/**Sets up StopSelector, TimePicker, and TripSelector JPanels;
	 * Sets up "Go" button and its ActionListener;
	 * Sets up result panel in which route information is printed;
	 */
	public JPanelPlanTrip() {
		super();
		
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		m_tripSelector = new JPanelTripSelector();
		
		m_b_go = new JButton(Constants.BUTTON_GO_TEXT);		
		m_result = new JTextPane();
		m_result.setBorder(BorderFactory.createTitledBorder(Constants.GROUP_RESULT_TITLE));
		
		m_b_go.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				TripAttributes attr = m_tripSelector.getTripAttributes();
				if(attr.isUnordered() && attr.getNumberOfStations() > 5){
					m_result.setText("Please be patient... This will take sometime");
				}
				
				m_result.setText(attr.toString() + "\n" +
						FindRoutes.getResult(m_tripSelector.getTripAttributes()));
			}
		});
		
		JPanel resultPanel = new JPanel();
		resultPanel.setLayout(new BoxLayout(resultPanel, BoxLayout.Y_AXIS));
		resultPanel.add(m_b_go);
		resultPanel.add(new JScrollPane(m_result));
		
		this.add(m_tripSelector);
		this.add(resultPanel);
	}

	
}
