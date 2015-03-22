package Data;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.Vector;

import Main.Constants;

/**StationManager creates all the Stations; Used to interface with the Stations.
 * 
 *
 */
public class StationManager {
	LinkedHashMap<String, Station> m_stations;

	private static Vector<String> m_station_names;
	private static Vector<String> m_red_names;
	private static Vector<String> m_blue_names;
	private static Vector<String> m_orange_names;

	/**Creates instances of all the StationManager fields
	 * m_stations - Hashmap of all Stations
	 * m_station_names - Vector of all station names
	 * m_red_names - Vector of all station names on the red line
	 * m_blue_names - Vector of all station names on the blue line
	 * m_orange_names - Vector of all station names on the orange line
	 * 
	 */
	public StationManager(){
		this.m_stations = new LinkedHashMap<String, Station>();
		m_station_names = new Vector<String>();
		m_red_names = new Vector<String>();
		m_blue_names = new Vector<String>();
		m_orange_names = new Vector<String>();
	}

	/**Method used to create the StationManager
	 * 
	 */
	public static void createStationManager(){
		Constants.DATA_STATIONS = new StationManager();
		initializeStations();		 
	}

	/**Adds the given stopName to the given line color
	 * @param stopName the given stopName
	 * @param line the given line color
	 */
	public void addName(String stopName, Constants.LINE_COLOR_SELECTOR line){
		switch(line){
		case REDBRAIN:
		case REDASH:
		case RED:
			m_red_names.add(stopName);
			break;
		case BLUE:
			m_blue_names.add(stopName);
			break;
		case ORANGE:
			m_orange_names.add(stopName);
			break;
		default:
			break;
		}
		m_station_names.add(stopName);
	}

	/**Gets all the station names
	 * @return the Vector of all the station names 
	 */
	public static Vector<String> getStationNames(){
		return m_station_names;
	}

	/**Gets all the station names for the given line color
	 * @param line the given line color
	 * @return the Vector of all the station names for the given line
	 */
	public static Vector<String> getStationNames(Constants.LINE_COLOR_SELECTOR line){
		switch(line){
		case RED:
			return m_red_names;
		case BLUE:
			return m_blue_names;
		case ORANGE:
			return m_orange_names;
		default:
			return null;
		}
	}

	// is the station in the line?
	/**Is the given station in the given line?
	 * @param station the name of the given station
	 * @param line the line color
	 * @return If the station is in the line, true; If the station is not in the line, false;
	 */
	public static Boolean findStation(String station, Constants.LINE_COLOR_SELECTOR line) {
		Vector<String> stations = getStationNames(line);
		for(String str : stations) {
			return station.equals(str);
		}
		return false;
	}

	// given a station name, find and return the station.
	// default if available is the RED LINE
	// ex. Downtown Crossing will return Downtown Crossing_South Station
	/**Gets the Station given the station name (for the red line)
	 * @param stationName the name of the Station
	 * @return the Station instance of the given station name
	 * @throws Exception if station name does not match a station in the red line
	 */
	public Station getStation(String stationName) throws Exception {
		for (Entry<String, Station> entry : Constants.DATA_STATIONS.m_stations.entrySet()) {
			String[] key = entry.getKey().split("_");
			Station value = entry.getValue();
			if(key[0].contains(stationName))
				return value;
		}
		throw new Exception("Invalid stationName");
	}

	// given a station name and line color, find and return the station.
	// ex. Downtown Crossing, ORANGE will return Downtown Crossing_Chinatown
	/**Gets the Station given the station name and line color
	 * @param stationName the name of the Station
	 * @param color the color of the line
	 * @return the Station instance of the given station name in the given line color
	 * @throws Exception if station name or line color is not found
	 */
	public static Station getStation(String stationName, Constants.LINE_COLOR_SELECTOR color) throws Exception {
		for (Entry<String, Station> entry : Constants.DATA_STATIONS.m_stations.entrySet()) {
			String[] key = entry.getKey().split("_");
			Station value = entry.getValue();
			if(key[0].contains(stationName) && (entry.getValue().getLineColor().equals(color)))
				return value;
		}
		throw new Exception("Invalid stationName or color: " + stationName + ", " + color);
	}

