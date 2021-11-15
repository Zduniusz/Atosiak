package com.zduniusz.discord.commandlist;

import com.zduniusz.Main;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;

public class Infections {
    public static void reply(SlashCommandEvent event){
        event.reply("`Dzisiaj jest " + Main.dailyCovidStat.getDailyInfected() + " nowych zakażeń`").setEphemeral(false).queue();
    }
}
