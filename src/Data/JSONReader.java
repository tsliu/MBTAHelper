package Data;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import Main.Constants;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;

// reads the feed and creates the data structure
/**The JSONReader parses the MBTA data into the different Data classes.
 * 
 *
 */
public class JSONReader {

	private JsonParser jp;
	private ObjectMapper mapper;
	
	/**The TripList data structure; Given by MBTA 
	 * @see TripList
	 */
	public TripList triplist;
	/**The TripCompiler used to map the data.
	 * 
	 */
	public TripCompiler tripcomp;
		
	/**JSONReader constructor; The JSONReader gets built based on the source, and provided line color.
	 * @param source The source of the data. Can be live or local files.
	 * @param color The color of the line for which the data is being loaded.
	 * @throws Exception
	 */
	public JSONReader(Constants.DATA_SOURCE_SELECTOR source, Constants.LINE_COLOR_SELECTOR color) throws Exception{
			URL u = null;
			File f = null;
			switch(color) {
			case RED:
				f = Constants.FILE_RED;
				u = new URL(Constants.URL_RED);
				break;
			case BLUE:
				f = Constants.FILE_BLUE;
				u = new URL(Constants.URL_BLUE);
				break;
			case ORANGE:
				f = Constants.FILE_ORANGE;
				u = new URL(Constants.URL_ORANGE);
				break;
			default:
				break;
			}
			
			switch(source){
			case LIVE:
				this.jp = new JsonFactory().createJsonParser(getJson(u));
				break;
			case FILES:
				this.jp = new JsonFactory().createJsonParser(f);
				break;
			default:
				break;
			}
			mapper = new ObjectMapper();
			tripcomp = mapper.readValue(jp, TripCompiler.class);
			//System.out.println(tripcomp.toString());
	}

	private String getJson (URL u ) throws Exception {
			HttpURLConnection connect = (HttpURLConnection) u.openConnection();
			connect.setRequestMethod("GET");
			connect.setUseCaches(false);
			connect.setConnectTimeout(10000);
			connect.setReadTimeout(10000);
			connect.connect();
			
			int status = connect.getResponseCode();
			
			if(status == 201 || status == 200) {
				BufferedReader br = new BufferedReader(new InputStreamReader(connect.getInputStream()));
				String line = null;
				String content = "";
				while ((line = br.readLine()) != null) {
					content += line + "\n";
				}
				br.close();
				return content;
			} else {
				System.out.println("Bad Connect" + status);
				return null;
			}
	}
	
	
	/**Gets the TripList.
	 * @return TripList
	 */
	public TripList getTripList() {
		return tripcomp.getTripList();
	}
	
	/**Loads the data to create the StationManager.
	 * @param source The source of the data. Can be live or local files.
	 * @param color The color of the line for which data is being loaded.
	 * @throws Exception
	 * @see StationManager
	 */
	public static void loadData(Constants.DATA_SOURCE_SELECTOR source, Constants.LINE_COLOR_SELECTOR color) throws Exception{
		if(color == Constants.LINE_COLOR_SELECTOR.ALL){
			if(source != Constants.DATA_SOURCE_SELECTOR.FILES || Constants.FILE_RED != null)
			Constants.DATA_RED = (new JSONReader(source, Constants.LINE_COLOR_SELECTOR.RED)).getTripList();
			if(source != Constants.DATA_SOURCE_SELECTOR.FILES || Constants.FILE_BLUE != null)
			Constants.DATA_BLUE = (new JSONReader(source, Constants.LINE_COLOR_SELECTOR.BLUE)).getTripList();
			if(source != Constants.DATA_SOURCE_SELECTOR.FILES || Constants.FILE_ORANGE != null)
			Constants.DATA_ORANGE = (new JSONReader(source, Constants.LINE_COLOR_SELECTOR.ORANGE)).getTripList();
		} else {
			TripList triplist = (new JSONReader(source, color)).getTripList();
			switch(color){
			case RED:
				Constants.DATA_RED = triplist;
				break;
			case BLUE:
				Constants.DATA_BLUE = triplist;
				break;
			case ORANGE:
				Constants.DATA_ORANGE = triplist;
			default:
				break;
			}
		}
		StationManager.createStationManager();
	}
}
