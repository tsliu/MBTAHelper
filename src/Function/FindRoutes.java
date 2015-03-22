package Function;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Vector;

import Data.Station;
import Main.Constants;

/**Class used to provide routes
 * 
 */
public class FindRoutes {

	private static long RedTime;
	private static long BlueTime;
	private static long OrangeTime;
	private static long earliestDepartureTime;
	private static long lastestArrivalTime;
	private static long travelTime;
	private static long fileTime;
	private static long previousFileTime;
	private static long currentTime;
	private static String error;
	private static boolean errorFlag;
	private static boolean missingTrainFlag;
	private static long firstDeparture;
	private static boolean firstDepartureFlag;
	private static long lastArrival;
	private static boolean lastArrivalFlag;

	/**Get the final route result based on the input trip attributes
	 * @param attr the given TripAttributes (preferences for route)
	 * @return a String describing the route
	 */
	public static String getResult(TripAttributes attr){
		if(attr.isUnordered()){
			attr = UnorderedHelper.getOptimumRoute(attr);
			System.out.println("Optimized TripAttribute: " + attr.toString());
		}
		
		return getResultOrdered(attr);
	}
	
	
	// prints the route result
	/**Gets the final route for an ordered list of stations based on the input trip attributes
	 * @param attr the given TripAttributes (preferences for route)
	 * @return a String describing the route
	 */
	public static String getResultOrdered(TripAttributes attr){

		RedTime = Constants.DATA_RED.getCurrentTime();
		BlueTime = Constants.DATA_BLUE.getCurrentTime();
		OrangeTime = Constants.DATA_ORANGE.getCurrentTime();
		Vector<String> stationList = new Vector<String>(); 

		if (attr.isUseDepartureTime()){
			earliestDepartureTime = attr.getDepartureTime();
		} else {
			earliestDepartureTime = 1555555555;
		}

		if (attr.isUseArrivalTime()){
			lastestArrivalTime = attr.getArrivalTime();
		} else {
			lastestArrivalTime = 0;
		}

		travelTime = 0;
		fileTime = 0;
		previousFileTime = 0;
		currentTime = 0;
		missingTrainFlag = false;
		error = "";
		errorFlag = false;
		firstDeparture = 0;
		firstDepartureFlag = false;
		lastArrival = 0;
		lastArrivalFlag = false;

		stationList = FindRoutes.getAllStations(attr);

		if (!errorFlag){
			return FindRoutes.findFinalRoute(
					FindRoutes.stationAnalyzer(
							FindRoutes.getAllTransfers(stationList)), attr);
		} else {
			return error;
		}
	}

	// inputs TripAttributes and outputs a string, indicating all the stations user need to get to
	/**Gets all the stations the user needs to get to
	 * @param tr the given TripAttributes (preferences for route)
	 * @return String of all the stations the user selected
	 */
	public static Vector<String> getAllStations(TripAttributes tr){

		Vector<String> v = new Vector<String>();

		// puts all stations that the user wants to go into a vector of strings
		if(tr.hasOriginStation()){
			v.add(tr.getOriginStation());
		}
		if(!tr.getListOfStations().isEmpty()){
			v.addAll(tr.getListOfStations());
		}
		if(tr.hasDestinationStation()){
			v.add(tr.getDestinationStation());
		}

		if (v.size() < 2) {
			error = "Please enter at lease 2 stations for findRoutes\n";
			errorFlag = true;
		}

		return v;

	}

