package Data;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.*;



/**The Trip data structure given by the MBTA.
 * 
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Trip {
	/**The TripID
	 * 
	 */
	public String tripid;
	/**The heading on this trip; indicated by last stop on the line
	 * 
	 */
	public String destination;
	/**The notes on the Trip.
	 * 
	 */
	public String note = null;
	/**The Train properties
	 * 
	 */
	public TrainPos position;
	/**The list of Predictions (times) until the arrival for the given stop.
	 * 
	 */
	public ArrayList<Prediction> predictions;
	
	
	/**Constructor for the Trip data structure
	 * @param id the TripID
	 * @param dest the heading for this trip
	 * @param n the notes on for this trip
	 * @param pos the Train properties
	 * @param preds the Predictions for this trip
	 */
	public Trip(String id, String dest, String n, TrainPos pos, ArrayList<Prediction> preds) {
		tripid = id;
		destination = dest;
		note = n;
		position = pos;
		predictions = preds;
	}
	
	public Trip() {
	}

	// SETTERS
	/**Sets the tripid field to the given String
	 * @param trip the given tripid
	 */
	@JsonProperty("TripID")
	public void setTripID(String trip) { tripid = trip; }
	
	/**Sets the destination field to the given String
	 * @param dest the given destination
	 */
	@JsonProperty("Destination")
	public void setDestination(String dest) { destination = dest; }
	
	/**Sets the note field to the given String
	 * @param n the given note
	 */
	@JsonProperty("Note")
	public void setNote(String n) { note = n; }
	
	/**Sets the position field to the given TrainPos
	 * @param tp the given train properties
	 */
	@JsonProperty("Position")
	public void setPosition(TrainPos tp) { position = tp; }
	
	/**Sets the predictions to the given ArrayList<Predictions>
	 * @param p the given ArrayList of Predictions
	 */
	@JsonProperty("Predictions")
	public void setPredictions(ArrayList<Prediction> p) { predictions = p; }
	
	// GETTERS
	/**Gets the tripid field
	 * @return the tripid
	 */
	public String getTripID() { return tripid; }
	/**Gets the destination field
	 * @return the heading of the trip; the name of the terminal stop
	 */
	public String getDestination() { return destination; }
	/**Gets the note field
	 * @return the notes for the trip; e.g. Red train is a "Big Red" train
	 */
	public String getNote() { return note; }
	/**Gets the position field
	 * @return the TrainPos (train properties)
	 */
	public TrainPos getTrainPos() { return position; }
	/**Gets the predictions field
	 * @return the Predictions until arrival for the given stop
	 */
	public ArrayList<Prediction> getPredictions() { return predictions; }
	
	/**
	 * @return the Trip and its field in the form of a string.
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		String preds = "";
		for(Prediction p : predictions){
			preds += p.toString() + "\n";
		}
		
		return "Trip: " + tripid + ", Destination: " + destination + ", Note: " + note + 
				", Position: " + position + ", Predictions:\n" + preds;
	}
}
