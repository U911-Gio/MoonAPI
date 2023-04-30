package be.moondevelopment.moonapi.framework.utils;


import org.apache.commons.lang3.time.DurationFormatUtils;

import java.text.DecimalFormat;
import java.util.StringJoiner;

public class TimeUtil {

    public static final long PERMANENT = -1;

    public static ThreadLocal<DecimalFormat> SECONDS() {
        return ThreadLocal.withInitial(() -> new DecimalFormat("0.#"));
    }

    public static String formatDuration(long input) {
        return DurationFormatUtils.formatDurationWords(input, true, true);
    }

    public static String getTime(long seconds) {
        final StringJoiner joiner = new StringJoiner(" ");

        long minutes = seconds / 60;
        long hours = minutes / 60;
        final long days = hours / 24;

        seconds %= 60;
        minutes %= 60;
        hours %= 24;

        if (days > 0) {
            joiner.add(days + "d");
        }

        if (hours > 0) {
            joiner.add(hours + "h");
        }

        if (minutes > 0) {
            joiner.add(minutes + "m");
        }

        if (seconds > 0) {
            joiner.add(seconds + "s");
        }

        return joiner.toString();
    }

    public static String getDuration(long input) {
        return input == PERMANENT ? "Permanent" : formatDuration(input);
    }

}