	// inserts transfer stations in between 2 stations if needed
	/**Inserts a transfer station between two given stations on different lines (if needed)
	 * @param start the name of the starting Station
	 * @param end the name of the ending Station
	 * @return the transfer Station of the two given stations
	 */
	public static Vector<Station> getTransfers(String start, String end){

		Vector<Station> v_result = new Vector<Station>();
		Station start_station = null;
		Station end_station = null;
		Station downtown_crossing = null;
		Station state_street = null;
		Station jfk_umass = null;

		// transfer stations
		String downtown = "Downtown Crossing";
		String state = "State Street";
		String jfk = "JFK/UMass";

		downtown_crossing = Constants.DATA_STATIONS.getStationsByNameSingle(downtown).firstElement();
		state_street = Constants.DATA_STATIONS.getStationsByNameSingle(state).firstElement();
		jfk_umass = Constants.DATA_STATIONS.getStationsByNameSingle(jfk).firstElement();
		start_station = Constants.DATA_STATIONS.getStationsByNameSingle(start).firstElement();
		end_station = Constants.DATA_STATIONS.getStationsByNameSingle(end).firstElement();

		// orange line - blue line transfer
		if ((start_station.getLineColor() == Constants.LINE_COLOR_SELECTOR.ORANGE) && (end_station.getLineColor() == Constants.LINE_COLOR_SELECTOR.BLUE)
				|| (start_station.getLineColor() == Constants.LINE_COLOR_SELECTOR.BLUE) && (end_station.getLineColor() == Constants.LINE_COLOR_SELECTOR.ORANGE)){
			v_result.add(start_station);
			v_result.add(state_street);
			v_result.add(end_station);
		} 
		// orange line - red line transfer
		else if (((start_station.getLineColor() == Constants.LINE_COLOR_SELECTOR.ORANGE) &&
				(end_station.getLineColor() == Constants.LINE_COLOR_SELECTOR.RED ||
				end_station.getLineColor() == Constants.LINE_COLOR_SELECTOR.REDASH ||
				end_station.getLineColor() == Constants.LINE_COLOR_SELECTOR.REDBRAIN))
				|| ((start_station.getLineColor() == Constants.LINE_COLOR_SELECTOR.RED ||
				start_station.getLineColor() == Constants.LINE_COLOR_SELECTOR.REDASH ||
				start_station.getLineColor() == Constants.LINE_COLOR_SELECTOR.REDBRAIN) &&
				(end_station.getLineColor() == Constants.LINE_COLOR_SELECTOR.ORANGE))){
			v_result.add(start_station);
			v_result.add(downtown_crossing);
			v_result.add(end_station);
		} 
		// blue line - red line transfer
		else if ((start_station.getLineColor() == Constants.LINE_COLOR_SELECTOR.BLUE) &&
				(end_station.getLineColor() == Constants.LINE_COLOR_SELECTOR.RED ||
				end_station.getLineColor() == Constants.LINE_COLOR_SELECTOR.REDASH ||
				end_station.getLineColor() == Constants.LINE_COLOR_SELECTOR.REDBRAIN)){
			v_result.add(start_station);
			v_result.add(state_street);
			v_result.add(downtown_crossing);
			v_result.add(end_station);
		} 
		// red line - blue line transfers
		else if ((start_station.getLineColor() == Constants.LINE_COLOR_SELECTOR.RED ||
				start_station.getLineColor() == Constants.LINE_COLOR_SELECTOR.REDASH ||
				start_station.getLineColor() == Constants.LINE_COLOR_SELECTOR.REDBRAIN) &&
				(end_station.getLineColor() == Constants.LINE_COLOR_SELECTOR.BLUE)) {
			v_result.add(start_station);
			v_result.add(downtown_crossing);
			v_result.add(state_street);
			v_result.add(end_station);
		}
		// red line - transfers
		else if ((start_station.getLineColor() == Constants.LINE_COLOR_SELECTOR.REDASH) && (end_station.getLineColor() == Constants.LINE_COLOR_SELECTOR.REDBRAIN)
				|| (start_station.getLineColor() == Constants.LINE_COLOR_SELECTOR.REDBRAIN) && (end_station.getLineColor() == Constants.LINE_COLOR_SELECTOR.REDASH)){
			v_result.add(start_station);
			v_result.add(jfk_umass);
			v_result.add(end_station);
		}
		// same line, no transfers
		else {
			v_result.add(start_station);
			v_result.add(end_station);
		}

		return v_result;
	}

