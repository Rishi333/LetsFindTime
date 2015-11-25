package resources;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {

    public static void main(String[] args) {
//                String person1 =
//                "2015-11-05T18:00:00-08:00|2015-11-05T19:15:00-08:00\n" +
//                "2015-11-05T19:30:00-08:00|2015-11-05T20:45:00-08:00\n" +
//                "2015-11-09T15:00:00-08:00|2015-11-09T17:45:00-08:00\n" +
//                "2015-11-10T10:30:00-08:00|2015-11-10T11:45:00-08:00\n" +
//                "2015-11-10T18:00:00-08:00|2015-11-10T19:15:00-08:00\n" +
//                "2015-11-10T19:30:00-08:00|2015-11-10T20:45:00-08:00\n" +
//                "2015-11-12T10:30:00-08:00|2015-11-12T11:45:00-08:00\n" +
//                "2015-11-12T18:00:00-08:00|2015-11-12T19:15:00-08:00\n" +
//                "2015-11-12T19:30:00-08:00|2015-11-12T20:45:00-08:00\n" +
//                "2015-11-16T15:00:00-08:00|2015-11-16T17:45:00-08:00";

        String person1 =
                "2015-11-05T12:00:00-08:00|2015-11-05T13:15:00-08:00$" +
                "2015-11-05T15:10:00-08:00|2015-11-05T16:00:00-08:00";

        String person2 =
                "2015-11-05T13:00:00-08:00|2015-11-05T13:45:00-08:00$" +
                "2015-11-05T15:30:00-08:00|2015-11-05T16:00:00-08:00";

        String person3 =
                "2015-11-05T12:00:00-08:00|2015-11-05T12:45:00-08:00$" +
                "2015-11-05T13:30:00-08:00|2015-11-05T13:45:00-08:00$"  +
                "2015-11-05T15:30:00-08:00|2015-11-05T15:55:00-08:00";

        String constraints =
                "2015-11-05T12:00:00-08:00|2015-11-05T16:01:00-08:00";

        EventParser p1 = new EventParser(person1);
        EventParser p2 = new EventParser(person2);
        EventParser p3 = new EventParser(person3);
        EventParser c = new EventParser(constraints);

        List<List<Event>> people = new ArrayList<>();
        people.add(p1.events);
        people.add(p2.events);
        people.add(p3.events);

        LetsFindTimeAlg alg = new LetsFindTimeAlg(people, c.events);
        Date[] retval = alg.getFreeTime();
        System.out.println("\nStart of free time: " + retval[0] + "\nEnd of free time: " + retval[1]);
    }
}
