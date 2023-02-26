package input_generation;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import java.util.HashMap;



public class DataGenerator {

    public static Map<String, ArrayList<UnImpededData>> info = new HashMap<String, ArrayList<UnImpededData>>();

    public static void main(String[] args) {
        
        DataReader dataReader = new DataReader();
        
        try{
            dataReader.getData();
        }catch(Exception E){
            E.printStackTrace();
        }
        
        //creating the Datafile and Creating the headers
        String csvFile = "DataFile.csv";
        //headers
        String[] headers = {"Date", "Airline","Season", "Unimpeded Time", "Gate No ", "Taxi-out time", "Push Back Time", "Delay", "Unix Time"};
        
        //airline - get the keys and pick the airline  randomly
        info = dataReader.data;
        String[] airlines = new String[dataReader.data.keySet().size()];
        int j=0;
        
        for(String key:dataReader.data.keySet()){
            airlines[j++]=key;
        }
    
        try (FileWriter writer = new FileWriter(csvFile)) {
            // Write headers
           // writer.write(String.join(",", headers));
            //writer.write("\n");
            
            //code to generate the data randomly

            for(int i =0; i<200000;i++){

                int randTravelTime = randomTravelTimeGenerator();
                
                java.sql.Timestamp randTimeStamp = randomTimeStampGenerator();
                String taxiOutTime = randTimeStamp.toString();
                int month = getMonth(taxiOutTime);
                int season=getSeason(month);
                String date=taxiOutTime.substring(0,10);
                int gateNumber = getGateNumber(randTravelTime);
                int rnd = new Random().nextInt(airlines.length);
                int delay = getDelay();
                String airline = airlines[rnd];
                double impDouble = getImpDataDouble(season, airline);
                int unimpededTime = (int)impDouble*60;
                long unixTime = randTimeStamp.getTime() / 1000;
                randTravelTime = randTravelTime * 60;


                String[] data = {date.toString(), airline,Integer.toString(season), Integer.toString(unimpededTime),Integer.toString(gateNumber), Integer.toString(randTravelTime), taxiOutTime, Integer.toString(delay), Long.toString(unixTime)};
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

    public static int getDelay(){
        int randomNum = generateRandomNumber(1, 600);;
        return randomNum;
    }

    public static Double getImpDataDouble(int season, String airline){
        ArrayList<UnImpededData> cache = info.get(airline);
        for(UnImpededData d : cache){
            if(d.getSeason() == season){
                return d.getUnImpededTime();
            }
        }
        return 0.0;
    }

    public static String getTime(String timeStamp){
        return timeStamp.toString().substring(11,16);
    }

    /**
     * This function will generate the random between the 10 - 45 for the pushback time
     * 
     * @return integer - random numnber
     */
    public static int randomTravelTimeGenerator(){
        // generating the random number between 0 -10 for the probability
        int randomNum = generateRandomNumber(1, 10);
        int random =0;
        // if the randomnumber is between 1 - 5 
        //generate the random number between 10 -20 with probability of 50%
        if(randomNum<=5){
            random = generateRandomNumber(10, 20);
        }
        // if the randomnumber is between 6 - 8 
        //generate the random number between 21 -30 with the probability of 30%
        else if(randomNum>5 && randomNum<=8){
            random = generateRandomNumber(21, 30);
        }
        // if the randomnumber is between 9 - 10 
        //generate the random number between 31 -45 with the probability of 20%
        else{
            random = generateRandomNumber(31, 45);
        }
        return random;
    }


    public static int getGateNumber(int time){
        int gate=0, gate1=0,gate2=0;

        if(time>=10 && time<=20){
            //1-5 or 15-20
            gate1 = generateRandomNumber(1, 5);
            gate2 = generateRandomNumber(15, 20);
            gate=new Random().nextBoolean() ? gate1 : gate2;

        }
        else if (time>20 && time<=30){
            //6-9 or 13-14
            gate1=generateRandomNumber(6, 9);
            gate2=generateRandomNumber(13, 14);
            gate=new Random().nextBoolean() ? gate1 : gate2;

        }else {
            //10-12
            gate=generateRandomNumber(10, 12);

        }
        return gate;
    }

    /**
     * this function will generate the random timestamps for year 2012
     */
    public static java.sql.Timestamp randomTimeStampGenerator(){
        long offset = java.sql.Timestamp.valueOf("2012-01-01 00:00:00").getTime();
        long end = java.sql.Timestamp.valueOf("2013-01-01 00:00:00").getTime();
        long diff = end - offset + 1;
        java.sql.Timestamp rand = new java.sql.Timestamp(offset + (long)(Math.random() * diff));
        return rand;

    }

    public static int generateRandomNumber(int min, int max) {
        Random random = new Random();
        return random.nextInt((max - min) + 1) + min;
    }

    public static int getMonth(String timeStamp ){
        return Integer.parseInt(timeStamp.toString().substring(5,7));

    }

    public static int getSeason(int month){
        int season=0;
        switch (month){
            case 1:
            case 2:
            case 3: season= 1;
            break;
            case 4:
            case 5:
            case 6:season= 2;
            break;
            case 7:
            case 8:
            case 9:season= 3;
            break;
            case 10:
            case 11:
            case 12:season= 4;
            break;

        }
        return season;
    }

}
