package Data;

import java.util.HashMap;
import java.util.Map.Entry;

import Main.Constants;

/**The Station data structure; Our own custom data structure used to hold the Station data.
 * 
 *
 */
public class Station {
	
	private String StationId;
	private int PositionId;
	private Constants.LINE_COLOR_SELECTOR LineColor;

	private boolean IsTransfer;
	private Station PreviousStation;
	private Station NextStation;
	private Station TransferStation;
	private HashMap<Integer, String> StopForward;
	private HashMap<String, Integer> StopForwardReversed;
	private HashMap<Integer, String> StopBackward;
	private HashMap<String, Integer> StopBackwardReversed;
	
	/**Creates Station based on StopID and color of the line.
	 * @param stationId The StopID of the station.
	 * @param line The color of the line.
	 */
	public Station(String stationId, Constants.LINE_COLOR_SELECTOR line){
		this(stationId, line, 0);
	}
	
	/**Creates Station based on StopID, color of the line, and if it is a transfer stop.
	 * @param stationId The StopID of the station.
	 * @param line The color of the line.
	 * @param isTransfer If this station is a transfer stop.
	 */
	public Station(String stationId, Constants.LINE_COLOR_SELECTOR line, boolean isTransfer){
		this(stationId, line, 0, isTransfer);
	}
	
	/**Creates Station based on StopID, color of the line, and the position of the stop relative to the last stop
	 * Position is determined by direction; e.g. On Orange line: heading to Oak Grove or heading to Forest Hills.
	 * @param stationId The StopID of the station.
	 * @param line The color of the line.
	 * @param positionId The number of stops this stop is away from the last stop.
	 */
	public Station(String stationId, Constants.LINE_COLOR_SELECTOR line, int positionId){
		this(stationId, line,  positionId, false);
	}
	
	/**Creates Station based on StopID, color of the line, the position of the stop relative to the last stop
	 * on the line, and if it is a transfer stop.
	 * @param stationId The StopID of the station.
	 * @param line The color of the line.
	 * @param positionId The position of the stop relative to the last stop.
	 * @param isTransfer If this stop is a transfer station
	 */
	public Station(String stationId, Constants.LINE_COLOR_SELECTOR line,  int positionId, boolean isTransfer){
		this.StationId = stationId;
		this.LineColor = line;
		this.PositionId = positionId;
		this.IsTransfer = isTransfer;
		this.PreviousStation = null;
		this.NextStation = null;
		this.TransferStation = null;
		this.StopForward = new HashMap<Integer, String>();
		this.StopBackward = new HashMap<Integer, String>();
		this.StopForwardReversed = new HashMap<String, Integer>();
		this.StopBackwardReversed = new HashMap<String, Integer>();		
		
		Constants.DATA_STATIONS.addName(stationId, line);
	}
	
	/**Inserts a Prediction into the Hashmap of Predictions for stops proceeding this Station.
	 * @param n the position of the Station.
	 * @param id the StopID of the Station.
	 */
	public void insertStopForward(int n, String id){
		this.StopForward.put(new Integer(n), id);
		this.StopForwardReversed.put(id, new Integer(n));
	}
	
	/**Inserts a Prediction into the Hashmap of Predictions for stops preceding this Station.
	 * @param n the position of the Station.
	 * @param id the StopID of the Station.
	 */
	public void insertStopBackward(int n, String id){
		this.StopBackward.put(new Integer(n), id);
		this.StopBackwardReversed.put(id, new Integer(n));
	}
	
	
	//Setters and Getters
	/**Gets the PositionID.
	 * @return the PositionID.
	 */
	public int getPositionId(){
		return PositionId;
	}
	
	/**Sets the PositionID field to the given PositionID.
	 * @param positionId the given PositionID.
	 */
	public void setPositionId(int positionId){
		PositionId = positionId;
	}
	
	/**Gets the StationID.
	 * @return the StationID.
	 */
	public String getStationId() {
		return StationId;
	}

	/**Sets the StationID field to the given StationID.
	 * @param stationId the given StationID.
	 */
	public void setStationId(String stationId) {
		StationId = stationId;
	}

	/**Gets the line color.
	 * @return the line color.
	 */
	public Constants.LINE_COLOR_SELECTOR getLineColor() {
		return LineColor;
	}

	/**Sets the LineColor to the given LineColor.
	 * @param lineColor the given LineColor.
	 */
	public void setLineColor(Constants.LINE_COLOR_SELECTOR lineColor) {
		LineColor = lineColor;
	}

