package Data;

import com.fasterxml.jackson.annotation.*;

/**Used to parse out the Trips from TripList
 * 
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TripCompiler {

	private TripList tl;
	
	/**Constructor for TripCompiler; does not take any arguments
	 * 
	 */
	public TripCompiler() {
		
	}
	
	/**Sets the tl field to the given TripList
	 * @param t the given TripList
	 */
	@JsonProperty("TripList")
	public void setTripList(TripList t) { tl = t; }
	
	/**Gets the tl field
	 * @return the TripList
	 */
	public TripList getTripList() { return tl; }
	
	/**
	 * @return the TripCompiler and its fields in the form of a String.
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return tl.toString();
	}
}
