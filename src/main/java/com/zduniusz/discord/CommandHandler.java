package com.zduniusz.discord;

import com.zduniusz.discord.commandlist.Infections;
import com.zduniusz.discord.commandlist.LuckyNumber;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class CommandHandler extends ListenerAdapter {
    @Override
    public void onSlashCommand(SlashCommandEvent event) {
        switch (event.getName()) {
            case "zakazenia" -> Infections.reply(event);
            case "szczęśliwy" -> LuckyNumber.reply(event);
        }
    }

}
