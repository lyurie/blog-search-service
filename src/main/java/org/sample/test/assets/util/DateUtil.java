package org.sample.test.assets.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DateUtil {

    private static final TimeZone defaultTimeZone = TimeZone.getTimeZone("GMT");

    public static String getCurrentDateTime(String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        simpleDateFormat.setTimeZone(defaultTimeZone);

        return simpleDateFormat.format(new Date(System.currentTimeMillis()));
    }
}
