package Main;
import java.awt.Color;
import java.awt.Dimension;
import java.io.File;
import java.text.SimpleDateFormat;

import Data.StationManager;
import Data.TripList;


/**Class containing all the constants used in the program. 
 * 
 *
 */
public class Constants {
	
	//Main
	/**Name of the application:
	 * MBTA Helper - KungFu Pandas
	 */
	public final static String APPLICATION_NAME = "MBTA Helper - KungFu Pandas";
	
	//Usage Instructions
	/**Instructions for parameter options
	 * -L: Use Live Data
	 * -F: Use Local Data
	 * -R <path>: Specify path for Red Line File
	 * -B <path>: Specify path for Blue Line File
	 * -O <path>: Specify path for Orange Line File
	 */
	public final static String CMD_INSTRUCTION = 
	"@@@@@@@@@@@@@@@@@@@@@@@@\n" +
	"Available Options:\n" +
	"-L: Use Live Data.\n" +
	"-F: Use Local Files.\n" +
	"-R <path>: Specify path for Red Line File.\n" +
	"-B <path>: Specify path for Blue Line File.\n" +
	"-O <path>: Specify path for Orange Line File.\n" +
	"@@@@@@@@@@@@@@@@@@@@@@@@\n";
	
	//Tab Names
	/**Tab Name for "find train locations" function panel: Train Locataions
	 */
	public final static String FUNCTION_TRAIN_LOCATION = "Train Locations";
	/**Tab Name for "find all stops" function panel: Find Stops
	 */
	public final static String FUNCTION_FIND_STOP = "Find Stops";
	/**Tab Name for selecting data source panel: Data Source
	 */
	public final static String FUNCTION_CHANGE_DATA = "Data Source";
	/**Tab Name for view map panel: View Map
	 */
	public final static String FUNCTION_VIEW_MAP = "View Map";
	/**Tab Name for "view next train arrival times" function panel: Next Arrivals
	 */
	public final static String FUNCTION_NEXT_ARRIVAL = "Next Arrivals";
	/**Tab Name for "find route" function panel: Plan Trip
	 */
	public final static String FUNCTION_PLAN_TRIP = "Plan Trip";
	
	//UI Components
	/**UI Component: Format of the date and time
	 */
	public final static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MM/dd: HH:mm");
	
	/**UI Component: LineColorSelector title
	 */
	public final static String GROUP_SELECT_LINE_TITLE = "Line";
	/**UI Component: Result pane title
	 */
	public final static String GROUP_RESULT_TITLE = "Result";
	/**UI Component: Available Stops pane title
	 */
	public final static String GROUP_AVAILABLE_STOPS_TITLE = "Available";
	/**UI Component: Selected Stops pane title
	 */
	public final static String GROUP_SELECTED_STOPS_TITLE = "Selected";
	/**UI Component: Time Picker, Departure Time pane title
	 */
	public final static String GROUP_TIME_DEPARTURE = "Departure Time";
	/**UI Component: Time Picker, Arrival Time pane title
	 */
	public final static String GROUP_TIME_ARRIVAL = "Arrival Time";
	/**UI Component: Route Options pane title
	 */
	public final static String GROUP_ROUTE_OPTION = "Route Option";
	/**UI Component: Origin Stop pane title
	 */
	public final static String GROUP_STOP_START = "Origin Stop";
	/**UI Component: Destination Stop pane title
	 */
	public final static String GROUP_STOP_END = "Destination Stop";
	
	/**UI Component: Refresh data button text
	 */
	public final static String BUTTON_REFRESH_DATA = "Refresh Live Data";
	/**UI Component: Add stop button text
	 */
	public final static String BUTTON_ADD_TEXT = "+";
	/**UI Component: Remove stop button text
	 */
	public final static String BUTTON_REMOVE_TEXT = "-";
	/**UI Component: Go button text
	 */
	public final static String BUTTON_GO_TEXT = "Go";
	/**UI Component: Red line button text
	 */
	public final static String BUTTON_RED_TEXT = "R";
	/**UI Component: Blue line button text
	 */
	public final static String BUTTON_BLUE_TEXT = "B";
	/**UI Component: Orange line button text
	 */
	public final static String BUTTON_ORANGE_TEXT = "O";
	
