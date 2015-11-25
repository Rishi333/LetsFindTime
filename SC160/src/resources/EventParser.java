/**
 * Created by SteveWang on 11/16/15.
 */

package resources;

import java.util.ArrayList;
import java.util.List;

public class EventParser {
    List<Event> events = new ArrayList<Event>();

    EventParser(String input) {
        String lines[] = input.split("\\$");

        for(String line: lines) {
            if(isMalformed(line)) {
                System.out.println("Invalid Input at Event Parser: " + line);
                continue;
            }
            events.add(new Event((line)));
        }

        System.out.println(events);
    }

    private boolean isMalformed(String input) {
        if(input.contains("|"))
            if(input.split("\\|").length == 2)
                return false;

        return true;
    }
}
