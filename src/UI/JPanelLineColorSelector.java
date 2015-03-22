package UI;

import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import Main.Constants;


/**JPanel in which user selects the Line Color;
 * Used in JPanels where a line needs to be selected, with the exception of data source selection.
 *
 */
@SuppressWarnings("serial")
public class JPanelLineColorSelector extends JPanel {

	JButton red;
	JButton blue;
	JButton orange;
	
	/**Sets up buttons for the Blue, Orange, and Red lines;
	 * Button behaves differently based upon the ActionListener parameters
	 * @param action_red the action taken when the Red button is activated
	 * @param action_blue the action taken when the Blue button is activated
	 * @param action_orange the action taken when the Orange button is activated
	 */
	public JPanelLineColorSelector(ActionListener action_red, ActionListener action_blue, ActionListener action_orange){
		super();
		
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		this.setBorder(BorderFactory.createTitledBorder(Constants.GROUP_SELECT_LINE_TITLE));
		
		red = new JButton(Constants.BUTTON_RED_TEXT);
		red.setBackground(Constants.BUTTON_RED_COLOR);
		//red.setMinimumSize(Constants.BUTTON_SELECT_LINE_DIMENSION);
		red.setMaximumSize(Constants.BUTTON_SELECT_LINE_DIMENSION);
		red.setPreferredSize(Constants.BUTTON_SELECT_LINE_DIMENSION);
		red.addActionListener(action_red);
		
		this.add(Box.createVerticalGlue());
		
		blue = new JButton(Constants.BUTTON_BLUE_TEXT);
		blue.setBackground(Constants.BUTTON_BLUE_COLOR);
		//blue.setMinimumSize(Constants.BUTTON_SELECT_LINE_DIMENSION);
		blue.setMaximumSize(Constants.BUTTON_SELECT_LINE_DIMENSION);
		blue.setPreferredSize(Constants.BUTTON_SELECT_LINE_DIMENSION);
		blue.addActionListener(action_blue);
		
		this.add(Box.createVerticalGlue());
		
		orange = new JButton(Constants.BUTTON_ORANGE_TEXT);
		orange.setBackground(Constants.BUTTON_ORANGE_COLOR);
		//orange.setMinimumSize(Constants.BUTTON_SELECT_LINE_DIMENSION);
		orange.setMaximumSize(Constants.BUTTON_SELECT_LINE_DIMENSION);
		orange.setPreferredSize(Constants.BUTTON_SELECT_LINE_DIMENSION);
		orange.addActionListener(action_orange);
		
		this.add(red);
		this.add(blue);
		this.add(orange);
		
		this.setMaximumSize(new Dimension(3 * Constants.BUTTON_SELECT_LINE_DIMENSION.width, 3 * Constants.BUTTON_SELECT_LINE_DIMENSION.height));
	}
	
}
