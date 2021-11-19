package com.zduniusz.discord.commandlist;

import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;

import java.time.LocalDate;
import java.time.temporal.IsoFields;

public class Monitors {
    public static void reply(SlashCommandEvent event) {
        LocalDate today = LocalDate.now();

        int weekIndex = (LocalDate.of(1, today.getMonth(), 1).isAfter(LocalDate.of(1, 9, 1)) ?
            today.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR) - LocalDate.of(0, 9, 1).get(IsoFields.WEEK_OF_WEEK_BASED_YEAR) :
            today.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR)) + 1;
        event.reply(String.format("%s %s", weekIndex, 33-weekIndex)).queue();
    }
}