	/**UI Component: Unordered flag checkbox text
	 */
	public final static String CHECKBOX_ORDER = "Unordered";
	/**UI Component: Use flag checkbox text
	 */
	public final static String CHECKBOX_USE = "Use";
	
	/**UI Component: Use live data radiobutton text
	 */
	public final static String RADIOBUTTON_USE_LIVE = "Use live data";
	/**UI Component: Use local data radiobutton text
	 */
	public final static String RADIOBUTTON_USE_FILES = "Use local data";
	
	/**Maximum number of stops user can select
	 */
	public final static int INT_MAX_STATIONS_IN_TRIP = 10;
	
	/**Color of Red line button
	 */
	public final static Color BUTTON_RED_COLOR = Color.red;
	/**Color of Blue line button
	 */
	public final static Color BUTTON_BLUE_COLOR = new Color(30, 144, 255);
	/**Color of Orange line button
	 */
	public final static Color BUTTON_ORANGE_COLOR = Color.orange;
	
	/**Dimensions of the application
	 */
	public final static Dimension APPLICATION_DIMENSION = new Dimension(750, 600);
	/**Dimensions of the line selection button
	 */
	public final static Dimension BUTTON_SELECT_LINE_DIMENSION = new Dimension(50, 20);
	/**Dimensions of general buttons
	 */
	public final static Dimension BUTTON_GENERAL_DIMENSION = new Dimension(50,20);
	/**Dimensions of panels
	 */
	public final static Dimension PANEL_GENERAL_DIMENSION = new Dimension(550, 80);
	/**Dimensions of fields
	 */
	public final static Dimension FIELD_GENERAL_DIMENSION = new Dimension(150, 40);
	/**Maximum dimension of a subpanel
	 */
	public final static Dimension SUBPANEL_MAXIMUM_DIMENSION = new Dimension(550, 600);
	
	public static enum LINE_COLOR_SELECTOR {
		RED,
		REDASH,
		REDBRAIN,
		BLUE,
		ORANGE,
		ALL;
	}
	
	public static enum DATA_SOURCE_SELECTOR {
		LIVE,
		FILES;
	}
	
	public static enum ROUTE_OPTION {
		FASTEST_ROUTE,
		EARLIEST_DEPARTURE,
		EARLIEST_ARRIVAL,
		FEWEST_TRANSFER
	}
	
	/**Data Source
	 */
	public static DATA_SOURCE_SELECTOR DATA_SOURCE;
	
	//DATA
	/**MBTA Map image path
	 */
	public final static String PATH_MAP = "MBTAMap.jpg";
	/**Application icon path
	 */
	public final static String PATH_ICON = "kungfupandaicon.png";
	/**URL of Red line live data
	 */
	public final static String URL_RED = "http://developer.mbta.com/lib/rthr/red.json";
	/**URL of Blue line live data
	 */
	public final static String URL_BLUE = "http://developer.mbta.com/lib/rthr/blue.json";
	/**URL of Orange line live data
	 */
	public final static String URL_ORANGE = "http://developer.mbta.com/lib/rthr/orange.json";
	
	/**Red line local data file
	 */
	public static File FILE_RED = null;
	/**Blue line local data file
	 */
	public static File FILE_BLUE = null;
	/**Orange line local data file
	 */
	public static File FILE_ORANGE = null;

	/**TripList for Red line
	 */
	public static TripList DATA_RED;
	/**TripList for Blue line
	 */
	public static TripList DATA_BLUE;
	/**TripList for Orange line
	 */
	public static TripList DATA_ORANGE;

	/**StationManager
	 */
	public static StationManager DATA_STATIONS = new StationManager();
}
