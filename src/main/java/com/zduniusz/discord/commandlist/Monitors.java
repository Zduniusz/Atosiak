package com.zduniusz.discord.commandlist;

import com.zduniusz.Main;
import com.zduniusz.data.monitors.Monitor;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class Monitors {
    public static void reply(SlashCommandEvent event) {
        LocalDate today = LocalDate.now(ZoneId.of("CET"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

        Optional<Monitor> optionalMonitors = Main.monitorsList.stream().filter(x -> today.isAfter(LocalDate.parse(x.date, formatter)) && today.isBefore(LocalDate.parse(x.date, formatter).plusDays(6)) || today.isEqual(LocalDate.parse(x.date, formatter))|| today.isEqual(LocalDate.parse(x.date, formatter).plusDays(1))).findFirst();
        if (optionalMonitors.isEmpty()) {
            event.reply("Dyżurni nie zostali wpisani.").queue();
            return;
        }

        Monitor monitors = optionalMonitors.get();

        StringBuilder monitorsFormatted = new StringBuilder();

        for (int i = 0; i < monitors.monitors.size(); i++) {
            String monitor = monitors.monitors.get(i);
            monitorsFormatted.append("**").append(monitor).append("**");

            if (monitors.monitors.size() - i > 1)
                monitorsFormatted.append(" i ");
        }

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM");
        event.reply("Od dnia " + dtf.format(LocalDate.parse(monitors.date)) + " do " + dtf.format(LocalDate.parse(monitors.date).plusDays(6)) + ": " + monitorsFormatted).queue();
    }
}
