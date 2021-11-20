package com.zduniusz.discord.commandlist;

import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

public class Monitors {
    public static void reply(SlashCommandEvent event) {
        event.reply(String.format("Dzisiaj dyżurnymi są: **%s** i **%s**", getMonitors()[0], getMonitors()[1])).queue(); //Send message
    }

    public static String[] getMonitors() {
        Date today = new Date(); //Today's date
        Date firstOfSeptember = new GregorianCalendar((getMonth(today) == 1 ? getYear(today) - 1 : getYear(today)), 8, 1).getTime(); // First of september

        long diffInMillies = Math.abs(today.getTime() - firstOfSeptember.getTime()); //Difference between two dates in miliseconds
        long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS); // Difference between two dates in days

        String[] monitors = new String[2]; //Return Data
        monitors[0] = String.valueOf(getMonitorsFromDiff((int) diff)[0]);
        monitors[1] = String.valueOf(getMonitorsFromDiff((int) diff)[1]);
        return monitors;
    }

    private static int getMonth(Date date) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date); //Turn data to calendar
        int month = calendar.get(Calendar.MONTH);
        return month; //Return month
    }

    private static int getYear(Date date) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date); //Turn data to calendar
        int year = calendar.get(Calendar.YEAR);
        return year; //Return month
    }

    private static int[] getMonitorsFromDiff(int diff) {
        int[] monitors = new int[2]; //Create array of monitors

        int weeks = (diff / 7) + 1; //Get number of weeks by dividing diff in days by 7 and adding 1
        if (weeks > 16) { //If weeks is greater than 16 or 32 subtract it by 16 or 32
            weeks = weeks - 16;
        }else if (weeks > 32) {
            weeks = weeks - 32;
        }

        monitors[0] = weeks; //  1st monitor is the number of weeks
        monitors[1] = 32 - weeks; // 2nd monitor is 32 - number of weeks
        return monitors; // Return monitors
    }
}
