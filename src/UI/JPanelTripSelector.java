package UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import Function.TripAttributes;
import Main.Constants;


/**JPanel used to select all the options for finding a route
 * 
 *
 */
@SuppressWarnings("serial")
public class JPanelTripSelector extends JPanelStopSelector {
	
	JComboBox m_cb_routeOptions;
	JPanelTimePicker m_tp_departure;
	JPanelTimePicker m_tp_arrival;
	JButton m_b_addStart;
	JButton m_b_addEnd;
	JButton m_b_removeStart;
	JButton m_b_removeEnd;
	JTextField m_tf_start;
	JTextField m_tf_end;
	JCheckBox m_cb_order;
	
	/**Inherits fields and methods from JPanelStopSelector
	 * 
	 */
	public JPanelTripSelector() {
		super();
	}
	
	@Override
	protected void initializeUIComponents(){
		super.initializeUIComponents();	
		m_cb_routeOptions = new JComboBox(Constants.ROUTE_OPTION.values());
		m_cb_routeOptions.setBorder(BorderFactory.createTitledBorder(Constants.GROUP_ROUTE_OPTION));
		m_tp_departure = new JPanelTimePicker(Constants.GROUP_TIME_DEPARTURE);
		m_tp_arrival = new JPanelTimePicker(Constants.GROUP_TIME_ARRIVAL);
		m_tf_start = new JTextField();
		m_tf_start.setEditable(false);
		m_tf_start.setPreferredSize(Constants.FIELD_GENERAL_DIMENSION);
		m_tf_start.setMinimumSize(Constants.FIELD_GENERAL_DIMENSION);
		m_tf_start.setMaximumSize(Constants.FIELD_GENERAL_DIMENSION);
		m_tf_start.setBorder(BorderFactory.createTitledBorder(Constants.GROUP_STOP_START));
		m_tf_end = new JTextField();
		m_tf_end.setEditable(false);
		m_tf_end.setPreferredSize(Constants.FIELD_GENERAL_DIMENSION);
		m_tf_end.setMinimumSize(Constants.FIELD_GENERAL_DIMENSION);
		m_tf_end.setMaximumSize(Constants.FIELD_GENERAL_DIMENSION);
		m_tf_end.setBorder(BorderFactory.createTitledBorder(Constants.GROUP_STOP_END));
		m_b_addStart = new JButton(Constants.BUTTON_ADD_TEXT);
		m_b_addStart.setMinimumSize(Constants.BUTTON_GENERAL_DIMENSION);
		m_b_addStart.setPreferredSize(Constants.BUTTON_GENERAL_DIMENSION);
		m_b_addStart.setMaximumSize(Constants.BUTTON_GENERAL_DIMENSION);
		m_b_addStart.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {			
				if(!m_availableStops.isSelectionEmpty()){
					Object[] objs = m_availableStops.getSelectedValues();
					if(canInsert(objs[0].toString()) && (!limitStationNumber() || m_numberOfStopsSelected < Constants.INT_MAX_STATIONS_IN_TRIP)){
						m_numberOfStopsSelected++;
						m_tf_start.setText(objs[0].toString());
					}
				}
			}
		});
		m_b_removeStart = new JButton(Constants.BUTTON_REMOVE_TEXT);
		m_b_removeStart.setMinimumSize(Constants.BUTTON_GENERAL_DIMENSION);
		m_b_removeStart.setPreferredSize(Constants.BUTTON_GENERAL_DIMENSION);
		m_b_removeStart.setMaximumSize(Constants.BUTTON_GENERAL_DIMENSION);
		m_b_removeStart.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(m_tf_start.getText() != null && !m_tf_start.getText().equals("")){
					m_numberOfStopsSelected--;
					m_tf_start.setText("");
				}
			}
		});
		m_b_addEnd = new JButton(Constants.BUTTON_ADD_TEXT);
		m_b_addEnd.setMinimumSize(Constants.BUTTON_GENERAL_DIMENSION);
		m_b_addEnd.setPreferredSize(Constants.BUTTON_GENERAL_DIMENSION);
		m_b_addEnd.setMaximumSize(Constants.BUTTON_GENERAL_DIMENSION);
		m_b_addEnd.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(!m_availableStops.isSelectionEmpty()){
					Object[] objs = m_availableStops.getSelectedValues();
					if(canInsert(objs[0].toString()) && (!limitStationNumber() || m_numberOfStopsSelected < Constants.INT_MAX_STATIONS_IN_TRIP)){
						m_numberOfStopsSelected++;
						m_tf_end.setText(objs[0].toString());
					}
				}
			}
		});
		m_b_removeEnd = new JButton(Constants.BUTTON_REMOVE_TEXT);
		m_b_removeEnd.setMinimumSize(Constants.BUTTON_GENERAL_DIMENSION);
		m_b_removeEnd.setPreferredSize(Constants.BUTTON_GENERAL_DIMENSION);
		m_b_removeEnd.setMaximumSize(Constants.BUTTON_GENERAL_DIMENSION);
		m_b_removeEnd.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(m_tf_end.getText() != null && !m_tf_end.getText().equals("")){
					m_numberOfStopsSelected--;
					m_tf_end.setText("");
				}
			}
		});
		m_cb_order = new JCheckBox(Constants.CHECKBOX_ORDER);
	}
	
	@Override
	protected void addUIComponents(){
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));	
		
		JPanel startEndPanel = new JPanel();
		startEndPanel.setLayout(new BoxLayout(startEndPanel, BoxLayout.X_AXIS));
		startEndPanel.add(m_b_addStart);
		startEndPanel.add(m_b_removeStart);
		startEndPanel.add(m_tf_start);
		startEndPanel.add(Box.createHorizontalGlue());
		startEndPanel.add(m_b_addEnd);
		startEndPanel.add(m_b_removeEnd);
		startEndPanel.add(m_tf_end);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
		buttonPanel.add(m_b_add);
		buttonPanel.add(m_b_remove);
		buttonPanel.add(Box.createHorizontalGlue());
		buttonPanel.add(m_cb_order);
		
		JPanel p_options = new JPanel();
		p_options.setLayout(new BoxLayout(p_options, BoxLayout.X_AXIS));
		p_options.add(m_cb_routeOptions);
		p_options.add(Box.createHorizontalGlue());
		p_options.add(m_tp_departure);
		p_options.add(Box.createHorizontalGlue());
		p_options.add(m_tp_arrival);
		p_options.setMaximumSize(Constants.PANEL_GENERAL_DIMENSION);
		
		this.add(p_options);
		this.add(m_lineSelector);
		
		this.add(new JScrollPane(m_availableStops));
		this.add(startEndPanel);
		this.add(buttonPanel);
		this.add(new JScrollPane(m_selectedStops));
		
		this.setMaximumSize(Constants.SUBPANEL_MAXIMUM_DIMENSION);
	}
	
	@Override
	protected boolean canInsert(String selected) {
		return m_numberOfStopsSelected < Constants.INT_MAX_STATIONS_IN_TRIP;
	}
	
	@Override
	protected boolean limitStationNumber() {
		return true;
	}
	
	/**Gets the attributes for the Route:
	 * Attributes include: Departure Time, Arrival Time, Origin Station, Destination Station,
	 * Unordered Route, and the List of Stations the user wishes to go to
	 * @return the attributes for the route.
	 */
	public TripAttributes getTripAttributes(){
		TripAttributes attr = new TripAttributes();
		
		attr.setRouteOption((Constants.ROUTE_OPTION) m_cb_routeOptions.getSelectedItem());
		attr.setUseDepartureTime(m_tp_departure.isUse());
		attr.setUseArrivalTime(m_tp_arrival.isUse());
		attr.setDepartureTime(m_tp_departure.getTime());
		attr.setArrivalTime(m_tp_arrival.getTime());
		attr.setHasOriginStation(!"".equals(m_tf_start.getText()));
		attr.setHasDestinationStation(!"".equals(m_tf_end.getText()));
		attr.setOriginStation(m_tf_start.getText().trim());
		attr.setDestinationStation(m_tf_end.getText().trim());
		attr.setUnordered(m_cb_order.isSelected());
		attr.setListOfStations(getSelectedStops());
		
		

		// ----------- YY's Testing ---------- 
		
		/*
		Vector<Station> vct = new Vector<Station>();
		Station start = null;
		Station end = null;

		vct = Function.FindRoutes.getAllTransfers(Function.FindRoutes.getAllStations(attr));
		start = vct.get(0);
		end = vct.get(1);
		Function.FindRoutes.findSingleRoute(start, end);
		 */

		// ----------- YY's Testing ---------- 
		
		
		return attr;
	}
}