	/**Inserts all transfer stations required into the input Vector<String>
	 * @param all_strings the names of all the stations 
	 * @return a Vector<Station> with all the required transfer stations inserted into the input Vector<String>
	 */
	public static Vector<Station> getAllTransfers(Vector<String> all_strings){

		Vector<Station> v_result = new Vector<Station>();
		Vector<Station> v_sub = new Vector<Station>();

		for (int i = 0; i < (all_strings.size() - 1); i++){

			v_sub.removeAllElements();

			if (i == 0){
				v_sub = getTransfers(all_strings.get(i), all_strings.get(i+1));
				v_result.addAll(v_sub);
			} else {
				v_sub = getTransfers(all_strings.get(i), all_strings.get(i+1));
				v_sub.remove(0);
				v_result.addAll(v_sub);
			}
		}
		/*
		System.out.println("--------all Stations----------");
		for (int i = 0; i < v_result.size(); i++){
			System.out.println(v_result.elementAt(i).getStationId() + " #########");
		}
		System.out.println("------end of all Stations----------");
		 */
		return v_result;
	}

	/**Gets the text result based on the given start station, end station, and trip attributes
	 * @param start the given start station
	 * @param end the given end station
	 * @param ta the given TripAttributes (preferences for route)
	 * @return the resulting route information
	 */
	public static String findSingleRoute(Station start, Station end, TripAttributes ta){

		String result1 = "";
		String result2 = "";
		String result3 = "";
		String result4 = "----------------------\n";
		String trainID = "";
		long start_time = 0;
		long end_time = 0;
		long departure_time = 0;
		long arrival_time = 0;
		HashMap<Integer, String> trainList_start = new HashMap<Integer, String>();
		HashMap<String, Integer> trainList_end = new HashMap<String, Integer>();
		ArrayList<Integer> al_start = new ArrayList<Integer>();
		Collection<String> al_end = new ArrayList<String>();

		if(start.getPositionId() > end.getPositionId()){
			trainList_start = start.getStopForward();
			trainList_end = end.getStopForwardReversed();
		} else {
			trainList_start = start.getStopBackward();
			trainList_end = end.getStopBackwardReversed();
		}

		al_start = Utilities.sortIntegerSet(trainList_start.keySet()); // list of sorted seconds, array<int>
		al_end = trainList_end.keySet(); // list of trainIDs, array<string>
		// System.out.println(Utilities.reverseHashMap(trainList_start).toString());
		// System.out.println(trainList_end.toString());

		if (!missingTrainFlag){

			if (start.getLineColor().equals(Constants.LINE_COLOR_SELECTOR.ORANGE)){
				fileTime = OrangeTime;
			} else if (start.getLineColor().equals(Constants.LINE_COLOR_SELECTOR.BLUE)){
				fileTime = BlueTime;
			} else {
				fileTime = RedTime;
			}

			if (previousFileTime == 0){
				previousFileTime = fileTime;
			}

			currentTime = previousFileTime + travelTime;

			for (int i = 0; i < al_start.size(); i++){

				String earliestTrain = trainList_start.get(al_start.get(i));

				if (al_end.contains(earliestTrain)
						&& currentTime <= fileTime + al_start.get(i)
						&& al_start.get(i) >= 0){

					trainID = earliestTrain;
					start_time = al_start.get(i);
					end_time = trainList_end.get(earliestTrain);
					departure_time = fileTime + start_time;
					arrival_time = fileTime + end_time;

					if (earliestDepartureTime >= departure_time && lastestArrivalTime <= arrival_time) {
						result1 = "Take Train (ID: " + trainID + ")\n";
						result2 += "From " + start.getStationId()  + " [" + start.getLineColor() 
								+ "] (at: " + Utilities.timeStampToString(departure_time) + ")\n";
						result3 += "To " + end.getStationId() + " [" + end.getLineColor()
								+ "] (at: " + Utilities.timeStampToString(arrival_time) + ")\n"
								;
						travelTime = end_time;
						previousFileTime = fileTime;

						if(firstDepartureFlag){
							firstDeparture = fileTime + start_time;
						}
						if(lastArrivalFlag){
							lastArrival = fileTime + end_time;
						}

						break;
					}
				}
			}			
		}
		// if it couldn't find a service train, print out the basic route info to the user
		if (result1.isEmpty()){
			result1 = "Take a Train \n";
			result2 = "From " + start.getStationId() + " [" + start.getLineColor() + "]\n";
			result3 = "To " + end.getStationId() + " [" + end.getLineColor() + "]\n";
			missingTrainFlag = true;
		}

		return result1 + result2 + result3 + result4;
	}

