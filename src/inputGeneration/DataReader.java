/**

    This class reads data from a CSV file and stores it in a HashMap.
    The CSV file is expected to contain information about airlines' unimpeded times for each season.
    The HashMap is structured such that the keys are airline names, and the values are ArrayLists of
    UnImpededData objects, each of which contains a season number and an unimpeded time.
 */
package inputGeneration;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author ravi
 *
 */
public class DataReader {
	/**
	 * Reads data from a CSV file and stores it in the HashMap.
	 *
	 * @throws Exception if there is an error reading the file or parsing the data
	 */
	public Map<String, ArrayList<UnImpededData>> data = new HashMap<String, ArrayList<UnImpededData>>();

	public void getData() throws Exception {
		ArrayList<UnImpededData> temp = new ArrayList<UnImpededData>();
		// parsing a CSV file into Scanner class constructor
		Scanner sc = new Scanner(new File("APM-Report.csv"));
		// sets the delimiter pattern
		int i = 1;
		while (sc.hasNextLine()) { // returns a boolean value
			String line = sc.nextLine();
			String[] words = line.split(",");

			String airLine = "";
			String temp1, temp2;
			int season = -1;
			Double unImpededTime = 0.0;

			// storing the season value.
			airLine = words[0];
			if (i == 1) {
				airLine = airLine.substring(1);
				i++;
			}
			temp1 = words[1];
			season = Integer.parseInt(temp1);

			// storing the unimpeded data
			temp2 = words[2];
			unImpededTime = Double.parseDouble(temp2);

			UnImpededData uiData = new UnImpededData(season, unImpededTime);

			if (data.containsKey(airLine)) {
				// this if the hash Map already contains the keys
				// we are getting the value associated with the key and adding the new data to
				// the existing data
				temp = data.get(airLine);
				temp.add(uiData);
				data.replace(airLine, temp);
			} else {
				// this if a new airline is discovered
				// add the new information
				ArrayList<UnImpededData> uiDataList = new ArrayList<UnImpededData>();
				uiDataList.add(uiData);
				data.put(airLine, uiDataList);

			}
		}

		sc.close(); // closes the scanner
	}
}
