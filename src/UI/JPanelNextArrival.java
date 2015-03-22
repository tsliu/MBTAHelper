package UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

import Function.NextArrival;
import Main.Constants;

/**JPanel used for the NextArrival functionality;
 * User selects a Line Color, and the stops for which he/she wishes to view the next train arrivals
 *
 */
@SuppressWarnings("serial")
public class JPanelNextArrival extends JPanel {

	JPanelStopSelector m_stopSelector;
	JTextPane m_result;
	JButton m_b_go;
	
	/**Sets up StopSelector JPanel;
	 * Sets up "Go" button and its ActionListener;
	 * Sets up the results panel in which NextArrivals are printed
	 */
	public JPanelNextArrival(){
		super();
		
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		this.m_stopSelector = new JPanelStopSelector();
		this.m_result = new JTextPane();
		this.m_result.setBorder(BorderFactory.createTitledBorder(Constants.GROUP_RESULT_TITLE));
		
		m_b_go = new JButton(Constants.BUTTON_GO_TEXT);
		ActionListener al_go = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				m_result.setText(NextArrival.getResult(m_stopSelector.getSelectedStops()));
			}
		};
		m_b_go.addActionListener(al_go);
		m_b_go.setMinimumSize(Constants.BUTTON_GENERAL_DIMENSION);
		m_b_go.setMaximumSize(Constants.BUTTON_GENERAL_DIMENSION);
		m_b_go.setPreferredSize(Constants.BUTTON_GENERAL_DIMENSION);
		
		JPanel resultPanel = new JPanel();
		resultPanel.setLayout(new BoxLayout(resultPanel, BoxLayout.Y_AXIS));
		resultPanel.add(m_b_go);
		resultPanel.add(new JScrollPane(m_result));
		
		this.add(m_stopSelector);
		this.add(resultPanel);
	}

}
