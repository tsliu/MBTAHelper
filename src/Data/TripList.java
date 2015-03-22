package Data;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.*;


/**The TripList data structure given by the MBTA
 * 
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TripList {
	/**The current time
	 * 
	 */
	public long currenttime;
	/**The color of the line
	 * 
	 */
	public String line;
	/**The Trips for this line; Trips of all trains on this line.
	 * 
	 */
	public ArrayList<Trip> trips;
	
	/**Constructor for TripList
	 * @param time the current time
	 * @param l the color of the line
	 * @param t the ArrayList of Trips
	 */
	public TripList(long time, String l, ArrayList<Trip> t) {
		currenttime = time;
		line = l;
		trips = t;
	}

	public TripList() {
		
	}

	// SETTERS
	/**Sets the currenttime field to the given String
	 * @param ct the given time
	 */
	@JsonProperty("CurrentTime")
	public void setCurrentTime(String ct) { currenttime = Long.parseLong(ct); }
	
	/**Sets the line field to the given String
	 * @param l the given line color
	 */
	@JsonProperty("Line")
	public void setLine(String l) { line = l; }
	
	/**Sets the trips to the given ArrayList<Trip>
	 * @param t the given ArrayList of Trips
	 */
	@JsonProperty("Trips")
	public void settrips(ArrayList<Trip> t) { trips = t; }
	
	// GETTERS
	/**Gets the current time
	 * @return the current time
	 */
	public long getCurrentTime() { return currenttime; }
	/**Gets the color of the line
	 * @return the color of the line
	 */
	public String getLine() { return line; }
	/**Gets the ArrayList of Trips
	 * @return the ArrayList of Trips
	 */
	public ArrayList<Trip> gettrips() { return trips; }
	
	
	
	
	
	
	
	/**Constructor for TripList
	 * @param data JSON data in a String
	 */
	public TripList(String data) {
		String[] parsed = parsedData(data);
		currenttime = Long.parseLong(parsed[0]);
		line = parsed[1];
		trips = new ArrayList<Trip>();
	}
	
	private String[] parsedData(String data) {
		String[] split = new String[1];
		String t = data.substring(1).replaceAll("CurrentTime", "").
				replaceAll("Line", " ").replaceAll("Trips", "");
		split = t.split(" ");
		return split;
	}
	
	/**
	 * @return the TripList and its fields in the form of a String
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		String t = "";
		try {
			for(Trip tr : trips){
				t += tr.toString() + "/n";
			}
		} catch(NullPointerException e) {

		}
		return "TripsList: CurrentTime: " + currenttime + ", Line: " + line + ", Trips:\n" + t;
	}
}
