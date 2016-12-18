package com.papaver.thenewyorktimes.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Office on 2016-12-17.
 */

public class DateUtils {

    private static final String SOURCE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
    private static final String DESTINATION_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static String formattedDate(String date) {
        SimpleDateFormat fromFormat = new SimpleDateFormat(SOURCE_FORMAT);
        SimpleDateFormat toFormat = new SimpleDateFormat(DESTINATION_FORMAT);

        Date fromDate = null;
        try {
            fromDate = fromFormat.parse(date);
        } catch ( ParseException e ) {
            fromDate = new Date();
        }

        return toFormat.format(fromDate);
    }

    public static long getTimeStamp(String date) {
        SimpleDateFormat fromFormat = new SimpleDateFormat(SOURCE_FORMAT);
        Date fromDate = null;
        try {
            fromDate = fromFormat.parse(date);
        } catch ( ParseException e ) {
            return 0;
        }

        return fromDate.getTime();
    }

}
