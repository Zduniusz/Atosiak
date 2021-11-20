package com.zduniusz.discord.commandlist;

import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

public class Monitors {
    public static void reply(SlashCommandEvent event) {
        event.reply(String.format("Dzisiaj dyżurnymi są: **%s** i **%s**", getMonitors()[0], getMonitors()[1])).queue();
    }

    public static String[] getMonitors() {
        Date today = new Date();
        Date firstOfSeptember = new GregorianCalendar((getMonth(today) == 1 ? getYear(today) - 1 : getYear(today)), 8, 1).getTime();

        System.out.println(today + " " + firstOfSeptember);

        long diffInMillies = Math.abs(today.getTime() - firstOfSeptember.getTime());
        long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);

        String[] monitors = new String[2];
        monitors[0] = String.valueOf(getMonitorsFromDiff((int) diff)[0]);
        monitors[1] = String.valueOf(getMonitorsFromDiff((int) diff)[1]);
        return monitors;
    }

    private static int getMonth(Date date) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        int month = calendar.get(Calendar.MONTH);
        return month;
    }

    private static int getYear(Date date) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        return year;
    }

    private static int[] getMonitorsFromDiff(int diff) {
        int[] monitors = new int[2];

        int weeks = (diff / 7) + 1;
        if (weeks > 16) {
            weeks = weeks - 16;
        }
        if (weeks > 32) {
            weeks = weeks - 32;
        }

        monitors[0] = weeks;
        monitors[1] = 32 - weeks;
        return monitors;
    }
}
