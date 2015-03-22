package Function;

import java.util.Vector;

public class UnorderedHelper {
	
	/**The number seconds the trip takes
	 * 
	 */
	public static long bestValueSoFar;
	/**The trip that is the fastest
	 * 
	 */
	public static TripAttributes bestTripSoFar;
	
	/**Goes through the permutations of all the possible routes and returns the best trip
	 * @param attr the TripAttributes, the preferences for the route
	 * @return the best trip
	 */
	public static TripAttributes getOptimumRoute(TripAttributes attr){
		
		bestValueSoFar = Long.MAX_VALUE;
		bestTripSoFar = attr;
		
		permutation(new Vector<String>(), attr.getListOfStations(), attr);

		return attr;
	}
	
	
	/**Recursive call that creates all possible routes
	 * @param pre an empty Vector<String>
	 * @param pos Vector<String> of all the station names in the selected list of stations
	 * @param attr the TripAttributes, the preferences for the route
	 */
	@SuppressWarnings("unchecked")
	public static void permutation(Vector<String> pre, Vector<String> pos, TripAttributes attr){
		int n = pos.size();
		if(n == 0) {
			attr.setListOfStations(pre);
			tryOrder(attr);
		} else {
			for(int i = 0; i < n; i ++){
				Vector<String> preCloned = (Vector<String>) pre.clone();
				Vector<String> posCloned = (Vector<String>) pos.clone();
				preCloned.add(pos.get(i));
				posCloned.remove(i);
				permutation(preCloned, posCloned, attr);
			}
		}
	}
	
	
	/**Compares bestTripSoFar with the current route created by permutation,
	 * If the permutation is a more optimal route than bestTripSoFar, bestTripSoFar is
	 * changed to the permutation
	 * @param attr the TripAttributes, the preferences for the route
	 */
	public static void tryOrder(TripAttributes attr){
		long workValue = Long.MAX_VALUE;
		OrderProperties op = FindRoutes.getOrderProps(attr);
		
		if(op.isHasTrains()) {
			switch(attr.getRouteOption()){
			case FASTEST_ROUTE:
				workValue = op.getTime();
				break;
			case EARLIEST_DEPARTURE:
				workValue = op.getDepartureTime();
				break;
			case EARLIEST_ARRIVAL:
				workValue = op.getArrivalTime();
				break;
			case FEWEST_TRANSFER:
			default:
				workValue = op.getTransfers();
			}
			
			if(workValue < bestValueSoFar){
				bestTripSoFar = attr;
				bestValueSoFar = workValue;
			}
		}
	}
	
	/*
	public static TripAttributes getOptimumRoutes(TripAttributes attr){
		
		int numberOfStations = attr.getNumberOfStations();
		int maxCombinations = Utilities.factorial(numberOfStations);
		Vector<String> selectedStops = attr.getListOfStations();
		
		long bestValueSoFar = Long.MAX_VALUE;
		Vector<String> bestTripSoFar = selectedStops;

		long workValue = 0;
		Vector<String> workStopsVector = new Vector<String>();
		OrderProperties workOrderProperties = new OrderProperties();
		
		//This might become very expansive as maxCombination could be up to 10 factorial!
		for(int i = 0; i < maxCombinations; i++){
			System.out.println("Running for variation " + i);
			workStopsVector = getOrderCombination(selectedStops, i);
			attr.setListOfStations(workStopsVector);
			workOrderProperties = FindRoutes.getOrderProps(attr);

			if(!workOrderProperties.isHasTrains()) continue;
			
			switch(attr.getRouteOption()){
			case FASTEST_ROUTE:
				workValue = workOrderProperties.getTime();
				break;
			case EARLIEST_DEPARTURE:
				workValue = workOrderProperties.getDepartureTime();
				break;
			case EARLIEST_ARRIVAL:
				workValue = workOrderProperties.getArrivalTime();
				break;
			case FEWEST_TRANSFER:
			default:
				workValue = workOrderProperties.getTransfers();
			}
			
			if(workValue < bestValueSoFar){
				bestTripSoFar = workStopsVector;
				bestValueSoFar = workValue;
			}
		}

		attr.setListOfStations(bestTripSoFar);
		return attr;
	}
	
	public static Vector<String> getOrderCombination(Vector<String> source, int variation){
		return source;
	}
	*/

}
