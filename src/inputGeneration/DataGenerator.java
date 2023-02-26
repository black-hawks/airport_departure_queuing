/**
 * A class that generates input data for a flight management system.
 * The data consists of various attributes such as airline, gate number, taxi-out time, push back time, delay, etc.
 */

package inputGeneration;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


/**
 * @author ravi
 *
 */
public class DataGenerator {

	/**
	 * A map to store the unimpeded data for various airlines and seasons.
	 */
	public static Map<String, ArrayList<UnImpededData>> info = new HashMap<String, ArrayList<UnImpededData>>();

	/**
	 * The main method of the program.
	 * It reads the unimpeded data for various airlines and seasons from a file,
	 * generates random input data based on various probability distributions,
	 * and writes the data to a CSV file.
	 * Finally, it sorts the data using external merge sort and writes it to another file.
	 *
	 * @param args The command-line arguments.
	 */
	public static void main(String[] args) {

		DataReader dataReader = new DataReader();

		try {
			dataReader.getData();
		} catch (Exception E) {
			E.printStackTrace();
		}

		// creating the Data file and Creating the headers
		String csvFile = "DataFile.csv";
		// headers
		String[] headers = { "Date", "Airline", "Season", "Unimpeded Time", "Gate No ", "Taxi-out time",
				"Push Back Time", "Delay", "Unix Time" };

		// airline - get the keys and pick the airline randomly
		info = dataReader.data;
		String[] airlines = new String[dataReader.data.keySet().size()];
		int j = 0;

		for (String key : dataReader.data.keySet()) {
			airlines[j++] = key;
		}

		try (FileWriter writer = new FileWriter(csvFile)) {

			// code to generate the data randomly

			for (int i = 0; i < 200000; i++) {

				int randTravelTime = randomTravelTimeGenerator();

				java.sql.Timestamp randTimeStamp = randomTimeStampGenerator();
				String taxiOutTime = randTimeStamp.toString();
				int month = getMonth(taxiOutTime);
				int season = getSeason(month);
				String date = taxiOutTime.substring(0, 10);
				int gateNumber = getGateNumber(randTravelTime);
				int rnd = new Random().nextInt(airlines.length);
				int delay = getDelay();
				String airline = airlines[rnd];
				double impDouble = getUnImpDataDouble(season, airline);
				int unimpededTime = (int) impDouble * 60;
				long unixTime = randTimeStamp.getTime() / 1000;
				randTravelTime = randTravelTime * 60;

				String[] data = { date.toString(), airline, Integer.toString(season), Integer.toString(unimpededTime),
						Integer.toString(gateNumber), Integer.toString(randTravelTime), taxiOutTime,
						Integer.toString(delay), Long.toString(unixTime) };
				writer.write(String.join(",", data));
				writer.write("\n");
			}

			ExternalMergeSort externalMergeSort = new ExternalMergeSort();
			externalMergeSort.sort("DataFile.csv", "InputFile.csv");
			System.out.println("CSV file created successfully.");
		} catch (IOException e) {
			System.err.println("Error creating CSV file: " + e.getMessage());
		}

	}

	/**

    Generates a random delay time between 1 and 600.
    @return A random delay time between 1 and 600.
    */
	public static int getDelay() {
		int randomNum = generateRandomNumber(1, 600);
		;
		return randomNum;
	}
	
	/**

    Retrieves the unimpeded time for the specified season and airline.
    @param season The season for which to retrieve the unimpeded time.
    @param airline The airline for which to retrieve the unimpeded time.
    @return The unimpeded time for the specified season and airline.
    
    */
	public static Double getUnImpDataDouble(int season, String airline) {
		ArrayList<UnImpededData> cache = info.get(airline);
		for (UnImpededData d : cache) {
			if (d.getSeason() == season) {
				return d.getUnImpededTime();
			}
		}
		return 0.0;
	}

	/**

    Retrieves the time part of the specified timestamp.
    @param timeStamp The timestamp from which to retrieve the time part.
    @return The time part of the specified timestamp.
    
    */
	public static String getTime(String timeStamp) {
		return timeStamp.toString().substring(11, 16);
	}
	