	// initializes all the Stations
	private static void initializeStations(){
		// Red Line Proper
		Constants.DATA_STATIONS.m_stations.put("Alewife_Davis", new Station("Alewife", Constants.LINE_COLOR_SELECTOR.RED, 11));
		Constants.DATA_STATIONS.m_stations.put("Davis_Porter Square", new Station ("Davis", Constants.LINE_COLOR_SELECTOR.RED, 10));
		Constants.DATA_STATIONS.m_stations.put("Porter Square_Harvard Square", new Station("Porter Square", Constants.LINE_COLOR_SELECTOR.RED, 9));
		Constants.DATA_STATIONS.m_stations.put("Harvard Square_Central Square", new Station("Harvard Square", Constants.LINE_COLOR_SELECTOR.RED, 8));
		Constants.DATA_STATIONS.m_stations.put("Central Square_Kendall/MIT", new Station("Central Square", Constants.LINE_COLOR_SELECTOR.RED, 7));
		Constants.DATA_STATIONS.m_stations.put("Kendall/MIT_Charles/MGH", new Station("Kendall/MIT", Constants.LINE_COLOR_SELECTOR.RED, 6));
		Constants.DATA_STATIONS.m_stations.put("Charles/MGH_Park Street", new Station("Charles/MGH", Constants.LINE_COLOR_SELECTOR.RED, 5));
		Constants.DATA_STATIONS.m_stations.put("Park Street_Downtown Crossing", new Station("Park Street", Constants.LINE_COLOR_SELECTOR.RED, 4));
		Constants.DATA_STATIONS.m_stations.put("Downtown Crossing_South Station", new Station("Downtown Crossing", Constants.LINE_COLOR_SELECTOR.RED, 3, true));
		Constants.DATA_STATIONS.m_stations.put("South Station_Broadway", new Station("South Station", Constants.LINE_COLOR_SELECTOR.RED, 2));
		Constants.DATA_STATIONS.m_stations.put("Broadway_Andrew", new Station("Broadway", Constants.LINE_COLOR_SELECTOR.RED, 1));
		Constants.DATA_STATIONS.m_stations.put("Andrew_JFK/UMass", new Station("Andrew", Constants.LINE_COLOR_SELECTOR.RED, 0));

		// Red Line Ashmont
		Constants.DATA_STATIONS.m_stations.put("JFK/UMass_Savin Hill", new Station("JFK/UMass", Constants.LINE_COLOR_SELECTOR.REDASH, 4, true));
		Constants.DATA_STATIONS.m_stations.put("Savin Hill_Fields Corner", new Station("Savin Hill", Constants.LINE_COLOR_SELECTOR.REDASH, 3));
		Constants.DATA_STATIONS.m_stations.put("Fields Corner_Shawmut", new Station("Fields Corner", Constants.LINE_COLOR_SELECTOR.REDASH, 2));
		Constants.DATA_STATIONS.m_stations.put("Shawmut_Ashmont", new Station("Shawmut", Constants.LINE_COLOR_SELECTOR.REDASH, 1));
		Constants.DATA_STATIONS.m_stations.put("Ashmont_End", new Station("Ashmont", Constants.LINE_COLOR_SELECTOR.REDASH, 0));

		// Red Line Braintree
		Constants.DATA_STATIONS.m_stations.put("JFK/UMass_North Quincy", new Station("JFK/UMass", Constants.LINE_COLOR_SELECTOR.REDBRAIN, 5, true));
		Constants.DATA_STATIONS.m_stations.put("North Quincy_Wollaston", new Station("North Quincy", Constants.LINE_COLOR_SELECTOR.REDBRAIN, 4));
		Constants.DATA_STATIONS.m_stations.put("Wollaston_Quincy Center", new Station("Wollaston", Constants.LINE_COLOR_SELECTOR.REDBRAIN, 3));
		Constants.DATA_STATIONS.m_stations.put("Quincy Center_Quincy Adams", new Station("Quincy Center", Constants.LINE_COLOR_SELECTOR.REDBRAIN, 2));
		Constants.DATA_STATIONS.m_stations.put("Quincy Adams_Braintree", new Station("Quincy Adams", Constants.LINE_COLOR_SELECTOR.REDBRAIN, 1));
		Constants.DATA_STATIONS.m_stations.put("Braintree_End", new Station("Braintree", Constants.LINE_COLOR_SELECTOR.REDBRAIN, 0));

		// Orange Line
		Constants.DATA_STATIONS.m_stations.put("Oak Grove_Malden Center", new Station("Oak Grove", Constants.LINE_COLOR_SELECTOR.ORANGE, 18));
		Constants.DATA_STATIONS.m_stations.put("Malden Center_Wellington", new Station("Malden Center", Constants.LINE_COLOR_SELECTOR.ORANGE, 17));
		Constants.DATA_STATIONS.m_stations.put("Wellington_Sullivan", new Station("Wellington", Constants.LINE_COLOR_SELECTOR.ORANGE, 16));
		Constants.DATA_STATIONS.m_stations.put("Sullivan_Community College", new Station("Sullivan", Constants.LINE_COLOR_SELECTOR.ORANGE, 15));
		Constants.DATA_STATIONS.m_stations.put("Community College_North Station", new Station("Community College", Constants.LINE_COLOR_SELECTOR.ORANGE, 14));
		Constants.DATA_STATIONS.m_stations.put("North Station_Haymarket", new Station("North Station", Constants.LINE_COLOR_SELECTOR.ORANGE, 13));
		Constants.DATA_STATIONS.m_stations.put("Haymarket_State Street", new Station("Haymarket", Constants.LINE_COLOR_SELECTOR.ORANGE, 12));
		Constants.DATA_STATIONS.m_stations.put("State Street_Downtown Crossing", new Station ("State Street", Constants.LINE_COLOR_SELECTOR.ORANGE, 11, true));
		Constants.DATA_STATIONS.m_stations.put("Downtown Crossing_Chinatown", new Station("Downtown Crossing", Constants.LINE_COLOR_SELECTOR.ORANGE, 10, true));
		Constants.DATA_STATIONS.m_stations.put("Chinatown_Tufts Medical", new Station("Chinatown", Constants.LINE_COLOR_SELECTOR.ORANGE, 9));
		Constants.DATA_STATIONS.m_stations.put("Tufts Medical_Back Bay", new Station("Tufts Medical", Constants.LINE_COLOR_SELECTOR.ORANGE, 8));
		Constants.DATA_STATIONS.m_stations.put("Back Bay_Mass Ave", new Station("Back Bay", Constants.LINE_COLOR_SELECTOR.ORANGE, 7));
		Constants.DATA_STATIONS.m_stations.put("Mass Ave_Ruggles", new Station("Mass Ave", Constants.LINE_COLOR_SELECTOR.ORANGE, 6));
		Constants.DATA_STATIONS.m_stations.put("Ruggles_Roxbury Crossing", new Station("Ruggles", Constants.LINE_COLOR_SELECTOR.ORANGE, 5));
		Constants.DATA_STATIONS.m_stations.put("Roxbury Crossing_Jackson Square", new Station("Roxbury Crossing", Constants.LINE_COLOR_SELECTOR.ORANGE, 4));
		Constants.DATA_STATIONS.m_stations.put("Jackson Square_Stony Brook", new Station("Jackson Square", Constants.LINE_COLOR_SELECTOR.ORANGE, 3));
		Constants.DATA_STATIONS.m_stations.put("Stony Brook_Green Street", new Station("Stony Brook", Constants.LINE_COLOR_SELECTOR.ORANGE, 2));
		Constants.DATA_STATIONS.m_stations.put("Green Street_Forest Hills", new Station("Green Street", Constants.LINE_COLOR_SELECTOR.ORANGE, 1));
		Constants.DATA_STATIONS.m_stations.put("Forest Hills_End", new Station("Forest Hills", Constants.LINE_COLOR_SELECTOR.ORANGE, 0));

		// Blue Line
		Constants.DATA_STATIONS.m_stations.put("Wonderland_Revere Beach", new Station("Wonderland", Constants.LINE_COLOR_SELECTOR.BLUE, 11));
		Constants.DATA_STATIONS.m_stations.put("Revere Beach_Beachmont", new Station("Revere Beach", Constants.LINE_COLOR_SELECTOR.BLUE, 10));
		Constants.DATA_STATIONS.m_stations.put("Beachmont_Suffolk Downs", new Station("Beachmont", Constants.LINE_COLOR_SELECTOR.BLUE, 9));
		Constants.DATA_STATIONS.m_stations.put("Suffolk Downs_Orient Heights", new Station("Suffolk Downs", Constants.LINE_COLOR_SELECTOR.BLUE, 8));
		Constants.DATA_STATIONS.m_stations.put("Orient Heights_Wood Island", new Station("Orient Heights", Constants.LINE_COLOR_SELECTOR.BLUE, 7));
		Constants.DATA_STATIONS.m_stations.put("Wood Island_Airport", new Station("Wood Island", Constants.LINE_COLOR_SELECTOR.BLUE, 6));
		Constants.DATA_STATIONS.m_stations.put("Airport_Maverick", new Station("Airport", Constants.LINE_COLOR_SELECTOR.BLUE, 5));
		Constants.DATA_STATIONS.m_stations.put("Maverick_Aquarium", new Station("Maverick", Constants.LINE_COLOR_SELECTOR.BLUE, 4));
		Constants.DATA_STATIONS.m_stations.put("Aquarium_State Street", new Station("Aquarium", Constants.LINE_COLOR_SELECTOR.BLUE, 3));
		Constants.DATA_STATIONS.m_stations.put("State Street_Government Center", new Station("State Street", Constants.LINE_COLOR_SELECTOR.BLUE, 2, true));
		Constants.DATA_STATIONS.m_stations.put("Government Center_Bowdoin", new Station("Government Center", Constants.LINE_COLOR_SELECTOR.BLUE, 1));
		Constants.DATA_STATIONS.m_stations.put("Bowdoin_End", new Station("Bowdoin", Constants.LINE_COLOR_SELECTOR.BLUE, 0));

		setupStationLine();
	}

