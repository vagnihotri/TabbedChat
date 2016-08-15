package co.haptik.tabbedchat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by vijayagnihotri on 15/08/16.
 */
public class Utility {

    public static final String DATE_FORMAT_DISPLAY = "d MMMM yyyy h:mm a";
    public static final String DATE_FORMAT_SERVER = "yyyy-MM-dd'T'HH:mm:ss";

    public static String formatDateTimeString(String dateTime) {
        String formattedDate = "";
        SimpleDateFormat serverTime = new SimpleDateFormat(DATE_FORMAT_SERVER);
        SimpleDateFormat displayFormat = new SimpleDateFormat(DATE_FORMAT_DISPLAY);
        try {
            Date date = serverTime.parse(dateTime);
            formattedDate = displayFormat.format(date);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return formattedDate;
    }
}