	/**

    Generates a random travel time between 10 and 45 based on a weighted probability distribution.
    @return A random travel time between 10 and 45.
    */

	public static int randomTravelTimeGenerator() {
		// generating the random number between 0 -10 for the probability
		int randomNum = generateRandomNumber(1, 10);
		int random = 0;
		// if the random number is between 1 - 5
		// generate the random number between 10 -20 with probability of 50%
		if (randomNum <= 5) {
			random = generateRandomNumber(10, 20);
		}
		// if the random number is between 6 - 8
		// generate the random number between 21 -30 with the probability of 30%
		else if (randomNum > 5 && randomNum <= 8) {
			random = generateRandomNumber(21, 30);
		}
		// if the random number is between 9 - 10
		// generate the random number between 31 -45 with the probability of 20%
		else {
			random = generateRandomNumber(31, 45);
		}
		return random;
	}

	/**

    Determines the gate number for the specified taxi-out time.
    @param time The time for which to determine the gate number.
    @return The gate number for the specified time.
    */
	public static int getGateNumber(int time) {
		int gate = 0, gate1 = 0, gate2 = 0;

		if (time >= 10 && time <= 20) {
			// 1-5 or 15-20
			gate1 = generateRandomNumber(1, 5);
			gate2 = generateRandomNumber(15, 20);
			gate = new Random().nextBoolean() ? gate1 : gate2;

		} else if (time > 20 && time <= 30) {
			// 6-9 or 13-14
			gate1 = generateRandomNumber(6, 9);
			gate2 = generateRandomNumber(13, 14);
			gate = new Random().nextBoolean() ? gate1 : gate2;

		} else {
			// 10-12
			gate = generateRandomNumber(10, 12);

		}
		return gate;
	}

	/**

    Generates a random java.sql.Timestamp object representing a time between January 1, 2012 00:00:00 and January 1, 2013 00:00:00.
    @return a random java.sql.Timestamp object representing a time between January 1, 2012 00:00:00 and January 1, 2013 00:00:00
    */
	public static java.sql.Timestamp randomTimeStampGenerator() {
		long offset = java.sql.Timestamp.valueOf("2012-01-01 00:00:00").getTime();
		long end = java.sql.Timestamp.valueOf("2013-01-01 00:00:00").getTime();
		long diff = end - offset + 1;
		java.sql.Timestamp rand = new java.sql.Timestamp(offset + (long) (Math.random() * diff));
		return rand;

	}

	/**

    Generates a random integer between the specified minimum and maximum values (inclusive).
    @param min The minimum value that the generated integer can be.
    @param max The maximum value that the generated integer can be.
    @return A random integer between min and max.
    */
	public static int generateRandomNumber(int min, int max) {
		Random random = new Random();
		return random.nextInt((max - min) + 1) + min;
	}
	/**

    Returns the month number from a timestamp string in the format "yyyy-MM-dd HH:mm:ss".
    @param timeStamp the timestamp string from which the month number is to be extracted.
    @return the month number (an integer from 1 to 12) from the given timestamp string.
    */
	public static int getMonth(String timeStamp) {
		return Integer.parseInt(timeStamp.toString().substring(5, 7));

	}

	/**

    This method returns the season number based on the input month number.
    The months are grouped into seasons as follows:
    Season 1: January, February, March
    Season 2: April, May, June
    Season 3: July, August, September
    Season 4: October, November, December
    @param month the month number (1-12)
    @return the season number (1-4) corresponding to the input month
    */
	public static int getSeason(int month) {
		int season = 0;
		switch (month) {
		case 1:
		case 2:
		case 3:
			season = 1;
			break;
		case 4:
		case 5:
		case 6:
			season = 2;
			break;
		case 7:
		case 8:
		case 9:
			season = 3;
			break;
		case 10:
		case 11:
		case 12:
			season = 4;
			break;

		}
		return season;
	}

}
