package UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import Main.Constants;

/**JPanel for selecting stops; 
 * Used in NextArrivals and PlanTrip panels 
 *
 */
@SuppressWarnings("serial")
public class JPanelStopSelector extends JPanel{
	
	int m_numberOfStopsSelected;
	JPanelLineColorSelector m_lineSelector;
	JList m_availableStops;
	JList m_selectedStops;
	DefaultListModel m_list_available;
	DefaultListModel m_list_selected;
	JButton m_b_add;
	JButton m_b_remove;

	/**Sets up ActionListeners for Blue, Orange, and Red Lines
	 * Sets up stops for Blue, Orange, and Red Lines
	 * Sets up "Add" and "Remove" Buttons and their Actions
	 * Sets up "Go" button and its Action
	 */
	public JPanelStopSelector(){
		super();
		m_numberOfStopsSelected = 0;
		initializeUIComponents();
		addUIComponents();
	}
	
	protected void initializeUIComponents(){
		
		ActionListener al_red = new ActionListener(){
			@SuppressWarnings("static-access")
			@Override
			public void actionPerformed(ActionEvent arg0) {
				m_availableStops.setListData(Constants.DATA_STATIONS.getStationNames(Constants.LINE_COLOR_SELECTOR.RED));
			}
		};
		ActionListener al_blue = new ActionListener(){
			@SuppressWarnings("static-access")
			@Override
			public void actionPerformed(ActionEvent arg0) {
				m_availableStops.setListData(Constants.DATA_STATIONS.getStationNames(Constants.LINE_COLOR_SELECTOR.BLUE));
			}
		};
		ActionListener al_orange = new ActionListener(){
			@SuppressWarnings("static-access")
			@Override
			public void actionPerformed(ActionEvent arg0) {
				m_availableStops.setListData(Constants.DATA_STATIONS.getStationNames(Constants.LINE_COLOR_SELECTOR.ORANGE));
			}
		};
		m_lineSelector = new JPanelLineColorSelector(al_red, al_blue, al_orange);
		
		ActionListener al_add = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0){
				if(!m_availableStops.isSelectionEmpty()){
					for(Object obj : m_availableStops.getSelectedValues()){
						if(canInsert(obj.toString())){
							m_list_selected.addElement(obj);
							m_numberOfStopsSelected++;
						}
					}
				}
			}
		};
		m_b_add = new JButton(Constants.BUTTON_ADD_TEXT);
		m_b_add.setMaximumSize(Constants.BUTTON_GENERAL_DIMENSION);
		m_b_add.setPreferredSize(Constants.BUTTON_GENERAL_DIMENSION);
		m_b_add.addActionListener(al_add);
		ActionListener al_remove = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0){
				if(!m_selectedStops.isSelectionEmpty()){
					for(Object obj : m_selectedStops.getSelectedValues()){
						m_list_selected.removeElement(obj);
						m_numberOfStopsSelected--;
					}
				}
			}
		};
		m_b_remove = new JButton(Constants.BUTTON_REMOVE_TEXT);
		m_b_remove.setMaximumSize(Constants.BUTTON_GENERAL_DIMENSION);
		m_b_remove.setPreferredSize(Constants.BUTTON_GENERAL_DIMENSION);
		m_b_remove.addActionListener(al_remove);
		


		m_list_available = new DefaultListModel();
		m_availableStops = new JList(m_list_available);
		m_availableStops.setBorder(BorderFactory.createTitledBorder(Constants.GROUP_AVAILABLE_STOPS_TITLE));
		m_availableStops.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		m_list_selected = new DefaultListModel();
		m_selectedStops = new JList(m_list_selected);
		m_selectedStops.setBorder(BorderFactory.createTitledBorder(Constants.GROUP_SELECTED_STOPS_TITLE));
		m_selectedStops.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
	}
	
	protected boolean canInsert(String selected) {
		return !m_list_selected.contains(selected);
	}

	protected boolean limitStationNumber() {
		return false;
	}

	protected void addUIComponents(){
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
		buttonPanel.add(m_b_add);
		buttonPanel.add(m_b_remove);
		
		this.add(m_lineSelector);
		this.add(new JScrollPane(m_availableStops));
		this.add(buttonPanel);
		this.add(new JScrollPane(m_selectedStops));
		
		this.setMaximumSize(Constants.SUBPANEL_MAXIMUM_DIMENSION);
	}
	
	/**Gets the list of stops that have been selected
	 * @return a Vector of Strings of the selected stops
	 */
	public Vector<String> getSelectedStops(){
		Vector<String> stops = new Vector<String>();
		for(int i = 0; i < m_selectedStops.getModel().getSize(); i++){
			stops.add( m_selectedStops.getModel().getElementAt(i).toString() );
		}
		return stops;
	}
}