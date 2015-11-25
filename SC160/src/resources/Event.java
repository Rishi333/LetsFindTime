/**
 * Created by SteveWang on 11/16/15.
 */
package resources;
import javax.xml.bind.DatatypeConverter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Event {
    EventTime startTime = null;
    EventTime endTime = null;
    int duration = 0;

    Event(String input) {
        String times[] = input.split("\\|");

        try {
            Calendar startCal = DatatypeConverter.parseDate((times[0]));
            Calendar endCal = DatatypeConverter.parseDate(times[1]);
            startTime = new EventTime(startCal.getTime());
            endTime = new EventTime(endCal.getTime());

            //Calculate Duration
            Long start = startTime.getTime();
            Long end = endTime.getTime();
            Long durationMs = end - start;
            duration = (int) (durationMs / 60000); //Convert ms to minutes
        }
        catch (IllegalArgumentException e) {
            System.out.println("\n\nINVALID INPUT Malformed Event: " + input);
        }
    }

    @Override
    public String toString() {
        SimpleDateFormat format = new SimpleDateFormat("");
        return "Event\nStart Time: " + startTime + "\nEnd Time: " + endTime + "\nDuration " +
                duration + "\n\n";
    }
}
