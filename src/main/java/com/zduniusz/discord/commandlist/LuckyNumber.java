package com.zduniusz.discord.commandlist;

import com.zduniusz.Main;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;

import java.time.*;
import java.time.temporal.TemporalAccessor;
import java.util.Date;
import java.util.Optional;

public class LuckyNumber {
    public static void reply(SlashCommandEvent event) {
        LocalDate today = LocalDate.now(ZoneId.of("CET"));

        Optional<com.zduniusz.data.luckynumber.LuckyNumber> optionalLuckyNumbers = Main.luckyNumberList.stream().filter(x -> x.date.equals(today.toString().replace("-", "."))).findFirst();

        if (optionalLuckyNumbers.isEmpty()) {
            event.reply("Dzisiaj jest dzień wolny od szkoły albo szczęśliwe numerki nie zostały wpisane").queue();
            return;
        }

        com.zduniusz.data.luckynumber.LuckyNumber luckyNumbers = optionalLuckyNumbers.get();

        StringBuilder luckyNumbersFormatted = new StringBuilder();

        for (int i = 0; i < luckyNumbers.luckyNumbers.size(); i++) {
            String luckyNumber = luckyNumbers.luckyNumbers.get(i);
            luckyNumbersFormatted.append("**").append(luckyNumber).append("**");

            if (luckyNumbers.luckyNumbers.size() - i > 1)
                luckyNumbersFormatted.append(" i ");
        }


        event.reply("Dzisiaj szczęśliwy numerek mają: " + luckyNumbersFormatted).queue();
    }
}
