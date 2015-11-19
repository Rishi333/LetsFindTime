/**
 * Created by SteveWang on 11/16/15.
 */

import java.util.Date;
import java.util.List;

/*
   How to use
    1. Pass in each persons event string to EventParser
    2. Build those Event(s) from the EventParser into a list
    3. Pass in the constraints for the window of the search to EventParser
    4. Create a new LetsFindTimeAlg and pass in the list from step 2 and the constraints from step 3
    5. Call .getFreeTime() which returns an array of Dates. First is the start, second is the end.
 */
public class LetsFindTimeAlg {
    List<Event> constraints;
    List<List<Event>>  people;

    int MAX_NUM_PEOPLE = 5;
    int WINDOW_IN_MINUTES = 30;

    int [][] peoples = new int[MAX_NUM_PEOPLE][];
    int [] results;
    int numOfPeople;
    int numTimeSlots;

    LetsFindTimeAlg(List<List<Event>> people, List<Event> constraints) {
        this.constraints = constraints;
        this.people = people;

        numOfPeople = people.size();
        numTimeSlots = (int) Math.ceil(constraints.get(0).duration / 30);

        results = new int [numTimeSlots];

        for(int i = 0; i < people.size(); i++) {
            peoples[i] = new int[numTimeSlots];
            peoples[i] = setBusyTimes(peoples[i], people.get(i));
            for(int j = 0; j < numTimeSlots; j++) {
                System.out.print(peoples[i][j]);
            }
            System.out.println();
        }
    }

    public Date[] getFreeTime() {
        Date [] retval = new Date [2];

        for(int i = 0; i < numOfPeople; i++) {
            andWithResult(peoples[i]);
        }

//        System.out.print("\n\n");
//        for(int i = 0; i < numTimeSlots; i++) {
//            System.out.print(results[i]);
//        }

        int[] longestPositionAndLength = findLongestTime();

        if(longestPositionAndLength[0] != -1) {
            long startInMS = convertMINtoMS((convertMSToMin(constraints.get(0).startTime.getTime()) + (longestPositionAndLength[0] * WINDOW_IN_MINUTES)));
            retval[1] = new Date(startInMS);
            long endInMS = startInMS + convertMINtoMS(longestPositionAndLength[1] * WINDOW_IN_MINUTES);
            retval[0] = new Date(endInMS);

            System.out.println(retval[0]);
            System.out.println(retval[1]);
        }
        else {
            System.out.println("\n\n\nERROR: Could not find a common time\n\n\n");
        }

        return retval;
    }

    //Int array structure is first position is position and second position is length
    //Returns -1 if no time is found and position number if there is
    public int [] findLongestTime() {
        int [] longestPositionAndLength = {-1, -1};
        int longestLength = 0;
        int tempLength = 0;

        for(int i = 0; i < numTimeSlots; i++) {
            tempLength = (results[i] == 0) ? 1 + tempLength : 0;
            if (tempLength > longestLength) {
                longestLength = tempLength;
                longestPositionAndLength[0] = i - longestLength + 1;
            }
        }
        longestPositionAndLength[1] = longestLength;
        return longestPositionAndLength;
    }

    public void andWithResult(int [] p1) {
        for(int i = 0; i < numTimeSlots; i++) {
            if(p1[i] != 0 || results[i] != 0) {
                results[i] = 1;
            }
        }
    }

    private int [] setBusyTimes(int [] arrTimes, List<Event> person) {
        Event cons = constraints.get(0);

        long startOfConstraint = convertMSToMin(cons.startTime.getTime());
        long endOfConstraint = startOfConstraint +  cons.duration;

        for(Event event: person) {
            long startEvent = convertMSToMin(event.startTime.getTime());
            long endEvent = startEvent +  event.duration;
            long start = startOfConstraint;

            for(int i = 0; i < numTimeSlots; i++) {
                if(start < endOfConstraint && arrTimes[i] != 1) {
                    if(startEvent >= start && startEvent < start + WINDOW_IN_MINUTES) {
                        arrTimes[i] = 1;
                        startEvent = startEvent + WINDOW_IN_MINUTES > endEvent ? endEvent : startEvent + WINDOW_IN_MINUTES;
                    }
                }
                start += WINDOW_IN_MINUTES;
            }
        }
        return arrTimes;
    }

    private long convertMSToMin(long ms) {
        return ms / 60000;
    }

    private long convertMINtoMS(long min) {
        return min * 60000;
    }
}
