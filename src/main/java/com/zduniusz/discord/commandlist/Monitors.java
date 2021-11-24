package com.zduniusz.discord.commandlist;

import com.zduniusz.Main;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class Monitors {
    public static void reply(SlashCommandEvent event) {
        LocalDate today = LocalDate.now(ZoneId.of("CET"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

        Optional<com.zduniusz.data.monitors.Monitors> optionalMonitors = Main.monitorsList.stream().filter(x -> today.isAfter(LocalDate.parse(x.date[0], formatter)) && today.isBefore(LocalDate.parse(x.date[1], formatter)) || today.isEqual(LocalDate.parse(x.date[0], formatter))|| today.isEqual(LocalDate.parse(x.date[1], formatter))).findFirst();
        if (optionalMonitors.isEmpty()) {
            event.reply("Dyżurni nie zostali wpisani.").queue();
            return;
        }

        com.zduniusz.data.monitors.Monitors monitors = optionalMonitors.get();

        StringBuilder monitorsFormatted = new StringBuilder();

        for (int i = 0; i < monitors.monitors.size(); i++) {
            String monitor = monitors.monitors.get(i);
            monitorsFormatted.append("**").append(monitor).append("**");

            if (monitors.monitors.size() - i > 1)
                monitorsFormatted.append(" i ");
        }

        event.reply("Dzisiaj dyżurnymi są: " + monitorsFormatted).queue();
    }
}
