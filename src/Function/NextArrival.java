package Function;

import java.util.Vector;

import Data.Station;
import Main.Constants;

/**Class that handles the NextArrival functionality
 *
 */
public class NextArrival {

	/**Gets the approximate arrival times of the next trains for the given stations
	 * @param stationNames the station names of the stations the user wishes to know the next arrival times for
	 * @return a String stating the arrival times of all trains for the given Stations in both directions
	 */
	public static String getResult(Vector<String> stationNames){
		StringBuilder sb = new StringBuilder();
		
		sb.append("Next Arrival Information:\n");
		
		for(Station station : Constants.DATA_STATIONS.getStationsByName(stationNames)){
			sb.append("\n### " + station.getStationId() + " (" + station.getLineColor().toString() + ") ###\n");
			
			if(station.getStopForward() != null && !station.getStopForward().isEmpty()){
				sb.append( (station.getPreviousStation() == null ? "Lauching From Here" : "Arriving From " + station.getPreviousStation().getStationId()) + ":\n" );
				for(Integer i : Utilities.sortIntegerSet(station.getStopForward().keySet())){
					sb.append(station.getStopForward().get(i) == null ? "    A train" : "    Train " + station.getStopForward().get(i));
					sb.append(": " + i.toString() + " seconds.\n");
				}
			}
			
			if(station.getStopBackward() != null && !station.getStopBackward().isEmpty()){
				sb.append( (station.getNextStation() == null ? "Lauching From Here" : "Arriving From " + station.getNextStation().getStationId()) + ":\n" );
				for(Integer i : Utilities.sortIntegerSet(station.getStopBackward().keySet())){
					sb.append(station.getStopBackward().get(i) == null ? "    A train" : "    Train " + station.getStopBackward().get(i));
					sb.append(": " + i.toString() + " seconds.\n");
				}
			}

		}
		return sb.toString();
	}
}
