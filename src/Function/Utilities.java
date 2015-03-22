package Function;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;

import Main.Constants;

/**Helper methods used in multiple classes
 *
 */
public class Utilities {
	
	/**Sorts an ArrayList of integers
	 * @param set a Set of Integers
	 * @return a sorted ArrayList of integers based on set
	 */
	public static ArrayList<Integer> sortIntegerSet(Set<Integer> set){
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.addAll(set);
		Collections.sort(list);
		return list;
	}
	
	/**Reverses a Hashmap of Integers 
	 * @param hm given Hashmap
	 * @return a reversed Hashmap based on hm
	 */
	public static HashMap<String, Integer> reverseHashMap(HashMap<Integer, String> hm){
		
		Integer currentKey;
		String currentValue;
		ArrayList<Integer> list = new ArrayList<Integer>();
		HashMap<String, Integer> hm_new= new HashMap<String, Integer>();
		list = sortIntegerSet(hm.keySet());
		
		for(int i = 0; i < list.size(); i++){
			currentKey = list.get(i);
			currentValue = hm.get(currentKey);
			hm_new.put(currentValue, currentKey);
		}
		
		return hm_new;
	}
	
	/**Changes a time stamp to a String
	 * @param time the given time stamp
	 * @return the time stamp in String form
	 */
	public static String timeStampToString(long time){
		return (new StringBuilder(Constants.DATE_FORMAT.format(new Date(time * 1000)))).toString();
	}

	/**Gets the time stamp for a train based on the given time and line color
	 * @param time the given time
	 * @param line the line color
	 * @return the current time of the line color's data + the given time
	 */
	public static long getTimeStampForTrain(int time, Constants.LINE_COLOR_SELECTOR line){
		switch(line){
		case REDASH:
		case REDBRAIN:
		case RED:
			return Constants.DATA_RED.getCurrentTime() + time;
		case BLUE:
			return Constants.DATA_BLUE.getCurrentTime() + time;
		case ORANGE:
			return Constants.DATA_ORANGE.getCurrentTime() + time;
		default:
			return 0;
		}
	}
	
	/**Factorial of given int
	 * @param n an integer
	 * @return n! (n factorial)
	 */
	public static int factorial(int n){
		switch(n){
		case 0:
			System.out.println("Illegal Argument Detected in Utilities.factorial(" + n + ")!!!");
			return -1;
		case 1:
			return 1;
		default:
			return n * Utilities.factorial(n - 1);
		}
	}
}