	/**Get the final text result based on the input vector of Station pairs
	 * @param vv a vector of pairs of stations
	 * @param ta the given TripAttributes (preferences for route)
	 * @return the route information
	 */
	public static String findFinalRoute(Vector<Vector<Station>> vv, TripAttributes ta){

		String result = "";

		for (int i = 0; i < vv.size(); i++){
			if (i == 0){
				firstDepartureFlag = true;
			} else {
				firstDepartureFlag = false;
			}
			if (i == vv.size() - 1){
				lastArrivalFlag = true;
			} else {
				lastArrivalFlag = false;
			}
			result += findSingleRoute(vv.get(i).get(0), vv.get(i).get(1), ta);
		}

		//System.out.println(result);
		return "----------------------\n" + result;
	}

	/**Pairs a vector of stations into a new vector
	 * @param v Vector of stations
	 * @return a Vector<Vector<Station>> (used to find route information)
	 */
	public static Vector<Vector<Station>> stationAnalyzer(Vector<Station> v){

		Vector<Vector<Station>> result = new Vector<Vector<Station>>();
		Vector<Station> trans_station = new Vector<Station>();
		Vector<Station> trans_station2 = new Vector<Station>();
		Main.Constants.LINE_COLOR_SELECTOR color = null;

		// iterates through the input stations, and pairs the inputs
		for (int i = 0; i < v.size() - 1; i++){

			Vector<Station> pair = new Vector<Station>();
			Station first = v.get(i);
			Station second = v.get(i+1);

			// skips the stop if the first stop is the same as the second stop
			if (v.get(i).getStationId().equals(v.get(i+1).getStationId())){
				continue;
			}

			// no need to make changes if both first and second stations are not transfer
			if ((!first.isIsTransfer()) && (!second.isIsTransfer())){
				pair.add(first);
				pair.add(second);
				result.add(pair);
			} 
			// make the first station the same color as the second station
			// if the first station is a transfer, but the second is not
			else if ((first.isIsTransfer()) && (!second.isIsTransfer())){
				trans_station = Constants.DATA_STATIONS.getStationsByNameSingle(first.getStationId());
				color = second.getLineColor();

				for (int n = 0; n < trans_station.size(); n++){
					if (trans_station.get(n).getLineColor() == color){
						first = trans_station.get(n);
					}
				}
				pair.add(first);
				pair.add(second);
				result.add(pair);
			} 
			// make the second station the same color as the first station
			// if the second station is a transfer, but the first station is not
			else if ((second.isIsTransfer()) && (!first.isIsTransfer())){
				trans_station = Constants.DATA_STATIONS.getStationsByNameSingle(second.getStationId());
				color = first.getLineColor();

				for (int x = 0; x < trans_station.size(); x++){
					if (trans_station.get(x).getLineColor() == color){
						second = trans_station.get(x);
					}
				}
				pair.add(first);
				pair.add(second);
				result.add(pair);
			}
			// make the first and second station have to the same color
			// if they are both transfers
			else {
				trans_station = Constants.DATA_STATIONS.getStationsByNameSingle(first.getStationId());
				trans_station2 = Constants.DATA_STATIONS.getStationsByNameSingle(second.getStationId());

				for (int y = 0; y < trans_station.size(); y++){
					for (int z = 0; z < trans_station2.size(); z++){
						if (trans_station.get(y).getLineColor() == trans_station2.get(z).getLineColor()){
							color = trans_station2.get(z).getLineColor();
						}
					}
				}

				for (int a = 0; a < trans_station.size(); a++){
					if (trans_station.get(a).getLineColor() == color){
						first = trans_station.get(a);
					}
				}

				for (int b = 0; b < trans_station2.size(); b++){
					if (trans_station2.get(b).getLineColor() == color){
						second = trans_station2.get(b);
					}
				}
				pair.add(first);
				pair.add(second);
				result.add(pair);
			}
		}
		/*
		System.out.println("-------------------------");
		for(int a = 0; a < result.size(); a++){

			System.out.println(a+1 + ": " + result.get(a).get(0).getStationId() + 
					" (" + result.get(a).get(0).getLineColor().toString() + ")" + " --- " +
					result.get(a).get(1).getStationId() +
					" (" + result.get(a).get(1).getLineColor().toString() + ")");

		}
		System.out.println("-------------------------");
		 */
		return result;
	}