	// sets up all the PreviousStation and NextStation for all Stations
	// sets up TransferStation for Stations that are transfers
	// sets up all the stops
	private static void setupStationLine() {
		setupPreviousStation();
		setupNextStation();
		setTransfers();
		setStops();
	}

	// sets up all the PreviousStation for all Stations
	private static void setupPreviousStation() {
		//Red Line Proper
		Constants.DATA_STATIONS.m_stations.get("Alewife_Davis").setPreviousStation(null);
		Constants.DATA_STATIONS.m_stations.get("Davis_Porter Square").setPreviousStation(Constants.DATA_STATIONS.m_stations.get("Alewife_Davis")); 
		Constants.DATA_STATIONS.m_stations.get("Porter Square_Harvard Square").setPreviousStation(Constants.DATA_STATIONS.m_stations.get("Davis_Porter Square"));
		Constants.DATA_STATIONS.m_stations.get("Harvard Square_Central Square").setPreviousStation(Constants.DATA_STATIONS.m_stations.get("Porter Square_Harvard Square"));
		Constants.DATA_STATIONS.m_stations.get("Central Square_Kendall/MIT").setPreviousStation(Constants.DATA_STATIONS.m_stations.get("Harvard Square_Central Square"));
		Constants.DATA_STATIONS.m_stations.get("Kendall/MIT_Charles/MGH").setPreviousStation(Constants.DATA_STATIONS.m_stations.get("Central Square_Kendall/MIT"));
		Constants.DATA_STATIONS.m_stations.get("Charles/MGH_Park Street").setPreviousStation(Constants.DATA_STATIONS.m_stations.get("Kendall/MIT_Charles/MGH"));
		Constants.DATA_STATIONS.m_stations.get("Park Street_Downtown Crossing").setPreviousStation(Constants.DATA_STATIONS.m_stations.get("Charles/MGH_Park Street"));
		Constants.DATA_STATIONS.m_stations.get("Downtown Crossing_South Station").setPreviousStation(Constants.DATA_STATIONS.m_stations.get("Park Street_Downtown Crossing"));
		Constants.DATA_STATIONS.m_stations.get("South Station_Broadway").setPreviousStation(Constants.DATA_STATIONS.m_stations.get("Downtown Crossing_South Station"));
		Constants.DATA_STATIONS.m_stations.get("Broadway_Andrew").setPreviousStation(Constants.DATA_STATIONS.m_stations.get("South Station_Broadway"));
		Constants.DATA_STATIONS.m_stations.get("Andrew_JFK/UMass").setPreviousStation(Constants.DATA_STATIONS.m_stations.get("Broadway_Andrew"));

		//Red Line Ashmont
		Constants.DATA_STATIONS.m_stations.get("JFK/UMass_Savin Hill").setPreviousStation(Constants.DATA_STATIONS.m_stations.get("Andrew_JFK/UMass"));
		Constants.DATA_STATIONS.m_stations.get("Savin Hill_Fields Corner").setPreviousStation(Constants.DATA_STATIONS.m_stations.get("JFK/UMass_Savin Hill"));
		Constants.DATA_STATIONS.m_stations.get("Fields Corner_Shawmut").setPreviousStation(Constants.DATA_STATIONS.m_stations.get("Savin Hill_Fields Corner"));
		Constants.DATA_STATIONS.m_stations.get("Shawmut_Ashmont").setPreviousStation(Constants.DATA_STATIONS.m_stations.get("Fields Corner_Shawmut"));
		Constants.DATA_STATIONS.m_stations.get("Ashmont_End").setPreviousStation(Constants.DATA_STATIONS.m_stations.get("Shawmut_Ashmont"));

		// Red Line Braintree
		Constants.DATA_STATIONS.m_stations.get("JFK/UMass_North Quincy").setPreviousStation(Constants.DATA_STATIONS.m_stations.get("Andrew_JFK/UMass"));
		Constants.DATA_STATIONS.m_stations.get("North Quincy_Wollaston").setPreviousStation(Constants.DATA_STATIONS.m_stations.get("JFK/UMass_North Quincy"));
		Constants.DATA_STATIONS.m_stations.get("Wollaston_Quincy Center").setPreviousStation(Constants.DATA_STATIONS.m_stations.get("North Quincy_Wollaston"));
		Constants.DATA_STATIONS.m_stations.get("Quincy Center_Quincy Adams").setPreviousStation(Constants.DATA_STATIONS.m_stations.get("Wollaston_Quincy Center"));
		Constants.DATA_STATIONS.m_stations.get("Quincy Adams_Braintree").setPreviousStation(Constants.DATA_STATIONS.m_stations.get("Quincy Center_Quincy Adams"));
		Constants.DATA_STATIONS.m_stations.get("Braintree_End").setPreviousStation(Constants.DATA_STATIONS.m_stations.get("Quincy Adams_Braintree"));

		// Orange Line
		Constants.DATA_STATIONS.m_stations.get("Oak Grove_Malden Center").setPreviousStation(null);
		Constants.DATA_STATIONS.m_stations.get("Malden Center_Wellington").setPreviousStation(Constants.DATA_STATIONS.m_stations.get("Oak Grove_Malden Center"));
		Constants.DATA_STATIONS.m_stations.get("Wellington_Sullivan").setPreviousStation(Constants.DATA_STATIONS.m_stations.get("Malden Center_Wellington"));
		Constants.DATA_STATIONS.m_stations.get("Sullivan_Community College").setPreviousStation(Constants.DATA_STATIONS.m_stations.get("Wellington_Sullivan"));
		Constants.DATA_STATIONS.m_stations.get("Community College_North Station").setPreviousStation(Constants.DATA_STATIONS.m_stations.get("Sullivan_Community College"));
		Constants.DATA_STATIONS.m_stations.get("North Station_Haymarket").setPreviousStation(Constants.DATA_STATIONS.m_stations.get("Community College_North Station"));
		Constants.DATA_STATIONS.m_stations.get("Haymarket_State Street").setPreviousStation(Constants.DATA_STATIONS.m_stations.get("North Station_Haymarket"));
		Constants.DATA_STATIONS.m_stations.get("State Street_Downtown Crossing").setPreviousStation(Constants.DATA_STATIONS.m_stations.get("Haymarket_State Street"));
		Constants.DATA_STATIONS.m_stations.get("Downtown Crossing_Chinatown").setPreviousStation(Constants.DATA_STATIONS.m_stations.get("State Street_Downtown Crossing"));
		Constants.DATA_STATIONS.m_stations.get("Chinatown_Tufts Medical").setPreviousStation(Constants.DATA_STATIONS.m_stations.get("Downtown Crossing_Chinatown"));
		Constants.DATA_STATIONS.m_stations.get("Tufts Medical_Back Bay").setPreviousStation(Constants.DATA_STATIONS.m_stations.get("Chinatown_Tufts Medical"));
		Constants.DATA_STATIONS.m_stations.get("Back Bay_Mass Ave").setPreviousStation(Constants.DATA_STATIONS.m_stations.get("Tufts Medical_Back Bay"));
		Constants.DATA_STATIONS.m_stations.get("Mass Ave_Ruggles").setPreviousStation(Constants.DATA_STATIONS.m_stations.get("Back Bay_Mass Ave"));
		Constants.DATA_STATIONS.m_stations.get("Ruggles_Roxbury Crossing").setPreviousStation(Constants.DATA_STATIONS.m_stations.get("Mass Ave_Ruggles"));
		Constants.DATA_STATIONS.m_stations.get("Roxbury Crossing_Jackson Square").setPreviousStation(Constants.DATA_STATIONS.m_stations.get("Ruggles_Roxbury Crossing"));
		Constants.DATA_STATIONS.m_stations.get("Jackson Square_Stony Brook").setPreviousStation(Constants.DATA_STATIONS.m_stations.get("Roxbury Crossing_Jackson Square"));
		Constants.DATA_STATIONS.m_stations.get("Stony Brook_Green Street").setPreviousStation(Constants.DATA_STATIONS.m_stations.get("Jackson Square_Stony Brook"));
		Constants.DATA_STATIONS.m_stations.get("Green Street_Forest Hills").setPreviousStation(Constants.DATA_STATIONS.m_stations.get("Stony Brook_Green Street"));
		Constants.DATA_STATIONS.m_stations.get("Forest Hills_End").setPreviousStation(Constants.DATA_STATIONS.m_stations.get("Green Street_Forest Hills"));

		// Blue Line
		Constants.DATA_STATIONS.m_stations.get("Wonderland_Revere Beach").setPreviousStation(null);
		Constants.DATA_STATIONS.m_stations.get("Revere Beach_Beachmont").setPreviousStation(Constants.DATA_STATIONS.m_stations.get("Wonderland_Revere Beach"));
		Constants.DATA_STATIONS.m_stations.get("Beachmont_Suffolk Downs").setPreviousStation(Constants.DATA_STATIONS.m_stations.get("Revere Beach_Beachmont"));
		Constants.DATA_STATIONS.m_stations.get("Suffolk Downs_Orient Heights").setPreviousStation(Constants.DATA_STATIONS.m_stations.get("Beachmont_Suffolk Downs"));
		Constants.DATA_STATIONS.m_stations.get("Orient Heights_Wood Island").setPreviousStation(Constants.DATA_STATIONS.m_stations.get("Suffolk Downs_Orient Heights"));
		Constants.DATA_STATIONS.m_stations.get("Wood Island_Airport").setPreviousStation(Constants.DATA_STATIONS.m_stations.get("Orient Heights_Wood Island"));
		Constants.DATA_STATIONS.m_stations.get("Airport_Maverick").setPreviousStation(Constants.DATA_STATIONS.m_stations.get("Wood Island_Airport"));
		Constants.DATA_STATIONS.m_stations.get("Maverick_Aquarium").setPreviousStation(Constants.DATA_STATIONS.m_stations.get("Airport_Maverick"));
		Constants.DATA_STATIONS.m_stations.get("Aquarium_State Street").setPreviousStation(Constants.DATA_STATIONS.m_stations.get("Maverick_Aquarium"));
		Constants.DATA_STATIONS.m_stations.get("State Street_Government Center").setPreviousStation(Constants.DATA_STATIONS.m_stations.get("Aquarium_State Street"));
		Constants.DATA_STATIONS.m_stations.get("Government Center_Bowdoin").setPreviousStation(Constants.DATA_STATIONS.m_stations.get("State Street_Government Center"));
		Constants.DATA_STATIONS.m_stations.get("Bowdoin_End").setPreviousStation(Constants.DATA_STATIONS.m_stations.get("Government Center_Bowdoin"));
	}

