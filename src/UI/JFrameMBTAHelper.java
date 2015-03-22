package UI;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;

import Main.Constants;


/**The graphics for the application;
 * Sets up the JFrame for the JPanels used in the rest of the application
 *
 */
@SuppressWarnings("serial")
public class JFrameMBTAHelper extends JFrame {

	JTabbedPane m_tabbedPane;

	/**Sets up the dimensions of the JFrame;
	 * Sets up all the tabs for the different functions of the application;
	 */
	public JFrameMBTAHelper(){
		super(Constants.APPLICATION_NAME);
		this.setSize(Constants.APPLICATION_DIMENSION);
		
		try{
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
			this.setIconImage(ImageIO.read(new File(Constants.PATH_ICON)));
		} catch (Exception ex){
			ex.printStackTrace();
		}

		m_tabbedPane = new JTabbedPane();
		m_tabbedPane.add(Constants.FUNCTION_CHANGE_DATA, new JPanelChangeData());
		m_tabbedPane.add(Constants.FUNCTION_VIEW_MAP, new JPanelMap());
		m_tabbedPane.add(Constants.FUNCTION_TRAIN_LOCATION, new JPanelTrainLocations());
		m_tabbedPane.add(Constants.FUNCTION_FIND_STOP, new JPanelTrainStops());
		m_tabbedPane.add(Constants.FUNCTION_NEXT_ARRIVAL, new JPanelNextArrival());
		m_tabbedPane.add(Constants.FUNCTION_PLAN_TRIP, new JPanelPlanTrip());
		this.add(m_tabbedPane);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
}