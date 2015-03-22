package UI;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;

import Main.Constants;

/**JPanel for selecting a time
 * 
 *
 */
@SuppressWarnings("serial")
public class JPanelTimePicker extends JPanel {
	JCheckBox m_use;
	JSpinner m_spinner;
	
	/**Sets up spinner for time selection
	 * Sets up flag, stating if the panel is to be used
	 * @param title Name of the JPanel, e.g. Departure Time
	 */
	public JPanelTimePicker(String title){
		m_spinner = new JSpinner(new SpinnerDateModel());
		m_use = new JCheckBox(Constants.CHECKBOX_USE);
		
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		this.setBorder(BorderFactory.createTitledBorder(title));
		this.add(m_use);
		this.add(m_spinner);
	}
	
	/**Sets the time selection spinner to the current date and time
	 * @return the time selection spinner set to the current date and time 
	 */
	public long getTime(){
		
		SpinnerDateModel model = (SpinnerDateModel) m_spinner.getModel();
		return model.getDate().getTime() / 1000;
	}
	
	/**Gets the m_use field
	 * @return the boolean stating if this panel is to be used
	 */
	public boolean isUse(){
		return m_use.isSelected();
	}
}