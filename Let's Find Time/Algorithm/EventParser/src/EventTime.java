/**
 * Created by SteveWang on 11/16/15.
 */

import java.util.Calendar;
import java.util.Date;

public class EventTime {
    Date date;
    int year;
    int month;
    int day;
    int hour;
    int minute;
    int second;

    EventTime(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        this.date = date;
        year = cal.get(Calendar.YEAR);
        month = cal.get(Calendar.MONTH);
        day = cal.get(Calendar.DAY_OF_MONTH);
        hour = cal.get(Calendar.HOUR_OF_DAY);
        minute = cal.get(Calendar.MINUTE);
        second = cal.get(Calendar.SECOND);
    }

    public Long getTime() {
        return date.getTime();
    }

    @Override
    public String toString() {
        return date.toString();
    }
}