	/**Gets the trip properties from the given trip attribute
	 * @param attr the given TripAttributes (preferences for route)
	 * @return the OrderProperties retrieved from the TripAttributes
	 */
	public static OrderProperties getOrderProps(TripAttributes attr){


		OrderProperties op = new OrderProperties();

		RedTime = Constants.DATA_RED.getCurrentTime();
		BlueTime = Constants.DATA_BLUE.getCurrentTime();
		OrangeTime = Constants.DATA_ORANGE.getCurrentTime();
		Vector<String> stationList = new Vector<String>();
		Vector<Vector<Station>> stationListPair = new Vector<Vector<Station>>();


		if (attr.isUseDepartureTime()){
			earliestDepartureTime = attr.getDepartureTime();
		} else {
			earliestDepartureTime = 1555555555;
		}

		if (attr.isUseArrivalTime()){
			lastestArrivalTime = attr.getArrivalTime();
		} else {
			lastestArrivalTime = 0;
		}

		travelTime = 0;
		fileTime = 0;
		previousFileTime = 0;
		currentTime = 0;
		missingTrainFlag = false;
		error = "";
		errorFlag = false;
		firstDeparture = 0;
		firstDepartureFlag = false;
		lastArrival = 0;
		lastArrivalFlag = false;

		stationList = FindRoutes.getAllStations(attr);
		int transfersNum = 0;

		if (!errorFlag){
			stationListPair = FindRoutes.stationAnalyzer(FindRoutes.getAllTransfers(stationList));
			FindRoutes.findFinalRoute(stationListPair, attr);
			for(int i = 0; i < stationListPair.size() - 1; i++){
				int dir1 = stationListPair.get(i).get(0).getPositionId() - stationListPair.get(i).get(1).getPositionId();
				int dir2 = stationListPair.get(i+1).get(0).getPositionId() - stationListPair.get(i+1).get(1).getPositionId();
				if ((stationListPair.get(i).get(0).getLineColor() != stationListPair.get(i+1).get(0).getLineColor()) ||
						(dir1 * dir2 < 0)){
					transfersNum += 1;
				}
			}
		}

		op.setDepartureTime(firstDeparture);
		op.setArrivalTime(lastArrival);
		if (lastArrival != 0 && firstDeparture != 0){
			op.setTime((int) (lastArrival - firstDeparture));
		}
		op.setTransfers(transfersNum);
		op.setHasTrains(!missingTrainFlag);

		System.out.println("Departure :" + op.getDepartureTime());
		System.out.println("Arrival: " + op.getArrivalTime());
		System.out.println("Travel Time: " + op.getTime());
		System.out.println("Transfer: " + op.getTransfers());
		System.out.println("Are there service trains? " + op.isHasTrains());
		System.out.println("----------------------------------");

		return op;

	}
}







