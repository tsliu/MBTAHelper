package Function;

/**Class contains all the properties of the different routes
 *
 */
public class OrderProperties {
	
	private long departureTime;
	private long arrivalTime;
	private int time;
	private int transfers;
	private boolean hasTrains;
	
	/**Gets the hasTrains field; does the route have trains?
	 * @return the hasTrains field
	 */
	public boolean isHasTrains() {
		return hasTrains;
	}
	
	/**Sets the hasTrain field to the given Boolean
	 * @param hasTrains the given Boolean
	 */
	public void setHasTrains(boolean hasTrains) {
		this.hasTrains = hasTrains;
	}
	
	/**Gets the departureTime field; the departure time of the route
	 * @return the departureTime field
	 */
	public long getDepartureTime() {
		return departureTime;
	}
	
	/**Sets the departureTime field to the given long
	 * @param departureTime the given long
	 */
	public void setDepartureTime(long departureTime) {
		this.departureTime = departureTime;
	}
	
	/**Gets the arrivalTime field; the arrivalTime of the route
	 * @return the arrivalTime field
	 */
	public long getArrivalTime() {
		return arrivalTime;
	}
	
	/**Sets the arrivalTime field to the given long
	 * @param arrivalTime the given long
	 */
	public void setArrivalTime(long arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	
	/** Gets the time field; the amount of time the route would take
	 * @return the time field
	 */
	public int getTime() {
		return time;
	}
	
	/**Sets the time field to the given int
	 * @param time the given int
	 */
	public void setTime(int time) {
		this.time = time;
	}
	
	/**Gets the transfers field; the number of transfers in this route
	 * @return the transfers field
	 */
	public int getTransfers() {
		return transfers;
	}
	
	/**Sets the transfers field to the given int
	 * @param transfers the given int
	 */
	public void setTransfers(int transfers) {
		this.transfers = transfers;
	}
	
	

}
