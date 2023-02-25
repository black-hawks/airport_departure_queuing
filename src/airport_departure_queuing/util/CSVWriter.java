import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CSVWriter {
    public void writeToCsv(long pushback,long expectedWheelOff,long actualWheelOff){
        Date pbtime = new Date(pushback);
        Date expectedwo = new Date(expectedWheelOff);
        Date actualwo = new Date(actualWheelOff);

        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        String formattedpbtime = format.format(pbtime);
        String formattedexpectedwo = format.format(expectedwo);
        String formattedactualwo = format.format(actualwo);

        try {
            PrintWriter writer = new PrintWriter(new FileWriter("Output.csv", true));
            writer.print(formattedpbtime + ",");
            writer.print(formattedexpectedwo + ",");
            writer.print(formattedactualwo);
            writer.println();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
