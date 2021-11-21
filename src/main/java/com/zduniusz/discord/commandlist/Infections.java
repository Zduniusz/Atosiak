package com.zduniusz.discord.commandlist;

import com.zduniusz.Main;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

@SuppressWarnings("unused")
public class Infections {
    public static void reply(SlashCommandEvent event) {
        event.reply("Dzisiaj jest **" + formatNumber(Main.dailyCovidStat.getDailyInfected()) + "** nowych zakażeń. Dane z **" + Main.dailyCovidStat.getTxtDate() + "**").setEphemeral(false).queue();
    }


    protected static String formatNumber(int number) {
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");

        DecimalFormatSymbols formatSymbols = decimalFormat.getDecimalFormatSymbols();
        formatSymbols.setGroupingSeparator(' ');
        decimalFormat.setDecimalFormatSymbols(formatSymbols);

        return decimalFormat.format(number);
    }
}