	// sets up all the NextStations for all Stations
	private static void setupNextStation() {
		//Red Line Proper
		Constants.DATA_STATIONS.m_stations.get("Alewife_Davis").setNextStation(Constants.DATA_STATIONS.m_stations.get("Davis_Porter Square"));
		Constants.DATA_STATIONS.m_stations.get("Davis_Porter Square").setNextStation(Constants.DATA_STATIONS.m_stations.get("Porter Square_Harvard Square"));
		Constants.DATA_STATIONS.m_stations.get("Porter Square_Harvard Square").setNextStation(Constants.DATA_STATIONS.m_stations.get("Harvard Square_Central Square"));
		Constants.DATA_STATIONS.m_stations.get("Harvard Square_Central Square").setNextStation(Constants.DATA_STATIONS.m_stations.get("Central Square_Kendall/MIT"));
		Constants.DATA_STATIONS.m_stations.get("Central Square_Kendall/MIT").setNextStation(Constants.DATA_STATIONS.m_stations.get("Kendall/MIT_Charles/MGH"));
		Constants.DATA_STATIONS.m_stations.get("Kendall/MIT_Charles/MGH").setNextStation(Constants.DATA_STATIONS.m_stations.get("Charles/MGH_Park Street"));
		Constants.DATA_STATIONS.m_stations.get("Charles/MGH_Park Street").setNextStation(Constants.DATA_STATIONS.m_stations.get("Park Street_Downtown Crossing"));
		Constants.DATA_STATIONS.m_stations.get("Park Street_Downtown Crossing").setNextStation(Constants.DATA_STATIONS.m_stations.get("Downtown Crossing_South Station"));
		Constants.DATA_STATIONS.m_stations.get("Downtown Crossing_South Station").setNextStation(Constants.DATA_STATIONS.m_stations.get("South Station_Broadway"));
		Constants.DATA_STATIONS.m_stations.get("South Station_Broadway").setNextStation(Constants.DATA_STATIONS.m_stations.get("Broadway_Andrew"));
		Constants.DATA_STATIONS.m_stations.get("Broadway_Andrew").setNextStation(Constants.DATA_STATIONS.m_stations.get("Andrew_JFK/UMass"));
		//Constants.DATA_STATIONS.m_stations.get("Andrew_JFK/UMass").setNextStation(Constants.DATA_STATIONS.m_stations.get("JFK/UMass_Savin Hill"));
		Constants.DATA_STATIONS.m_stations.get("Andrew_JFK/UMass").setNextStation(null);

		//Red Line Ashmont
		Constants.DATA_STATIONS.m_stations.get("JFK/UMass_Savin Hill").setNextStation(Constants.DATA_STATIONS.m_stations.get("Savin Hill_Fields Corner"));
		Constants.DATA_STATIONS.m_stations.get("Savin Hill_Fields Corner").setNextStation(Constants.DATA_STATIONS.m_stations.get("Fields Corner_Shawmut"));
		Constants.DATA_STATIONS.m_stations.get("Fields Corner_Shawmut").setNextStation(Constants.DATA_STATIONS.m_stations.get("Shawmut_Ashmont"));
		Constants.DATA_STATIONS.m_stations.get("Shawmut_Ashmont").setNextStation(Constants.DATA_STATIONS.m_stations.get("Ashmont_End"));
		Constants.DATA_STATIONS.m_stations.get("Ashmont_End").setNextStation(null);

		//Red Line Braintree
		Constants.DATA_STATIONS.m_stations.get("JFK/UMass_North Quincy").setNextStation(Constants.DATA_STATIONS.m_stations.get("North Quincy_Wollaston"));
		Constants.DATA_STATIONS.m_stations.get("North Quincy_Wollaston").setNextStation(Constants.DATA_STATIONS.m_stations.get("Wollaston_Quincy Center"));
		Constants.DATA_STATIONS.m_stations.get("Wollaston_Quincy Center").setNextStation(Constants.DATA_STATIONS.m_stations.get("Quincy Center_Quincy Adams"));
		Constants.DATA_STATIONS.m_stations.get("Quincy Center_Quincy Adams").setNextStation(Constants.DATA_STATIONS.m_stations.get("Quincy Adams_Braintree"));
		Constants.DATA_STATIONS.m_stations.get("Quincy Adams_Braintree").setNextStation(Constants.DATA_STATIONS.m_stations.get("Braintree_End"));
		Constants.DATA_STATIONS.m_stations.get("Braintree_End").setNextStation(null);

		//Orange Line
		Constants.DATA_STATIONS.m_stations.get("Oak Grove_Malden Center").setNextStation(Constants.DATA_STATIONS.m_stations.get("Malden Center_Wellington"));
		Constants.DATA_STATIONS.m_stations.get("Malden Center_Wellington").setNextStation(Constants.DATA_STATIONS.m_stations.get("Wellington_Sullivan"));
		Constants.DATA_STATIONS.m_stations.get("Wellington_Sullivan").setNextStation(Constants.DATA_STATIONS.m_stations.get("Sullivan_Community College"));
		Constants.DATA_STATIONS.m_stations.get("Sullivan_Community College").setNextStation(Constants.DATA_STATIONS.m_stations.get("Community College_North Station"));
		Constants.DATA_STATIONS.m_stations.get("Community College_North Station").setNextStation(Constants.DATA_STATIONS.m_stations.get("North Station_Haymarket"));
		Constants.DATA_STATIONS.m_stations.get("North Station_Haymarket").setNextStation(Constants.DATA_STATIONS.m_stations.get("Haymarket_State Street"));
		Constants.DATA_STATIONS.m_stations.get("Haymarket_State Street").setNextStation(Constants.DATA_STATIONS.m_stations.get("State Street_Downtown Crossing"));
		Constants.DATA_STATIONS.m_stations.get("State Street_Downtown Crossing").setNextStation(Constants.DATA_STATIONS.m_stations.get("Downtown Crossing_Chinatown"));
		Constants.DATA_STATIONS.m_stations.get("Downtown Crossing_Chinatown").setNextStation(Constants.DATA_STATIONS.m_stations.get("Chinatown_Tufts Medical"));
		Constants.DATA_STATIONS.m_stations.get("Chinatown_Tufts Medical").setNextStation(Constants.DATA_STATIONS.m_stations.get("Tufts Medical_Back Bay"));
		Constants.DATA_STATIONS.m_stations.get("Tufts Medical_Back Bay").setNextStation(Constants.DATA_STATIONS.m_stations.get("Back Bay_Mass Ave"));
		Constants.DATA_STATIONS.m_stations.get("Back Bay_Mass Ave").setNextStation(Constants.DATA_STATIONS.m_stations.get("Mass Ave_Ruggles"));
		Constants.DATA_STATIONS.m_stations.get("Mass Ave_Ruggles").setNextStation(Constants.DATA_STATIONS.m_stations.get("Ruggles_Roxbury Crossing"));
		Constants.DATA_STATIONS.m_stations.get("Ruggles_Roxbury Crossing").setNextStation(Constants.DATA_STATIONS.m_stations.get("Roxbury Crossing_Jackson Square"));
		Constants.DATA_STATIONS.m_stations.get("Roxbury Crossing_Jackson Square").setNextStation(Constants.DATA_STATIONS.m_stations.get("Jackson Square_Stony Brook"));
		Constants.DATA_STATIONS.m_stations.get("Jackson Square_Stony Brook").setNextStation(Constants.DATA_STATIONS.m_stations.get("Stony Brook_Green Street"));
		Constants.DATA_STATIONS.m_stations.get("Stony Brook_Green Street").setNextStation(Constants.DATA_STATIONS.m_stations.get("Green Street_Forest Hills"));
		Constants.DATA_STATIONS.m_stations.get("Green Street_Forest Hills").setNextStation(Constants.DATA_STATIONS.m_stations.get("Forest Hills_End"));
		Constants.DATA_STATIONS.m_stations.get("Forest Hills_End").setNextStation(null);

		//Blue Line
		Constants.DATA_STATIONS.m_stations.get("Wonderland_Revere Beach").setNextStation(Constants.DATA_STATIONS.m_stations.get("Revere Beach_Beachmont"));
		Constants.DATA_STATIONS.m_stations.get("Revere Beach_Beachmont").setNextStation(Constants.DATA_STATIONS.m_stations.get("Beachmont_Suffolk Downs"));
		Constants.DATA_STATIONS.m_stations.get("Beachmont_Suffolk Downs").setNextStation(Constants.DATA_STATIONS.m_stations.get("Suffolk Downs_Orient Heights"));
		Constants.DATA_STATIONS.m_stations.get("Suffolk Downs_Orient Heights").setNextStation(Constants.DATA_STATIONS.m_stations.get("Orient Heights_Wood Island"));
		Constants.DATA_STATIONS.m_stations.get("Orient Heights_Wood Island").setNextStation(Constants.DATA_STATIONS.m_stations.get("Wood Island_Airport"));
		Constants.DATA_STATIONS.m_stations.get("Wood Island_Airport").setNextStation(Constants.DATA_STATIONS.m_stations.get("Airport_Maverick"));
		Constants.DATA_STATIONS.m_stations.get("Airport_Maverick").setNextStation(Constants.DATA_STATIONS.m_stations.get("Maverick_Aquarium"));
		Constants.DATA_STATIONS.m_stations.get("Maverick_Aquarium").setNextStation(Constants.DATA_STATIONS.m_stations.get("Aquarium_State Street"));
		Constants.DATA_STATIONS.m_stations.get("Aquarium_State Street").setNextStation(Constants.DATA_STATIONS.m_stations.get("State Street_Government Center"));
		Constants.DATA_STATIONS.m_stations.get("State Street_Government Center").setNextStation(Constants.DATA_STATIONS.m_stations.get("Government Center_Bowdoin"));
		Constants.DATA_STATIONS.m_stations.get("Government Center_Bowdoin").setNextStation(Constants.DATA_STATIONS.m_stations.get("Bowdoin_End"));
		Constants.DATA_STATIONS.m_stations.get("Bowdoin_End").setNextStation(null);
	}

