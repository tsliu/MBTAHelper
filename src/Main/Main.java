package Main;
import UI.JFrameMBTAHelper;


/**The Main class;
 * 
 *
 */
public class Main {

	/**Sets up the JFrameMBTAHelper
	 * Uses ParameterHandler to determine use of live data or local files;
	 * @param args the parameters used to determine use of live data or local files
	 * and the path of the local data
	 */
	public static void main(String args[]){
		//Parse the arguments.
		ParameterHandler.handleParameters(args);
		//Establish and show main window.
		@SuppressWarnings("unused")
		JFrameMBTAHelper mainFrame = new JFrameMBTAHelper();
	}
	
}
