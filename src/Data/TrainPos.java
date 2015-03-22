package Data;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.*;

/**The properties of Trains;
 * 
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TrainPos {

	private String timestamp;
	private String train;
	private double latitude;
	private double longitude;
	private double heading;

	
	/**Train Constructor; the properties of a Train
	 * @param time the amount of time remaining until the train arrives at the next Station
	 * @param tr the trainID
	 * @param lat the latitude of the train
	 * @param l the longitude of the train
	 * @param head the direction the train is heading in
	 */
	public TrainPos(String time, String tr, double lat, double l, double head) {
		timestamp = time;
		train = tr;
		latitude = lat;
		longitude = l;
		heading = head;
	}
	
	public TrainPos() {
		
	}
	
	// SETTERS
	/**Sets timestamp to the given String
	 * @param time the given time
	 */
	@JsonProperty("Timestamp")
	public void setTimestamp(String time) { timestamp = time; }
	
	/**Sets train to the given String
	 * @param t the given train
	 */
	@JsonProperty("Train")
	public void setTrain(String t) { train = t; }
	
	/**Sets latitude to the given String
	 * @param l the given latitude
	 */
	@JsonProperty("Lat")
	public void setLatitude(String l) { latitude = Double.parseDouble(l); }
	
	/**Sets longitude to the given String
	 * @param l the given longitude
	 */
	@JsonProperty("Long")
	public void setLongitude(String l) { longitude = Double.parseDouble(l); }
	
	/**Sets heading to the given String
	 * @param h the given heading
	 */
	@JsonProperty("Heading")
	public void setHeading(String h) { heading= Double.parseDouble(h); }
	
	// GETTERS
	/**Gets the timestamp field.
	 * @return the time remaining until the train arrives at the next stop.
	 */
	public String getTimestamp() { return timestamp; }
	/**Gets the train field.
	 * @return the TrainID of the train.
	 */
	public String getTrain() { return train; }
	/**Gets the latitude field.
	 * @return the latitude of the train.
	 */
	public Double getLatitude() { return latitude; }
	/**Gets the longitude field.
	 * @return the longitude of the train.
	 */
	public Double getLongitude() { return longitude; }
	/**Gets the heading field
	 * @return the heading of the train.
	 */
	public Double getHeading() { return heading; }
	
	
	
	
	
	
	
	/**Parses out the JSON data into the Train properties.
	 * @param data the JSON data in String form
	 */
	public TrainPos(String data) {
		ArrayList<String> parsed = parseData(data);
		try {
			timestamp = parsed.get(0);
			train = parsed.get(1);
			latitude = Double.parseDouble(parsed.get(2));
			longitude = Double.parseDouble(parsed.get(3));
			heading = Double.parseDouble(parsed.get(4));
		} 
		catch(IndexOutOfBoundsException e) {
			System.out.println("Error with parsed data, could not create TrainPos \n" +
					parsed.size());
		}
	}

	private ArrayList<String> parseData(String data) {
		ArrayList<String> split = parseNumbers(data);
		return split;
	}
	
	private ArrayList<String> parseNumbers(String data) {
		String regex = "[^0-9.-]";
		String t = data.replaceAll(regex, ",");
		ArrayList<String> split = removeCommas(t.split(","));
		
		System.out.println(split);
		return split;
	}

	private ArrayList<String> removeCommas(String[] data) {
		ArrayList<String> t = new ArrayList<String>();
		for(String s : data) {
			if(s.length() > 0)
				t.add(s);
		}
		return t;
	}
	
	/**
	 * @return the Train and its fields in the form of a string.
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "TrainPos: " + timestamp + ", Train: " + train + ", Lat: " + latitude + 
				", Long: " + longitude + ", Heading: " + heading;
	}
}
