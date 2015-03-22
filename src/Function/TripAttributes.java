package Function;

import java.util.Vector;

import Main.Constants;

/**The attributes/options for a trip route
 *
 */
public class TripAttributes {
	
	private Constants.ROUTE_OPTION m_routeOption;
	private boolean m_useDepartureTime;
	private boolean m_useArrivalTime;
	private long m_departureTime;
	private long m_arrivalTime;
	private boolean m_hasOriginStation;
	private boolean m_hasDestinationStation;
	private String m_originStation;
	private String m_destinationStation;
	private boolean m_unordered;
	private Vector<String> m_listOfStations;
	
	/**Sets the initial attributes of a trip route
	 * 
	 */
	public TripAttributes(){
		m_routeOption = Constants.ROUTE_OPTION.FASTEST_ROUTE;
		m_useDepartureTime = false;
		m_useArrivalTime = false;
		m_departureTime = 0;
		m_arrivalTime = 0;
		m_hasOriginStation = false;
		m_hasDestinationStation = false;
		m_originStation = "";
		m_destinationStation = "";
		m_unordered = false;
		m_listOfStations = new Vector<String>();
	}

	/**Gets the preferred route option;
	 * Fastest Route, Earliest Departure, Earliest Arrival, Fewest Transfers
	 * @return the m_routeOption field
	 */
	public Constants.ROUTE_OPTION getRouteOption() {
		return m_routeOption;
	}

	/**Sets the routeOption to the given route option
	 * @param m_routeOption the given route option
	 */
	public void setRouteOption(Constants.ROUTE_OPTION m_routeOption) {
		this.m_routeOption = m_routeOption;
	}
	
	/**Gets the use departure time flag
	 * @return the m_useDepartureTime field
	 */
	public boolean isUseDepartureTime() {
		return m_useDepartureTime;
	}

	/**Sets the use departure time flag to the given Boolean
	 * @param m_useDepartureTime the given Boolean
	 */
	public void setUseDepartureTime(boolean m_useDepartureTime) {
		this.m_useDepartureTime = m_useDepartureTime;
	}

	/**Gets the use arrival time flag
	 * @return the m_useArrivalTime field
	 */
	public boolean isUseArrivalTime() {
		return m_useArrivalTime;
	}

	/**Sets the use arrival time to the given Boolean
	 * @param m_useArrivalTime the given Boolean
	 */
	public void setUseArrivalTime(boolean m_useArrivalTime) {
		this.m_useArrivalTime = m_useArrivalTime;
	}

	/**Gets the departure time
	 * @return the m_departureTime field
	 */
	public long getDepartureTime() {
		return m_departureTime;
	}

	/**Sets the departure time to the given time
	 * @param m_departureTime the given departure time
	 */
	public void setDepartureTime(long m_departureTime) {
		this.m_departureTime = m_departureTime;
	}

	/**Gets the arrival time
	 * @return the m_arrivalTime field
	 */
	public long getArrivalTime() {
		return m_arrivalTime;
	}

	/**Sets the arrival time to the given time
	 * @param m_arrivalTime the given arriavl time
	 */
	public void setArrivalTime(long m_arrivalTime) {
		this.m_arrivalTime = m_arrivalTime;
	}
	
	/**Gets the origin station flag; is there an origin station?
	 * @return the m_hasOriginStation field
	 */
	public boolean hasOriginStation(){
		return m_hasOriginStation;
	}
	
	/**Sets the hasOriginStation field to the given Boolean
	 * @param value the given Boolean
	 */
	public void setHasOriginStation(boolean value){
		m_hasOriginStation = value;
	}
	
	/**Gets the destination station flag; is there a destination station?
	 * @return the m_hasDestinationStation field
	 */
	public boolean hasDestinationStation(){
		return m_hasDestinationStation;
	}
	
	/**Sets the hasDestinationStation field to the given Boolean
	 * @param value the given Boolean
	 */
	public void setHasDestinationStation(boolean value){
		m_hasDestinationStation = value;
	}

	/**Gets the origin station
	 * @return the m_originStation field
	 */
	public String getOriginStation() {
		return m_originStation;
	}

	/**Sets the origin station to the given String
	 * @param m_originStation the given String
	 */
	public void setOriginStation(String m_originStation) {
		this.m_originStation = m_originStation;
	}

	/**Gets the destination station
	 * @return the m_destinationStation field
	 */
	public String getDestinationStation() {
		return m_destinationStation;
	}

	/**Sets the destination station to the given String
	 * @param m_destinationStation the given String
	 */
	public void setDestinationStation(String m_destinationStation) {
		this.m_destinationStation = m_destinationStation;
	}

	/**Gets the unordered flag; is unordered checked?
	 * @return the m_unordered field
	 */
	public boolean isUnordered() {
		return m_unordered;
	}

	/**Sets the unordered flag to the given Boolean
	 * @param m_unordered the given Boolean
	 */
	public void setUnordered(boolean m_unordered) {
		this.m_unordered = m_unordered;
	}

	/**Gets the list of stations that has been selected
	 * @return the m_listOfStations field
	 */
	public Vector<String> getListOfStations() {
		return m_listOfStations;
	}

	/**Sets the m_listOfStations field to the given Vector of station names
	 * @param m_listOfStations the list of station names
	 */
	public void setListOfStations(Vector<String> m_listOfStations) {
		this.m_listOfStations = m_listOfStations;
	}
	
	/**Gets the number of stations in the m_listOfStations field
	 * @return the number of stations in m_listOfStations
	 */
	public int getNumberOfStations(){
		int n = this.getListOfStations().size();
		if(this.hasOriginStation()) n++;
		if(this.hasDestinationStation()) n++;
		return n;
	}

	/**
	 * @return Returns the TripAttributes in String form.
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString(){
		StringBuilder s = new StringBuilder();
		s.append("Trip Type: " + m_routeOption.toString() + "\n");
		s.append("Departure Time: " + m_departureTime + " seconds\n");
		s.append("Arrival Time: " + m_arrivalTime + " seconds\n");
		s.append("Origin Station: " + m_originStation + "\n");
		s.append("Destination Station: " + m_destinationStation + "\n");
		s.append("Other Stations (" + (m_unordered ? "Unordered" : "Ordered") + "):\n");
		for(String str : m_listOfStations){
			s.append("    * " + str + "\n");
		}
		return s.toString();
	}
}