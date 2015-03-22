package Main;

import java.io.File;

/**ParameterHandler parses and deals with the parameters the
 * application takes upon running
 * 
 */
public class ParameterHandler {
	
	/**Parses the arguments and changes options of application based on the arguments:
	 * If -L or -l, application uses live data;
	 * If -F or -f, application uses local data, user must specify path to local data;
	 * User must use the following parameters to specify local data paths:
	 * -R or -r to specify path to Red Line data
	 * -B or -b to specify path to Blue Line data
	 * -O or -o to specify path to Orange Line data
	 * @param args the arguments the application takes
	 */
	public static void handleParameters(String[] args){
		try {
			Constants.DATA_SOURCE = Constants.DATA_SOURCE_SELECTOR.LIVE;
			
			String arg;
			for(int i = 0; i < args.length; i++) {
				arg = args[i];
				if(arg.startsWith("-") && arg.length() == 2){
					switch(arg.charAt(1)){
					case 'l':
					case 'L':
						Constants.DATA_SOURCE = Constants.DATA_SOURCE_SELECTOR.LIVE;
						break;
					case 'F':
					case 'f':
						Constants.DATA_SOURCE = Constants.DATA_SOURCE_SELECTOR.FILES;
						break;
					case 'R':
					case 'r':
						attemptToLoadFile(args[++i], Constants.LINE_COLOR_SELECTOR.RED);
						break;
					case 'B':
					case 'b':
						attemptToLoadFile(args[++i], Constants.LINE_COLOR_SELECTOR.BLUE);
						break;
					case 'O':
					case 'o':
						attemptToLoadFile(args[++i], Constants.LINE_COLOR_SELECTOR.ORANGE);
						break;
					default:
						throw new Exception("Unknown Option!: " + arg);
					}
				} else {
					 throw new Exception("Invalid Argument: " + arg);
				}
			}
			
		} catch (Exception e){
			System.out.println(e.getMessage());
			System.out.println(Constants.CMD_INSTRUCTION);
			
		}
	}
	
	private static void attemptToLoadFile(String path, Constants.LINE_COLOR_SELECTOR line) throws Exception {
		//See if the path is another option, if so, the actual path is not supplied.
		if(path.startsWith("-")){
			throw new Exception("Please provide a path for the file of the " + line.toString() + " line!");
		}
		
		//If path is wrapped in quotes, strip the quotes off the path.
		if(path.startsWith("\"") && path.endsWith("\"")){
			path = path.substring(1, path.length() - 1);
		}
		
		File file = new File(path);
		if(!file.exists()){
			throw new Exception("File not found. Please check the path for the file of the " + line.toString() + " line!");
		}
		
		switch(line){
		case RED:
		case REDASH:
		case REDBRAIN:
			Constants.FILE_RED = file;
			break;
		case BLUE:
			Constants.FILE_BLUE = file;
			break;
		case ORANGE:
			Constants.FILE_ORANGE = file;
			break;
		default:
			throw new Exception("Invalid Line");
		}
	}
	
}