	/**Gets the flag on whether the station is a transfer station.
	 * @return True of False on whether the station is a transfer station.
	 */
	public boolean isIsTransfer() {
		return IsTransfer;
	}

	/**Sets the flag on whether the station is a transfer station to the given boolean.
	 * @param isTransfer the given boolean.
	 */
	public void setIsTransfer(boolean isTransfer) {
		IsTransfer = isTransfer;
	}

	/**Gets the previousStation.
	 * @return the PreviousStation; Station that precedes this Station. 
	 */
	public Station getPreviousStation() {
		return PreviousStation;
	}

	/**Sets the PreviousStation to the given Station.
	 * @param previousStation the given Station
	 */
	public void setPreviousStation(Station previousStation) {
		PreviousStation = previousStation;
	}

	/**Gets the NextStation.
	 * @return the NextStation; Station that proceeds this Station.
	 */
	public Station getNextStation() {
		return NextStation;
	}

	/**Sets the NextStation to the given Station.
	 * @param nextStation the given Station.
	 */
	public void setNextStation(Station nextStation) {
		NextStation = nextStation;
	}

	
	/**Gets the TransferStation.
	 * @return the TransferStation; Same station on different line.
	 */
	public Station getTransferStation() {
		return TransferStation;
	}

	/**Sets the TransferStation to the given Station.
	 * @param transferStation the given Station.
	 */
	public void setTransferStation(Station transferStation) {
		TransferStation = transferStation;
	}

	/**Gets the Hashmap of Predictions for stops proceeding the Station.
	 * @return the Hashmap of all the Predictions for stops proceeding the Station.
	 */
	public HashMap<Integer, String> getStopForward() {
		return StopForward;
	}

	/**Sets the Hashmap of Predictions for stops proceeding the Station to the given Hashmap
	 * @param stopForward the given Hashmap
	 */
	public void setStopForward(HashMap<Integer, String> stopForward) {
		StopForward = stopForward;
	}

	/**Gets the Hashmap of Predictions for stops preceding the Station.
	 * @return the Hashmap of all the Predictions for stops preceding the Station.
	 */
	public HashMap<Integer, String> getStopBackward() {
		return StopBackward;
	}

	/**Sets the Hashmap of Predictions for stops preceding the Station to the given Hashmap
	 * @param stopBackward the given Hashmap
	 */
	public void setStopBackward(HashMap<Integer, String> stopBackward) {
		StopBackward = stopBackward;
	}
	
	/**Gets the reversed Hashmap of Predictions for stops proceeding this Station.
	 * @return the reversed Hashmap of all the Predictions for stops proceeding the Station.
	 */
	public HashMap<String, Integer> getStopForwardReversed() {
		return StopForwardReversed;
	}

	/**Sets the StopForwardReversed Hashmap to the given Hashmap
	 * @param stopForwardReversed the given Hashmap
	 */
	public void setStopForwardReversed(HashMap<String, Integer> stopForwardReversed) {
		StopForwardReversed = stopForwardReversed;
	}

	/**Gets the reversed Hashmap of Predictions for stops preceding this Station.
	 * @return the reversed Hashmap of all the Predictions for stops preceding this Station.
	 */
	public HashMap<String, Integer> getStopBackwardReversed() {
		return StopBackwardReversed;
	}

	/**Sets the StopBackwardReversed Hashmap to the given Hashmap
	 * @param stopBackwardReversed the given Hashmap
	 */
	public void setStopBackwardReversed(
			HashMap<String, Integer> stopBackwardReversed) {
		StopBackwardReversed = stopBackwardReversed;
	}

	/**
	 * @return the Station and its fields in the form of a String.
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		String prev = null;
		String next = null;
		if(PreviousStation != null)
			prev = PreviousStation.getStationId();
		if(NextStation != null)
			next = NextStation.getStationId();
		return "Prev: " + prev + ", " + StationId + ", Next: " + next + ",\n" +
				"StopForward: \n" + hashMapToString(StopForward) + "StopBackward: \n" + hashMapToString(StopBackward);
	}
	
	private String hashMapToString(HashMap<Integer, String> hm) {
		String out = "";
		for (Entry<Integer, String> entry : hm.entrySet()) {
			out += "TrainId: " + entry.getValue() + " Seconds: " + entry.getKey() + '\n';
		}
		
		return out;
	}
}
