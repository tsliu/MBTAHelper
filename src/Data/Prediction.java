package Data;

import com.fasterxml.jackson.annotation.*;

/**The Prediction data structure given by the MBTA.
 * 
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Prediction {

	/**The StopID of the station.
	 * 
	 */
	public String StopID;
	/**The name of the station.
	 * 
	 */
	public String stop;
	/**The amount of time left until the train arrives at the station.
	 * 
	 */
	public long seconds;
	

	/**Prediction constructor; data structure from MBTA.
	 * @param id The StopID of the station.
	 * @param st The name of the station,
	 * @param sec The amount of time left until the train arrives at the station.
	 */
	public Prediction(String id, String st, long sec) {
		StopID = id;
		stop = st;
		seconds = sec;
	}
	
	public Prediction() {
		
	}
	
	// SETTERS
	/**Sets the StopID field to the given id.
	 * @param id Given id.
	 */
	@JsonProperty("StopID")
	public void setStopID(String id) { StopID = id; }
	
	/**Sets the Stop field to the given id.
	 * @param st
	 */
	@JsonProperty("Stop")
	public void setStop(String st) { stop = st; }
	
	/**Sets the Seconds field to the given id.
	 * @param sec
	 */
	@JsonProperty("Seconds")
	public void setSeconds(String sec) { seconds = Long.parseLong(sec); }
	
	// GETTERS
	/**Gets the StopID field
	 * @return the StopID field
	 */
	public String getStopID() { return StopID; }
	/**Gets the Stop field
	 * @return the Stop field
	 */
	public String getStop() { return stop; }
	/**Gets the Seconds field
	 * @return the Seconds field
	 */
	public long getSeconds() { return seconds; }
	
	
	/**
	 * @return the Prediction and its fields in the form of a string.
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "Prediction: " + "StopId: " + StopID + ", Stop: " + stop + ", Seconds: " + seconds;
	}
}
