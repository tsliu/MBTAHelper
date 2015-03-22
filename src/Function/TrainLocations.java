package Function;

import Data.TripList;


/**Class that handles the functionality of finding all the trains' locations
 *
 */
public class TrainLocations {

	TripList trips;

	/**TrainLocation Constructor based on TripList
	 * @param t the given TripList
	 */
	public TrainLocations(TripList t){
		this.trips = t;
	}

	/**Finds the locations of all the trains on the Red line based on the given TripList
	 * @param t the given TripList
	 * @return a String containing all the locations of the trains on the Red line
	 */
	public static String getRedLineLocations(TripList t){

		String AlewifeTrains = "Current Red Line train locations: (for trains heading to Alewife)\n";
		String BraintreeTrains = "Current Red Line train locations: (for trains heading to Braintree)\n";
		String AshmontTrains = "Current Red Line train locations: (for trains heading to Ashmont)\n";

		for (int i = 0; i<t.trips.size(); i++){
			if(t.trips.get(i).position == null){
				continue;
			}
			if (t.trips.get(i).destination.equals("Alewife")){
				AlewifeTrains = AlewifeTrains + "- A service train is approaching station: " 
						+ t.trips.get(i).predictions.get(0).stop + "\n";
			} else if (t.trips.get(i).destination.equals("Braintree")){
				BraintreeTrains = BraintreeTrains + "- A service train is approaching station: " 
						+ t.trips.get(i).predictions.get(0).stop + "\n";
			} else
				AshmontTrains = AshmontTrains + "- A service train is approaching station: " 
						+ t.trips.get(i).predictions.get(0).stop + "\n";
		}

		return AlewifeTrains + "\n" + BraintreeTrains + "\n" + AshmontTrains;
	}
	
	/**Finds the locations of all the trains on the Blue line based on the given TripList
	 * @param t the given TripList
	 * @return a String containing all the locations of the trains on the Blue line
	 */
	public static String getBlueLineLocations(TripList t){

		String BowdoinTrains = "Current Blue Line train locations: (for trains heading to Bowdoin)\n";
		String WonderlandTrains = "Current Blue Line train locations: (for trains heading to Wonderland)\n";

		for (int i = 0; i<t.trips.size(); i++){
			if(t.trips.get(i).position == null){
				continue;
			}
			if (t.trips.get(i).destination.equals("Bowdoin")){
				BowdoinTrains = BowdoinTrains + "- A service train is approaching station: " 
						+ t.trips.get(i).predictions.get(0).stop + "\n";
			} else
				WonderlandTrains = WonderlandTrains + "- A service train is approaching station: " 
						+ t.trips.get(i).predictions.get(0).stop + "\n";
		}

		return BowdoinTrains + "\n" + WonderlandTrains;
	}
	
	/**Finds the locations of all the trains on the Orange line based on the given TripList
	 * @param t the given TripList
	 * @return a String containing all the locations of the trains on the Orange line
	 */
	public static String getOrangeLineLocations(TripList t){

		String OakGroveTrains = "Current Orange Line train locations: (for trains heading to Oak Grove)\n";
		String ForestHillsTrains = "Current Orange Line train locations: (for trains heading to Forest Hills)\n";

		for (int i = 0; i<t.trips.size(); i++){
			if(t.trips.get(i).position == null){
				continue;
			}
			if (t.trips.get(i).destination.equals("Oak Grove")){
				OakGroveTrains = OakGroveTrains + "- A service train is approaching station: " 
						+ t.trips.get(i).predictions.get(0).stop + "\n";
			} else
				ForestHillsTrains = ForestHillsTrains + "- A service train is approaching station: " 
						+ t.trips.get(i).predictions.get(0).stop + "\n";
		}

		return OakGroveTrains + "\n" + ForestHillsTrains;
	}
}