	// Sets up TransferStation field for transfer stations
	private static void setTransfers() {
		// Red Line
		Constants.DATA_STATIONS.m_stations.get("Downtown Crossing_South Station").setTransferStation(Constants.DATA_STATIONS.m_stations.get("Downtown Crossing_Chinatown"));
		Constants.DATA_STATIONS.m_stations.get("JFK/UMass_Savin Hill").setTransferStation(Constants.DATA_STATIONS.m_stations.get("JFK/UMass_North Quincy"));
		Constants.DATA_STATIONS.m_stations.get("JFK/UMass_North Quincy").setTransferStation(Constants.DATA_STATIONS.m_stations.get("JFK/UMass_Savin Hill"));

		// Orange Line
		Constants.DATA_STATIONS.m_stations.get("Downtown Crossing_Chinatown").setTransferStation(Constants.DATA_STATIONS.m_stations.get("Downtown Crossing_South Station"));
		Constants.DATA_STATIONS.m_stations.get("State Street_Downtown Crossing").setTransferStation(Constants.DATA_STATIONS.m_stations.get("State Street_Government Center"));

		// Blue Line
		Constants.DATA_STATIONS.m_stations.get("State Street_Government Center").setTransferStation(Constants.DATA_STATIONS.m_stations.get("State Street_Downtown Crossing"));

	}

