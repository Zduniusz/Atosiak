package com.zduniusz.discord.commandlist;

import com.zduniusz.Main;
import com.zduniusz.data.covid.DailyCovidStat;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@SuppressWarnings("unused")
public class Infections {
    public static void reply(SlashCommandEvent event) {
        LocalDateTime dataFrom = LocalDateTime.parse(Main.dailyCovidStat.getLastUpdatedAtSource(), DateTimeFormatter.ISO_OFFSET_DATE_TIME);


        event.reply(
                getDaysDifference(dataFrom.toLocalDate(), dataFrom.getHour() + ":" + dataFrom.getMinute())
                        + "**"
                        + formatNumber(Main.dailyCovidStat.getDailyInfected())
                        + "** nowych zakażeń."
                        + getCovidLevel())
                .setEphemeral(false).queue();
    }

    protected static String getCovidLevel() {
        long polandPopulation = 37_660_000L;

        if (Main.weeklyCovidStat == null)
            return "";

        float avgInfections = 0;

        for (DailyCovidStat dailyCovidStat : Main.weeklyCovidStat) {
            avgInfections += dailyCovidStat.getDailyInfected();
        }
        avgInfections /= 7;

        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);


        float covidLevelNumber = avgInfections / ((float) polandPopulation / 100_000);
        String result = " Próg zasad bezpieczeństwa: **" + df.format(covidLevelNumber) + "** - ";

        if (covidLevelNumber > 75)
            return result + "MAMY PRZEJEBANE";
        if (covidLevelNumber > 70)
            return result + "Narodowa kwarantanna";
        if (covidLevelNumber > 50)
            return result + "Bezpiecznik";
        if (covidLevelNumber > 25)
            return result + "Cała Polska strefą czerwoną";
        if (covidLevelNumber > 10)
            return result + "Cała Polska strefą żółtą, wybrane powiaty czerwone";
        if (covidLevelNumber > 2)
            return result + "Regionalny podział na strefy czerwone, żółte i zielone";

        return " Próg zasad bezpieczeństwa: **" + df.format(covidLevelNumber) + "**";
    }

    protected static String getDaysDifference(LocalDate date, String time) {
        int difference = LocalDate.now(ZoneId.of("CET")).compareTo(date);

        StringBuilder result = new StringBuilder();

        String shortDate = date.format(DateTimeFormatter.ofPattern("dd.MM"));

        switch (difference) {
            case 0 -> result.append("Dzisiaj (**").append(time).append(" ").append(shortDate).append("**) jest ");
            case 1 -> result.append("Wczoraj (**").append(time).append(" ").append(shortDate).append("**) było ");
            default -> result.append(difference).append(" dni temu (**").append(time).append(" ").append(shortDate).append("**) było ");
        }
        return result.toString();
    }


    protected static String formatNumber(int number) {
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");

        DecimalFormatSymbols formatSymbols = decimalFormat.getDecimalFormatSymbols();
        formatSymbols.setGroupingSeparator(' ');
        decimalFormat.setDecimalFormatSymbols(formatSymbols);

        return decimalFormat.format(number);
    }
}