	private static void setStops() {
		try {
			setupRedStops(Constants.DATA_RED);
			setupStops(Constants.DATA_BLUE, "Wonderland", "Bowdoin", Constants.LINE_COLOR_SELECTOR.BLUE);
			setupStops(Constants.DATA_ORANGE, "Oak Grove", "Forest Hills", Constants.LINE_COLOR_SELECTOR.ORANGE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void setupStops(TripList tl, String in, String out, Constants.LINE_COLOR_SELECTOR col) throws Exception {
		Vector<Vector<Trip>> trips = parseTrip(tl.gettrips(), in, out);
		setStopDirection(trips.get(0), in, col);
		setStopDirection(trips.get(1), out, col);
	}

	private static void setupRedStops(TripList tl) throws Exception {
		Vector<Trip> inTrips = new Vector<Trip>();
		Vector<Trip> outTrips = new Vector<Trip>();
		for(Trip t : tl.gettrips()) {
			if(t.destination.equals("Alewife")) {
				inTrips.add(t);
			}
			if(t.destination.equals("Ashmont") || t.destination.equals("Braintree")) {
				outTrips.add(t);
			}
		}

		setStopDirection(inTrips, "Alewife", Constants.LINE_COLOR_SELECTOR.RED);
		setStopDirection(outTrips, "out", Constants.LINE_COLOR_SELECTOR.RED);
	}

	// takes the list of trips and returns a Vector<Vector<Trip>> of sorted trips based on the given two destination
	private static Vector<Vector<Trip>> parseTrip(ArrayList<Trip> trips, String in, String out) {
		Vector<Vector<Trip>> vecTrips = new Vector<Vector<Trip>>();
		Vector<Trip> inTrips = new Vector<Trip>();
		Vector<Trip> outTrips = new Vector<Trip>();
		for(Trip t : trips) {
			if(t.destination.equals(in)) {
				inTrips.add(t);
			}
			if(t.destination.equals(out)) {
				outTrips.add(t);
			}
		}
		vecTrips.add(inTrips);
		vecTrips.add(outTrips);

		return vecTrips;
	}

	// given a list of trips and a direction, add each prediction to the actual Station
	private static void setStopDirection(Vector<Trip> trips, String direction, Constants.LINE_COLOR_SELECTOR col) throws Exception {
		for(Trip t : trips) {
			String trainId = null;
			if(t.getTrainPos() != null) {
				trainId = t.getTrainPos().getTrain();
			}
			ArrayList<Prediction> predictions = t.getPredictions();
			for(Prediction p : predictions) {
				if(col.equals(Constants.LINE_COLOR_SELECTOR.RED)) {
					setPredictionRed(trainId, p, direction, col);
				}
				else {
				setPrediction(trainId, p, direction, col);
				}
			}
		}
	}

	private static void setPrediction(String trainId, Prediction p, String direction, Constants.LINE_COLOR_SELECTOR col) throws Exception {
		Station station = null;
		station = getStation(p.getStop(), col);

		if(station != null) {
			Vector<String> firstLastStations = findFirstLastStation(col);

			String stationKey = makeStationKey(station);

			// orange / blue line
			if(direction.equals(firstLastStations.get(0))) {
				Constants.DATA_STATIONS.m_stations.get(stationKey).insertStopBackward((int)p.getSeconds(), trainId);
			}
			else if(direction.equals(firstLastStations.get(1))) {
				Constants.DATA_STATIONS.m_stations.get(stationKey).insertStopForward((int)p.getSeconds(), trainId);
			}
			else throw new Exception("Invalid Direction" 
					+ direction + ", " +  firstLastStations.get(0) + ", " + firstLastStations.get(1));
		}
		else throw new Exception("Invalid Station Name: " + p.getStop());
	}
	
	private static void setPredictionRed(String trainId, Prediction p, String direction, Constants.LINE_COLOR_SELECTOR col) throws Exception {
		Station station = null;
		try {
			station = getStation(p.getStop(), Constants.LINE_COLOR_SELECTOR.RED);
		}
		catch (Exception e) {
		}
		try {
			station = getStation(p.getStop(), Constants.LINE_COLOR_SELECTOR.REDASH);
		}
		catch(Exception e1) {
		}
		try {
			station = getStation(p.getStop(), Constants.LINE_COLOR_SELECTOR.REDBRAIN);
		}
		catch(Exception e2) {
		}
		
		if(station != null) {
			String stationKey = makeStationKey(station);
			
			if(direction.equals("Alewife")) {
				Constants.DATA_STATIONS.m_stations.get(stationKey).insertStopBackward((int)p.getSeconds(), trainId);
			}
			else if(direction.equals("out")) {
				Constants.DATA_STATIONS.m_stations.get(stationKey).insertStopForward((int)p.getSeconds(), trainId);
			}
			else throw new Exception("Invalid Direction Redline: " + direction);
		}
	}
	
	private static String makeStationKey(Station station) {
		String stationKey = "";
		if(station.getStationId().equals("Andrew")) {
			stationKey = station.getStationId() + "_JFK/UMass";
		}
		else if(station.getNextStation() == null) {
			stationKey = station.getStationId() + "_End";
		}
		else {
			stationKey = station.getStationId() + "_" + station.getNextStation().getStationId();
		}
		return stationKey;
	}

	// finds the name of the first or last station in a line
	private static Vector<String> findFirstLastStation(Constants.LINE_COLOR_SELECTOR col) {
		Vector<String> names = new Vector<String>();
		Vector<String> stations = getStationNames(col);
		names.add(stations.get(0));
		names.add(stations.get(stations.size() -1));

		return names;
	}

	/**Gets a Vector of Stations given a Vector of station names
	 * @param stationNames the Vector of station names in Strings 
	 * @return Vector of Stations that match stationNames
	 */
	public Vector<Station> getStationsByName(Vector<String> stationNames) {
		Vector<Station> result = new Vector<Station>();
		for (Entry<String, Station> entry : Constants.DATA_STATIONS.m_stations.entrySet()) {
			Station value = entry.getValue();
			for (String str : stationNames) {
				if(value.getStationId().contains(str)) {
					result.add(value);
				}
			}
		}

		return result;
	}

	/**Gets a Vector of Stations given a station name
	 * @param s the station name in a String
	 * @return Vector of Stations that match s
	 */
	public Vector<Station> getStationsByNameSingle(String s) {
		Vector<Station> result = new Vector<Station>();
		for (Entry<String, Station> entry : Constants.DATA_STATIONS.m_stations.entrySet()) {
			Station value = entry.getValue();
			if(value.getStationId().equals(s)) {
				result.add(value);
			}
		}
		return result;
	} 
}
